package com.nhnacademy.command;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("id"))) {
            session.invalidate();
        }

        return "redirect:/login.do";
    }

}
