<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp"%>
<link href="${path }/css/mydialog.css" rel="stylesheet" />
</head>
<body class="sticky-header">

	<section> <%@include file="../common/left.jsp"%>

	<!-- main content start-->
	<div class="main-content">
		<%@include file="../common/header.jsp"%>

		<!-- page heading start-->
		<div class="page-heading">
			<ul class="breadcrumb">
				<li>商品管理</li>
				<li>商品属性</li>
				<li>添加属性</li>
			</ul>
		</div>
		<!-- page heading end-->

		<!--body wrapper start-->
		<div class="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<section class="panel"> <header class="panel-heading">
					添加品牌属性 </header>
					<div class="panel-body">
						<form class="form-horizontal" id="form111" method="post" action="">
						<input type="hidden" id="attrid" name="paId" value="${attr.paId }">
						<input type="hidden" id="attrname" value="${attr.attrName }">
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">*归属分类</label>
								<div class="col-lg-6">
									<select id="catid" class="btn btn-default">
									<option value="-1">==请选择==</option>
									<c:forEach items="${catList }" var="cat">
										<option value="${cat.id }" <c:if test="${cat.id == attr.catId }">selected='selected'</c:if>>${cat.name }</option>
										</c:forEach>
									</select>&nbsp;大类&nbsp;&nbsp;
									
									<select id="cateid" class="btn btn-default" name="cateId">
									
										<option value="-1">==请选择==</option>
										<c:forEach items="${cateList }" var="cate">
										<option value="${cate.id }" <c:if test="${cate.id == attr.cateId }">selected='selected'</c:if>>${cate.name }</option>
										</c:forEach>
									</select>&nbsp;小类
									 <span></span>
								</div>
							</div>

							<div class="form-group">

								<label class="col-lg-2 col-sm-2 control-label">*属性名称</label>
								<div class="col-lg-6">
									<input id="attrName" name="attrName" type="text" class="form-control"
									     value="${attr.attrName }"> <span></span>
								</div>
							</div>
							<div class="form-group">

								<label class="col-lg-2 col-sm-2 control-label">*录入方式</label>
								<div class="col-lg-6">      
								<div class="square ">
                                <div class="radio ">
                                    <input id="radio1" type="radio" name="attrType" value="0" checked="checked">
                                    <label for="radio1">下拉列表</label>
                                </div>
                                <div class="radio ">
                                    <input id="radio2" type="radio" name="attrType" value="1" <c:if test="${attr.attrType == 1 }">checked='checked'</c:if>>
                                    <label for="radio2">单选按钮</label>
                                   
                                </div>
                                <div class="radio ">
                                    <input id="radio3" type="radio" name="attrType" value="2" <c:if test="${attr.attrType == 2 }">checked='checked'</c:if>>
                                    <label for="radio3">复选框</label>
                                   
                                </div>
                                <div class="radio ">
                                    <input id="radio4" type="radio" name="attrType" value="3" <c:if test="${attr.attrType == 3 }">checked='checked'</c:if>>
                                    <label for="radio4">文本框</label>
                                   
                                </div>
                            </div>               
									<span></span>
								</div>
							</div>

							<div class="form-group">

								<label class="col-lg-2 col-sm-2 control-label">*可选属性值</label>
								<div class="col-lg-6">
									<input id="optval" name="optionValues" type="text" class="form-control" value="${attr.optionValues }"
										 placeholder="多个值以英文逗号分隔" >
									<span></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">*最小销售规格</label>
								<div class="col-lg-6">
									<input type="radio" name="isSpec" value="false" checked="checked">否&nbsp;
									<input type="radio" name="isSpec" value="true" <c:if test="${attr.isSpec}">checked='checked'</c:if>>是&nbsp;
								</div>
							</div>
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">*作为筛选条件</label>
								<div class="col-lg-6">
									<input type="radio" name="isSelect" value="false" checked="checked">否&nbsp;
									<input type="radio" name="isSelect" value="true" <c:if test="${attr.isSelect}">checked='checked'</c:if>>是&nbsp;
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
				</div>
			</div>

		</div>
		<!--body wrapper end-->

		<!--footer section start-->
		<footer class="sticky-footer"> 2016 &copy; MaoShop </footer>
		<!--footer section end-->
	</div>
	<!-- main content end--> </section>

	<%@include file="../common/js.jsp"%>
	<script type="text/javascript" src="${path}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${path}/js/mydialog.js"></script>

	<script type="text/javascript">
	$(function(){
		
		$("#form111").submit(function(){
			var isSubmit = true;
			
			var cate = $("#cateid").val();
			if(cate == -1){
				$("#cateid").next("span").html("<font color='red'>二级分类未选择</font>");
				isSubmit = false;
			}else{
				$("#cateid").next("span").html("");
			}
			var attr = $("#attrName").val();
			if(attr == ""){
				$("#attrName").next("span").html("<font color='red'>属性名不能为空</font>");
				isSubmit = false;
			}else{
				$("#attrName").next("span").html("");
				var attrname = $("#attrname").val();
				if(attrname != "" && attrname == attr){
					var result = "yes";
				}else{
					var result = validAttrName(attr,cate);
				}
				if(result == "no"){
					$("#attrName").next("span").html("<font color='red'>该属性已存在</font>");
					isSubmit = false;
				}else{
					$("#attrName").next("span").html("");
				}
				
			}
			var optval = $("#optval").val();
			if(optval == ""){
				$("#optval").next("span").html("<font color='red'>可选值不能为空</font>");
				isSubmit = false;
			}else{
				optval = optval.replace(/\，/g,",");
				$("#optval").val(optval);
				$("#optval").next("span").html("");
			}
			
			if(isSubmit){
				$.myloading({title : '正在提交，请稍等...'});
				var url = "#";
				if ($("#attrid").val() != "") {
					url = "${path}/item/modifyFeature.do";
				} else {
					url = "${path}/item/addFeature.do"
				}
				$("#form111").attr("action", url);
			}
			return isSubmit;
		});
		
		$("#catid").change(function(){
			var parentId = $(this).val();
			loadOption(parentId);
		})
	});
	
	function loadOption(parentId){
		$.ajax({
			url:"${path}/category/loadOption.do",
			type:"post",
			dataType:"text",
			async:false,
			data:{
				parentId:parentId
			},
			success:function(responseText){
				//清空要追加的select
				$("#cateid").empty();
				//把请选择的option给加回来
				$("#cateid").append("<option value='-1'>==请选择==</option>");

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
		})
	}
	
		function validAttrName(attrName,csid) {
			var result = "yes";
			var option = {
				url : "${path}/item/validAttrName.do",
				type : "post",
				dataType : "text",
				async : false,//修改为同步
				data : {
					attrName : attrName,
					csid:csid
				},
				success : function(responseText) {
					result = responseText;
				},
				error : function() {
					alert("系统错误");
				}
			};
			$.ajax(option);
			return result;
		}
		
		function back(){
			window.location.href = "${path}/item/feature.do";
		}
	</script>
</body>
</html>