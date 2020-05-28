package in.spring.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserListVO implements Serializable {
    private static final long serialVersionUID = 99890L;

    private List<UserVO> users = new ArrayList<>();
    private Integer pageCount = 0;
    private Long userCount = 0L;
}