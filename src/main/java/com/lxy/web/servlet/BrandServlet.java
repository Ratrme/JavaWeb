package com.lxy.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lxy.pojo.Brand;
import com.lxy.pojo.PageBean;
import com.lxy.service.BrandService;
import com.lxy.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Brand> brands = brandService.selectAll();

        String string = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(string);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        //将数据转换为brand格式
        Brand brand = JSON.parseObject(param, Brand.class);

        brandService.add(brand);

        //响应标识
        response.getWriter().write("success");
    }


    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        //将数据转换为brand格式
        Brand brand = JSON.parseObject(param, Brand.class);
        brandService.update(brand);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        int id = JSON.parseObject(param, int.class);
        brandService.deleteById(id);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        int[] ids = JSON.parseObject(param, int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        String string = JSON.toJSONString(pageBean);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(string);
    }

    public void selectByPageCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        BufferedReader reader = request.getReader();
        String param = reader.readLine();
        Brand brand = JSON.parseObject(param, Brand.class);
        PageBean<Brand> pageBean = brandService.selectByPageCondition(currentPage, pageSize,brand);
        String string = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(string);
    }
}
