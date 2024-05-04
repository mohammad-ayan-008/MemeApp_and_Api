package Org.Memes.Api.Meme_Storing_Api.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.repositories.UserRepository;
import java.util.*;
/**
 * UserService
 */
@Service
public class UserService {

  @Autowired
  private UserRepository repo;
 
  private PasswordEncoder en = new BCryptPasswordEncoder();
    
    
  public User SaveUser(User u) {
    u.setPassword(en.encode(u.getPassword()));
    return repo.save(u);
  }
    
  public User SaveUse2(User u) {
       return repo.save(u);
  }
    
    
  public List<User>  getAll(){
      return repo.findAll();
  }
    
  public User getById(String id) {
    return repo.findById(id).orElse(null);
  }

  public User getByName(String Name) {
    return repo.findByUserName(Name);
  }

}
