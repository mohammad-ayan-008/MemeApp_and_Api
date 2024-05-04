package org.memes.memevortex.fragmentsViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.RecyclerViewUtils.RecyclerAdapter;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.databinding.LayoutCommunityBinding;

@AndroidEntryPoint
public class CommunityFragment extends Fragment {

    @Inject
    public LayoutCommunityBinding binding;

    public MainViewModle modle;

    

    private RecyclerView view;
    private RecyclerAdapter adapter;
    private List<Meme> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
        // TODO: Implement this method
        //binding = LayoutCommunityBinding.inflate(LayoutInflater.from(getContext()), arg1, false);

        return binding.getRoot();
    }

    
    @Override
    public void onViewCreated(View arg0, Bundle arg1) {
        super.onViewCreated(arg0, arg1);
        // TODO: Implement this method
        modle = new ViewModelProvider(this).get(MainViewModle.class);
        view = binding.recyclerView;
        view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(getContext(), data);
        view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        
        
        modle.getAllUsers()
                .observe(
                        this,
                        x -> {
                            data.clear();
                            data.addAll(x);
                            adapter.notifyDataSetChanged();
                        //    Toast.makeText(getContext(), "data :- " + x.toString(), 0).show();
                        });
      
        
    }
}
