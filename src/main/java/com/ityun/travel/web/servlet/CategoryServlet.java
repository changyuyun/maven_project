package com.ityun.travel.web.servlet;

import com.ityun.travel.domain.Category;
import com.ityun.travel.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category/*")
public class CategoryServlet extends BaseServlet {
    /**
     * tab分类
     * @param request
     * @param response
     * @throws IOException
     */
    public void allCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        List<Category> list = categoryService.findAll();
        responseWithJson(response, true, "成功", list);
    }
}
