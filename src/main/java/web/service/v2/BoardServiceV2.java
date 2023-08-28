package web.service.v2;

import model.dao.BoardDaoV1;
import model.dto.BoardDto;

import java.util.List;

public class BoardServiceV2 {

    private static final BoardServiceV2 instance = new BoardServiceV2();
    private final BoardDaoV1 dao = BoardDaoV1.getInstance();

    public static BoardServiceV2 getInstance() {
        return instance;
    }

    private BoardServiceV2() {
    }

    public BoardDto getBoard(Long idx) {
        return dao.selectOne(idx);
    }

    public List<BoardDto> getAllBoards() {
        return dao.selectAll();
    }

    public BoardDto putBoard(BoardDto boardDto) {
        dao.insertOne(boardDto);

        Long maxIdx = dao.getMaxIdx();
        return dao.selectOne(maxIdx);
    }

    public void deleteBoard(Long idx) {
        dao.deleteOne(idx);
    }

    public void updateBoard(BoardDto boardDto) {
        dao.update(boardDto);
    }
}
