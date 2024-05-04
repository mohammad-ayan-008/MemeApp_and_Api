package org.memes.memevortex.Retrofitutils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginModle {
    private String userName;
    private String password;

    public LoginModle(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
