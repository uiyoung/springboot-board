package cf.uiyoung.board.service;

import cf.uiyoung.board.dto.Board;

import java.util.List;

public interface BoardService {
    void save();
    Board getPost();
    List<Board> getAllPosts();
    void editPost();
    void deletePost();
}
