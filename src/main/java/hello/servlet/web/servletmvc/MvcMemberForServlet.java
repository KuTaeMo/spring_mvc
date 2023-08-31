package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberForServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberForServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

        //Dispatcher : Controller에서 View로 이동할 때 사용함
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  // 다른 서블릿이나 JSP로 이동할 수 있는 기능, 서버 내부에서 다시 호출이 발생한다.
        // 서버 내부에서 다시 호출이 발생한다는 것은 /servlet-mvc/members/new-form 을 통해서 해당 servlet을 거쳐서 /WEB-INF/views/new-form.jsp 의 뷰를 호출하는데도
        // url은 변경없이 그대로 유지된다는 것이다.

        // 기존의 jsp 하위 디렉토리 안의 자원들은 url로 직접 호출 가능했지만
        // WEB-INF 하위 디렉토리 안에 있는 자원들은 controller를 거치지 않고 직접적으로 호출할 수 없다. WAS의 RULE

        // redirect는 실제 클라이언트에 응답이 나갔다가 클라이언트가 redirect 경로로 다시 요청한다.
        // => 클라이언트가 인지할 수 있고 URL 경로도 실제로 변경된다.

        // 반면에, 포워드(forward)는 서버 내부에서 일어나는 호출이기 떄문에 클라이언트가 전혀 인지하지 못한다.

        // 내 정리 : forward를 하면 url이 변경되지 않지만 response.sendRedirect("경로") 를 하게 되면 해당 url로 변경되면서 새로운 뷰를 호출!!

    }
}
