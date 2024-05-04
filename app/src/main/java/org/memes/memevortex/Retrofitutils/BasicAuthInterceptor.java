package org.memes.memevortex.Retrofitutils;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Credentials;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request authenticatedRequest = chain.request().newBuilder()
            .header("Authorization", credentials).build();
        return chain.proceed(authenticatedRequest);
    }
    
}