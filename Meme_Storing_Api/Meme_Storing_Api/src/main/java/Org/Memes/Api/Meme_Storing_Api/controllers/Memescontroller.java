package Org.Memes.Api.Meme_Storing_Api.controllers;
import Org.Memes.Api.Meme_Storing_Api.entities.Memes;
import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.CloudnaryService;
import Org.Memes.Api.Meme_Storing_Api.service.MemesService;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;
import org.apache.coyote.Request;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@RestController
@RequestMapping("/memes")
public class Memescontroller {

  @Autowired
  MemesService service;

  @Autowired
  UserService Uservice;
    
  @Autowired
  CloudnaryService servive_C;  

  @GetMapping
  public List<Memes> current_User(){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User u= Uservice.getByName(auth.getName());
      return u.getMemes();
  }

  @DeleteMapping("{id}")
  public   String dlt(@PathVariable String id){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User u= Uservice.getByName(auth.getName());
      service.delete(u,id);   
      return "Ok";
  }
    
    
  @PostMapping
  public boolean postImage(@RequestParam String title,MultipartFile file)throws Exception{
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     String userName = auth.getName();
     User u= Uservice.getByName(userName);    
     Map data=servive_C.Upload(file);
     String url = (String) data.get("url");
     Memes m = new Memes(title,url);
     u.getMemes().add(m);
     service.saveMeme(m);
     Uservice.SaveUse2(u);
     return true;
  }
    
}