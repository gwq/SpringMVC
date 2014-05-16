<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>JFreeChart</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.js"></script>
	<script type="text/javascript" src="scripts/jquery-1.7.js"></script>
   <script type="text/javascript">   
         function ajaxpost(){
         inputdata = {pernum:$("#pernum").val()};
         $.ajax({
              type:'POST',
              url:'${pageContext.request.contextPath}/user/chartchange.do',
              dataType : 'json', 
			  data:inputdata,
			  success:function(data){
			     document.getElementById("img1").src = "${pageContext.request.contextPath}/datapic/areachart.png?"+(Math.round(Math.random()*(1000-1))+1);
				}
         });
       } 
   </script>
  </head>
  
  
  <body>
     <img id ="img1" src="${pageContext.request.contextPath}/datapic/areachart.png"/>
     <input id="pernum" name="pernum"/><br/>
    <input type="button" value="ajax"  onclick="ajaxpost()"/>
    </body>
</html>
