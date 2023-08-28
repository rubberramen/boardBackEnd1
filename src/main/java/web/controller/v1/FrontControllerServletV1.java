package web.controller.v1;

import web.controller.v1.controller.BoardControllerV1;
import web.controller.v1.controller.BoardListControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
//        controllerMap.put("/v1/board/list", new BoardListControllerV1());
        controllerMap.put("/v1/boardList", new BoardListControllerV1());
        controllerMap.put("/v1/board", new BoardControllerV1());
//        controllerMap.put("/v1/board/*", new BoardListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = getRequestURI(request);

        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        controller.process(request, response);
    }

    private String getRequestURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI();    // /v2/boardList
        String substring = requestURI.substring(4);  // boardList   // board/1

        char[] chars = substring.toCharArray();
        for (char aChar : chars) {
            if (aChar == '/') {
                requestURI = "/v1/board";
            }
        }
        return requestURI;
    }
}
