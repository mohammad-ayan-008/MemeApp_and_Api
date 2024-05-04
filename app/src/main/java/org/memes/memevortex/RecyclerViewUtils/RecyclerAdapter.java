package org.memes.memevortex.RecyclerViewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import org.memes.memevortex.RecyclerViewUtils.RecyclerAdapter;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.Retrofitutils.Models.UserModle;
import org.memes.memevortex.databinding.MemecardBinding;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    private Context c;

    private List<Meme> models;

    public RecyclerAdapter(Context c, List<Meme> models) {
        this.c = c;
        this.models = models;
    }

    class Holder extends RecyclerView.ViewHolder {
        MemecardBinding bind;

        public Holder(MemecardBinding binding) {
            super(binding.getRoot());
            this.bind = binding;
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new RecyclerAdapter.Holder(MemecardBinding.inflate(LayoutInflater.from(c),arg0,false));
    }

    @Override
    public void onBindViewHolder(Holder arg0, int arg1) {
          Meme modle = models.get(arg1);
          if(modle!=null){
            arg0.bind.Textv.setText("Title "+modle.getTitle());
            Glide.with(c).load(modle.getUrl()).into(arg0.bind.Imagev);
            
          }
    }
    
    

    @Override
    public int getItemCount() {
         return  models.size();
    }
}
