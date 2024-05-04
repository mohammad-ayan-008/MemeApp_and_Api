package Org.Memes.Api.Meme_Storing_Api.service;

import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

/**
 * CloudnaryService
 */
@Service
public class CloudnaryService {
  @Autowired
  private Cloudinary service;

     public Map Upload(MultipartFile file)throws Exception{
       return service.uploader().upload(file.getBytes(),Map.of());
     }
    
}
