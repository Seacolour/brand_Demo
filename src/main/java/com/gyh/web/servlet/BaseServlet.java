package com.gyh.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* 替换HttpServlet,根据请求的最后一段路径来进行分发
* */

public class BaseServlet extends HttpServlet {

    //根据最后一段路径来进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri=req.getRequestURI();
        //2.获取最后一段路径，方法名
        int index=uri.lastIndexOf('/');
        String methodName=uri.substring(index+1);

        //3.执行方法
        //3.1获取BrandServlet /UserServlet 字节码对象 class

        Class<? extends BaseServlet> cls=this.getClass();

        //3.2获取方法Method对象
        try {
            Method method=cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //3.3执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
