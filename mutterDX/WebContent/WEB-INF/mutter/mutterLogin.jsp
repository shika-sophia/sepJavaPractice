<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/mutterStyle.css">
<title>mutterLogin.jsp</title>
</head>
<body>
<header>
    <jsp:include page="mutterHeader.jsp" />
</header>
<div id="content" align="center">
<table>
<tr>
  <th>
  <i>＊ Login ＊</i><br>
  <% if(msgList.isEmpty()){ %>
      <p class="message">ログインしてください</p>
  <% } else { %>
      <p class="errMsg">
      <% for(String msg : msgList){ %>
        <%= msg %><br>
      <% }//for msgList%>
      </p>
  <% }//if-else %>
  </th>
</tr>
<tr>
  <td>
    <form action="/mutterDX/MutterLoginServlet" method="post">
      <p class="input">Name: <input type="text" name="name" size="10" required="required"></p>
      <p class="input">Pass:　<input type="password" name="pass" size="10" required="required"></p>
      <br>
      <p align="right"><button type="submit"><i> Login </i></button>　　</p>
    </form>
</table>
</div>
<footer>
    <jsp:include page="mutterFooter.jsp" />
</footer>
</body>
</html>