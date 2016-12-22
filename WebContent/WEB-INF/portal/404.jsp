<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>404</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="./common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

</head>
<body>
<%@include file="./common/head.jsp" %>
<div class="men">
<div class="container">
	    <div class="error-404 text-center">
			<h1>404</h1>
			<p>没有找到这个页面</p>
			<a class="b-home" href="${path }/index">回到主页</a>
		 </div>
      </div>
</div>
<%@include file="./common/footer.jsp" %>
</body>
</html>