package com.gyh.web.servlet;


import com.alibaba.fastjson.JSON;
import com.gyh.pojo.Brand;
import com.gyh.pojo.PageBean;
import com.gyh.service.BrandService;
import com.gyh.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Brand> brands = brandService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service添加
        brandService.add(brand);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("update");
        request.setCharacterEncoding("utf-8");
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service添加
        brandService.update(brand);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("delete");
        //1. 接收品牌数据
        String itemId=request.getParameter("id");
        //转化为Int
        int id=Integer.parseInt(itemId);
        //2. 调用service添加
        brandService.delete(id);
        //3. 响应成功的标识
        response.getWriter().write("success");

    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //2. 调用service添加
        brandService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");

    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数 url?currentPage=pageSize=5
        String _currenPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");

        int currentPage=Integer.parseInt(_currenPage);
        int pageSize=Integer.parseInt(_pageSize);

        //2. 转为JSON
        PageBean<Brand> pageBean=brandService.selectByPage(currentPage,pageSize);

        String jsonString=JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /*
    * 分页条件查询
    * */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收当前页码和每页展示条数 url?currentPage=pageSize=5
        String _currenPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");

        int currentPage=Integer.parseInt(_currenPage);
        int pageSize=Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 转为JSON
        PageBean<Brand> pageBean=brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        String jsonString=JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
