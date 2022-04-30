package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("id"))) {
            session.invalidate();
        }

        req.setAttribute("view", "redirect:/login.do");
    }

}
