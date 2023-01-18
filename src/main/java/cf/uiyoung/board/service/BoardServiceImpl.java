package cf.uiyoung.board.service;

import cf.uiyoung.board.dao.BoardDao;
import cf.uiyoung.board.dto.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;

    @Override
    @Transactional
    public void save(Board board) {
        boardDao.addBoard(board);
    }

    @Override
    public Integer getTotalCount() {
        return boardDao.getTotalCount();
    }

    @Override
    @Transactional
    public Board getPost(Long boardId) {
        return getPost(boardId, true);
    }

    @Transactional
    public Board getPost(Long boardId, boolean isIncreaseViewCount) {
        Board board = boardDao.getBoard(boardId);
        if (isIncreaseViewCount) {
            boardDao.increaseViewCount(boardId);
        }
        return board;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> getPostsByPage(int page) {
        return boardDao.getBoards(page);
    }

    @Override
    @Transactional
    public void editPost(Board board) {
        boardDao.updateBoard(board);
    }

    @Override
    @Transactional
    public void deletePost(Long boardId) {
        boardDao.deleteBoard(boardId);
    }
}
