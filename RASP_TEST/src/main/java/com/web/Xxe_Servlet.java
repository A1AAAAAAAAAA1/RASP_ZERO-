package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class Xxe_Servlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String str_xxe = req.getParameter("data");
//        System.out.println(str_xxe);
        resp.setContentType("text/html;charset=UTF-8");
        String tmp ="";
        //获取响应的输出流
        PrintWriter out = resp.getWriter();
        if (str_xxe !=null){
            try {
                DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document doc = documentBuilder.parse(new InputSource(new StringReader(str_xxe)));
                NodeList RegistrationNo = doc.getElementsByTagName("foo");
                tmp = RegistrationNo.item(0).getFirstChild().getNodeValue();
                //html回显数据
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>查询结果</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>XXE回显</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<tr>");
                out.println("<td>" + tmp + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        }


    }
}
