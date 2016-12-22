<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品快照</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<%@include file="../common/taglib.jsp" %>
<link href="${path }/css1/flexslider.css" rel='stylesheet' type='text/css' />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
function change_div(id){
	if(id == 'detail1'){
		$('#detail1').css('display','block');
		$('#detail2').css('display','none');
		$('#deta1').attr('class','here');
		$('#deta2').removeClass();
	}else if(id == 'detail2'){
		$('#detail1').css('display','none');
		$('#detail2').css('display','block');
		$('#deta2').attr('class','here');
		$('#deta1').removeClass();
	}
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
<div class="container">
	  <div class="single_top">
	       <div class="col-md-12 single_right">
	   	       <div class="grid images_3_of_2">
								<img class="etalage_thumb_image" src="${filePath}${snapshot.productImg}"/>
						 <div class="clearfix"></div>		
				  </div> 
				  <div class="desc1 span_3_of_2">
				    <h3>您现在查看的是交易快照，<a href="${path }/product/detail.do?id=${snapshot.productId}">点击这里查看最新商品信息</a></h3>
				    <p>${snapshot.productName }</p>
				    <p class="m_5">购买价格：￥${snapshot.buyPrice }</p>
				    <p>购买规格:${snapshot.spec }</p>
				
					
				  </div>
				  <div class="clearfix"></div>	
       </div>
      <div class="clearfix"> </div>
     </div>
		<h2 class="h2 h2_ch mt">
				<em> 
					<a id="deta1" href="javascript:void(0);" title="商品描述" rel="#detailTab1" class="here" onclick="change_div('detail1')">商品描述</a> 
					<a id="deta2" href="javascript:void(0);" title="规格参数" rel="#detailTab2" onclick="change_div('detail2')">规格参数</a>
				</em>
				<cite></cite>
	    </h2>
	    <div class="detail_wrap">
	    	<div id="detail1" class="detail1" style="display:block;" >${snapshot.desctext }</div>
	    	<div id="detail2" class="detail2" style="display:none;" >
	    	<table class="table table-borderded table-hover">
						<thead>
							<tr>
								<th colspan="2">基本参数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${snapshot.paramter }" var="para">
								<tr>
									<th width="15%" class="alg_r"></th>
									<td>${para }</td>
								</tr>
							</c:forEach>
						</tbody>
			</table>
	    	</div>
	    	
	    </div>
     <h4 class="head_single">浏览历史</h4>
     <div class="span_3">
	          	 <div class="col-sm-3 grid_1">
	          	    <a href="single.html">
				     <img src="${path}/images1/pic9.jpg" class="img-responsive" alt=""/>
				     <h3>parum clari</h3>
				   	 <p>Duis autem vel eum iriure</p>
				   	 <h4>Rs.399</h4>
			         </a>  
				  </div> 
				<div class="col-sm-3 grid_1">
	          	    <a href="single.html">
				     <img src="${path}/images1/pic8.jpg" class="img-responsive" alt=""/>
				     <h3>parum clari</h3>
				   	 <p>Duis autem vel eum iriure</p>
				   	 <h4>Rs.399</h4>
			         </a>  
				  </div> 
				 <div class="col-sm-3 grid_1">
	          	    <a href="single.html">
				     <img src="${path}/images1/pic1.jpg" class="img-responsive" alt=""/>
				     <h3>parum clari</h3>
				   	 <p>Duis autem vel eum iriure</p>
				   	 <h4>Rs.399</h4>
			         </a>  
				  </div> 
<!-- 				  <div class="col-sm-3 grid_1">
	          	    <a href="single.html">
				     <img src="${path}/images1/pic3.jpg" class="img-responsive" alt=""/>
				     <h3>parum clari</h3>
				   	 <p>Duis autem vel eum iriure</p>
				   	 <h4>Rs.399</h4>
			         </a>  
				  </div> --> 
				  <div class="clearfix"></div>
	     </div>
      </div>
</div>
<%@include file="../common/footer.jsp" %>
<script defer src="${path }/js1/jquery.flexslider.js"></script>
<script type="text/javascript">
$(function(){
  //SyntaxHighlighter.all();
});
$(window).load(function(){
  $('.flexslider').flexslider({
	animation: "slide",
	start: function(slider){
	  $('body').removeClass('loading');
	}
  });
});
 </script>
</body>
</html>		