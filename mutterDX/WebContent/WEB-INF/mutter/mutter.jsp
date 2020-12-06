<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.MutterData" %>

<% String msgFlag = (String) request.getAttribute("msgFlag"); %>
<% List<String> msgList = (List<String>) request.getAttribute("msgList"); %>
<% List<String> mutterList = (List<String>) application.getAttribute("mutterList");%>
<% List<String> dateTimeList = (List<String>) application.getAttribute("dateTimeList"); %>

<% MutterData data = (MutterData) session.getAttribute("data"); %>
<% String name = data.getName(); %>

<% int lastIndex = 0; %>
<% if (mutterList == null || mutterList.isEmpty()){
       ;
   } else {
       lastIndex = mutterList.size() - 1;
   } %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/mutterStyle.css">
<title>mutter.jsp</title>
</head>
<body>
<body>
<header>
    <jsp:include page="mutterHeader.jsp" />
</header>
<div id="content" align="center">
<table id="mutter">
<tr>
  <th>
  <i>＊ Mutter ＊</i><br>
  <% switch(msgFlag){
     case "overText":
     case "reload":
     case "cannotSave":  %>
         <p class="errMsg">
  <%     break;
     default:
      %>
         <p class="message">
  <%     break;
     }//switch %>

      <% for(String msg : msgList){ %>
          <%= msg %><br>
      <% }//for msgList%>
      </p>
  </th>
</tr>
<tr>
  <td>
  <form action="/mutterDX/MutterServlet" method="post">
      <p><input type="text" name="mutter" size="36" required="required">&emsp;
      <button type="submit"><span style="font-size: medium;">&thinsp;投稿&thinsp;</span></button></p>
  </form>
  <p><a href="/mutterDX/MutterFunctionServlet?action=load"><button><i> Load &thinsp;</i></button></a>&emsp;
     <a href="/mutterDX/MutterFunctionServlet?action=save"><button><i> Save &thinsp;</i></button></a>&emsp;
     <a href="/mutterDX/MutterFunctionServlet?action=edit"><button><i> Edit &thinsp;</i></button></a>&emsp;
     <a href="/mutterDX/MutterFunctionServlet?action=logout"><button><i> Logout &thinsp;</i></button></a>
  </p>
  </td>
</tr>
<tr>
  <td>
   <% if (mutterList == null || mutterList.isEmpty()){
        ;
      } else {
          for(int i = 0; i < mutterList.size(); i++){ %>

              <p class="printMutter"><%= name %> : <%= mutterList.get(i) %>

              <!-- mutterが長いときは改行 / nameに合わせてインデント -->
              <% if(mutterList.get(i).length() > 18){%>
                    <br><span style="text-indent: 10%;">
              <%    for(int j = 0; j < name.length(); j++){ %>
                        &emsp;
              <%     } //for j%>
                    </span>
              <% } //if length %>

              <span style="font-size: medium;">〔 <%= dateTimeList.get(i) %> 〕</span></p>

   <%     }//for i %>
   <% }//if-else null%>
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
<footer>
    <jsp:include page="mutterFooter.jsp" />
</footer>
</body>
</html>