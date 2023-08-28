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

@WebServlet(name = "servletTest01", urlPatterns = "/oneData")
public class ServletTest01 extends HttpServlet {

    BoardDaoV0 boardDaoV0 = new BoardDaoV0();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        BoardDto boardDto = boardDaoV0.selectOneArticle();
        String result = objectMapper.writeValueAsString(boardDto);
        response.getWriter().write(result);
    }
}
