package org.memes.memevortex.Retrofitutils;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.MultipartBody;
import org.memes.memevortex.Retrofitutils.LoginModle;
import org.memes.memevortex.Retrofitutils.Models.Meme;
import org.memes.memevortex.Retrofitutils.Models.UserModle;
import org.memes.memevortex.Retrofitutils.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MemeApi {
    
    @POST("sign_in")
    Observable<ResponseBody> postUserData(@Body LoginModle modle);
    
    @GET("/users/{userName}")
    Observable<ResponseBody> getAuth_Verified(@Path(value="userName") String userName);
   
    @POST("/sign_in/login")
    Observable<ResponseBody> login(@Body LoginModle modle);
    
    @Multipart
    @POST("/memes")
    Observable<ResponseBody> postMeme(@Part("title") okhttp3.RequestBody title,@Part MultipartBody.Part file);
    
    @GET("/users")
    Observable<List<Meme>> getAllUsers_memes();
    
    @GET("/memes")
    Observable<List<Meme>> getMymemes();
    
    @DELETE("/memes/{id}")
    Observable<ResponseBody> delete(@Path("id") String id);
    
}
