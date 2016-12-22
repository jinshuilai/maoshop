<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<!--  data table -->
<link rel="stylesheet" href="${path }/js/data-tables/DT_bootstrap.css" />
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
                   一级分类
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
                    分类列表
                    <span class="tools pull-right">
                        <a href="javascript:;" class="fa fa-chevron-down"></a>
                        <a href="javascript:;" class="fa fa-times"></a>
                     </span>
                </header>
                <div class="panel-body">
                <div class="adv-table editable-table ">
                <div class="clearfix">
                    <div class="btn-group">
                        <button id="editable-sample_new" class="btn btn-primary">
                            添加 <i class="fa fa-plus"></i>
                        </button>
                    </div>
                    <div class="btn-group pull-right">
                       
                    </div>
                </div>
                <div class="space15"></div>
                <table class="table table-striped table-hover table-bordered" id="editable-sample">
                <thead>
                <tr>
                    <th>分类名称</th>
                    <th>备注</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cateList}" var="cat">
                <tr class="" cid="${cat.id }">
                    <td>${cat.name }</td>
                    <td>${cat.icon }</td>
                    <td><a class="edit" href="javascript:;">编辑</a></td>
                    <td><a class="delete" href="javascript:;">删除</a></td>
                </tr>
              </c:forEach>
                </tbody>
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
<script src="${path}/js/editable-table.js"></script>

<!-- END JAVASCRIPTS -->
<script>
    jQuery(document).ready(function() {
        EditableTable.init();
    });
</script>
</body>
</html>