package web.service;

import model.dto.BoardDto;

import java.util.List;

public interface BoardService {

    BoardDto getBoard(Long idx);

    List<BoardDto> getAllBoards();

    BoardDto putBoard(BoardDto boardDto);

    void deleteBoard(Long idx);

    void updateBoard(BoardDto boardDto);
}
