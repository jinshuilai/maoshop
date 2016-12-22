<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看评价</title>
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
	   
				<div class="contact_box">
		 <div class="col-md-4 contact-top" style="float:left">
		<h2>
          	商品
        </h2>
        <ul class="list">
			<li><img alt="" src="${filePath }${item.sku}" width="160px" height="160px"></li>
			<li>${item.custName }</li>
		</ul>
	   </div>
	      <div class="col-md-8 contact-top">
			<h1>
	          	评价:
	        </h1>
	        <div class="contact_grid">
			 
					<div class="to">
                     	<c:if test="${item.fbLevel == 1 }">好评</c:if>
                     	<c:if test="${item.fbLevel == 2 }">中评</c:if>
                     	<c:if test="${item.fbLevel == 3 }">差评</c:if>
					</div>
					<div class="text">
	                   <textarea readonly="readonly"> ${item.content } </textarea>
	                </div>
	                
					<div class="text">
	                   	<c:if test="${item.anonymous == 1 }">匿名评价|</c:if>
	                   	<fmt:formatDate value="${item.createdAt }" pattern="yyyy/MM/dd HH:mm" />
	                </div>
					<div class="clearfix"></div>
               
           </div>
       </div>
     
	   <div class="clearfix"> </div>
	   
	  </div>
	   <HR style="border:1px dashed #987cb9;" width="100%" color="#987cb9" SIZE="1">
		</div>
		</form>
	 </div>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>