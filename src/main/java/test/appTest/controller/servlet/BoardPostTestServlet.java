package test.appTest.controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.BoardDaoV0;
import model.dto.BoardDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet(urlPatterns = "/board")
public class BoardPostTestServlet extends HttpServlet {

    BoardDaoV0 boardDaoV0 = new BoardDaoV0();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String body = getBody(request);
        BoardDto boardDto = objectMapper.readValue(body, BoardDto.class);

        boardDaoV0.insertOne(boardDto);
        Long maxIdx = boardDaoV0.getMaxIdx();
        BoardDto boardDto1 = boardDaoV0.selectOne(maxIdx);

        String result = objectMapper.writeValueAsString(boardDto1);
        response.getWriter().write(result);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        String body = getBody(request);
//        String body = request.getReader().lines().collect(Collectors.joining());
        BoardDto updatedDto = objectMapper.readValue(body, BoardDto.class);

        boardDaoV0.update(updatedDto);
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
