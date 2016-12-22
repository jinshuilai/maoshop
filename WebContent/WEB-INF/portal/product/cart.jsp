<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购物车</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<link rel="stylesheet" href="${path }/css1/lanrenzhijia.css" media="all">
<script type="text/javascript">
jQuery(document).ready(function($) {
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})


function changeQuantity(skuId,quantity){
	if(quantity < 1){
		alert("数量必须大于0！");
		return false;
	}else{
		var jsonObj = validStock(skuId,quantity);
		
		if(jsonObj.result == "no"){
			alert("当前库存不足，实际库存为："+jsonObj.stock);
			updateCartNum(skuId,jsonObj.stock);
		}else{
			updateCartNum(skuId,quantity);
		}
	}
}

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

function updateCartNum(skuId,quantity){
	window.location.href="${path}/cart/updateCartNum.do?skuId="+skuId+"&quantity="+quantity;
}

function clearCart(){
	window.location.href="${path}/cart/clearCart.do";
}

function trueBuy(){
	$.ajax({
		url:"${path}/user/getUser.do",
		type:"post",
		dataType:"text",
		success:function(responseText){
			var jsonObj = $.parseJSON(responseText);		
			if(jsonObj.user != null){
				var result = validCart();
				if(result != "success"){
					alert(result);
				}else{
					window.location.href = "${path}/order/toSubmitOrder.do";
				}
			}else{
				$('.theme-popover-mask').fadeIn(100);
				$('.theme-popover').slideDown(200);
			}
		},
		error:function(){
			alert("系统错误");
		}
	});
}
function validCart(){
	var result = "";
	$.ajax({
		url:"${path}/cart/validCart.do",
		type:"post",
		async:false,
		dataType:"text",
		success:function(responseText){
			result = responseText;			
		},
		error:function(){
			alert("系统错误");
		}
	});
	return result;
}

function loginAjax(){
	var username = $("#username").val();
	var password = $("#password").val();
	$.ajax({
		url:"${path}/user/loginAjax.do",
		type:"post",
		dataType:"text",
		data:{
			username:username,
			password:password
		},
		success:function(responseText){
			if(responseText == "userpass_error"){
				$("#loginerror").html("用户名或密码错误");
			}else{
				$('.theme-popover-mask').fadeOut(100);
				$('.theme-popover').slideUp(200);
				
				var result = validCart();
				if(result != "success"){
					alert(result);
				}else{
					window.location.href = "${path}/order/toSubmitOrder.do";
				}
			}	
			
		},
		error:function(){
			alert("系统错误");
		}
	});
	
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
<div class="container">
	    <div class="wishlist">
	    <c:choose>
	    <c:when test="${fn:length(cartList) < 1}">
		  	  <h2>购物车空空的哦,去看看心仪的商品吧...</h2>
		  	 </c:when>
		  	 <c:otherwise>
	<div class="w ofc case">
	<div>
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc pb40">

			<div class="page">
				<b class="l f14 blue pt48">
					我挑选的商品：
				</b>
			</div>
			

			<table class="tab table table-hover" style="text-align:center;">
			<thead>
			<tr>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>单价（元）</th>
			<th>数量</th>
			<th>小计（元）</th>
			<th>操作</th>
			</tr>     
			</thead>
			<tbody>
			<c:forEach items="${cartList }" var="cart">
				<tr>
					<td style="vertical-align:middle;">${cart.skuId }</td>
					<td class="nwpic">
						
					<a href="${path }/product/detail.do?id=${cart.sku.product.proId}" class="pic"><img src="${filePath }${cart.sku.product.imgs }" width="80px" height="80px"/></a>
								
					<a href="${path }/product/detail.do?id=${cart.sku.product.proId}">${cart.sku.product.pname }</a>
								
							
					</td>
					<td style="vertical-align:middle;">
						<c:forEach items="${cart.sku.specList }" var="spec">
							${spec.attrvalue }
						</c:forEach>
					</td>
					<td style="vertical-align:middle;">￥ ${cart.sku.shopPrice } </td>
					<td style="vertical-align:middle;">
						<a href="javascript:void(0);" title="减"  onclick="changeQuantity(${cart.skuId},${cart.quantity - 1})">-</a>
						<input type="text"  size="1" name="" value="${cart.quantity }" onblur="changeQuantity(${cart.skuId},this.value)"/>
						<a href="javascript:void(0);" title="加"  onclick="changeQuantity(${cart.skuId},${cart.quantity + 1})">+</a></td>
					<td style="vertical-align:middle;">￥${cart.sku.shopPrice * cart.quantity }</td>
					<td class="blue" style="vertical-align:middle;"><a href="${path}/cart/deleteCart.do?skuId=${cart.skuId}" title="删除">删除</a><br /><a href="javascript:void(0);" title="收藏">收藏</a></td>
				</tr>         
				</c:forEach>
			</tbody>
			</table>


			<div class="page">
				<span class="l">
					<input type="button" value="继续购物" title="继续购物" class="hand btn100x26c" />
					<input type="button" value="清空购物车" title="清空购物车" class="hand btn100x26c" onclick="clearCart()" />
				</span>
				<span class="r box_gray">
					<dl class="total">
						<dt>购物车金额小计：<cite>(共<var id="totalNum"><c:out value="${itemNum }"/></var>个商品)</cite></dt>
						<dd>商品金额：￥<var id="totalMoney"><fmt:formatNumber value="${totalPrice }" pattern="#0.00"/></var></dd>
						<dd>运费：￥<var><c:out value="0.00"/></var></dd>
						<dd class="orange">应付总额：￥<var id="totalMoney1"><fmt:formatNumber value="${totalPrice }" pattern="#0.00"/></var></dd>
						<dd class="alg_c"><input id="settleAccountId" type="button" value="结算" class="hand btn136x36a" onclick="trueBuy();"/></dd>
					</dl>
				</span>
			</div>

			
		</div>
		<div class="fl"></div><div class="fr"></div>
	</div>

</div></c:otherwise></c:choose>
		  </div>
</div>
</div>
<div class="theme-popover">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3>登录 是一种态度</h3>
     </div>
     <div class="theme-popbod dform">
           <form class="theme-signin" name="loginform" action="" method="post">
                <ol>
                     <li><h4>你必须先登录！</h4></li>
                     <li><strong>用户名：</strong><input id="username" class="ipt" type="text" name="log" size="20" /></li>
                     <li><strong>密码：</strong><input id="password" class="ipt" type="password" name="pwd"  size="20" /></li>
                     <li><input class="btn btn-primary" type="button" name="submit" value=" 登 录 " onclick="loginAjax()"/></li>
                </ol>
           </form>
           <div id="loginerror" style="color:red;"></div>
     </div>
</div>
<div class="theme-popover-mask"></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>