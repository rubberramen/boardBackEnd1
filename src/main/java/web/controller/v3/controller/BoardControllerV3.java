package web.controller.v3.controller;

import model.dto.BoardDto;
import web.controller.ControllerUtils;
import web.controller.v3.ControllerV3;
import web.service.BoardService;
import web.service.v2.BoardServiceV2;
import web.service.v3.BoardServiceImplV3;

import java.io.IOException;
import java.util.Map;

public class BoardControllerV3 implements ControllerV3 {

//    private final BoardServiceV2 service = BoardServiceV2.getInstance();

    private final BoardService service = BoardServiceImplV3.getInstance();

    @Override
    public Object process(String method, String body, String pathInfo, Map<String, String> paramMap) throws IOException {
        switch (method) {
            case "GET": {
                long idx = getIdx(pathInfo);
                BoardDto board = service.getBoard(idx);
                return board;
            }
            case "DELETE": {
                long idx = getIdx(pathInfo);
                service.deleteBoard(idx);
                break;
            }
            case "POST": {
                BoardDto boardDto = ControllerUtils.getBodytoObject(body, BoardDto.class);
                BoardDto boardDto1 = service.putBoard(boardDto);
                return boardDto1;
            }
            case "PUT":
                BoardDto boardDto = ControllerUtils.getBodytoObject(body, BoardDto.class);
                service.updateBoard(boardDto);
                break;
        }
        return null;
    }

    private long getIdx(String pathInfo) {
        String variableValue = pathInfo.substring(7);
        return Long.parseLong(variableValue);
    }
}
