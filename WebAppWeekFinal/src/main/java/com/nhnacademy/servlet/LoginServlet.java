package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
    @WebInitParam(name = "id", value = "admin"),
    @WebInitParam(name = "pwd", value = "12345")
})
public class LoginServlet extends HttpServlet {
    private String idInitParam;
    private String pwdInitParam;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        idInitParam = config.getInitParameter("id");
        pwdInitParam = config.getInitParameter("pwd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            req.setAttribute("view", "/loginForm.jsp");
        } else {
            req.setAttribute("view", "/loginSuccess.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");
        String requestUri = req.getParameter("requestUri");
        if (id != null && pwd != null && id.equals(idInitParam) && pwd.equals(pwdInitParam)) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            session.setAttribute("requestUri",requestUri);
            req.setAttribute("view", "redirect:/login.do");
        } else {
            req.setAttribute("view", "/loginForm.jsp");
        }
    }

}
