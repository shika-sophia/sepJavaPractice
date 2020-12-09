<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<% List<String> dayList = (List<String>) session.getAttribute("dayList"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/webCalendarStyle.css">
<title>input.jsp</title>
</head>

<body>
<div id="input" align="center">
<table>
<tr><td>
<form action="/webCalendar/StartServlet" method="post">
    <p><input type="text" name="year" size="8" required="required">年&thinsp;
    <input type="text" name="month" size="4" required="required">月&emsp;
    <button type="submit">送信</button>
    </p>
</form>
</td></tr>
</table>
</div>
</body>
</html>