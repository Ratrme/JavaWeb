package com.lxy.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.lxy.pojo.Brand;
import com.lxy.service.BrandService;
import com.lxy.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据
        BufferedReader reader = request.getReader();
        String line = reader.readLine();
        //将数据转换为brand格式
        Brand brand = JSON.parseObject(line, Brand.class);

        brandService.add(brand);

        //响应标识
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
