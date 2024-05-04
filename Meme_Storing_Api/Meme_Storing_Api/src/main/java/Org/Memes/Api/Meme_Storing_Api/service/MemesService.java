package Org.Memes.Api.Meme_Storing_Api.service;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Org.Memes.Api.Meme_Storing_Api.entities.Memes;
import Org.Memes.Api.Meme_Storing_Api.repositories.MemesRepository;
import java.util.*;

/**
 * MemesService
 */
@Service
public class MemesService {

  @Autowired
  private MemesRepository repo;
   
  @Autowired
  private  UserService uservice; 

  public Memes saveMeme(Memes memes) {
     return repo.save(memes);
  }
    
  public Memes findById(String id){
      return repo.findById(id).orElse(null);
  }
  public void delete(User u,String id) {
       boolean b= u.getMemes().removeIf(x-> x.getId().equals(id));
       if(b){
           repo.deleteById(id);
           uservice.SaveUse2(u);
       }
  }
  public List<Memes> loadAll() {
      return repo.findAll();
  }
    

}
