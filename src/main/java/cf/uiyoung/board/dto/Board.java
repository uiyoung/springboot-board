package cf.uiyoung.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
public class Board {
    private Long boardId;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime regDate;
    private LocalDateTime upDate;
    private Long viewCnt;
}