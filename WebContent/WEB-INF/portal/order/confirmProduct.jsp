<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
$(function(){
	$("input[name='isPrint']").click(function(){
		$(this).parent().parent().find('dd').hide();
		$(this).parent().next().show();
	});
	
	/*addree*/
	$("#adrList input").click(function(){
		var val = $(this).val();
		if(val == 'add'){
			$('#addAddress').show(500);
		}else{
			$('#addAddress').hide(500);
		}
	});

	$('#adrList').find('tr').hover(function(){
		$(this).addClass('over');
	},function(){
		$(this).removeClass('over');
	});
	
	//选择省的选项的时的事件定义
	$("#province").change(function(){
		var parentId = $(this).val();
		loadOption(parentId, "#mycity");
	})
	$("#mycity").change(function(){
		var parentId = $(this).val();
		loadOption(parentId, "#district");
	})
});

function loadOption(parentId, selectId){
	$.ajax({
		url:"${path}/user/loadOption.do",
		type:"post",
		dataType:"text",
		async:false,
		data:{
			parentId:parentId
		},
		success:function(responseText){
			//清空要追加的select
			
			if(selectId == "#mycity"){
				//如果是选择其中一个省，加载该省下的所有的option时既要清空城市的option也要清空区县
				$(selectId).empty();
				$("#district").empty();
				//把城市和区县的请选择的option给加回来
				$(selectId).append("<option value='-1'>城市</option>");
				$("#district").append("<option value='-1'>区县</option>");
			}else{
				//如果是选择城市，加载城市下的区县，只清区县下的option即可
				$(selectId).empty();
				$(selectId).append("<option value='-1'>区县</option>");
			}
			//alert(responseText);
			//把json字符串转换成json对象
			var jsonObj = $.parseJSON(responseText);
			for(var i = 0; i < jsonObj.rList.length; i++){
				var opt = $("<option value='"+jsonObj.rList[i].regionId+"'>"+jsonObj.rList[i].regionName+"</option>");
				//追加option
				$(selectId).append(opt);
			}
		},
		error:function(){
			alert("系统错误");
		}
	})
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
<form action="${path}/order/submitOrder.do" method="post">
<div class="sending">
		<h3>配送方式</h3>
		<div class="transform">
			<input type="radio" checked="checked">快递运输
			<div class="s_detail">
			默认使用顺丰快递配送，我们将在24小时内将货物发出。<br/>
			如需指定配送快递和发货时间，请填写备注
			</div>
		</div>
	</div>
	<div class="sending">
		<h3>收货人信息</h3>
		<div class="transform">
			<h4>常用地址</h4>
			<div class="s_detail">
				<dl class="distr">
		<dd>
			<div class="box_d bg_gray2 ofc">
				<table cellspacing="0" summary="" id="adrList" class="tab">
				<c:forEach items="${addrList }" var="addr">
				<tr>
				<td colspan="4"><input type="radio" value="${addr.shipid }" <c:if test="${addr.isDefault }">checked</c:if> name="ship" />
				${addr.consignee}　　${addr.phone}　　${addr.province }${addr.city }${addr.district }${addr.address }　　${addr.remark }</td>
				</tr>
				</c:forEach>
				<tr>
				<td colspan="4"><input type="radio" value="add" name="ship" />使用新的收货地址</td>
				</tr>
				</table>
			</div>
		</dd>
		</dl>
		</div>
		<ul id="addAddress" class="shipaddr" style="display:none">
		<li>收货人姓名：
			<span><input type="text" id="username" name="consignee" maxLength="20" /></span>
		</li>
		<li>　收货地址：
		<select id="province" name="provinceId">
			<option value="-1" selected="selected">请选择省</option>
			<c:forEach items="${rList }" var="region">
				<option value="${region.regionId }">${region.regionName }</option>
			</c:forEach>
		</select>
		<select id="mycity" name="cityId">
			<option value="-1" selected>城市</option>
		</select>
		<select id="district" name="districtId">
			<option value="-1" selected>县/区</option>
		</select>
		</li>
		<li>　街道地址：
			<span><input type="text" id="nick" name="address" maxLength="50"  /></span>
		</li>
		<li>　邮政编码：
			<span><input type="text" id="zipCode" name="postcode" maxLength="6"  /></span>
		</li>
		<li>　联系电话：
			<span><input type="text" id="telphone" name="phone" maxLength="11"  /></span>
		</li>
		</ul>
		
		</div>
	</div>
	<div class="sending">
		<h3>发票信息</h3>
		<div class="transform">
			<dl class="distr">
			<dt><input type="checkbox" name="isPrint" value="1"/>需要打印发票</dt>
			<dd class="box_d bg_gray2 ofc" style="display:none">
				<ul class="uls form">
				<li><label for="">发票类型：</label><input type="radio" value="1" name="payable" checked="checked" />个人&nbsp;&nbsp;<input type="radio" value="2" name="payable" />单位</li>
				<li><label for="">发票抬头：</label><span class="bg_text"><input type="text" id="invoiceNick" name="company" maxLength="32"  /></span></li>
				<li><label>发票内容：</label><select name="contents"><option value="1" selected>明细</option><option value="2">办公用品</option></select></li>
				</ul>
			</dd>
		</dl>
		</div>
	</div>
	<div class="sending">
		<h3>商品清单</h3>
		<table class="tab table table-hover" style="text-align:center;width:930px;">
			<thead>
			<tr>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>规格</th>
			<th>单价（元）</th>
			<th>数量</th>
			<th>小计（元）</th>
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
						${cart.quantity }
					<td style="vertical-align:middle;">￥${cart.sku.shopPrice * cart.quantity }</td>
				</tr>         
				</c:forEach>
			</tbody>
			</table>
	</div>
	<div class="sending">
		<h3>订单备注</h3>
		<div class="transform" style="height:260px;">
		
			<div class="page">
			<span>
				<textarea name="custRemark" rows="4" cols="70" resize: none;></textarea>
				
			</span>
				<span class="r box_gray">
					<dl class="total">
						<dt>最终订单金额：<cite>(共<var id="totalNum"><c:out value="${itemNum }"/></var>个商品)</cite></dt>
						<dd>商品金额：￥<var id="totalMoney"><fmt:formatNumber value="${totalPrice }" pattern="#0.00"/></var></dd>
						<dd>运费：￥<var><c:out value="0.00"/></var></dd>
						<dd class="orange">应付总额：￥<var id="totalMoney1"><fmt:formatNumber value="${totalPrice }" pattern="#0.00"/></var></dd>
						<dd class="alg_c"><input id="settleAccountId" type="submit" value="提交订单" class="hand btn136x36a"/></dd>
					</dl>
				</span>
			</div>
	
		</div>
	</div>
	</form>
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>