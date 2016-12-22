<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="${path }/js/jquery.validate.min.js"></script>
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
	    <div class="register" >
	   
	     <div class=" login-right" style="margin:0 auto;width:50%">   
          <h3>用户注册</h3>
				<form id="form1" method="post" action="${path }/user/register.do">
				  <div>
					<span>*用户名称:</span>
					<input type="text" name="userName" placeholder="请输入用户名" wid="user"> 
					<span style="color:red">${name }</span>
				  </div>
				  <div>
					<span>*用户密码:</span>
					<input type="password" name="password" placeholder="请输入密码" id="pwd">
					 <span style="color:red"></span>
				  </div>
				  <div>
					<span>*确认密码:</span>
					<input type="password" name="password1" placeholder="请再次输入密码"> 
					<span style="color:red"></span>
				  </div>
				  <div>
					<span>*邮箱地址:</span>
					<input type="text" name="email" placeholder="请输入邮箱地址" wid="user"> 
					<span style="color:red"></span>
				  </div>
				  <div>
					<span>手机号码:</span>
					<input type="text" name="phone" maxlength="11" placeholder="请输入手机号码" wid="user"> 
				  </div>
				  <div>
					<span style="font-family:'宋体';">*验证码:&nbsp;</span>
					<input type="text" name="captcha" placeholder="请输入验证码" wid="yz">
					<img id="loginCap" alt="换一张" src="${path }/user/getImage.do" onclick="changeImg()"> 
					<span style="color:red">${tip }</span>
				  </div>
				  
				  <input type="submit" style="margin-left:150px;" value="注册">
				  <a class="forgot" href="#" style="margin-left:100px;">已有账号，前往登录</a>
			    </form>
		   </div>
		    <script type="text/javascript">
            $.validator.setDefaults({  
                        submitHandler: function(form) {  
                             form.submit();  
                        }  
            });  
            $(function()
            {
                // Validation       
                $("#form1").validate(
                {                   
                    // Rules for form validation
                    rules:
                    {
                        userName:
                        {
                            required: true,
                            minlength: 3
                        },
                        password:
                        {
                            required: true,
                            minlength: 6,
                            maxlength: 20
                        },
                        password1:
                        {
                            required: true,
                            minlength: 6,
                            maxlength: 20,
                            equalTo: '#pwd'
                        },
                        email:{
                        	required: true,
                            email: true
                        }   
                    },
                    
                    // Messages for form validation
                    messages:
                    {
                        userName:
                        {
                            required: '请输入用户名',
                            minlength: '用户名最少为3位'
                        },
                        
                        password:
                        {
                            required: '请输入密码',
                            minlength: '密码最少为6位，最多20位'
                        },
                        password1:
                        {
                            required: '再输入一次',
                            minlength: '密码最少为6位，最多20位',
                            equalTo: '密码不一致'
                        },
                        email:{
                        	required: '邮箱不能为空',
                            email: '邮箱格式不对'
                        }
                    },                  
                    
                    // Do not change code below
                    errorPlacement: function(error, element)
                    {
                        error.appendTo(element.next("span"));
                    }
                });
            });         
        </script>
		 </div></div></div>
 <div class="clearfix"> </div>
<%@include file="../common/footer.jsp" %>
</body>
</html>		