<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"  %>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="java.util.Arrays" %>
<%@ page import="model.CalendarLogic"  %>

<% String listFlag = (String)request.getAttribute("listFlag"); %>

<% CalendarLogic calen = (CalendarLogic)session.getAttribute("calen"); %>
<% int year = calen.getYear(); %>
<% int month = calen.getMonth(); %>
<% int lastDay = calen.getLastDay(); %>
<% int dayWeek = calen.getDayWeek(); %>

<% String[] dayWeekArr = new String[]{
        "日","月","火","水","木","金","土"
   };%>

<% List<String> list = new ArrayList<>(42);
   switch(listFlag){
   case "base":
   default:
       list = calen.getBaseList();
       break;

   case "prev":
       list = calen.getPrevList();
       year = calen.getPrevYear();
       month = calen.getPrevMonth();
       break;

   case "next":
       list = calen.getNextList();
       year = calen.getNextYear();
       month = calen.getNextMonth();
       break;
} //switch %>
<%
boolean shortFlag = true;

if (dayWeek == 5 && lastDay == 31){
  shortFlag = false;
} else if (dayWeek == 6 && lastDay >= 30){
  shortFlag = false;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/webCalendarStyle.css">
<title>calendarTable.jsp</title>
</head>
<body>
<!-- 汎用カレンダー Table -->
<table id="<%= listFlag %>">
<tr><!-- 年月 -->
  <th colspan="7" id="<%= listFlag %>">
  <i><%= year %>年 <%= month %>月</i>
  </th>
</tr>
  <colgroup span="1" class="sunday"></colgroup>
  <colgroup span="5" class="weekday"></colgroup>
  <colgroup span="1" class="saturnday"></colgroup>
<tr><!-- 固定値の表示(日月火水木金土) -->
    <% for (String dayWeekStr : dayWeekArr) { %>
        <td><%= dayWeekStr %></td>
    <% } //for dayWeekArr %>
</tr>
<!-- 日付の表示 -->
<tr>
    <% for(int i = 0; i < list.size(); i++){ %>
        <td><i><%= list.get(i) %></i></td>

        <!-- 7つごとに改行 -->
        <% if((i + 1) % 7 == 0 && i != 0){ %>
        </tr><tr>
        <% } %>

        <% if(i >= 35 && shortFlag == true){
              break;
           } %>
    <% } //for list.size()%>
</tr>
</table>
<% list.clear(); %>
</body>
</html>