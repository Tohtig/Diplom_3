package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User2 {
    private String email;
    private String password;

    public User2(UserAccount account) {
        this.email = account.getEmail();
        this.password = account.getPassword();
    }

    @Override
    public String toString() {
        return String.format("Учетная запись пользователя. email: %s; Пароль: %s.", email, password);
    }
}

