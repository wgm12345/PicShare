<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: wgm
  Date: 17/3/29
  Time: 上午10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer>
  <p>
    <%
      Date date = new Date();
      out.print(date.toString());
    %>
    -PicShare
  </p>
</footer>
