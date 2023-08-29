package web.controller.v3.controller;

import model.dto.BoardDto;
import web.controller.v3.ControllerV3;
import web.service.v2.BoardServiceV2;

import java.util.List;
import java.util.Map;

public class BoardListControllerV3 implements ControllerV3 {

    private final BoardServiceV2 service = BoardServiceV2.getInstance();

    @Override
    public Object process(String method, String body, String pathInfo, Map<String, String> paramMap) {
        List<BoardDto> allBoards = service.getAllBoards();
        return allBoards;
    }
}
