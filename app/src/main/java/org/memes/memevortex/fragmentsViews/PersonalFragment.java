package org.memes.memevortex.fragmentsViews;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import dagger.hilt.android.AndroidEntryPoint;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.memes.memevortex.MainActivity;
import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.R;
import org.memes.memevortex.RecyclerViewUtils.RecyclerAdapter;
import org.memes.memevortex.RecyclerViewUtils.RecyclerAdapterv2;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.databinding.LayoutPersonalBinding;
@AndroidEntryPoint
public class PersonalFragment extends Fragment {
   
    
    public Context c;
    
    @Inject
    public LayoutPersonalBinding bidning;
    
    private MainViewModle modle;

    private RecyclerView view;
    private RecyclerAdapterv2 adapter;
    private List<Meme> data = new ArrayList<>();
  
    ActivityResultLauncher<String> launcher;
    
    
    
    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        // TODO: Implement this method
        view = bidning.recyclerView;
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapterv2(getContext(), data);
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
       
        
        return bidning.getRoot();
    }
    
    @Override
    public void onViewCreated(View arg0, Bundle arg1) {
        super.onViewCreated(arg0, arg1);
        modle = new ViewModelProvider(this).get(MainViewModle.class);
        launcher=  registerForActivityResult(new ActivityResultContracts.GetContent(),ur->{
         if(ur!=null){
           
          // Toast.makeText(getContext(),"uri path"+path,0).show(); 
             File f= new File(getFilePathFromContentUri(ur));
            // String title="my new meme"
            
              TextInputEditText edit = new TextInputEditText(getContext());
              TextInputLayout l = new TextInputLayout(getContext());
              l.setHint("Title");
              l.addView(edit);
                
                new MaterialAlertDialogBuilder(getContext())
                .setView(l)
                .setPositiveButton("Post",(DialogInterface in,int i)->{
                    Toast.makeText(getContext(),"Wait for the meme to Be uploaded",0).show();
                    bidning.progress.setVisibility(View.VISIBLE);    
                    modle.uploadMeme(edit.getText().toString(),f).observe(this,x->{
                     Toast.makeText(getContext(),"uploaded  status= " + x.getStatus(),0).show();
                     update();               
                    });
                     
                }).setTitle("Enter the Title for image")
                .create().show();
             
            
        }
    });
        
        
        update();
        bidning.upload.setOnClickListener(V->{
            launcher.launch("image/*");
        });
        
        adapter.setOnMenuItemClick((String pos)->{
            bidning.progress.setVisibility(View.VISIBLE);    
            modle.delete(pos).observe(this,x->{
                 Toast.makeText(getContext(),""+x.getStatus(),0).show();
                 update();       
            });
        });
        
        
    }
    
    
    
    private String getFilePathFromContentUri(Uri contentUri) {
    String filePath = null;
    String[] projection = {MediaStore.Images.Media.DATA};
    Cursor cursor = getContext().getContentResolver().query(contentUri, projection, null, null, null);
    if (cursor != null) {
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        filePath = cursor.getString(columnIndex);
        cursor.close();
    }
    return filePath;
   }
    
   public void update(){
       modle.getMymemes().observe(this,Memes->{
                        if(Memes!=null){
                             data.clear();
                             data.addAll(Memes);
                             adapter.notifyDataSetChanged();
                             bidning.progress.setVisibility(View.INVISIBLE);   
                         }  
                     });
        }
}
