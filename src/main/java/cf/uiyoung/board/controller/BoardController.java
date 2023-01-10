package cf.uiyoung.board.controller;

import cf.uiyoung.board.dto.Board;
import cf.uiyoung.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/")
    public String board(@RequestParam(required = true, defaultValue = "1") int page, HttpSession session, Model model){
        List<Board> boards = boardService.getPostsByPage(page);
        model.addAttribute("boards", boards);

        return "board";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam int boardId, Model model){
        Board board = boardService.getPost(boardId);
        model.addAttribute("board",board);
        return "detail";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "write";
    }

    @PostMapping("/write")
    public String write(){
        return null;
    }
}
