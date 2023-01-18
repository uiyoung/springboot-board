package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Board;

import java.util.List;

public interface BoardDao {
    void addBoard(Board board);
    Integer getTotalCount();
    Board getBoard(Long boardId);
    List<Board> getBoards(int page);
    void updateBoard(Board board);
    void increaseViewCount(Long boardId);
    void deleteBoard(Long boardId);
}
