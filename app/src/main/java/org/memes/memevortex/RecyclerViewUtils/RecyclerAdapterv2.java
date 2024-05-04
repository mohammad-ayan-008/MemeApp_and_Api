package org.memes.memevortex.RecyclerViewUtils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import org.memes.memevortex.RecyclerViewUtils.RecyclerAdapter;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.Retrofitutils.Models.UserModle;
import org.memes.memevortex.databinding.MemecardBinding;
import org.memes.memevortex.R;
public class RecyclerAdapterv2 extends RecyclerView.Adapter<RecyclerAdapterv2.Holder> {

    private Context c;

    private List<Meme> models;
    private onClickmenu menues;
    
    public RecyclerAdapterv2(Context c, List<Meme> models) {
        this.c = c;
        this.models = models;
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        MemecardBinding bind;

        public Holder(MemecardBinding binding) {
            super(binding.getRoot());
            binding.coordinator.setOnLongClickListener(this);
            this.bind = binding;
        }

        @Override
        public boolean onLongClick(View arg0) {
            PopupMenu menu = new PopupMenu(arg0.getContext(),arg0);
            menu.inflate(R.menu.navs);
            menu.show();
            menu.setOnMenuItemClickListener(item->{
               menues.onClick(models.get(getAdapterPosition()).getId());      
               return true; 
            });
            return true;
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup arg0, int arg1) {
        return new RecyclerAdapterv2.Holder(
                MemecardBinding.inflate(LayoutInflater.from(c), arg0, false));
    }

    @Override
    public void onBindViewHolder(Holder arg0, int arg1) {
        Meme modle = models.get(arg1);
        if (modle != null) {
            arg0.bind.Textv.setText("Title " + modle.getTitle());
            Glide.with(c).load(modle.getUrl()).into(arg0.bind.Imagev);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    
    public void setOnMenuItemClick(onClickmenu menu){
        this.menues=menu;
    }
    public interface onClickmenu{
        void onClick(String id);
    }
}
