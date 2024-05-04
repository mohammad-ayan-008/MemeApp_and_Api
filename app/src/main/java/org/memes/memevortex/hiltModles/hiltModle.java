package org.memes.memevortex.hiltModles;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Named;
import org.memes.memevortex.LoginUser;
import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.Register;
import org.memes.memevortex.Utils.Preffernces;
import org.memes.memevortex.databinding.ActivityMainBinding;
import org.memes.memevortex.databinding.LayoutCommunityBinding;
import org.memes.memevortex.databinding.LayoutLoginUserBinding;
import org.memes.memevortex.databinding.LayoutPersonalBinding;
import org.memes.memevortex.databinding.LayoutRegisterBinding;
import org.memes.memevortex.Repo.Repository;
import org.memes.memevortex.hiltModles.hiltModle;
import org.memes.memevortex.Retrofitutils.LoginModle;

@Module
@InstallIn(ActivityComponent.class)
public class hiltModle {
    

    
    
    @Provides
    public LayoutLoginUserBinding getBinding(Activity act){
        return LayoutLoginUserBinding.inflate(LayoutInflater.from(act));
    }
    
    @Provides
    public ActivityMainBinding getBinding1(Activity act){
        return ActivityMainBinding.inflate(LayoutInflater.from(act));
    }
   
    @Provides
    public LayoutRegisterBinding getBinding2(@ActivityContext Context act){
        return LayoutRegisterBinding.inflate(LayoutInflater.from(act));
    }
    
    
    
    
    
    @Provides
    public MainViewModle getViewModle(Application app){
           return new ViewModelProvider.AndroidViewModelFactory(app).create(MainViewModle.class);
    }
    
   
  
    
    
}
