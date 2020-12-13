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
<% List<String> dayList = calen.getDayList(); %>
<% List<String> prevList = calen.getPrevList(); %>
<% List<String> nextList = calen.getDayList(); %>

<% String[] dayWeekArr = new String[]{
        "日","月","火","水","木","金","土"
   };%>

<% List<String> list = new ArrayList<>(42);
   switch(listFlag){
   case "day":
   default:
       list.addAll(dayList);
       break;

   case "prev":
       list.addAll(prevList);
       break;

   case "next":
       list.addAll(nextList);
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
<table>
<tr><!-- 年月 -->
  <th colspan="7">
  <p><%= year %>年 <%= month %>月</p>
  </th>
</tr>
<tr><!-- 固定値の表示(日月火水木金土) -->
    <% for (String dayWeekStr : dayWeekArr) { %>
        <td><%= dayWeekStr %></td>
    <% } //for dayWeekArr %>
</tr>
<!-- 日付の表示 -->
<tr>
    <% for(int i = 0; i < list.size(); i++){ %>
        <td><%= list.get(i) %></td>

        <!-- 7つごとに改行 -->
        <% if((i + 1) % 7 == 0 && i != 0){ %>
        </tr><tr>
        <% } %>

        <% if(i > 36 && shortFlag == true){
              break;
           } %>
    <% } //for list.size()%>
</tr>
</table>
<% list.clear(); %>
</body>
</html>