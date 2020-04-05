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
    /**
     * 注册
     * @param request
     * @param response
     * @throws IOException
     */
    public void registerUserServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String check = request.getParameter("check");
        if (check == null) {
            responseWithJson(response, false, "验证码非法", null);
            return;
        }
        //校验验证码
        if (!checkCheckCode(request, response, check)) {
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

        UserServiceImpl userService = new UserServiceImpl();
        boolean ret = userService.register(user);
        if (ret) {
            responseWithJson(response, true, "注册成功", null);
        } else {
            responseWithJson(response, false, "注册失败", null);
        }
    }

    /**
     * 登陆
     * @param request
     * @param response
     */
    public void loginServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String check = request.getParameter("check");
        if (check == null) {
            responseWithJson(response, false, "验证码非法", null);
            return;
        }
        //校验验证码
        if (!checkCheckCode(request, response, check)) {
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
        UserServiceImpl userService = new UserServiceImpl();
        User ret = userService.login(user);
        if (ret == null) {
            responseWithJson(response, false, "用户名或密码错误", null);
            return;
        }
        request.getSession().setAttribute("user", ret);
        responseWithJson(response, true, "登陆成功", null);
    }

    /**
     * 获取用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void getUserInfoServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            responseWithJson(response, false, "未登录", null);
            return;
        }
        responseWithJson(response, true, "成功", user);
    }

    /**
     * 退出登陆
     * @param request
     * @param response
     * @throws IOException
     */
    public void loginOutServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();

        responseWithJson(response, true, "登出成功", null);
    }
}
