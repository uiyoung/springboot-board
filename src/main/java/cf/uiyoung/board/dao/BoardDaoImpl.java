package cf.uiyoung.board.dao;

import cf.uiyoung.board.dto.Board;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsertOperations insertBoard;
    private final RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);

    public BoardDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertBoard = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingGeneratedKeyColumns("board_id");
    }

    @Override
    public void addBoard(Board board) {
        board.setRegDate(LocalDateTime.now());
        board.setViewCnt(0);
        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        insertBoard.execute(params);
    }

    @Override
    public Integer getTotalCount() {
        String sql = "SELECT Count(*) FROM board";
        Integer result = jdbcTemplate.queryForObject(sql, Map.of(), Integer.class);

        return result.intValue();
    }

    @Override
    public Board getBoard(int boardId) {
        String sql = "SELECT board_id, title, content, user_id, reg_date, up_date, view_cnt from board WHERE board_id=:boardId";
        Board board = jdbcTemplate.queryForObject(sql, Map.of("boardId", boardId), rowMapper);

        return board;
    }

    public List<Board> getBoards(int page){
        // todo : 한페이지당 게시글 10을 상수로 만들기
        int offset = (page-1) * 10;
        String sql = "SELECT board_id, title, content, user_id, reg_date, view_cnt from board order by board_id desc LIMIT :offset, 10";
        List<Board> boards = jdbcTemplate.query(sql, Map.of("offset", offset), rowMapper);

        return boards;
    }

    @Override
//    public void updateBoard(int boardId, String title, String content) {
    public void updateBoard(Board board) {
        String sql = "UPDATE board SET title=:title, content=:content WHERE board_id=:boardId";
//        Board board = new Board();
//        board.setBoardId(boardId);
//        board.setTitle(title);
//        board.setContent(content);

        SqlParameterSource params = new BeanPropertySqlParameterSource(board);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void increaseViewCount(int boardId) {
        String sql = "UPDATE board SET view_cnt = view_cnt + 1 WHERE board_id = :boardId";
        jdbcTemplate.update(sql, Map.of("boardId", boardId));
    }

    @Override
    public void deleteBoard(Integer boardId) {
        String sql = "DELETE FROM board WHERE board_id=:boardId";
        jdbcTemplate.update(sql, Map.of("boardId", boardId));
    }
}