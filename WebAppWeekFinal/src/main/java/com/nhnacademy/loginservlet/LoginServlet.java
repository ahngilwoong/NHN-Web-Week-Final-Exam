package com.nhnacademy.loginservlet;

import com.nhnacademy.user.Repository;
import com.nhnacademy.user.UserRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
@Slf4j
public class LoginServlet extends HttpServlet {
//    private String configId;
//    private String configPwd;
    private String filterStr = "";
    private Repository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // <servlet>
        //     <init-param>
//        configId = config.getInitParameter("id");
//        configPwd = config.getInitParameter("pwd");
//        if (configId.equals("admin") && configPwd.equals("12345")) {
//
//        }
        userRepository = UserRepository.getInstance();
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

        if (userRepository.loginValidationChecked(id,pwd)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("requestUri",requestUri);
            // NOTE: RequestDispatcher??? ???????????? POST method??? ?????? ????????? ????????????.
            //       GET Method??? ????????? ????????? ??????????????? ????????? sendRedirect ??????.
            resp.sendRedirect(filterStr);
        } else {
            // NOTE: ?????? ???????????? ?????? RequestDispatcher?????? sendRedirect ?????? ??????.
            resp.sendRedirect("/loginForm.jsp");
        }
    }

}
