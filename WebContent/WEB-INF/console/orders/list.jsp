<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<script type="text/javascript">
function goPage(page,type){
	window.location.href = "${path}/orders/list.do?type="+ type +"&pageNum="+page;
}
function onsend(oid){
	window.location.href = "${path}/orders/toDeliver.do?id="+oid;
}
</script>
</head>
<body class="sticky-header">

<section>
    <%@include file="../common/left.jsp" %>
    
    <!-- main content start-->
    <div class="main-content" >
	<%@include file="../common/header.jsp" %>
        
		 <!-- page heading start-->
        <div class="page-heading">
            	<ul class="breadcrumb">
                <li>
                   订单管理
                </li>
                <li>
                   发货
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
           <div class="row">
                <div class="col-md-12">
                <section class="panel">
                        <header class="panel-heading custom-tab dark-tab">
                            <ul class="nav nav-tabs">
                                <li <c:if test="${show == 1 }">class='active'</c:if> >
                                    <a href="${path }/orders/show.do?type=1">未处理</a>
                                </li>
                                <li <c:if test="${show == 2 }">class='active'</c:if> >
                                    <a href="${path }/orders/show.do?type=2">已处理</a>
                                </li>
                            </ul>
                        </header>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div class="tab-pane active" id="home2">
                                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>订单编号</th>
                            <th>下单用户</th>
                            <th>支付价格</th>
                            <th>用户留言</th>
                            <th>下单时间</th>
                            <th>支付时间</th>
                            <th>发货时间</th>
                            <th>状态</th>
                            <th>操作</th>                      
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.dataList }" var="item">
                        <tr>
                            <td>${item.orderSn }</td>
                            <td><a href="${item.custId }">查看信息</a></td>
                            <td>${item.totalPrice }</td>
                            <td><textarea rows="4" cols="15" class="form-control" readonly="readonly">${item.custRemark }</textarea></td>
                            <td width="130"><fmt:formatDate value="${item.placeAt }" pattern="yyyy年MM月dd日HH点mm分" /></td>
                            <td width="130"><fmt:formatDate value="${item.payAt }" pattern="yyyy年MM月dd日HH点mm分" /></td>
                            <td width="130"><fmt:formatDate value="${item.deliverAt }" pattern="yyyy年MM月dd日HH点mm分" /></td>
                            <td>${item.status }</td>
                            <td>
                            <c:if test="${item.status == 1 }">
                              <a href="#" onclick="onsend('${item.oid}')">确认商品</a>
                            </c:if>
                            <c:if test="${item.status != 1 }">
                              <a href="#" onclick="see('${item.oid}')">查看</a>
                            </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div >
                	第${pageBean.pageNum}页/共${pageBean.totalPage}页，${pageBean.totalCount}条记录
            	<ul class="pagination" style="float: right;">
            	<c:if test="${pageBean.pageNum != 1 }">
            	<li><a href="#" onclick="goPage('1','${show}')">首页</a></li>
              	<li><a href="#" onclick="goPage('${pageBean.pageNum - 1 }','${show}')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li <c:if test="${pageBean.pageNum == i }">class='active'</c:if>><a href="#" onclick="goPage('${i}','${show}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${pageBean.pageNum != 1 && pageBean.pageNum != pageBean.totalPage }">
                    <li><a href="#" onclick="goPage('${pageBean.pageNum + 1 }','${show}')" >»</a></li>
                    <li><a href="#" onclick="goPage('${pageBean.totalPage }','${show}')">尾页</a></li>
                    </c:if>
                    </ul>
                 </div>
                                </div>
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