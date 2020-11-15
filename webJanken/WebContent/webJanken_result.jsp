<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>

<%@ page import="webPractice.webJanken.model.WebJankenData" %>

<% Integer count = (Integer)request.getAttribute("count"); %>
<% String message = (String)request.getAttribute("message"); %>

<% WebJankenData data = (WebJankenData)session.getAttribute("data"); %>
<% String result = data.getResult(); %>
<% String userHand = data.getUserHand(); %>
<% String comHand = data.getComHand(); %>
<% int winNum = data.getWinNum(); %>
<% double winRate = data.getWinRate(); %>
<% List<String> winList = data.getWinList(); %>

<% String rateFormat = String.format("%.3f", winRate); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="webJanken_style.css">
<meta charset="UTF-8">
<title>webJanken_result</title>
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
<i>＊ Result ＊</i>
  </th>
</tr>
<tr>
  <td>【 <%= count %>回目 】　<br>
  </td>
</tr>
<tr>
  <td>
    <p class="showHand">〔あなた: <%= userHand %> 〕  VS  〔COM : <%= comHand %> 〕</p>
    <p class="result"><%= result %></p>

    <p class="message">対戦結果
          <% if (count >= 10){ %>
              <br>
          <% } %>
      <% int i = 0; %>
    [ <% for (String win : winList){ %>
        <%= win %>&thinsp;

        <% i++; %>
        <% if (i % 10 == 0){ %>
            <br>
        <% } %>
    <% } %> ]</p>
    <p class="showHand">勝率: <%= rateFormat %> ( <%= winNum %>勝 / <%= count %>回 )</p>
    <br>
    <p class="message"><%= message %></p>
    <p><a href="/sepJavaRecurrent/WebJankenServlet">
            <button style="color: hotpink; font-weight: bold;">もう一度</button></a>　
       <a href="/sepJavaRecurrent/WebJankenServlet?action=init">
            <button style="color: hotpink; font-weight: bold;">最初から</button></a>

    </p>
  </td>
</tr>
</table>
</div>
</body>
</html>