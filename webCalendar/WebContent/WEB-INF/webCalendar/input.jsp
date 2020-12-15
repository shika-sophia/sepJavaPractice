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
<table id="input">
<tr>
  <td>
  <% if (msgList.isEmpty()){
        ;
     } else {
        for(String message : msgList){
            if(message.contains("< ! >")){ %>
                <p class="errMsg">
  <%        } else { %>
                <p>
   <%       } %>
            <%= message %></p>
    <%   }//for
     } //if-else %>
  </td>
</tr>
<tr>
  <td>
  <form action="/webCalendar/CalendarServlet" method="post">
    <p>
    <input type="text" name="year" size="4" required="required">年&thinsp;
    <input type="text" name="month" size="2" required="required">月&emsp;
    <button type="submit">送信</button>
    </p>
    <p>
    <button formaction="/webCalendar/MoveServlet" type="submit" name="move" value="prev"> ≪PREV </button>&emsp;
    <button formaction="/webCalendar/MoveServlet" type="submit" name="move" value="next"> NEXT≫ </button>
    </p>
  </form>
  </td>
</tr>
</table>
</body>
</html>