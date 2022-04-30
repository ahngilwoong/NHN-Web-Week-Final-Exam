package com.nhnacademy.servlet;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "langServlet", urlPatterns = "/change-lang")
public class LangServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        Optional.ofNullable(req.getParameter("lang"))
            .ifPresent(lang -> getServletContext().setAttribute("lang", lang));
        req.setAttribute("view", "redirect:/");
    }

}
