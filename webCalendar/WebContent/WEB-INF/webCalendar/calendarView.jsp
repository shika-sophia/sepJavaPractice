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
<% request.setAttribute("listFlag", "dayList"); %>
<jsp:include page="calendarTable.jsp" />
<br>
<jsp:include page="input.jsp" />
</body>
</html>