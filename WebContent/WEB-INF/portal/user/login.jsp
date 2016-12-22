<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
function changeImg(){
	var imgPath = "${path}/user/getImage.do?date="+new Date();
	$("#loginCap").attr("src",imgPath);
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
	<div class="container">
	    <div class="register">
			   <div class="col-md-6 login-left">
			  	 <img alt="pic" src="${path }/images1/tu1.jpg">
			   </div>
			   <div class="col-md-6 login-right">
			  	<h3>用户登录</h3>
				<form method="post" action="${path }/user/login.do" onkeydown="if(event.keyCode==13)return false;">
				  <div>
					<span>用户名:</span>
					<input type="text" name="username" placeholder="请输入用户名" wid="user"> 
				  </div>
				  <div>
					<span style="font-family:'宋体';">密&nbsp;码:</span>
					<input type="password" name="password" placeholder="请输入密码"> 
				  </div>
				  <div>
					<span>验证码:</span>
					<input type="text" name="captcha" placeholder="请输入验证码" wid="yz">
					<img id="loginCap" alt="换一张" src="${path }/user/getImage.do" onclick="changeImg()"> 
				  </div>
				  <font style="color:red">${tip }</font>
				  <input type="submit" style="margin-left:100px;" value="登录">
				  <a class="forgot" href="#" style="margin-left:100px;">忘记密码?</a>
			    </form>
			   </div>	
			   <div class="clearfix"> </div>
		</div>
	 </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>