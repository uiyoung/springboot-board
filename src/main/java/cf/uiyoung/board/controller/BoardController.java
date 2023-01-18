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

    @GetMapping("")
    public String board(@RequestParam(required = true, defaultValue = "1") int page, HttpSession session, Model model) {
        Integer totalCount = boardService.getTotalCount();

        int maxPage = totalCount / 10;
        if (totalCount % 10 > 0) {
            maxPage++;
        }

        if(page > maxPage) page = maxPage;
        page = Math.max(1, page);

        int startPage = page - 5;
        int endPage = page + 4;

        if(startPage < 3){
            startPage = 1;
            endPage = Math.min(maxPage, 10);
        } else if(endPage > maxPage){
            startPage = Math.max(maxPage - 9, 1);
            endPage = maxPage;
        }

        List<Board> boards = boardService.getPostsByPage(page);
        model.addAttribute("boards", boards);
        model.addAttribute("page", page);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("maxPage", maxPage);
        return "list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, @RequestParam(defaultValue = "1") int page, Model model) {
        Board board = boardService.getPost(id);
        model.addAttribute("board", board);
        model.addAttribute("page", page);
        return "detail";
    }

    @GetMapping("/save")
    public String writeForm() {
        return "write";
    }

    @PostMapping("/save")
    public String write(@ModelAttribute Board board) {
        // TODO : setUserId with login info
        board.setUserId(5L);
        board.setRegDate(LocalDateTime.now());
        boardService.save(board);
        return "redirect:/board/";
    }

    @GetMapping("/update/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Board board = boardService.getPost(id);
        model.addAttribute("board", board);
        return "edit";
    }

    @PostMapping("/update")
    public String edit(@ModelAttribute Board board) {
        boardService.editPost(board);
        return "redirect:/board/" + board.getBoardId();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deletePost(id);
        return "redirect:/board/";
    }
}
