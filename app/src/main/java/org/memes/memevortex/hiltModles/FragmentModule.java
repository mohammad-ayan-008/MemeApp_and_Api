package org.memes.memevortex.hiltModles;
import android.content.Context;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;

import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import org.memes.memevortex.databinding.LayoutCommunityBinding;
import org.memes.memevortex.databinding.LayoutPersonalBinding;
import org.memes.memevortex.databinding.LayoutRegisterBinding;



@Module
@InstallIn(FragmentComponent.class)
public class FragmentModule {
    @Provides
    public LayoutPersonalBinding getBinding2(@ActivityContext Context act){
        return LayoutPersonalBinding.inflate(LayoutInflater.from(act));
    }
    
    
    
    @Provides
    public LayoutCommunityBinding getBinding4(@ActivityContext Context act){
        return LayoutCommunityBinding.inflate(LayoutInflater.from(act));
    }
    
    
}
