package com.nhnacademy.command;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LangController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional.ofNullable(request.getParameter("lang"))
            .ifPresent(lang -> request.getServletContext().setAttribute("lang", lang));

        return "redirect:/";
    }
}
