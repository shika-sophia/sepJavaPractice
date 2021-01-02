<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.CalendarLogic"  %>
<%@ page import="java.util.List" %>

<% List<String> memoList = (List<String>) request.getAttribute("memoList"); %>
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
<body><!-- memoTable -->
<table id="memo" style="vertical-align: top; text-align: left;">
<tr>
  <td>
  <!-- span styleはここに入れないと反映されない -->
  <span style="color: royalblue; font-weight: bold;">*ＭＥＭＯ*</span>&emsp;
  <span style="color: cornflowerblue;
    text-shadow: thistle 1.5px 1.5px 1.5px;
    font-weight: bold;">
    <%= year %>年<%= month %>月<%= day %>日 <%= memoDayWeek %></span><br>
  <form action="/webCalendar/MemoServlet" method="post">
    <p>
    <input type="text" name="memoStr" size="20" required="required">
    <button type="submit">送信</button>
    </p>
  </form>
  </td>
</tr>
<tr>
  <td>
    <% if (memoList.isEmpty()){
      ;
    } else { %>
      <form action="/webCalendar/FunctionServlet" method="post">

    <%  for (int i = 0; i < memoList.size(); i++){ %>
        <p id="memoStr">
        <input type="checkbox" name="deleteMemo" value="<%= i %>">&thinsp;
        <%= memoList.get(i) %>&emsp;&emsp;
 <%     } //for %>
        <button id="deleteBtn" type="submit">削除</button>
        </p>
        </form>
 <%  } //if-else %>
  </td>
</tr>
</table>
</body>
</html>