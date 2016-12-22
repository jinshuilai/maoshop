<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<script type="text/javascript">
function add(){
	window.location.href = "${path}/item/toAddBrand.do";
}
function goPage(page){
	window.location.href = "${path}/item/brand.do?pageNum="+page;
}
function del(bid){
	if (confirm("确定删除此记录 ?") == false) {
        return;
    }
	window.location.href = "${path}/item/delBrand.do?bid="+bid;
}
function modify(bid){
	window.location.href = "${path}/item/toEditBrand.do?bid="+bid;
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
                   商品品牌
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
                		 <button class="btn btn-info" onclick="add()">添加</button>
            </header>
            <div class="panel-body">
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>品牌名称</th>
                            <th>品牌logo</th>
                            <th>品牌官网</th>
                            <th>排序字段</th>
                            <th>品牌简介</th>
                            <th>操作</th>                      
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.dataList }" var="brand">
                        <tr>
                            <td>${brand.name }</td>
                            <td width="162"><img src="${filePath }${brand.logoPath }" height="90" width="160"></td>
                            <td>${brand.website }</td>
                            <td>${brand.sortOrder }</td>
                            <td><textarea rows="4" class="form-control" readonly="readonly">${brand.desct}</textarea></td>
                            <td>
                            <a href="#" onclick="modify('${brand.id}')">修改</a>|
                            <a href="#" onclick="del('${brand.id}')">删除</a>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            	
                <div >
                	第${pageBean.pageNum}页/共${pageBean.totalPage}页，${pageBean.totalCount}条记录
            	<ul class="pagination" style="float: right;">
            	<c:if test="${pageBean.pageNum != 1 }">
            	<li><a href="#" onclick="goPage('1')">首页</a></li>
              	<li><a href="#" onclick="goPage('${pageBean.pageNum - 1 }')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li <c:if test="${pageBean.pageNum == i }">class='active'</c:if>><a href="#" onclick="goPage('${i}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${pageBean.pageNum != pageBean.totalPage }">
                    <li><a href="#" onclick="goPage('${pageBean.pageNum + 1 }')" >»</a></li>
                    <li><a href="#" onclick="goPage('${pageBean.totalPage }')">尾页</a></li>
                    </c:if>
                    </ul>
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