<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.MutterData" %>

<% String msgFlag = (String) request.getAttribute("msgFlag"); %>
<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/mutterStyle.css">
<title>logout.jsp</title>
</head>
<body>
<header>
    <jsp:include page="mutterHeader.jsp" />
</header>
<div id="content" align="center">
<table id="mutter">
<tr>
  <th>
  <i>＊ Logout ＊</i><br>
  <p class="message">
  <% for(String msg : msgList){ %>
          <%= msg %><br>
  <% }//for msgList%>
  </p>
  </th>
</tr>
<tr>
  <td>
       <a href="/mutterDX/MutterLoginServlet"><button><i> Login </i></button></a>
  </td>
</tr>
</table>
</div>
<footer>
    <jsp:include page="mutterFooter.jsp" />
</footer>
</body>
</html>