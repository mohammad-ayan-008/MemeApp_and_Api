package Org.Memes.Api.Meme_Storing_Api.controllers;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.CloudnaryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Org.Memes.Api.Meme_Storing_Api.service.MemesService;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;
import java.util.*;

/**
 * usercontroller
 */
@RestController
@RequestMapping("/users")
public class usercontroller {

  @Autowired
  MemesService service;

  @Autowired
  UserService Uservice;
    
  @Autowired
  CloudnaryService servive_C;  

  @GetMapping
  public List<User> get() {
    return Uservice.getAll();
  }

 
}
