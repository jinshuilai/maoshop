<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<script type="text/javascript">
function see(pid){
	window.location.href = "${path}/item/seeProduct.do?pId="+pid;
}
function goPage(page,show){
	window.location.href = "${path}/item/saleProduct.do?showStatus="+ show +"&pageNum="+page;
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
                   商品管理
                </li>
                <li>
                   商品上下架
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
                                <li <c:if test="${show == 0 }">class='active'</c:if> >
                                    <a href="${path }/item/saleProduct.do?showStatus=0">未上架</a>
                                </li>
                                <li <c:if test="${show == 1 }">class='active'</c:if> >
                                    <a href="${path }/item/saleProduct.do?showStatus=1">已上架</a>
                                </li>
                            </ul>
                        </header>
                        <div class="panel-body">
                            <div class="tab-content">
                                <div class="tab-pane active" id="home2">
                                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>所属分类</th>
                            <th>品牌</th>
                            <th>商品名称</th>
                            <th>商品图片</th>
                            <th>标签</th>
                            <th>促销语</th>
                            <th>添加时间</th>
                            <th>操作</th>                      
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.dataList }" var="item">
                        <tr>
                            <td>${item.cateName }</td>
                            <td>${item.brandName }</td>
                            <td width="120">${item.pname }</td>
                            <td width="162"><img alt="img" src="${filePath }${item.imgs }"  height="90" width="160"/></td>
                            <td width="130">${item.tag }</td>
                            <td><textarea rows="4" cols="15" class="form-control" readonly="readonly">${item.shortDesc }</textarea></td>
                            <td width="130"><fmt:formatDate value="${item.onSaleAt }" pattern="yyyy年MM月dd日HH点mm分" /></td>
                            <td>
                            <a href="#" onclick="see('${item.proId}')">查看|</a>
                            <c:if test="${item.showStatus == 0 }">
                              <a href="#" onclick="onsale('${item.proId}')">上架</a>
                            </c:if>
                          	<c:if test="${item.showStatus == 1 }">
                          	 <a href="#" onclick="downsale('${item.proId}')">下架</a>
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
                   	<c:if test="${pageBean.pageNum != pageBean.totalPage }">
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
 <script type="text/javascript">
 function onsale(pid){
	 window.location.href = "${path}/item/changeShowUp.do?pid="+pid;
 }
 function downsale(pid){
	 window.location.href = "${path}/item/changeShowDown.do?pid="+pid;
 }
 </script>
</body>
</html>