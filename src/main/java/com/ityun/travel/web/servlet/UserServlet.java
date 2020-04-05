package com.ityun.travel.web.servlet;

import com.ityun.travel.domain.ResultInfo;
import com.ityun.travel.domain.User;
import com.ityun.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "UserServlet", urlPatterns = "/user/*")
public class UserServlet extends BaseServlet {
    public void registerUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check = request.getParameter("check");
        if (check == null) {
            responseWithJson(response, false, "验证码非法", null);
            return;
        }
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            responseWithJson(response, false, "验证码错误", null);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);

        UserServiceImpl userService = new UserServiceImpl();
        boolean ret = userService.register(user);
        if (ret) {
            responseWithJson(response, true, "注册成功", null);
        } else {
            responseWithJson(response, false, "注册失败", null);
        }
    }
}
