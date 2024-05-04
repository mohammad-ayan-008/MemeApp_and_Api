package Org.Memes.Api.Meme_Storing_Api.config;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;


@Configuration
public class configs {
    
    @Bean
    public Cloudinary getCloud(){
        
        Map config = new HashMap();
        config.put("cloud_name", "dfmwjqwdb");
        config.put("api_key", "157332283436348");
        config.put("api_secret", "7QcQwJAxSR9JGr8puzmyPUhj34k");
        return new Cloudinary(config);
        
    }
	
}