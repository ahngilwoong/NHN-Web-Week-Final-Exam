package com.nhnacademy.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartListController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/cart.jsp";
    }

}
