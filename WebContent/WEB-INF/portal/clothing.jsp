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
      
      $(".words li a").click(function(){
    	  var val = $(this).html();
    	  window.open("${path}/product/search.do?kw="+val);
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
	        <li><img src="${path }/images1/clothing1.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/clothing2.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/clothing3.jpg" class="img-responsive" alt=""/></li>
	      </ul>
	  </div>
	</div> 
</div>
<div class="content_top">
	<div class="container">
		<div class="sellers_grid">
			<div class="title_2">

				<span><i>1F </i> 服装鞋包</span>
			</div>
			<div class="mc">
				<div class="side">
					<div class="side_inner">
						<div class="banner"> 
							<img src="${path }/images1/clothes.jpg" width="330" height="475" alt="">
						</div>
						<ul class="themes">
						<c:forEach items="${csList }" var="cs" varStatus="st">
							<li class="fore${st.index + 1 }">
							<a target="_blank" href="${path }/product/list.do?cid=${cs.id }">
							<i class="icon"></i>
							<span>${cs.name }</span>
							</a>
							</li>
						</c:forEach>	
						</ul>
						<ul class="words">
							<li class="fore1">
								<a href="javascript:void(0);">潮流女装</a>
								<a href="javascript:void(0);">时尚男装</a>
								<a href="javascript:void(0);">秋冬外套</a>
								<a href="javascript:void(0);">春秋衬衫</a>
								<a href="javascript:void(0);">简约T恤</a>
								
							</li>
							<li class="fore2">
								<a href="javascript:void(0);">修身长裤</a>
								<a href="javascript:void(0);">休闲运动</a>
								<a href="javascript:void(0);">九分裤</a>
								<a href="javascript:void(0);">棉服</a>
								<a href="javascript:void(0);">打底裤</a>
								
							</li>
						</ul>
					</div>
					<div class="side_extra">
						<a href="#"><img src="${path }/images1/extraimg.jpg"></a>
					</div>
				</div>
				<div class="main">
					<div class="main-inner">
						<ul class="main-body">
							<li class="fore1">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic1.jpg" width="439" height="157"></a>
							</li>
							<li class="fore02">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic2.jpg" width="439" height="315"></a>
							</li>
							<li class="fore2">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic3.jpg" width="439" height="157"></a>
							</li>
						</ul>
						<ul class="main-body1">
							<li class="fore3">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic4.jpg" width="219" height="157"></a>
							</li>
							<li class="fore4">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic5.jpg" width="219" height="157"></a>
							</li>
							<li class="fore5">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic6.jpg" width="219" height="157"></a>
							</li>
							<li class="fore6" >
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic7.jpg" width="219" height="157"></a>
							</li>
						</ul>
						<ul class="main-extra">
							<li class="fore3">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic11.jpg" width="120" height="157"></a>
							</li>
							<li class="fore4">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic12.jpg" width="120" height="157"></a>
							</li>
							<li class="fore5">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic13.jpg" width="120" height="157"></a>
							</li>
							<li class="fore6">
								<a target="_blank" href="#"><img src="${path }/images1/clothes/pic14.jpg" width="120" height="157"></a>
							</li>
						</ul>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>	
	</div>
	
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