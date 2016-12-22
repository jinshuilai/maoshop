<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(function(){
	var num = ${onNum};
	if(num == 1){
		$("#s1").addClass("active");
	}else if(num == 2){
		$("#s2").addClass("active");
	}else if(num == 3){
		$("#s3").addClass("active");
	}
});
</script>
</head>
<body>
<div class="header_top">
  <div class="container">
  	<div class="header_top-box">
     <div class="header-top-left">
			
   			   <div class="clearfix"></div>
   			 </div>
			 <div class="cssmenu">
				<ul>
				  <c:choose>
				  <c:when test="${sessionScope.user != null }">
				  <li class="active"><a href="${path }/user/home/index.do">${sessionScope['user'].userName }</a></li> 
				  <li><a href="${path }/user/exit.do">退出</a></li> 
					<li><a href="${path }/user/home/toOrder.do">我的订单</a></li> 
				  </c:when>
				  <c:otherwise>
				  <li><a href="${path }/user/toLogin.do">登录</a></li> 
					<li><a href="${path }/user/toRegist.do">注册</a></li>
				  </c:otherwise>
				  </c:choose>		
				</ul>
			</div>
			<div class="clearfix"></div>
   </div>
</div>
</div>
<div class="header_bottom">
	<div class="container">
	 <div class="header_bottom-box">
		<div class="header_bottom_left">
			<div class="logo">
				<a href="${path }/index"><img src="${path }/images1/yigou.png" alt=""/></a>
			</div>
			<!-- <div class="content1">
				<div id="show">显示时间的位置</div>
			</div> -->
			<div class="clearfix"> </div>
		</div>
		<div class="header_bottom_right">
		<form action="${path }/product/search.do" method="get">
			<div class="search">
			  <input type="text" name="kw" value="${kw }" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '输入';}">
			  <input type="submit" value="">
	  		</div>
	  	</form>
	  		<div class="bag">
				<i class="bag_left"></i>
				<a href="${path }/cart/listCart.do"><li class="bag_right"><p>我的购物车</p> </li></a>
	  			<div class="clearfix"> </div>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
</div>
</div>

<div class="menu">
	<div class="container">
		<div class="menu_box">
	     <ul class="megamenu skyblue">
			<li id="s1" class="grid"><a class="color2" href="${path }/index">主页</a></li>
			<li><a class="color4" href="javascript:void(0);">全部分类</a>
				<div class="megapanel">
					<div class="row">
						<c:forEach items="${category }" var="cate">
						<div class="col1">
							<div class="h_nav">
								<h4>${cate.icon }</h4>
								<ul>
								<c:forEach items="${cate.csList }" var="cs">
									<li><a href="${path }/product/list.do?cid=${cs.id }">${cs.name }</a></li>
									
								</c:forEach>
								</ul>	
							</div>							
						</div>
						</c:forEach>
					  </div>
					</div>
			</li>				
			<li id="s2"><a class="color10" href="${path }/clothing.do">服装城</a>
				
			</li>
			<li id="s3"><a class="color3" href="${path }/electric.do">电器城</a>
			</li>
			<li><a class="color7" href="javascript:void(0);">美妆馆</a>
				
			</li>
			<li><a class="color8" href="javascript:void(0);">易达超市</a></li>
			<div class="clearfix"> </div>
		 </ul>
	  </div>
</div>
</div>
</body>
</html>