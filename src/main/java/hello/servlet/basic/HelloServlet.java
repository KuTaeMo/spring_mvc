package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // hello를 호출하면 service가 실행됨
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        
        // request, response 구현체가 로그에 찍힘
        System.out.println("request = " + request);     // request = org.apache.catalina.connector.RequestFacade@629e9122
        System.out.println("response = " + response);   // response = org.apache.catalina.connector.ResponseFacade@3f51ad19

        String username = request.getParameter("username");
        System.out.println("username = " + username);   // username = ku

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);

    }
}
