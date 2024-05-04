package org.memes.memevortex.Repo;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.memes.memevortex.Repo.Repository;
import org.memes.memevortex.Retrofitutils.LoginModle;
import org.memes.memevortex.Retrofitutils.MemeApi;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.Retrofitutils.Models.UserModle;
import org.memes.memevortex.Retrofitutils.ResponseBody;
import javax.inject.Named;
import retrofit2.http.Multipart;


public class Repository {
   
    
    public MemeApi call;
    private MemeApi Auth_api;
    private CompositeDisposable dispose = new CompositeDisposable();
    
    @Inject
    public Repository(MemeApi apicall,@Named("authapi")MemeApi api){
       call= apicall;
       this.Auth_api=api;  
    }
    
     public MutableLiveData<ResponseBody> getReponse(LoginModle modle){
           MutableLiveData response = new MutableLiveData();
           dispose.add(
           call.postUserData(modle)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              response.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        
          return response;
         
    }
    
    public MutableLiveData<ResponseBody> getAuthenticated(String UserName){
           MutableLiveData response = new MutableLiveData();
           dispose.add(
           Auth_api.getAuth_Verified(UserName)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              response.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        
          return response;
         
    }
    
    
     
    public MutableLiveData<ResponseBody> getLogin(LoginModle modle){
           MutableLiveData response = new MutableLiveData();
           dispose.add(
           call.postUserData(modle)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              response.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        
          return response;
         
    }
    
    
    public MutableLiveData<ResponseBody> login(LoginModle modle){
           MutableLiveData response = new MutableLiveData();
           dispose.add(
           call.login(modle)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              response.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        
          return response;
    }
    
    public MutableLiveData<ResponseBody> upload(String title,File file){
          MutableLiveData<ResponseBody>  resonse= new MutableLiveData<>();
          RequestBody titlebody = RequestBody.create(MediaType.parse("text/plain"),title);
          RequestBody fbody = RequestBody.create(MediaType.parse("image/*"),  file);
          MultipartBody.Part part=MultipartBody.Part.createFormData("file",file.getName(),fbody);
          dispose.add(Auth_api.postMeme(titlebody,part)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              resonse.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        return resonse;
    }
    
    public MutableLiveData<List<Meme>> getAllUsers_Memes(){
           MutableLiveData<List<Meme>>  resonse= new MutableLiveData<>();
           dispose.add(Auth_api.getAllUsers_memes()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              resonse.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        return resonse;
    }
    
     
    public MutableLiveData<List<Meme>> getMymemes(){
           MutableLiveData<List<Meme>>  resonse= new MutableLiveData<>();
           dispose.add(Auth_api.getMymemes()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(data->{
              resonse.setValue(data);
           },err->{
               Log.e("Error",err.getLocalizedMessage());
           })
          );
        return resonse;
    }
    
    
    public MutableLiveData<ResponseBody> deleteMeme(String id){
        MutableLiveData<ResponseBody>  resonse= new MutableLiveData<>();
        dispose.add(Auth_api.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(dataset->{
                resonse.setValue(dataset);
            },err->{
                 Log.e("Error",err.getLocalizedMessage());
            })
        );
        
        
        return resonse;
    }
}
