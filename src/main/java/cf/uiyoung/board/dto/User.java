package cf.uiyoung.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {
    private Long userId;
    private String email;
    private String name;
    private String password;
    private LocalDateTime regdate;
}

