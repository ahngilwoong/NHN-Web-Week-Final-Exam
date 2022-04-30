package com.nhnacademy.servlet;

import com.nhnacademy.dispatcher.domain.AmountException;
import com.nhnacademy.dispatcher.domain.Cart;
import com.nhnacademy.dispatcher.domain.FoodStand;
import com.nhnacademy.dispatcher.domain.Item;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setAttribute("view", "/cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String[] items = req.getParameterValues("item");

        ServletContext servletContext = req.getServletContext();
        FoodStand foodStand = (FoodStand) servletContext.getAttribute("foodstand");

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        for(String itemName: items) {
            Item item = foodStand.getItem(itemName);

            try {
                item.decreaseAmount();
            } catch (AmountException ex) {
                req.setAttribute("exception", ex);
                throw new RuntimeException(ex);
            }

            cart.add(item.getFood(), 1);
        }

        req.setAttribute("view", "/cart.jsp");
    }

}
