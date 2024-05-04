
package org.memes.memevortex;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.navigation.NavigationView;
import org.memes.memevortex.Modle.MainViewModle;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import dagger.hilt.android.AndroidEntryPoint;
import org.memes.memevortex.databinding.ActivityMainBinding;
import org.memes.memevortex.Utils.Preffernces;
import org.memes.memevortex.fragmentsViews.CommunityFragment;
import org.memes.memevortex.fragmentsViews.PersonalFragment;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    private ActivityMainBinding binding;
    private BottomNavigationView navView;
    private FragmentManager manager;
    private Fragment frag1,frag2;
    private MainViewModle modle;
    private Toolbar bar;
    private DrawerLayout dr;
    private NavigationView navs;
    private ActionBarDrawerToggle toogle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        // Inflate and get instance of binding
        
        bar = binding.toolbar;
        setSupportActionBar(bar);
        // set content view to binding's root
        setContentView(binding.getRoot());
     //   Toast.makeText(getApplicationContext(),Preffernces.load(this,Register.USER_SAVED),0).show();
   
      
      
        setDrawer();
      //  navView=binding.Navs;
        FragmentManager manager = getSupportFragmentManager();
       
        modle =new ViewModelProvider(this).get(MainViewModle.class);
        frag1=new CommunityFragment();
        frag2=new PersonalFragment();
      
            
        
        
      
        manager.beginTransaction().replace(R.id.FragLayout,frag1).commit();
     
        navs.setNavigationItemSelectedListener(item->{
                if(item.getItemId()== R.id.menu_home){
                   if(frag1!=null){
                      manager.beginTransaction().replace(R.id.FragLayout,frag1).commit();
                        dr.closeDrawer(GravityCompat.START);
                      return true;
                  }
                }else if(item.getItemId()==R.id.menu_mine){
                     if(frag2!=null){
                       manager.beginTransaction().replace(R.id.FragLayout,frag2).commit();
                       dr.closeDrawer(GravityCompat.START);
                       return true;
                   }
                }
                 
                return false;
        });
        
      
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu arg0) {
           // TODO: Implement this method
            new MenuInflater(this).inflate(R.menu.menus,arg0);
            return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem arg0) {
        // TODO: Implement this method
        if(arg0.getItemId() == R.id.menu_item1){
             Preffernces.save(getApplicationContext(),Register.USER_SAVED,"");
             Preffernces.savePass(getApplicationContext(),Register.USER_PASS,"");
             startActivity(new Intent(MainActivity.this,LoginUser.class));
             finish();
        }
        return super.onOptionsItemSelected(arg0);
    }
    
    
    public void setDrawer() {
    	dr= binding.drawer;
        navs=binding.navview;
        toogle = new ActionBarDrawerToggle(this,dr,bar,R.string.open,R.string.close);
        dr.addDrawerListener(toogle);
        toogle.syncState();
        
        
    }
}
