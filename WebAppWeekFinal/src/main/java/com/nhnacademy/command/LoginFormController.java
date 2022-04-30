package com.nhnacademy.command;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormController implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String requestUri = (String) session.getAttribute("requestUri");
        String filterStr = "";
        if (Objects.isNull(requestUri)){
            int getParam = requestUri.indexOf("=");
            filterStr = requestUri.substring(getParam+1,requestUri.length()-2);
        }

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            return "/loginForm.jsp";
        } else {
            if(filterStr.equals("")){
                return "/loginSuccess.jsp";
            }else{
                return filterStr;
            }
        }
    }

}
