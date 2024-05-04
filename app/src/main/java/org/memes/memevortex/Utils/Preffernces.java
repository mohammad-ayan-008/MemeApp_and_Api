package org.memes.memevortex.Utils;
import android.content.Context;
import android.content.SharedPreferences;

public class Preffernces {
   private static SharedPreferences prffs;
    
    
    public static void save(Context c,String key,String UserName){
        prffs = c.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prffs.edit();
        editor.putString(key,UserName);
        editor.apply();
        editor.commit();
    }
    public static void savePass(Context c,String key,String UserName){
        prffs = c.getSharedPreferences("user_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prffs.edit();
        editor.putString(key,UserName);
        editor.apply();
        editor.commit();
    }
    
    public static String load(Context c,String key_user){
        prffs = c.getSharedPreferences("user_data",Context.MODE_PRIVATE);
       
        //SharedPreferences.Editor editor=prffs.edit();
        return prffs.getString(key_user,"null");
        
    }
    public static String loadpass(Context c,String key_user){
        prffs = c.getSharedPreferences("user_data",Context.MODE_PRIVATE);
       
        //SharedPreferences.Editor editor=prffs.edit();
        return prffs.getString(key_user,"null");
        
    }
}
