package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Pbulid_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String prb_poc = req.getParameter("prbpoc");
//        System.out.println("接收参数为："+prb_poc);
        resp.setContentType("text/html;charset=UTF-8");
        String tmp ="";
        //获取响应的输出流
        PrintWriter out = resp.getWriter();
        try {
//            String prb_str ="whoami";
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd", "/c", prb_poc);
            Process process = processBuilder.start();
            InputStream inputStream =  process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
            //回显数据
            tmp = bufferedReader.readLine();
//            System.out.println(a);
//            System.out.println(bufferedReader.readLine());
            if (prb_poc !=null){
                //方法运行
                //html回显数据
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>查询结果</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ProcessBuilder command 命令执行回显</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<tr>");
                out.println("<td>" + tmp + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
