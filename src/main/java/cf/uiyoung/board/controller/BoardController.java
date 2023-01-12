package cf.uiyoung.board.controller;

import cf.uiyoung.board.dto.Board;
import cf.uiyoung.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/")
    public String board(@RequestParam(required = true, defaultValue = "1") int page, HttpSession session, Model model) {
        List<Board> boards = boardService.getPostsByPage(page);
        model.addAttribute("boards", boards);

        return "list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        Board board = boardService.getPost(id);
        model.addAttribute("board", board);
        return "detail";
    }

    @GetMapping("/save")
    public String writeForm() {
        return "write";
    }

    @PostMapping("/save")
    public String write(@ModelAttribute Board board) {
        System.out.println("board = " + board);
        // TODO : setUserId with login info
        board.setUserId(5);
        board.setRegDate(LocalDateTime.now());
        boardService.save(board);
        return "redirect:/board/";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable int id, Model model){
        Board board = boardService.getPost(id);
        model.addAttribute("board", board);
        return "edit";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Board board){
        System.out.println("hi there");
        boardService.editPost(board);
        return "redirect:/board/" + board.getBoardId();
    }

}
