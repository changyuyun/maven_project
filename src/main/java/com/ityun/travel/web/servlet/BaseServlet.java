package com.ityun.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ityun.travel.domain.ResultInfo;
import com.ityun.travel.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        //  /user/add
        String uri = req.getRequestURI();
        //获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //获取方法对象Method
        //谁调用我，this就代表谁
        try {
            Method method = this.getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private String writeValueAsString(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }

    public void responseWithJson(HttpServletResponse response, boolean flag, String msg, Object data) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        if (msg != null) {
            resultInfo.setMsg(msg);
        }
        if (data != null) {
            resultInfo.setData(data);
        }
        String json = writeValueAsString(resultInfo);
        response.getWriter().write(json);
    }

    public boolean checkCheckCode(HttpServletRequest request, HttpServletResponse response, String checkCode) {
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(checkCode)) {
            return false;
        }
        return true;
    }
    
    public int getCurrentUserId(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return 0;
        }
        int uid = user.getUid();
        return uid;
    }
}
