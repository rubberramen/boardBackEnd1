package web.controller.v3;

import com.fasterxml.jackson.databind.ObjectMapper;
import web.controller.ControllerUtils;
import web.controller.v3.controller.BoardControllerV3;
import web.controller.v3.controller.BoardListControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    public FrontControllerServletV3() {
        controllerMap.put("/v3/boardList", new BoardListControllerV3());
        controllerMap.put("/v3/board", new BoardControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = getRequestURI(request);

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ControllerUtils.setResponse(response);

        String method = request.getMethod();
        String body = ControllerUtils.getBody(request);
        String pathInfo = request.getPathInfo();
        Map<String, String> paramMap = createParamMap(request);

        Object object = controller.process(method, body, pathInfo, paramMap);
        String result = objectMapper.writeValueAsString(object);
        response.getWriter().write(result);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private String getRequestURI(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String substring = requestURI.substring(4);

        char[] chars = substring.toCharArray();
        for (char aChar : chars) {
            if (aChar == '/') {
                requestURI = "/v3/board";
            }
        }
        return requestURI;
    }
}
