<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<script type="text/javascript">
function back(){
	window.location.href = "${path}/category/secondShow.do";
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
                <li>
                添加二级分类
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
         <div class="row">
        <div class="col-lg-12">
            <section class="panel">
            <div class="panel-body">
                <form id="form1" class="form-horizontal adminex-form" method="post" action="">
                    <div class="form-group">
						<input type="hidden" id="csid" name="id" value="${cateSecond.id }">
                        <div class="col-lg-10">
                     <p>   选择所属的一级分类：</p>
                        	<select class="form-control input-lg m-bot15" name="parentId">
                                <c:forEach items="${cateList}" var="cat">
                                <option value="${cat.id }" <c:if test="${ cat.id == cateSecond.parentId }">selected='selected'</c:if> >
                                ${cat.name }</option>
                                </c:forEach>
                            </select>
                        
         <p> 填写二级分类名称：</p>
         <input class="form-control input-lg m-bot15" type="text" name="name" value="${cateSecond.name }">
		<br>
		<span style="margin-left:100px">
		 <button id="submitBut" type="button" class="btn btn-primary">提交</button> 
		 </span>
		 <span style="margin-left:100px">
		 <button class="btn btn-info" type="button" onclick="back()">返回</button>
         </span>             
                            
                        </div>
                    </div>
                </form>
            </div>
        </section>
        </div></div>
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
 	$(function(){
 		$("#submitBut").click(function() {  
 		    var url = "#";
 		    if($("#csid").val() != ""){
 		    	url = "${path}/category/modifyCs.do";
 		    } else{
 		    	url = "${path}/category/addCs.do"
 		    }
 		    $("#form1").attr("action", url);  
 		    $("#form1").submit(); 
 		});
 	});
 </script>
</body>
</html>