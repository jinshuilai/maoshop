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
	        <li><img src="${path }/images1/index-image2.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/index-image4.jpg" class="img-responsive" alt=""/></li>
	        <li><img src="${path }/images1/index-image6.jpg" class="img-responsive" alt=""/></li>
	      </ul>
	  </div>
	</div> 
</div>
<div class="content_top">
	<div class="container">
		<div class="title_2">
				<span>猜你喜欢</span>
		</div>
		<div class="grid_1">	
				
			<c:forEach items="${likeList }" var="item">
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="${filePath }${item.imgs}" class="img-responsive" alt="" width="246px" height="195px"/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<p>${item.pname }</p>
				 	<h4>￥ ${item.price }</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="${path }/product/detail.do?id=${item.proId }" class="link">查看详情</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="${path }/product/list.do?cid=${item.catId }" class="link1">查看更多</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			</c:forEach>
			
	
			<div class="clearfix"> </div>
			<div class="clearfix"> </div>
		</div>
		<div class="title_2">
				<span>邑站 · 品质生活</span>
		</div>
		<div class="column_center">
			<c:forEach items="${expenList }" var="item">
			<div class="col-md-3 span_6">
			  <div class="box_inner">
				<img src="${filePath }${item.imgs}" class="img-responsive" alt="" width="246px" height="195px"/>
				 <div class="sale-box"> </div>
				 <div class="desc">
				 	<h3>${item.pname }</h3>
				 	<h4>￥ ${item.price }</h4>
				 	<ul class="list2">
				 	  <li class="list2_left"><span class="m_1"><a href="${path }/product/detail.do?id=${item.proId }" class="link">查看详情</a></span></li>
				 	  <li class="list2_right"><span class="m_2"><a href="${path }/product/list.do?cid=${item.catId }" class="link1">查看更多</a></span></li>
				 	  <div class="clearfix"> </div>
				 	</ul>
				 	<div class="heart"> </div>
				 </div>
			   </div>
			</div>
			</c:forEach>
		
			<div class="clearfix"> </div>
		</div>
		<div class="barmenu">
			<a href="#"><img src="${path }/images1/chongzhi.jpg"></a>
			<a href="#"><img src="${path }/images1/zhiniaoku.jpg"></a>
			<a href="#"><img src="${path }/images1/fipus.jpg"></a>
			<div class="clearfix"></div>
		</div>	
	</div>
</div>
<div class="content_middle">
	<div class="container">
		<ul class="promote">
			<i class="promote_icon"> </i>
			<li class="promote_head"><h3>新品推荐</h3></li>
		</ul>
		 <ul id="flexiselDemo3">
		 		<c:forEach items="${newList }" var="item">
						<li><img src="${filePath }${item.imgs}"  class="img-responsive" width="200px" height="240px" />
						<div class="grid-flex"><h5>${item.pname } </h5><p>￥${item.price }</p>
							<div class="m_3"><a href="${path }/product/detail.do?id=${item.proId }" class="link2">查看详情</a></div>
							<div class="ticket"> </div>
						</div></li>
				</c:forEach>	
				     </ul>
				    <script type="text/javascript">
					 $(window).load(function() {
						$("#flexiselDemo3").flexisel({
							visibleItems: 6,
							animationSpeed: 1000,
							autoPlay:true,
							autoPlaySpeed: 3000,    		
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
					    	responsiveBreakpoints: { 
					    		portrait: { 
					    			changePoint:480,
					    			visibleItems: 1
					    		}, 
					    		landscape: { 
					    			changePoint:640,
					    			visibleItems: 2
					    		},
					    		tablet: { 
					    			changePoint:768,
					    			visibleItems: 3
					    		}
					    	}
					    });
					    
					});
				   </script>
				   
	</div>
</div>
<div class="container">
   <div class="content_middle_bottom">
	  <div class="col-md-4">
		  <ul class="spinner">
			<i class="spinner_icon"> </i>
			<li class="spinner_head"><h3>商品秒杀</h3></li>
			<div class="clearfix"> </div>
		  </ul>
		  <div class="timer_box">
			<div class="thumb"> </div>
			<div class="timer_grid">
 			<ul id="countdown">
			</ul>
				<ul class="navigation">
					<li>
						<p class="timeRefDays">DAYS</p>
					</li>
					<li>
						<p class="timeRefHours">HOURS</p>
					</li>
					<li>
						<p class="timeRefMinutes">MINUTES</p>
					</li>
					<li>
						<p class="timeRefSeconds">SECONDS</p>
					</li>
				</ul>
          </div>
          <div class="thumb_desc">
          	<h3> 女生搭配套装</h3>
          	<div class="price">
			   <span class="reducedfrom">$140.00</span>
			   <span class="actual">$120.00</span>
			</div>
		 </div>
		 <a href="#"><div class="m_3 deal"><div class="link3">加入购物车</div></div></a>
		</div>
		</div>
		<div class="col-md-8">
		  <ul class="spinner">
			<i class="paperclip"> </i>
			<li class="spinner_head"><h3>买家分享</h3></li>
			<div class="clearfix"> </div>
		  </ul>
		  <c:forEach items="${fbList }" var="fb">
		      <div class="a-top">
				 <div class="left-grid">
					<img src="${path }/images1/t4.jpg" class="img-responsive" alt=""/>
				 </div>
				 <div class="right-grid">
					<h4><a href="${path }/product/detail.do?id=${fb.productId}">
					${fb.sku }</a></h4>
					<p>${fb.content }</p>
				 </div>
				 <div class="but">
				   <a class="arrow" href="${path }/product/detail.do?id=${fb.productId}"> </a>
				 </div>
				 <div class="clearfix"></div>
			 </div>	
		 </c:forEach>
		</div>
		<div class="clearfix"></div>
	</div>
    <div class="content_bottom">
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="box_icon"> </i>
			 <li class="spinner_head"><h3>实体店面</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <img src="${path }/images1/t8.jpg" class="img-responsive" alt=""/>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="bubble"> </i>
			 <li class="spinner_head"><h3>关于我们</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <p>邑购商城是一家专门为邑大学子提供优质服务的网上商城。全场商品保证正品，为你提供便捷、省心、实惠的一站式购物服务！</p>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="mail"> </i>
			 <li class="spinner_head"><h3>分享到</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		 <ul class="social">
			<li><a href=""> <i class="fb"> </i> </a></li>
		    <li><a href=""><i class="tw"> </i> </a></li>
			<li><a href=""><i class="google"> </i> </a></li>
			<li><a href=""><i class="linkedin"> </i> </a></li>
			<li><a href=""><i class="skype"> </i> </a></li>
		</ul>
    	</div>
    	<div class="col-md-3 span_1">
    	  <ul class="spinner">
			 <i class="mail"> </i>
			 <li class="spinner_head"><h3>联系我们</h3></li>
			 <div class="clearfix"> </div>
		  </ul>
		  <p>广东省江门市蓬江区</p>
		  <p>五邑大学44栋</p>
		  <p>电话：12580</p>
		  <p><a href="#"> 邮箱：mao@yigou.com</a></p>
    	</div>
    	<div class="clearfix"> </div>
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