<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webPractice.mutter.model.MutterData" %>

<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<% MutterData data = (MutterData) session.getAttribute("data"); %>

<% String name = data.getName(); %>
<% List<String> mutterList = data.getMutterList();%>

<% String mutter = ""; %>
<% if (mutterList.isEmpty()){
    ;
   } else {
     int lastIndex = mutterList.size() - 1;
     mutter = mutterList.get(lastIndex);
   }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/mutterStyle.css">
<title>mutter.jsp</title>
</head>
<body>
<body>
<div id="upper">
<hr color="white" size="5">
<h1><i>・*＊ Mutter WebApplication ＊*・</i></h1>
<hr color="white" size="5">
</div>
<div id="content" align="center">
<table id="mutter">
<tr>
  <th>
  <i>＊ Mutter ＊</i><br>
  <p class="message">
      <% for(String msg : msgList){ %>
          <%= msg %><br>
      <% }//for msgList%>
  </p>
  </th>
</tr>
<tr>
  <td>
  <form action="sepJavaRecurrent/MutterServlet" method="post">
      <p><textarea name="mutter" rows="3" cols="15" required="required"></textarea>&emsp;
      <button type="submit">つぶやく</button></p>
  </form>
   <% if (mutter == null || mutter.equals("")){
        ;
      } else {
          for(String mtr : mutterList){ %>
                  <p><%= name %> : <%= mtr %></p>
   <%     }//for
      } //if-else
   %>
  </td>
</tr>
</table>
</div>
</body>
</html>