<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webPractice.mutter.model.MutterData" %>

<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<% MutterData data = (MutterData) session.getAttribute("data"); %>
<% String name = data.getName(); %>
<% String pass = data.getPass(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/mutterStyle.css">
<title>mutterRegister.jsp</title>
</head>
<body>
<div id="upper">
<hr color="white" size="5">
<h1><i>・*＊ Mutter ＊*・</i></h1>
<hr color="white" size="5">
</div>
<div id="content" align="center">
<table>
<tr>
  <th>
  <i>＊ Register ＊</i><br>
  <% if(msgList.isEmpty()){ %>
      <p class="message">登録してください</p>
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
    <form action="/sepJavaRecurrent/MutterRegisterServlet" method="post">
      <p class="input">Name: <input type="text" name="name" value="<%= name %>" size="10" required="required"></p>
      <p class="input">Pass:　<input type="password" name="pass" value="<%= pass %>" size="10" required="required"></p>
      <p class="input">Mail:　<input type="email" name="mail" size="10" required="required"></p>
      <br>
      <p align="right"><button type="submit"> 登録  </button>　　</p>
    </form>
</table>
</div>
</body>
</html>