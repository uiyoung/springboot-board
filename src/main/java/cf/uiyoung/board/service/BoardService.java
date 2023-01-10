package cf.uiyoung.board.service;

import cf.uiyoung.board.dto.Board;

import java.util.List;

public interface BoardService {
    void save(Board board);
    Board getPost(int boardId);
    List<Board> getPostsByPage(int page);
    void editPost(Board board);
    void deletePost(int boardId);
}
