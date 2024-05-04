package Org.Memes.Api.Meme_Storing_Api.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

/** Memes */
@Getter
@Setter
@NoArgsConstructor
@Document
public class Memes {

    @Id
    public String id;
    
    public String Title;
    public String url;

    public Memes(String Title, String url) {
        this.Title = Title;
        this.url = url;
    }
}
