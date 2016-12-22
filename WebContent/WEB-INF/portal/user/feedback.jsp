<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价</title>
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
	<div class="container">
	<form action="${path }/user/home/feedback.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="oid" value="${order.oid }">
	    <div class="register">
	    <c:forEach items="${order.itemList }" var="item">
				<div class="contact_box">
		 <div class="col-md-4 contact-top" style="float:left">
		<h2>
          	商品
        </h2>
        <ul class="list">
			<li><img alt="" src="${filePath }${item.img}" width="160px" height="160px"></li>
			<li>${item.proName }</li>
			<li>${item.skuSpec }</li>
		</ul>
	   </div>
	      <div class="col-md-8 contact-top">
			<h1>
	          	评价:
	        </h1>
	        <div class="contact_grid">
			 
					<div class="to">
                     	<input id="${item.itemId }1" name="fbLever${item.productId }" type="radio" value="1" required="required"/><label for="${item.itemId }1">好评</label>
						<input id="${item.itemId }2" name="fbLever${item.productId }" type="radio" value="2" required="required"/><label for="${item.itemId }2">中评</label>
						<input id="${item.itemId }3" name="fbLever${item.productId }" type="radio" value="3" required="required"/><label for="${item.itemId }3">差评</label>
					</div>
					<div class="text">
	                   <textarea name="fbtext${item.productId }" required="required"></textarea>
	                </div>
	                
					<div class="text">
	                   <input type="checkbox" name="fbnm${item.productId }" value="1">匿名评价
	                   <input type="file" />
	                </div>
					<div class="clearfix"></div>
               
           </div>
       </div>
     
	   <div class="clearfix"> </div>
	   
	  </div>
	   <HR style="border:1px dashed #987cb9;" width="100%" color="#987cb9" SIZE="1">
	  </c:forEach>
	  <div class="form-submit" style="text-align:center">
	         <input type="submit" value="提交">
		</div>   
		</div>
		</form>
	 </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>