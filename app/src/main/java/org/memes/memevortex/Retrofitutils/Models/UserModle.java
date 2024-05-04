package org.memes.memevortex.Retrofitutils.Models;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModle {
    private String id;
    private String userName;
    private List<Meme> memes;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Meme> getMemes() {
        return this.memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }
}
