<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
	<div class="wrap">
			<div class="vip_cont c100 clearfix">
				<!--左边列表导航  开始-->
				<div class="fl vip_left vip_magLeft">
					<dl>
						<dt>订单中心</dt>
						<dd>
							<p><a href="#" target="_blank">我的订单</a></p>
							<p><a href="#" target="_blank">我的预售</a></p>
							<p><a href="#" target="_blank">评价晒单</a></p>
							<p><a href="#" target="_blank">取消订单记录</a></p>
						</dd>
					</dl>
					<dl>
						<dt>关注中心</dt>
						<dd>
							<p><a href="#" target="_blank">关注的商品</a></p>
							<p><a href="#" target="_blank">关注的店铺</a></p>
							<p><a href="#" target="_blank">关注的品牌</a></p>
							<p><a href="#" target="_blank">浏览历史</a></p>
						</dd>
					</dl>
					<dl>
						<dt>我的账号</dt>
						<dd>
							<p><a href="#" target="_blank">基本资料</a></p>
							<p><a href="#" target="_blank">收货地址</a></p>
							<p><a href="#" target="_blank">修改密码</a></p>
						</dd>
					</dl>
				</div>
				<!--左边列表导航  结束-->

				<!--右边列表内容  开始-->
				<div class="fr vip_right vip_magRight">
					<!--用户信息  开始 -->
					<!--用户信息  开始 -->
					<div class="cus01">
						<div class="cusImg">
							<img src="${path }/images/photos/user-avatar.png" width="127px" height="127px" title="更换头像">
						</div>
						<div class="cusName">
							<p title="">用户名称</p>
							<span title="">金牌会员</span>
							<span class="bdTell">账号绑定<i></i><em>187****3765</em></span>
						</div>
						
					</div>
					<ul class="cus02">
						<li>
							<p><span>待付款</span><a href="#" target="_blank">去付款</a></p>
							<span><font>36</font></span>
						</li>
						<li>
							<p><span>待收货</span><a href="#" target="_blank">去收货</a></p>
							<span><font>36</font></span>
						</li>
						<li>
							<p><span>待评价</span><a href="#" target="_blank">去评价</a></p>
							<span><font>36</font></span>
						</li>
					</ul>
					<!-- 用户信息  结束 -->
				<!--右边列表内容  结束-->
			    </div>
		</div>
      </div>
		<!-- 内容  结束-->
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>