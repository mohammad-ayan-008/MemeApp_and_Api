package Org.Memes.Api.Meme_Storing_Api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;
import java.util.*;

@RestController
@RequestMapping("/sign_in")
public class RegisterUser {

  @Autowired
  private UserService service;

  @Autowired
  private PasswordEncoder en;

  @PostMapping
  public Map Save_User(@RequestBody User user) {
    Map p = new HashMap();
    if (null != service.getByName(user.getUserName())) {
      p.put("status", "User Alredy exists");
      return p;
    }

    service.SaveUser(user);
    p.put("status", "success");
    return p;
  }

  @PostMapping("login")
  public Map isUser(@RequestBody User u) {
    Map response = new HashMap<>();
    String userName = u.getUserName();
    String pass = u.getPassword();
    User user = service.getByName(userName);
    if (user != null && userName.trim().equals(user.getUserName()) && service.match(pass, user.getPassword())) {
      response.put("status", "success");
      return response;
    }

    // System.out.println(service.match(pass.trim(), user.getPassword()) +
    // userName + "\n" + user.getUserName().trim() + "------------\n" +
    // user.getPassword() + "\n" + pass.trim());

    response.put("status", "null");
    return response;
  }

}
