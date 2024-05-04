package org.memes.memevortex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import org.memes.memevortex.databinding.LayoutLoginUserBinding;
import org.memes.memevortex.Modle.MainViewModle;
import org.memes.memevortex.Retrofitutils.LoginModle;
import org.memes.memevortex.Utils.Preffernces;
@AndroidEntryPoint
public class LoginUser extends AppCompatActivity {
   
    public MainViewModle modle;
    @Inject
    public LayoutLoginUserBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        modle = new ViewModelProvider(this).get(MainViewModle.class);
        
        Toast.makeText(getApplicationContext(),Preffernces.load(this,Register.USER_SAVED).toString(),0).show();
        if(!Preffernces.load(this,Register.USER_SAVED).isEmpty()){
        modle.getAuth(Preffernces.load(this,Register.USER_SAVED)).observe(this,x->{
            Toast.makeText(getApplicationContext(),""+x.toString(),0).show();
            if(x.getStatus().trim().equals("present")){
                startActivity(new Intent(LoginUser.this,MainActivity.class));
                finish();
            }    
        });
            
       }else{
           Toast.makeText(getApplicationContext(),"No user was loged in ",0).show();
       }
        
        
        
        binding.Register.setOnClickListener(V->{
           
            startActivity(new Intent(LoginUser.this,Register.class));
            finish();
        });
        
        
        
        
        
        
        
          binding.submit.setOnClickListener(K->{
            String userName = binding.username.getText().toString();
            String pass = binding.passwrd.getText().toString();    
            Toast.makeText(getApplicationContext(),"Done"+pass+userName.toString(),0).show();     
             if(!userName.isEmpty()&&!pass.isEmpty()){
                modle.login(new LoginModle(userName,pass)).observe(this,x->{
                  Toast.makeText(getApplicationContext(),"Done"+x.toString(),0).show();
                  if(x.getStatus().trim().equals("success")){
                    Preffernces.save(this,Register.USER_SAVED,userName);   
                    Preffernces.savePass(this,Register.USER_PASS,pass);                      
                    startActivity(new Intent(LoginUser.this,MainActivity.class));
                   }
               
             });
           }
                           
        });
        
    }
}
