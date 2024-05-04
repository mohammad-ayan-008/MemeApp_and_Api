package Org.Memes.Api.Meme_Storing_Api.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import Org.Memes.Api.Meme_Storing_Api.entities.Memes;

/**
 * MemesRepository
 */

public interface MemesRepository extends MongoRepository<Memes, String> {

}
