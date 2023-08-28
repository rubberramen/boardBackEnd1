package web.controller.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.dao.BoardDaoV1;
import model.dto.BoardDto;
import web.controller.v1.ControllerV1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BoardControllerV1 implements ControllerV1 {

    BoardDaoV1 dao = BoardDaoV1.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {

            String body = getBody(request);
            BoardDto boardDto = objectMapper.readValue(body, BoardDto.class);

            dao.insertOne(boardDto);
            Long maxIdx = dao.getMaxIdx();
            BoardDto boardDto1 = dao.selectOne(maxIdx);

            String result = objectMapper.writeValueAsString(boardDto1);
            response.getWriter().write(result);

        } else if (method.equals("PUT")) {

            String body = getBody(request);
            BoardDto boardDto = objectMapper.readValue(body, BoardDto.class);

            dao.update(boardDto);
        } else if (method.equals("GET")) {
            String pathInfo = request.getPathInfo();
            String variableValue = pathInfo.substring(7);
            long idx = Long.parseLong(variableValue);
            BoardDto boardDto1 = dao.selectOne(idx);
            String result = objectMapper.writeValueAsString(boardDto1);
            response.getWriter().write(result);

        } else if (method.equals("DELETE")) {
            String pathInfo = request.getPathInfo();
            String variableValue = pathInfo.substring(7);
            long idx = Long.parseLong(variableValue);
            dao.deleteOne(idx);
        }
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
