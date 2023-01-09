package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardDaoImplTest {

    @Autowired
    BoardDao boardDao;

    @Test
    void addBoardTest() {
        boardDao.addBoard("title1","content1",5);
    }

    @Test
    void getTotalCountTest(){
        int result = boardDao.getTotalCount();
        System.out.println("result = " + result);

    }
    @Test
    void getBoardTest() {
        Board board = boardDao.getBoard(1);
        assertThat(board.getBoardId()).isEqualTo(1);
    }

    @Test
    void getBoardsTest() {
        List<Board> boards = boardDao.getBoards(1);
        for (Board board : boards) {
            System.out.println("board.getBoardId() = " + board.getBoardId());
        }
        assertThat(boards.size()).isEqualTo(10);
    }

    @Test
    void updateBoardTest() {
        boardDao.updateBoard(3, "testing", "Attention please");
    }

    @Test
    void updateViewCountTest() {
        boardDao.updateViewCount(39);
        Board board = boardDao.getBoard(39);
        assertThat(board.getViewCnt()).isEqualTo(1);
    }
    @Test
    void deleteBoard() {
        boardDao.deleteBoard(1);
    }
}