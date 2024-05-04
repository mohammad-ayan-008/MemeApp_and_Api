package Org.Memes.Api.Meme_Storing_Api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import Org.Memes.Api.Meme_Storing_Api.entities.User;

/**
 * UserRepository
 */

public interface UserRepository extends MongoRepository<User, String> {
  User findByUserName(String userName);
}
