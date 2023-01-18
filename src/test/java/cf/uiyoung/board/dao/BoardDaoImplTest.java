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
        for (int i = 0; i <65; i++) {
            Board board = new Board();
            board.setTitle("title" + i);
            board.setContent("content" + i);
            board.setUserId(5L);
            boardDao.addBoard(board);
        }
    }

    @Test
    void addBoardOneTest() {
            Board board = new Board();
            board.setTitle("title1");
            board.setContent("content1");
            board.setUserId(5L);
            boardDao.addBoard(board);
    }


    @Test
    void getTotalCountTest(){
        int result = boardDao.getTotalCount();
        System.out.println("result = " + result);

    }
    @Test
    void getBoardTest() {
        Board board = boardDao.getBoard(1L);
        assertThat(board.getBoardId()).isEqualTo(1L);
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
//        boardDao.updateBoard(3, "testing", "Attention please");
        Board board = boardDao.getBoard(4L);
        board.setTitle("hi22");
        board.setContent("22there");
        boardDao.updateBoard(board);
    }

    @Test
    void increaseViewCountTest() {
        boardDao.increaseViewCount(39L);
        Board board = boardDao.getBoard(39L);
        assertThat(board.getViewCnt()).isEqualTo(1);
    }
    @Test
    void deleteBoard() {
        boardDao.deleteBoard(1L);
    }
}