package in.spring.model.co;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSearchCO {
    private String q;
    private Integer size;
    private Integer page;
    private String sortBy;
    private String status;
}
