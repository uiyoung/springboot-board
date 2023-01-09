package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Board;

import java.util.List;

public interface BoardDao {
    void addBoard(String title, String content, Integer userId);
    Integer getTotalCount();
    Board getBoard(int boardId);
    List<Board> getBoards(int page);
    void updateBoard(int boardId, String title, String content);
    void updateViewCount(int boardId);
    void deleteBoard(Integer boardId);
}
