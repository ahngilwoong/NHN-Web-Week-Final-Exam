package com.nhnacademy.loginservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
    @WebInitParam(name = "id", value = "gilung"),
    @WebInitParam(name = "pwd", value = "12345")
})
@Slf4j
public class LoginServlet extends HttpServlet {
    private String configId;
    private String configPwd;
    private String filterStr = "";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // <servlet>
        //     <init-param>
        configId = config.getInitParameter("id");
        configPwd = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session)/* || Objects.isNull(session.getAttribute("id"))*/) {
            resp.sendRedirect("/loginForm.jsp");
        } else {
            ServletContext servletContext = req.getServletContext();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("Login Success: " + session.getAttribute("id") + "<br />");
                out.println("<a href='/logout'>Logout</a>");
//                if (!Objects.equals(requestUri,null)){
//                    resp.sendRedirect(filterStr);
//                }
            } catch (IOException ex) {
                log.error("", ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String requestUri = req.getParameter("requestUri");
        if (!Objects.equals(requestUri,null)){
            int getParam = requestUri.indexOf("=");
            filterStr = requestUri.substring(getParam+1,requestUri.length()-2);
        }
        log.info(filterStr+"//"+requestUri);

        if (configId.equals(id) && configPwd.equals(pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("pwd", pwd);

            // NOTE: RequestDispatcher를 사용하면 POST method로 다시 요청이 들어온다.
            //       GET Method로 새로운 요청이 시작되어야 하므로 sendRedirect 사용.
            resp.sendRedirect(filterStr);
        } else {
            // NOTE: 정적 리소스의 경우 RequestDispatcher보단 sendRedirect 사용 권장.
            resp.sendRedirect("/loginForm.jsp");
        }
    }

}
