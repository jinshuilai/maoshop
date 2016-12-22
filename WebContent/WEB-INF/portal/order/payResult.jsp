<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>支付结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="confirm">
		<div class="tl"></div><div class="tr"></div>
		<div class="ofc">

			<c:choose>
			<c:when test="${result == 'error1' }">
			<p class="failMsg">数据校验错误</p>
			</c:when>
			<c:when test="${result == 'error2' }">
			<p class="failMsg">支付失败</p>
			</c:when>
			<c:otherwise>
			<p class="okMsg">支付成功！</p>
			</c:otherwise>
			</c:choose>
			
			<table cellspacing="0" summary="" class="tab tab5">
			<tbody><tr>
			<th>订单号</th>
			<td><var class="blue"><b>${sn }</b></var></td>
			<th>已付现金</th>
			<td><var class="red"><b>￥${price }</b></var>&nbsp;元</td>
			<th>支付方式</th>
			<td>在线支付</td>
			</tr>
			<tr>
			<th></th>
			<td></td>
			</tr>
			</tbody></table>

		</div>
		<div class="fl"></div><div class="fr"></div>
	</div>

<%@include file="../common/footer.jsp" %>
</body>
</html>