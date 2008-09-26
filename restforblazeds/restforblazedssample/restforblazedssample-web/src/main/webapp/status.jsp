<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%=request.getParameter("message")%>
<%
  String status = request.getParameter("status");
  response.setStatus(Integer.parseInt(status));
%>