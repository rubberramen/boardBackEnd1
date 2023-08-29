package web.service.v3;

import model.dao.BoardDaoV1;
import model.dto.BoardDto;
import web.service.BoardService;

import java.util.List;

public class BoardServiceImplV3 implements BoardService {

    private final BoardDaoV1 dao = BoardDaoV1.getInstance();
    private static final BoardServiceImplV3 instance = new BoardServiceImplV3();

    public static BoardServiceImplV3 getInstance() {
        return instance;
    }

    private BoardServiceImplV3() {
    }

    @Override
    public BoardDto getBoard(Long idx) {
        return dao.selectOne(idx);
    }

    @Override
    public List<BoardDto> getAllBoards() {
        return dao.selectAll();
    }

    @Override
    public BoardDto putBoard(BoardDto boardDto) {
        dao.insertOne(boardDto);
        Long maxIdx = dao.getMaxIdx();
        return dao.selectOne(maxIdx);
    }

    @Override
    public void deleteBoard(Long idx) {
        dao.deleteOne(idx);
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        dao.update(boardDto);
    }
}
