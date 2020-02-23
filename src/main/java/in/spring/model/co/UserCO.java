package in.spring.model.co;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class UserCO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
