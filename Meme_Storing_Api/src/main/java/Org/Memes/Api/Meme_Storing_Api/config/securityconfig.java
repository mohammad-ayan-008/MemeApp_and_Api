package Org.Memes.Api.Meme_Storing_Api.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Org.Memes.Api.Meme_Storing_Api.customUserDetails;

/**
 * securityconfig
 */

@Configuration
@EnableWebSecurity
public class securityconfig {

  @Autowired
  private customUserDetails details;

  @Bean
  public PasswordEncoder passEncryptor() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain sFilterChain(HttpSecurity sec) throws Exception {
    return sec.csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("/sign_in/**").permitAll()
        .requestMatchers("/users/**").authenticated()
        .requestMatchers("/memes/**").authenticated()
        .and().httpBasic().and().build();
  }

  @Bean
  public AuthenticationProvider auth() {
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(details);
    p.setPasswordEncoder(passEncryptor());
    return p;
  }

}
