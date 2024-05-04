package org.memes.memevortex.Retrofitutils;
import android.content.Context;
import android.util.Log;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Named;
import okhttp3.OkHttpClient;
import org.memes.memevortex.Register;
import org.memes.memevortex.Retrofitutils.BasicAuthInterceptor;
import org.memes.memevortex.Retrofitutils.LoginModle;
import org.memes.memevortex.Retrofitutils.MemeApi;
import org.memes.memevortex.Utils.Preffernces;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
@InstallIn(SingletonComponent.class)
public class RetrofitCalls {
    private static String url="http://localhost:8085/";
    
    @Provides
    public MemeApi getApi(){
        return new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(MemeApi.class);
    }
    
    @Provides
    @Named("authapi")
    public MemeApi getApi2(@ApplicationContext Context m){
        LoginModle k=new LoginModle(Preffernces.load(m,Register.USER_SAVED),Preffernces.loadpass(m,Register.USER_PASS));
        Log.d("Debugggggg",k.toString());
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new BasicAuthInterceptor(k.getUserName(),k.getPassword()));
        
        return new Retrofit.Builder()
        .baseUrl(url)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(MemeApi.class);
    }
    
    
}
