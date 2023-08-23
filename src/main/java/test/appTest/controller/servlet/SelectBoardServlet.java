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

@WebServlet(urlPatterns = "/board/*")
public class SelectBoardServlet extends HttpServlet {

    BoardDao boardDao = new BoardDao();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String pathInfo = request.getPathInfo(); // "/variableValue"와 같은 형태의 값

        if (pathInfo != null) {
            // 첫 번째 문자인 '/' 제거하여 실제 변수 값을 얻음
            String variableValue = pathInfo.substring(1);

            long idx = Long.parseLong(variableValue);
            BoardDto boardDto = boardDao.selectOne(idx);

            String result = objectMapper.writeValueAsString(boardDto);
            response.getWriter().write(result);

        } else {
            response.getWriter().write("No variable value provided.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String pathInfo = request.getPathInfo(); // "/variableValue"와 같은 형태의 값

        if (pathInfo != null) {
            // 첫 번째 문자인 '/' 제거하여 실제 변수 값을 얻음
            String variableValue = pathInfo.substring(1);

            int idx = Integer.parseInt(variableValue);
            boardDao.deleteOne(idx);

        } else {
            response.getWriter().write("No variable value provided.");
        }
    }
}
