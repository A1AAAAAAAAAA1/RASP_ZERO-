package com.web;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.System.out;

public class Sql_Servlet extends HttpServlet{


    //JDBC连接参数
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_rasp";
    private static final String USER = "root";
    private static final String PASS = "root";

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        String  num = request.getParameter("num");
        StringBuilder result =new StringBuilder();
        response.setContentType("text/html;charset=UTF-8");
        //获取响应的输出流
        PrintWriter out = response.getWriter();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            String sql ="select flag from sql_rasp where num=" + num ;
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()){
//                String flag = rs.getString("flag");
////                System.out.println("flag为:"+flag);
////                System.out.println(flag.getClass().toString());
//                out.println("<br>flag=" + rs.getObject("flag"));
////                out.flush();
////                out.close();
//            }
            //构建HTML表格，填充查询结果
            response.setContentType("text/html;charset=UTF-8");
//            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>用户查询结果</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>用户查询结果</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>flag</th>");
            out.println("</tr>");
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getObject("flag") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }


    }









}
