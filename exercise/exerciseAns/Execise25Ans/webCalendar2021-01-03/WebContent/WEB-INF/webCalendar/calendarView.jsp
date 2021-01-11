<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="">
<link rel="stylesheet" type="text/css" href="css/webCalendarStyle.css">
<title>calendarView.jsp</title>
</head>
<body>
<header>
    <jsp:include page="calendarHeader.jsp" />
</header>
<div id="calendar" align="center">
    <% request.setAttribute("listFlag", "prev"); %>
    <jsp:include page="calendarTable.jsp" />

    <% request.setAttribute("listFlag", "base"); %>
    <jsp:include page="calendarTable.jsp" />

    <% request.setAttribute("listFlag", "next"); %>
    <jsp:include page="calendarTable.jsp" />
</div>
<div id="inputDiv" align="center">
    <jsp:include page="calendarInput.jsp" />
    <jsp:include page="calendarMemo.jsp" />
</div>
</body>
</html>