package com.crbc.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 不需要被访问到
public class BaseServlet extends HttpServlet {


    /**
     * 子类比如UserServlet的service被调用即会执行此方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("BaseServlet的service方法被执行了");
        // 完成方法的分发
        // 1. 获取请求路径
        String uri = req.getRequestURI();
        //System.out.println("请求uri:" + uri);

        // 2. 获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //System.out.println("方法名称：" + methodName);

        // 3. 获取方法对象Method
        try {
            // 如果方法前有protected修饰符，要使用如下语句：
            // 忽略访问权限修饰符
            //Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 暴力反射
            //method.setAccessible(true);
            //method.invoke(this, req, resp);

            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 4. 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
