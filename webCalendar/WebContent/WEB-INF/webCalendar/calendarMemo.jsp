<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CalendarLogic"  %>

<% CalendarLogic calen = (CalendarLogic)session.getAttribute("calen"); %>
<% int year = calen.getYear(); %>
<% int month = calen.getMonth(); %>
<% int day = calen.getDay(); %>
<% String memoDayWeek = calen.getMemoDayWeek(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/webCalendarStyle.css">
<title>calendarMemo.jsp</title>
</head>
<body>
<table id="memo">
<tr>
  <td>
  <span id="memoTitle">*ＭＥＭＯ*</span>
  <span id="menoDate"><%= year %>年<%= month %>月<%= day %>日<%= memoDayWeek %></span><br>
  <form action="/webCalendar/MemoServlet" method="post">
    <p>
    <input type="text" name="memoStr" size="20" required="required">
    <button type="submit">送信</button>
    </p>
  </form>
  </td>
</tr>
</table>
</body>
</html>