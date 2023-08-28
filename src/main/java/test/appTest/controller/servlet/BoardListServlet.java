package test.appTest.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.BoardDaoV0;
import model.dto.BoardDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/board/list")
public class BoardListServlet extends HttpServlet {

    BoardDaoV0 boardDaoV0 = new BoardDaoV0();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<BoardDto> list = boardDaoV0.selectAll();
        String result = objectMapper.writeValueAsString(list);
        response.getWriter().write(result);
    }
}
