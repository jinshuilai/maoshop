<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>支付</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
$(function(){
	$('.x4_153x44 li').click(function(){
		$('.x4_153x44').find('a').each(function(){
			$(this).removeClass('here');
		})
		$('.x4_153x44').find('input').each(function(){
			$(this).removeAttr("checked");
		})
		$(this).find('input').prop("checked", true);
		$(this).find('a').addClass('here');
		
	});
});
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc">

				<p class="okMsg">您的订单已成功提交，完成支付后，我们将立即发货！</p>

			<table cellspacing="0" summary="" class="tab tab5">
			<tbody><tr>
			<th>您的订单号</th>
			<td><var class="blue"><b>${order.orderSn }</b></var></td>
			<th>应付现金</th>
			<td><var class="red"><b>￥${order.totalPrice }</b></var>&nbsp;元</td>
			<th>支付方式</th>
			<td>在线支付</td>
			</tr>
			<tr>
			<th>配送方式</th>
			<td>快递</td>
			<th>预计到货时间</th>
			<td><fmt:formatDate value="${order.placeAt }" pattern="yyyy年MM月dd日" /></td>
			<th></th>
			<td></td>
			</tr>
			</tbody></table>
		<form action="${path }/order/pay.do" method="post">
		<input type="hidden" name="oid" value="${order.oid }">
			<dl class="distr">
			<dd>
				<div class="box_d bg_gray2 pb ofc">
					<h3>支持以下网银：</h3>
					<ul class="ul x4_153x44">
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n01" title="中国工商银行">中国工商银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n02" title="中国建设银行">中国建设银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n03" title="招商银行">招商银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n04" title="交通银行">交通银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n05" title="广发银行">广发银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n06" title="中国农业银行">中国农业银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n07" title="中国邮政银行">中国邮政银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n08" title="深圳发展银行">深圳发展银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n09" title="渤海银行">渤海银行</a></li>
						<li><input type="radio" name="bank" value="1"><a href="javascript:void(0);" class="inb n10" title="中信银行">中信银行</a></li>
					</ul>
					<h3>支持以下支付平台：</h3>
					<ul class="ul x4_153x44">
						<li><input type="radio" name="bank" value="zfb"><a href="javascript:void(0);" style="background: url('${path }/images1/zfb.jpg') no-repeat;" title="支付宝">支付宝</a></li>
					</ul>
				</div>
			</dd>
			</dl>

			<div class="page alg_c">
				<input type="submit" value="确认支付" id="payAlertIs" class="hand btn136x36b mr">
			</div>
		</form>
			<dl class="dl_msg">
			<dt>提示：</dt>
			<dd>请您在24小时内完成支付，否则订单会被自动取消。</dd>
			<dd class="mt inb_a"><a href="#" title="查看订单状态" class="blue">查看订单状态&gt;&gt;</a>
			<a href="#" title="查看购物帮助" class="blue">查看购物帮助&gt;&gt;</a>
			<a href="#" title="继续购物" class="blue">继续购物&gt;&gt;</a></dd>
			</dl>

		</div>
		<div class="fl"></div><div class="fr"></div>
	</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>