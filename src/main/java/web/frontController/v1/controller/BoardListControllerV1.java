package web.frontController.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.BoardDaoV1;
import model.dto.BoardDto;
import web.frontController.v1.ControllerV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BoardListControllerV1 implements ControllerV1 {

    BoardDaoV1 dao = BoardDaoV1.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BoardDto> boardDtos = dao.selectAll();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");

        String result = objectMapper.writeValueAsString(boardDtos);
        response.getWriter().write(result);
    }
}
