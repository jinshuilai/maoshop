<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<!--  data table -->
 <link href="${path }/js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
  <link href="${path }/js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="${path }/js/data-tables/DT_bootstrap.css" />
<script type="text/javascript">
function delcs(csid){
	if (confirm("确定删除此记录 ?") == false) {
        return;
    }
	window.location.href = "${path}/category/delCs.do?csid="+csid;
}

function editcs(csid){
	window.location.href = "${path}/category/toEditcs.do?csid="+csid;
}

function addcs(){
	window.location.href = "${path}/category/toAddcs.do";
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
                   分类管理
                </li>
                <li>
                   二级分类
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
            <button class="btn btn-info" type="button" onclick="addcs()">添加</button>
            <span class="tools pull-right">
                <a href="javascript:;" class="fa fa-chevron-down"></a>
                <a href="javascript:;" class="fa fa-times"></a>
             </span>
        </header>
        <div class="panel-body">
        <div class="adv-table">
        <table  class="display table table-bordered table-striped" id="dynamic-table">
        <thead>
        <tr>
        <th>排序</th>
            <th>所属一级分类</th>
            <th>二级分类名称</th>
            <th class="hidden-phone">修改</th>
            <th class="hidden-phone">删除</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${csList }" var="cs">
        <tr class="gradeX">
        	<td>${cs.id }</td>
            <td>${cs.parent }</td>
            <td>${cs.name }</td>
            <td class="center hidden-phone"><a href="#" onclick="editcs('${cs.id}')">修改</a></td>
            <td class="center hidden-phone"><a href="#" onclick="delcs('${cs.id}')">删除</a></td>
        </tr>
        </c:forEach>
        </table>
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
 <!--data table-->
<script type="text/javascript" src="${path }/js/data-tables/jquery.dataTables.js"></script>
<script type="text/javascript" src="${path }/js/data-tables/DT_bootstrap.js"></script>

<!--script for editable table-->
<script type="text/javascript">var path = "${path}";</script>
<script src="${path}/js/dynamic_table_init.js"></script>

</script>
</body>
</html>