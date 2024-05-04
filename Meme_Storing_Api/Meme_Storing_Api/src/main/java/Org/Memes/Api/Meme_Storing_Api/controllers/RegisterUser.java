package Org.Memes.Api.Meme_Storing_Api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;

@RestController
@RequestMapping("/sign_in")
public class RegisterUser {

  @Autowired
  private UserService service;

  @PostMapping
  public void Save_User(@RequestBody User user) {
    service.SaveUser(user);
  }
}
