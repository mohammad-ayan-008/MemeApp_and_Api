package org.memes.memevortex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import org.memes.memevortex.databinding.LayoutRegisterBinding;
import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.Retrofitutils.*;
import org.memes.memevortex.Utils.Preffernces;
@AndroidEntryPoint
public class Register extends AppCompatActivity {
   
    public static final String USER_SAVED="USER";
    public static final String USER_PASS="PASS";
    
    @Inject
    public LayoutRegisterBinding binding;
    
    private SharedPreferences prffs;
    
    
    public MainViewModle modle;
    
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        modle = new ViewModelProvider(this).get(MainViewModle.class);
        binding.registet.setOnClickListener(v->{
            String userName= binding.username.getText().toString();
            String pass = binding.pass.getText().toString();
            if(!userName.isEmpty()&&!pass.isEmpty()){
                modle.getResponse(new LoginModle(userName,pass)).observe(this,x->{
                  Toast.makeText(getApplicationContext(),"Done"+x.toString(),0).show();
                  if(x.getStatus().trim().equals("success")){
                    Preffernces.save(this,USER_SAVED,userName);   
                    Preffernces.savePass(this,USER_PASS,pass);                      
                    startActivity(new Intent(Register.this,MainActivity.class));
                   }
            });  
        }
      });
        
        
        
    }
    
    @Override
    
    public void onBackPressed() {
           
            startActivity(new Intent(Register.this,LoginUser.class));
            finish();
        
    }
    
    
    
    
    
}
