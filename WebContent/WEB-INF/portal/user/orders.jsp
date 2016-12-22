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
<style>
	tbody:before {
	 line-height:2em;
	 content:"-";
	 color:white; /* to hide text */
	 display:block;
	}
	
</style>
<script type="text/javascript">
function pay(sn){
	window.location.href = "${path}/order/toPay.do?sn="+sn;
}
function receipt(sn){
	if (confirm("确定收货 ?收货后钱款将打给卖家，请确保您已经收到货物") == false) {
        return;
    }
	window.location.href = "${path}/user/home/receipt.do?sn="+sn;
}
function alls(){
	window.location.href = "${path}/user/home/toOrder.do";
}
function cha(id){
	window.location.href = "${path}/user/home/toOrder.do?s="+id;
}
function feedback(id){
	window.location.href = "${path}/user/home/toFeedback.do?id="+id;
}
function detail(id){
	window.open("${path}/user/home/orderDetail.do?id="+id);
}
function goPage(page){
	window.location.href = "${path}/user/home/toOrder.do?s=${stat}&page="+page;
}
</script>
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
							<p><a href="${path }/user/home/toOrder.do" style="color:red;">我的订单</a></p>
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
							<p><a href="${path }/user/home/toShipAddr.do">收货地址</a></p>
							<p><a href="#" target="_blank">修改密码</a></p>
						</dd>
					</dl>
				</div>
				<!--左边列表导航  结束-->


				<div class="fr vip_right vip_magRight">
			
					<div class="cus01">
						
		<div class="ofc pb40">

			<div class="pagebar">
				<ul>
					<li><a <c:if test="${stat == null }">class='acts'</c:if> href="javascript:void(0);" onclick="alls()">全部订单</a></li>
					<li><a <c:if test="${stat == 0 }">class='acts'</c:if> href="javascript:void(0);" onclick="cha(0)">待付款</a></li>
					<li><a <c:if test="${stat == 2 }">class='acts'</c:if> href="javascript:void(0);" onclick="cha(2)">待收货</a></li>
					<li><a <c:if test="${stat == 3 }">class='acts'</c:if> href="javascript:void(0);" onclick="cha(3)">待评价</a></li>
				</ul>
				
			</div>
			<table  class="table table-striped table-bordered" cellspacing="0" style="font-size:14px;">
			<c:forEach items="${pageBean.dataList }" var="order">
			<tbody>
				<tr>
					<td colspan="5">
					<span><fmt:formatDate value="${order.placeAt }" pattern="yyyy年MM月dd日HH时mm分" /></span>
					<span style="margin-left:100px;">订单编号:${order.orderSn }</span>
					<td>
				</tr>
				<c:forEach items="${order.itemList }" var="item" varStatus="st">
				<tr>
					<td>
					<img alt="" src="${filePath }${item.img}" width="60px" height="60px">
					<a href="${path }/product/detail.do?id=${item.productId}" target="_blank">${item.proName }</a> 
					<a href="${path }/user/home/snapshot.do?id=${item.snapId}" target="_blank">[交易快照]</a>
							
					</td>
					<td rowspan="1" width="15%">
							${item.skuSpec }
					</td>
					<td rowspan="1" width="6%">
							${item.quantity }
					</td>
					<td rowspan="1" width="10%">￥${item.quantity * item.price}<br/>
					<c:if test="${order.status == 4 }">
					<br/><a href="${path }/user/home/seeFeedback.do?id=${item.itemId}">查看评价</a>
					</c:if>
					</td>
					<c:if test="${st.index == 0 }">
					<td rowspan="${fn:length(order.itemList)}" width="10%">
						<a href="javascript:void(0);" onclick="detail(${order.oid})">订单详情</a>
					</td>
					<td rowspan="${fn:length(order.itemList)}" width="15%">
					<c:choose>
					<c:when test="${order.status == 0 }">
					<input type="button" value="付款" onclick="pay('${order.orderSn}')"><br/>
					<input type="button" value="取消订单" >
					
					</c:when>
					<c:when test="${order.status == 1 }">
					买家已付款<br/>卖家未发货
					</c:when>
					<c:when test="${order.status == 2 }">
					<input type="button" value="确定收货" onclick="receipt('${order.orderSn}')">
					</c:when>
					<c:when test="${order.status == 3 }">
					<input type="button" value="评价" onclick="feedback('${order.oid}')"><br/>
					<font color="#aaa">订单已完成</font>
					</c:when>
					<c:otherwise><font color="#aaa">订单已完成</font></c:otherwise>
					</c:choose>
					
					</td>
					</c:if>
				</tr> 
				</c:forEach>       
				
			</tbody>
			</c:forEach>
			</table>
					</div>
					<div class="mens-toolbar">
			   <div class="sort" >
                	第${pageBean.pageNum}页/共${pageBean.totalPage}页，${pageBean.totalCount}条记录</div>
	        <div class="pager">   
	       		
            	<ul style="float: right;font-size:12px;">
            	<c:if test="${pageBean.pageNum != 1 }">
            	<li><a href="#" onclick="goPage('1')">首页</a></li>
              	<li><a href="#" onclick="goPage('${pageBean.pageNum - 1 }')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li <c:if test="${pageBean.pageNum == i }">class='active'</c:if>><a href="#" onclick="goPage('${i}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${ pageBean.pageNum != pageBean.totalPage && pageBean.totalPage != 0 }">
                    <li><a href="#" onclick="goPage('${pageBean.pageNum + 1 }')" >»</a></li>
                    <li><a href="#" onclick="goPage('${pageBean.totalPage }')">尾页</a></li>
                    </c:if>
                    </ul>
                 
	    	</div>
     	    <div class="clearfix"></div>
	     </div>
		<div class="fl"></div><div class="fr"></div>
	</div>
			    	</div>
				</div>
      </div>
		<!-- 内容  结束-->
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>