package com.ityun.travel.web.servlet;

import com.ityun.travel.domain.PageBean;
import com.ityun.travel.domain.Route;
import com.ityun.travel.service.impl.FavoriteServiceImpl;
import com.ityun.travel.service.impl.RouteImgServiceImpl;
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
    /**
     * 线路列表
     * @param request
     * @param response
     * @throws IOException
     */
    public void routeList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String routeName = request.getParameter("routeName");
        if (routeName != null && routeName.length() > 0) {
            // 中文乱码
            routeName = new String(routeName.getBytes("iso8859-1"), "utf-8");
        }
        int currentPage = 1;
        int pageSize = 10;
        int cid = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
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

    /**
     * 线路相册
     * @param request
     * @param response
     * @throws IOException
     */
    public void routeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ridStr = request.getParameter("rid");
        if (ridStr == null || ridStr.length() <= 0) {
            responseWithJson(response, false, "param rid error", null);
            return;
        }
        int rid = Integer.parseInt(ridStr);
        RouteImgServiceImpl routeImgService = new RouteImgServiceImpl();
        List imgs = routeImgService.getImgs(rid);
        responseWithJson(response, true, "成功", imgs);
    }

    /**
     * 线路详情
     * @param request
     * @param response
     */
    public void routeInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ridStr = request.getParameter("rid");
        if (ridStr == null || ridStr.length() <= 0) {
            responseWithJson(response, false, "param rid error", null);
            return;
        }
        int rid = Integer.parseInt(ridStr);
        RouteServiceImpl routeService = new RouteServiceImpl();
        Route info = routeService.findOne(rid);
        responseWithJson(response, true, "成功", info);
    }

    /**
     * 是否收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = getCurrentUserId(request, response);
        if (uid == 0) {
            responseWithJson(response, false, "未登录", null);
            return;
        }
        String ridStr = request.getParameter("rid");
        if (ridStr == null || ridStr.length() <= 0) {
            responseWithJson(response, false, "param rid error", null);
            return;
        }
        int rid = Integer.parseInt(ridStr);
        FavoriteServiceImpl favoriteService = new FavoriteServiceImpl();
        boolean favorite = favoriteService.isFavorite(uid, rid);
        if (!favorite) {
            responseWithJson(response, false, "未收藏", null);
            return;
        }
        responseWithJson(response, true, "已收藏", null);
    }

    /**
     * 收藏
     * @param request
     * @param response
     * @throws IOException
     */
    public void favorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int uid = getCurrentUserId(request, response);
        if (uid == 0) {
            responseWithJson(response, false, "未登录", null);
            return;
        }
        String ridStr = request.getParameter("rid");
        if (ridStr == null || ridStr.length() <= 0) {
            responseWithJson(response, false, "param rid error", null);
            return;
        }
        int rid = Integer.parseInt(ridStr);
        FavoriteServiceImpl favoriteService = new FavoriteServiceImpl();
        favoriteService.saveFavorite(uid, rid);
        responseWithJson(response, true, "收藏成功", null);
    }
}
