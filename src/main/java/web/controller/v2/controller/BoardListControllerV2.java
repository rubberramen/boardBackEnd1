package web.controller.v2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.BoardDaoV1;
import model.dto.BoardDto;
import web.controller.v1.ControllerV1;
import web.controller.v2.ControllerV2;
import web.service.v2.BoardServiceV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardListControllerV2 implements ControllerV2 {

    private final BoardServiceV2 service = BoardServiceV2.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BoardDto> allBoards = service.getAllBoards();
        String result = objectMapper.writeValueAsString(allBoards);
        response.getWriter().write(result);
    }
}
