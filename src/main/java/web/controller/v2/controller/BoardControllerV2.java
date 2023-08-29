package web.controller.v2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dto.BoardDto;
import web.controller.ControllerUtils;
import web.controller.v2.ControllerV2;
import web.service.v2.BoardServiceV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardControllerV2 implements ControllerV2 {

    private final BoardServiceV2 service = BoardServiceV2.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        BoardDto boardDto = getBodytoObject(request);

        switch (method) {
            case "GET": {
                long idx = getIdx(request);
                BoardDto board = service.getBoard(idx);
                String result = objectMapper.writeValueAsString(board);
                response.getWriter().write(result);
                break;
            }
            case "DELETE": {
                long idx = getIdx(request);
                service.deleteBoard(idx);
                break;
            }
            case "POST": {
                BoardDto boardDto1 = service.putBoard(boardDto);
                String result = objectMapper.writeValueAsString(boardDto1);
                response.getWriter().write(result);
                break;
            }
            case "PUT":
                service.updateBoard(boardDto);
                break;
        }
    }

    private BoardDto getBodytoObject(HttpServletRequest request) throws IOException {
        String body = ControllerUtils.getBody(request);
        BoardDto boardDto = null;
        if (!body.equals("")) {
            boardDto = objectMapper.readValue(body, BoardDto.class);
        }
        return boardDto;
    }

    private long getIdx(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String variableValue = pathInfo.substring(7);
        return Long.parseLong(variableValue);
    }
}
