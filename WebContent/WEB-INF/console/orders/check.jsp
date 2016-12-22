<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<script type="text/javascript">
function send(sn,uid){
	window.location.href = "${path}/orders/deliver.do?sn="+sn+"&uid="+uid;
}
</script>
</head>
<body class="sticky-header">

<section>
    
    <%@include file="../common/left.jsp"%>
    <!-- main content start-->
    <div class="main-content" >
	<%@include file="../common/header.jsp" %>
        
		 <!-- page heading start-->
        <div class="page-heading">
            	<ul class="breadcrumb">
                <li>
                   商品管理
                </li>
                <li>
                  发货
                </li>
                <li>
                商品确认
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
             <div class="row">
        <div class="col-sm-12">
        <section class="panel">
            <header class="panel-heading">
				订单编号：${order.orderSn }
            </header>
            <div class="panel-body">
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品规格</th>
                            <th>购买数量</th>
                                               
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${order.itemList }" var="item">
                        <tr>
                            <td>${item.productId }</td>
                            <td><img alt="img" src="${filePath }${item.img }"  height="90" width="160"/>
                            <a href="${path}/item/seeProduct.do?pId=${item.productId}">${item.proName }</a></td>
                            <td>${item.skuSpec }</td>
                            <td>${item.quantity }</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
                <div class="col-lg-12">
                <p></p>
                    <p>收货人信息</p>
                    <ul>
                    	<li>收货人姓名：${addr.consignee }</li>
                    	<li>联系电话：${addr.phone }</li>
                    	<li>邮政编码：${addr.postcode }</li>
                    	<li>收货地址：${addr.province }省${addr.city }市${addr.district }&nbsp;${addr.address }</li>
                    </ul>
                   </div>
            	<div class="col-lg-12">
					<button id="submitBut" type="button" class="btn btn-primary" onclick="send('${order.orderSn}','${order.custId }')">发货</button>
					<span style="margin-left: 100px">
						<button class="btn btn-info" type="button" onclick="history.go(-1)">返回</button>
					</span>
				</div>
               
            </div>
            </section>
             
            </div>
         </div>
            
        </div>
        <!--body wrapper end-->
        
		<!--footer section start-->
        <footer class="sticky-footer">
            2016 &copy; MaoShop
        </footer>
        <!--footer section end-->
    </div>
    <!-- main content end-->
</section>
 
 <%@include file="../common/js.jsp" %>

</body>
</html>