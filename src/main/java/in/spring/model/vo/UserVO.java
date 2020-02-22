package in.spring.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import in.spring.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonSerialize
@Getter
@Setter
public class UserVO {
    private String email;
    private Long id;
    private String firstName;
    private String lastName;
    private Boolean active;
    private String password;

    public UserVO(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.id = user.getId();
        this.active = user.getActive();
    }
}