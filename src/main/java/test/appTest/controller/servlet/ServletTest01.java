package test.appTest.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import test.appTest.model.dao.BoardDao;
import test.appTest.model.dto.BoardDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servletTest01", urlPatterns = "/oneData")
public class ServletTest01 extends HttpServlet {

    BoardDao boardDao = new BoardDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        BoardDto boardDto = boardDao.selectOneArticle();
        String result = objectMapper.writeValueAsString(boardDto);
        response.getWriter().write(result);
    }
}
