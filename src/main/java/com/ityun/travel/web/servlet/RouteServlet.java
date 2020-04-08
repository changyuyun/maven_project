package com.ityun.travel.web.servlet;

import com.ityun.travel.domain.PageBean;
import com.ityun.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 线路接口
 */
@WebServlet(name = "RouteServlet", urlPatterns = "/route/*")
public class RouteServlet extends BaseServlet {
    public void routeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String routeName = request.getParameter("routeName");

        int currentPage = 1;
        int pageSize = 10;
        int cid = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        if (cidStr != null && cidStr.length() > 0) {
            cid = Integer.parseInt(cidStr);
        } else {
            responseWithJson(response, false, "cid is error", null);
        }

        RouteServiceImpl routeService = new RouteServiceImpl();
        int count = routeService.count(cid, routeName);
        int totalPage = 0;
        if (count % pageSize == 0) {
            totalPage = count/pageSize;
        } else {
            totalPage = count/pageSize +1;
        }
        int start = (currentPage-1)*pageSize;
        PageBean pageBean = new PageBean();
        List list = routeService.list(cid, routeName, start, pageSize);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(list);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setTotalPage(totalPage);
        responseWithJson(response, true, "成功", pageBean);
    }
}
