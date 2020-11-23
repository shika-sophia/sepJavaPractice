<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webPractice.mutter.model.MutterData" %>
<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<% List<String> mutterList = (List<String>) application.getAttribute("mutterList"); %>
<% MutterData data = (MutterData) session.getAttribute("data"); %>
<% String name = data.getName(); %>
<% List<String> dateTimeList = data.getDateTimeList(); %>

<% String mutterFlag = ""; %>
<% int lastIndex = mutterList.size() - 1; %>

<% if (mutterList.isEmpty()){
    ;
   } else {
     mutterFlag = mutterList.get(lastIndex);
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
<h1><i>・*＊ Mutter ＊*・</i></h1>
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
  <form action="/sepJavaRecurrent/MutterServlet" method="post">
      <p><input type="text" name="mutter" size="20" required="required">&emsp;
      <button type="submit"><span style="font-size: medium;">&thinsp;投稿&thinsp;</span></button></p>
  </form>
  </td>
</tr>
<tr>
  <td>
   <% if (mutterFlag == null || mutterFlag.equals("")){
        ;
      } else {
          for(int i = 0; i < mutterList.size(); i++){ %>
              <p class="printMutter"><%= name %> : <%= mutterList.get(i) %>

              <!-- mtrが長いときは改行 / nameに合わせてインデント -->
              <% if(mutterList.get(i).length() > 18){%>
                    <br><span style="text-indent: 10%;">
              <%    for(int j = 0; j < name.length(); j++){ %>
                        &emsp;
              <%     } //for j%>
                    </span>
              <% } //if %>

              <span style="font-size: medium;"><%= dateTimeList.get(i) %></span></p>
   <%     }//for i %>
   <% }//if-else %>
  </td>
</tr>
<tr><!-- mutter数に応じて下部の余白を生成 -->
  <td>
  <% final int INITIAL = 10; %>
  <% for(int i = 0; i < (INITIAL - lastIndex); i++){ %>
        <br>
  <% } %>
  </td>
</tr>
</table>
</div>
</body>
</html>