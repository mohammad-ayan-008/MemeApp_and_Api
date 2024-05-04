package Org.Memes.Api.Meme_Storing_Api.entities;

import Org.Memes.Api.Meme_Storing_Api.entities.Memes;

import java.util.List;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/** User */
@Getter
@Setter
@Document
@NoArgsConstructor
public class User {

    @Id 
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull private String password;

    private List<String> roles = new ArrayList();

    @DBRef public List<Memes> memes = new ArrayList<>();

    public List<Memes> getMemes() {
        return this.memes;
    }

    public void setMemes(List<Memes> memes) {
        this.memes = memes;
    }
}
