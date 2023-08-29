package web.controller.v2;

import web.controller.ControllerUtils;
import web.controller.v2.controller.BoardControllerV2;
import web.controller.v2.controller.BoardListControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/v2/boardList", new BoardListControllerV2());
        controllerMap.put("/v2/board", new BoardControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = getRequestURI(request);

        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ControllerUtils.setResponse(response);
        controller.process(request, response);
    }

    private String getRequestURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String substring = requestURI.substring(4);

        char[] chars = substring.toCharArray();
        for (char aChar : chars) {
            if (aChar == '/') {
                requestURI = "/v2/board";
            }
        }
        return requestURI;
    }
}
