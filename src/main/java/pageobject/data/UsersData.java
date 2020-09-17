package pageobject.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersData {

    private String USER_NAME;
    private String PASSWORD;

    public UsersData(String userName, String password) {
        this.USER_NAME = userName;
        this.PASSWORD = password;
    }
}
