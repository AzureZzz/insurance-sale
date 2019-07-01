<%--
  Created by IntelliJ IDEA.
  User: 王涛
  Date: 2018/3/14
  Time: 13:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>
    <form action="http://localhost:8080/login" method="post">
      <input type="text" name="clerkId" value="123456"/>
      <input type="password" name="password" value="123456"/>
      <input type="submit"/>
    </form>
    <br>
    <form action="http://localhost:8080/delete" method="post">
      <input type="text" name="id"/>
      <input type="submit"/>
    </form>
  </body>
</html>
