package Org.Memes.Api.Meme_Storing_Api.controllers;

import Org.Memes.Api.Meme_Storing_Api.entities.Memes;
import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.CloudnaryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public List<Memes> getAllMeme() {
    return service.loadAll();
  }

  @GetMapping("{userName}")
  public Map isUserPresent(@PathVariable String userName) {
    Map p = new HashMap<>();
    if (null != Uservice.getByName(userName)) {
      p.put("status", "present");
      return p;
    }
    p.put("status", "nouser");
    return p;
  }

}
