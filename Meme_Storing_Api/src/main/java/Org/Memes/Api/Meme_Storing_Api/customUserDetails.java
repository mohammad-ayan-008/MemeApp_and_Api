package Org.Memes.Api.Meme_Storing_Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import Org.Memes.Api.Meme_Storing_Api.entities.User;
import Org.Memes.Api.Meme_Storing_Api.service.UserService;

/**
 * customUserDetails
 */
@Component
public class customUserDetails implements UserDetailsService {

  @Autowired
  private UserService service;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User u = service.getByName(username);
    if (u != null) {
   //   System.out.println(u.getUserName());
      UserDetails details = org.springframework.security.core.userdetails.User.builder()
          .username(u.getUserName())
          .password(u.getPassword())
          .roles(u.getRoles().toArray(new String[0]))
          .build();

      return details;
    }
    throw new UsernameNotFoundException("null User");
  }

}
