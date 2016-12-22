<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
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
                   添加商品
                </li>
                <li>
                   选择分类
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
            	<div class="row">
				<div class="col-lg-12">
					<section class="panel"> <header class="panel-heading">
					选择分类，重要！ </header>
					<div class="panel-body">
						<form class="form-horizontal" id="form111" method="post" action="${path }/item/toEditProduct.do">
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">*归属分类</label>
								<div class="col-lg-6">
									<select id="catid" class="btn btn-default">
									<option value="-1">==请选择==</option>
									<c:forEach items="${catList }" var="cat">
										<option value="${cat.id }">${cat.name }</option>
										</c:forEach>
									</select>&nbsp;一级分类&nbsp;&nbsp;
									
									<select id="cateid" class="btn btn-default" name="cateId">
									
										<option value="-1">==请选择==</option>
									</select>&nbsp;二级分类
									 <span></span>
								</div>
							</div>
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label"></label>

								<div class="col-lg-6">
									<button id="submitBut" type="submit" class="btn btn-primary">提交</button>
									<span style="margin-left: 100px">
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
 <script>
    $(function() {   
        
        $("#catid").change(function(){
			var parentId = $(this).val();
			loadOption(parentId);
		});
        
		$("#form111").submit(function(){
			var isSubmit = true;
			if($("#cateid").val == -1){
				$("#cateid").next("span").html("<font color='red'>二级分类不能为空！</font>");
				isSubmit = false;
			}
			return isSubmit;
		});
    });
    
    function loadOption(parentId){
		$.ajax({
			url:"${path}/category/loadOption.do",
			type:"post",
			dataType:"text",
			data:{
				parentId:parentId
			},
			success:function(responseText){
				//清空要追加的select
				$("#cateid").empty();
				//把请选择的option给加回来
				$("#cateid").append("<option value=''>==请选择==</option>");

				//把json字符串转换成json对象
				var jsonObj = $.parseJSON(responseText);
				for(var i = 0; i < jsonObj.csList.length; i++){
					var opt = $("<option value='"+jsonObj.csList[i].id+"'>"+jsonObj.csList[i].name+"</option>");
					//追加option
					$("#cateid").append(opt);
				}
			},
			error:function(){
				alert("系统错误");
			}
		});
	}
    
    function back(){
    	window.location.href = "${path}/item/product.do";
    }
</script>
</body>
</html>