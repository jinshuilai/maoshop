<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>邑购商城</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<%@include file="./common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: false,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
    
</script>
</head>
<body>
<%@include file="./common/head.jsp" %>
<div class="index_slider">
	<div id="boxID" class="container">
	<%-- <div class="loading"><img src="${path }/images1/loading.gif" alt="请稍候..." /></div> --%>
	  <div class="callbacks_container">
	      <ul class="rslides" id="slider">
	        <li><img src="${path }/images1/electric1.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/electric2.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/electric3.jpg" class="img-responsive" alt=""/></li>
	      </ul>
	  </div>
	</div> 
</div>
<div class="content_top">
	
</div>

<div class="footer">
	<div class="container">
		<img src="${path }/images1/pay.png" class="img-responsive" alt=""/>
		<ul class="footer_nav">
		  <li><a href="#">主页</a></li>
		  <li><a href="#">邑站社区</a></li>
		  <li><a href="#">微超市</a></li>
		  <li><a href="#">友情链接</a></li>
		  <li><a href="#">营销中心</a></li>
		  <li><a href="#">关于我们</a></li>
		  <li><a href="contact.html">联系我们</a></li>
		</ul>
		<p class="copy">Copyright &copy; 2016-10.Company name All rights reserved. More Information <a href="#" target="_blank" title="模板之家">邑站之家</a> - Collect from <a href="#" title="网页模板" target="_blank">快乐驿站</a></p>
	</div>
</div>
</body>
</html>		