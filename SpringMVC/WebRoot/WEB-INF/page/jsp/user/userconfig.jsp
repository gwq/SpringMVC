<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Login</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.js"></script>
	<script type="text/javascript" src="scripts/jquery-1.7.js"></script>
   <script type="text/javascript">   
       function ajaxpost(){
         inputdata = {name:$("#ajaxname").val()};
         $.ajax({
              type:'POST',
              url:'${pageContext.request.contextPath}/user/ajaxpost.do',
              dataType : 'json', 
			  data:inputdata,
			  success:function(data){
			     $("#message").append(data.success);
				}
         });
       } 
   </script>
  </head>
  
  
  <body>
    <form action="${pageContext.request.contextPath}/user/configSet.do" method="post">
       <input id="name" name="name"/><br/>
       <input type="submit" value="add" >
    </form>
     <input id="ajaxname" name="ajaxname"/><br/>
    <input type="button" value="ajax"  onclick="ajaxpost()"/>
    <div id="message"/>
    <c:out value="${methodtype}"></c:out>
  </body>
</html>
