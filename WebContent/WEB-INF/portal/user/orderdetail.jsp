<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<%@include file="../common/taglib.jsp"%>
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>
<style type="text/css">
.order-detail-info{position:relative;_height:120px;height:auto;min-height:120px;padding:6px 0 12px;margin-bottom:30px;border-bottom:1px solid #e0e0e0}
</style>
</head>
<body>
	<%@include file="../common/head.jsp"%>
	<div class="men">
		<div class="wrap">
			<div class="vip_cont c100 clearfix">
				<!--左边列表导航  开始-->
				<div class="fl vip_left vip_magLeft">
					<dl>
						<dt>订单中心</dt>
						<dd>
							<p>
								<a href="${path }/user/home/toOrder.do" style="color: red;">我的订单</a>
							</p>
							<p>
								<a href="#" target="_blank">我的预售</a>
							</p>
							<p>
								<a href="#" target="_blank">评价晒单</a>
							</p>
							<p>
								<a href="#" target="_blank">取消订单记录</a>
							</p>
						</dd>
					</dl>
					<dl>
						<dt>关注中心</dt>
						<dd>
							<p>
								<a href="#" target="_blank">关注的商品</a>
							</p>
							<p>
								<a href="#" target="_blank">关注的店铺</a>
							</p>
							<p>
								<a href="#" target="_blank">关注的品牌</a>
							</p>
							<p>
								<a href="#" target="_blank">浏览历史</a>
							</p>
						</dd>
					</dl>
					<dl>
						<dt>我的账号</dt>
						<dd>
							<p>
								<a href="#" target="_blank">基本资料</a>
							</p>
							<p>
								<a href="${path }/user/home/toShipAddr.do">收货地址</a>
							</p>
							<p>
								<a href="#" target="_blank">修改密码</a>
							</p>
						</dd>
					</dl>
				</div>
				<!--左边列表导航  结束-->


				<div class="fr vip_right vip_magRight">

					<div class="cus01">
						<div>
							<div >
								<h2>订单详情</h2>
								<div>
									<h3>订单号：${order.orderSn }</h3>
								</div>
							</div>
							<div>
								<div>
									
									<div>
										<div>
										<div >
										<table class="info-table">
											<tbody>
												<tr>
													<th>下单时间：</th>
													<td><fmt:formatDate value="${order.placeAt }" pattern="yyyy年MM月dd日HH时mm分" /></td>
												</tr>
												<c:if test="${order.payAt != null}">
												<tr>
													<th>付款时间：</th>
													<td><fmt:formatDate value="${order.payAt }" pattern="yyyy年MM月dd日HH时mm分" /></td>
												</tr>
												</c:if>
												<c:if test="${order.deliverAt != null}">
												<tr>
													<th>发货时间：</th>
													<td><fmt:formatDate value="${order.deliverAt }" pattern="yyyy年MM月dd日HH时mm分" /></td>
												</tr>
												</c:if>
												<c:if test="${order.dealAt != null}">
												<tr>
													<th>完成时间：</th>
													<td><fmt:formatDate value="${order.dealAt }" pattern="yyyy年MM月dd日HH时mm分" /></td>
												</tr>
												</c:if>
											</tbody>
										</table>
										</div>
											<div style="display: block; padding:10px;">
												<p >
													物流公司：<a href="http://www.yundaex.com/" target="_blank">韵达快运(广州)</a>
													运单号：3101167423326
												</p>
											</div>
										</div>
										
									</div>
									<div id="editAddr" class="order-detail-info">

										<h3>收货信息</h3>
										<table class="info-table">
											<tbody>
												<tr>
													<th>姓&nbsp;&nbsp;名：</th>
													<td>${addr.consignee }</td>
												</tr>
												<tr>
													<th>联系电话：</th>
													<td>${addr.phone }</td>
												</tr>
												<tr>
													<th>收货地址：</th>
													<td>${addr.province }省${addr.city }市${addr.district }${addr.address }</td>
												</tr>
											</tbody>
										</table>
										<div class="actions"></div>

									</div>


									<div id="editTime" class="order-detail-info">
										<h3>支付方式及送货时间</h3>
										<table class="info-table">
											<tbody>
												<tr>
													<th>支付方式：</th>
													<td>在线支付</td>
												</tr>
												<tr>
													<th>送货时间：</th>
													<td>不限送货时间</td>
												</tr>
											</tbody>
										</table>
										<div class="actions"></div>
									</div>
									<div class="order-detail-info">
										<h3>发票信息</h3>
										<table class="info-table">
											<tbody>
												<tr>
													<th>发票类型：</th>
													<td>个人电子发票</td>
												</tr>
												<tr>
													<th>发票内容：</th>
													<td>购买商品明细</td>
												</tr>
												<tr>
													<th>发票抬头：</th>
													<td>个人</td>
												</tr>
											</tbody>
										</table>
										<div class="actions"></div>
									</div>
									<div class="order-detail-total">
										<table class="total-table">
											<tbody>
												<tr>
													<th>商品总价：</th>
													<td><span class="num">${order.productPrice }</span>元</td>
												</tr>
												<tr>
													<th>运&nbsp;&nbsp;费：</th>
													<td><span class="num">0</span>元</td>
												</tr>
												<tr>
													<th>订单金额：</th>
													<td><span class="num">${order.totalPrice }</span>元</td>
												</tr>
												<tr>
													<th class="total">实付金额：</th>
													<td class="total"><span class="num">${order.totalPrice }</span>元</td>
												</tr>
											</tbody>
										</table>
									</div>


								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- 内容  结束-->
	</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>