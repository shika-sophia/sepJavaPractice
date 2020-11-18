<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="webPractice.prime.model.PrimeData" %>

<% Integer x = (Integer)request.getAttribute("x"); %>
<% Integer y = (Integer)request.getAttribute("y"); %>
<% String calcWay = (String)request.getAttribute("calcWay"); %>

<% PrimeData data =(PrimeData)session.getAttribute("data"); %>
<% String xResult = data.getxResult(); %>
<% String yResult = data.getyResult(); %>
<% String zResult = data.getzResult(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="primeStyle.css">
<title>primeResult.jsp</title>
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
  <i>＊ Result ＊</i><br>
  </td>
</tr>
<tr>
  <td>
  <p class="result"><%= xResult %></p>
  <p class="result"><%= yResult %></p>

  <% if (calcWay.equals("gcd") || calcWay.equals("lcm")){ %>
      <p class="result"><%= zResult %></p>
  <% } %>
  </td>
</tr>
<tr>
  <td>
  <p><a href="/sepJavaRecurrent/PrimeServlet?action=again">
         <button style="color:hotpink; font-weight:bold">別の結果</button></a>&emsp;
     <a href="/sepJavaRecurrent/PrimeServlet?action=init">
         <button style="color:hotpink; font-weight:bold">最初から</button></a>
  </td>
</tr>
</table>
</div>
</body>
</html>