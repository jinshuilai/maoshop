<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<%@include file="../common/taglib.jsp" %>
<link href="${path }/css1/flexslider.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="${path }/css1/etalage.css">
<script src="${path }/js1/jquery.etalage.min.js"></script>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
jQuery(document).ready(function($){

	$('#etalage').etalage({
		thumb_image_width: 430,
		thumb_image_height: 430,
		source_image_width: 1290,
		source_image_height: 1290,
		show_hint: true,
		click_callback: function(image_anchor, instance_id){
			alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
		}
	});

});
function change_div(id){
	if(id == 'detail1'){
		$('#detail1').css('display','block');
		$('#detail2').css('display','none');
		$('#detail3').css('display','none');
		$('#deta1').attr('class','here');
		$('#deta2').removeClass();
		$('#deta3').removeClass();
	}else if(id == 'detail2'){
		$('#detail1').css('display','none');
		$('#detail2').css('display','block');
		$('#detail3').css('display','none');
		$('#deta2').attr('class','here');
		$('#deta1').removeClass();
		$('#deta3').removeClass();
	}else{
		$('#detail1').css('display','none');
		$('#detail2').css('display','none');
		$('#detail3').css('display','block');
		$('#deta3').attr('class','here');
		$('#deta2').removeClass();
		$('#deta1').removeClass();
	}
}

$(function(){
	$(".btn_wrap a").click(function() {
		$(".btn_wrap a").each(function() {
			$(this).removeClass();
		});
		$(this).attr("class", "skuhere");
		var skuId = $(this).attr("skuId");

		$.ajax({
			url : "${path}/product/getSkuById.do",
			type : "post",
			data:"skuId="+skuId,
			dataType : "text",
			success : function(responseText) {
				var jsonObj = $.parseJSON(responseText);
				$("#p1").html(jsonObj.shopPrice);
				$("#p2").html(jsonObj.marketPrice);
				if (jsonObj.stock > 0) {
					$("#stockState").html(jsonObj.stock);
					$("#addMyCart").css("visibility","visible");;
				} else {
					$("#stockState").html("没货");
					$("#addMyCart").css("visibility","hidden");
				}
			},
			error : function() {
				alert("系统错误");
			}

		});
	});
	
	$(".btn_wrap a:first").trigger("click");
	
	$("#addMyCart").click(function(){
		var skuId = null;
		$(".goods_list a").each(function(){
			var clazz = $(this).attr("class");
			if(clazz == "skuhere"){
				skuId = $(this).attr("skuId");
			}
		});
		var quantity = $("#buynum").val();
		if(quantity < 1 ){
			alert("数量有误");
			return;
		}
		var jsonObj = validStock(skuId,quantity);
		if(jsonObj.result == "yes"){
			addCart(skuId,quantity);
		}else{
			alert("库存不足");
		}
	});
	
	$('.pagebar li').click(function(){
		$('.pagebar').find('a').each(function(){
			$(this).removeClass('acts');
		})
		$(this).find('a').addClass('acts');
		var num = $(this).find('a').attr("pj");
		var pid = $("#proId").val();
		var fpath = "${path}/product/listFeedback.do?pid="+ pid +"&level="+num;
		$("#feedbackIframe").attr("src",fpath);
	});
});
function validStock(skuId,quantity){
	var result = null;
	var option = {
			url:"${path}/cart/validStock.do",
			type:"post",
			dataType:"text",
			async:false,//修改为同步
			data:{
				skuId:skuId,
				quantity:quantity
			},
			success:function(responseText){
				result = $.parseJSON(responseText);
			},
			error:function(){
				alert("系统错误");
			}
	};
	$.ajax(option);
	return result;
}

function addCart(skuId,quantity){
	var option = {
			url:"${path}/cart/addCart.do",
			type:"post",
			dataType:"text",
			data:{
				skuId:skuId,
				quantity:quantity
			},
			success:function(responseText){
				if(responseText == "success"){
					alert("加入购物车成功");
				}
			},
			error:function(){
				alert("系统错误");
			}
	};
	$.ajax(option);
}

function add(){
	var num = $("#buynum").val();
	$("#buynum").val(parseInt(num) + parseInt(1));
}
function sub(){
	var num = $("#buynum").val();
	if(num > 1){
		$("#buynum").val(num - 1);
	}
}
function gotofb(){
	
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
						<ul id="etalage">
						<c:forEach items="${info.imageList }" var="img">
						<c:if test="${fn:length(img.filepath) != 0}">
							<li>
								<img class="etalage_thumb_image" src="${filePath}${img.filepath}" class="img-responsive" />
								<img class="etalage_source_image" src="${filePath}${img.filepath}" class="img-responsive" title="" />
							</li>
						</c:if>
						</c:forEach>
						</ul>
						 <div class="clearfix"></div>		
				  </div> 
				  <div class="desc1 span_3_of_2">
				    <h1>${info.pname }</h1><input id="proId" type="hidden" value="${info.proId}">
				    <p style="color:red;font-size:14px;">${info.shortDesc }</p>
				    <p class="m_5">商城价：￥<font id="p1">888</font><span class="reducedfrom">市场价：￥<i id="p2">999</i></span></p>
				    <p>运费：包邮</p>
				    <p>库存：<span id="stockState">有货</span></p>
				    <p>累计评论：${leverNum.allFeed }</p>
				    <p>服务承诺：正品保障       七天无理由退货</p>
					<div class="goods_box">
						<ul>
							<li class="goods_list">
								<p>规格:</p>
								<div class="btn_wrap">
								<c:forEach items="${info.skuList }" var="sku" varStatus="state">
								<c:choose>
								<c:when test="${state.index == 0 }">
								<a href="javascript:void(0);" skuId="${sku.skuId }" class="skuhere">
								<c:forEach items="${sku.specList }" var="spec">
									${spec.attrvalue }
								</c:forEach>
								</a>
								</c:when>
								<c:otherwise>
								<a href="javascript:void(0);" skuId="${sku.skuId }">
								<c:forEach items="${sku.specList }" var="spec">
									${spec.attrvalue }
								</c:forEach>
								</a>
								</c:otherwise>
								</c:choose>
								</c:forEach>
								</div>
							</li>
							<li>
								<div class="btn_wrap1">
									<p>我要买:</p>
									<a href="javascript:void(0);" class="btn_reduce" onclick="sub()">-</a>
									<input type="text" value="1" id="buynum">
									<a href="javascript:void(0);" class="btn_add" onclick="add()">+</a>
								</div>
							</li>
							<li  class="goods_li">
								<input id="addMyCart" type="submit" value="加入购物车">
								<a href="javascript:void(0);">加入收藏</a>
								<a name="firstAnchor"></a> 
							</li>
						</ul>
					</div>
					
				  </div>
				  <div class="clearfix"></div>	
       </div>
      <div class="clearfix"> </div>
     </div>
		<h2 class="h2 h2_ch mt">
				<em> 
					<a id="deta1" href="javascript:void(0);" title="商品描述" rel="#detailTab1" class="here" onclick="change_div('detail1')">商品描述</a> 
					<a id="deta2" href="javascript:void(0);" title="规格参数" rel="#detailTab2" onclick="change_div('detail2')">规格参数</a>
					<a id="deta3" href="javascript:void(0);" title="商品评价" rel="#detailTab3" onclick="change_div('detail3')">商品评价</a>
				</em>
				<cite></cite>
	    </h2>
	    <div class="detail_wrap">
	    	<div id="detail1" class="detail1" style="display:block;" >${info.desctext }</div>
	    	<div id="detail2" class="detail2" style="display:none;" >
	    	<table class="table table-borderded table-hover">
						<thead>
							<tr>
								<th colspan="2">基本参数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${info.paraList }" var="para">
								<tr>
									<th width="15%" class="alg_r">${para.featureName }</th>
									<td>${para.attrValue }</td>
								</tr>
							</c:forEach>
						</tbody>
			</table>
	    	</div>
	    	<div id="detail3" class="detail3" style="display:none;">
	    	 
	    	<h2>好评率：${leverNum.praiseRate}%</h2>
	    	<div class="pagebar">
				<ul>
					<li><a class="acts"  href="javascript:void(0);" pj="">全部评价(${leverNum.allFeed })</a></li>
					<li><a  href="javascript:void(0);" pj="1">好评(${leverNum.praise })</a></li>
					<li><a  href="javascript:void(0);" pj="2">中评(${leverNum.medEval })</a></li>
					<li><a  href="javascript:void(0);" pj="3">差评(${leverNum.bad })</a></li>
				</ul>
				
			</div>
	    	<iframe id="feedbackIframe" src="${path }/product/listFeedback.do?pid=${info.proId}" frameBorder=0 scrolling=no width="86%" height="1000px"></iframe>
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