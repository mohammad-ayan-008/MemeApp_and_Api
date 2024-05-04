package org.memes.memevortex.Modle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import lombok.NoArgsConstructor;

import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.Repo.Repository;
import org.memes.memevortex.Retrofitutils.LoginModle;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.Retrofitutils.Models.UserModle;
import org.memes.memevortex.Retrofitutils.ResponseBody;

@HiltViewModel

public class MainViewModle extends ViewModel {
    public Repository repo;

    @Inject
    public MainViewModle (Repository repo){
        this.repo=repo;
    }
    
    
    
    
    public MutableLiveData<ResponseBody> getResponse(LoginModle modle){
           return repo.getReponse(modle);
    }
    public MutableLiveData<ResponseBody> getAuth(String userName){
           return repo.getAuthenticated(userName);
    }
    
    public MutableLiveData<ResponseBody> login(LoginModle modle){
           return repo.login(modle);
    }
    
    
    public MutableLiveData<ResponseBody> uploadMeme(String title ,File file){
           return repo.upload(title,file);
    }
  
    public MutableLiveData<List<Meme>> getAllUsers(){
           return repo.getAllUsers_Memes();
    }
    
     
    public MutableLiveData<List<Meme>> getMymemes(){
           return repo.getMymemes();
    }
    
     
    public MutableLiveData<ResponseBody> delete(String id){
           return repo.deleteMeme(id);
    }
    
}
