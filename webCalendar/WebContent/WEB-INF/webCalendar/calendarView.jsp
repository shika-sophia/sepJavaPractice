<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/webCalendarStyle.css">
<title>calendarView.jsp</title>
</head>
<body>
<div id="calendar" align="center">
prev
<% request.setAttribute("listFlag", "prev"); %>
<jsp:include page="calendarTable.jsp" />
<br>
day
<% request.setAttribute("listFlag", "base"); %>
<jsp:include page="calendarTable.jsp" />
<br>
next
<% request.setAttribute("listFlag", "next"); %>
<jsp:include page="calendarTable.jsp" />
</div>
<jsp:include page="input.jsp" />
</body>
</html>