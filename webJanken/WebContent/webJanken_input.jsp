<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Integer count = (Integer)request.getAttribute("count"); %>
<% String message = (String)request.getAttribute("message"); %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="webJanken_style.css">
<meta charset="UTF-8">
<title>webJanken_input</title>
</head>

<body>
<div id="upper">
<hr color="white" size="5">
<h1><i>・*＊ Janken Game ＊*・　</i></h1>
<hr color="white" size="5">
</div>
<br>
<br>
<br>
<div id="index" align="center">
<table class="index">
<tr>
  <th>
<br>
<i>＊ Select Your Hand ＊</i>
  </th>
</tr>
<tr>
  <td>【 <%= count %>回目 】　<br>
  <% if(message.contains("必ず")){ %>
      <p class="error"><%= message %></p>
  <% } else { %>
      <p class="message"><%= message %></p>
  <% } %>
  </td>
</tr>
<tr><td>
    <form action="/sepJavaRecurrent/WebJankenServlet" method="post">
        <p><input type="radio" name="userHand" value="0">グー　　</p>
        <p><input type="radio" name="userHand" value="1">チョキ　</p>
        <p><input type="radio" name="userHand" value="2">パー　　</p>

        <p><input type="submit" value="じゃんけんポン"  style="color: hotpink; font-weight: bold;">　</p>
    </form>
    <br>
</td></tr></table>
</div>
</body>
</html>