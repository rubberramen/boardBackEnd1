package web.controller.v3;

import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {

    Object process(String method, String body, String pathInfo, Map<String, String> paramMap) throws IOException;
}
