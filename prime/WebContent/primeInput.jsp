<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Integer x = (Integer)request.getAttribute("x"); %>
<% Integer y = (Integer)request.getAttribute("y"); %>
<% String message = (String)request.getAttribute("message"); %>

<% String xStr; %>
<% String yStr; %>
<% if (x == null) {
       xStr = "";
   } else {
     xStr = String.valueOf( x );
   }
%>
<% if (y == null) {
       yStr = "";
   } else {
       yStr = String.valueOf( y );
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="primeStyle.css">
<title>primeInput.jsp</title>
</head>
<body>
<div id="upper">
<hr color="white" size="5">
<h1><i>・*＊ Prime Number ＊*・</i></h1>
<hr color="white" size="5">
</div>
<div id="content" align="center">
<table>
<tr>
  <td>
  <i>＊ Input Value ＊</i><br>
  <% if(message.contains("必ず")){ %>
      <p class="error"><%= message %></p>
  <% } else { %>
      <p class="message"><%= message %></p>
  <% } %>
    <form action="/sepJavaRecurrent/PrimeServlet" method="post">
      <p class="showWay">Ｘ ＝ <input type="text" name="x" value="<%= xStr %>" size="7" required="required"></p>
      <p class="showWay">Ｙ ＝ <input type="text" name="y" value="<%= yStr %>" size="7"></p>
      <p class="showWay"><input type="radio" name="calcWay" value="prime" checked> Prime 素数 </p>
      <p class="showWay"><input type="radio" name="calcWay" value="divisor" > Divisor 約数 </p>
      <p class="showWay"><input type="radio" name="calcWay" value="multiple" > Multiple 倍数 </p>
      <p class="showWay"><input type="radio" name="calcWay" value="gcd" > ＧＣＤ 最大公約数 </p>
      <p class="showWay"><input type="radio" name="calcWay" value="lcm" > ＬＣＭ 最小公倍数 </p>

      <p><input type="submit" value="Submit"
            style="color: hotpink; font-weight: bold"></p>
</form>
       <!-- input type="reset" は 前の値を保持し表示する仕様なので無効-->
       <!-- <form>内だと リンクもdoPost()に飛ぶ。<form>外で Servletに飛ばして初期化 -->
       <p><a href="/sepJavaRecurrent/PrimeServlet?action=init">
         <button style="color:hotpink; font-weight:bold"> Reset </button></a></p>
  </td>
</tr>
</table>
</div>
</body>
</html>