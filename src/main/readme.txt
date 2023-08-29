v0 : 대충 서블릿으로 restAPI 구현
v1 : FrontController 첫 도입
v2 :
    - BoardController 개선 : 순서 조정 -> Switch Case
    - Controller Util 도입
    - Service 도입

v3 :
    - 개별 Controller 조정
        - Controller Servlet 종속성 제거.
        - 필요한 파라미터만 받게 : String method, String body, String pathInfo, Map<String, String> paramMap
    - Service 인터페이스 도입