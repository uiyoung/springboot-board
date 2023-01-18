package cf.uiyoung.board.service;

import cf.uiyoung.board.dto.Board;

import java.util.List;

public interface BoardService {
    void save(Board board);
    Integer getTotalCount();
    Board getPost(Long boardId);
    List<Board> getPostsByPage(int page);
    void editPost(Board board);
    void deletePost(Long boardId);
}
