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
				<li>商品品牌</li>
				<li>添加品牌</li>
			</ul>
		</div>
		<!-- page heading end-->

		<!--body wrapper start-->
		<div class="wrapper">
			<div class="row">
				<div class="col-lg-12">
					<section class="panel"> <header class="panel-heading">
					添加品牌信息 </header>
					<div class="panel-body">
						<form class="form-horizontal" id="form111" method="post"
							enctype="multipart/form-data" action="">
							<div class="form-group">
								<label for="inputName" class="col-lg-2 col-sm-2 control-label">*品牌名称</label>
								<div class="col-lg-6">
									<input name="name" type="text" class="form-control"
										id="brandName" reg2="^[a-zA-Z0-9\u4e00-\u9fa5]{1,20}$"
										tip="必须是中英文或数字字符，长度1-20" value="${brand.name }"> <span></span>
								</div>
							</div>

							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label"></label>

								<div class="col-lg-6">
										<c:choose>
											<c:when test="${brand.logoPath != null }">
											<img id="logo" alt="logo" src="${filePath }${brand.logoPath}"
										height="150" width="200">
											</c:when>
											<c:otherwise>
											<img id="logo" alt="logo" src="${path }/images/no.png"
										height="150" width="200">
											</c:otherwise>
										</c:choose>
										 <input type="hidden"
										id="lastPath" name="lastPath"> <input type="hidden"
										id="bid" name="id" value="${brand.id }">
										<input type="hidden"
										id="bname" name="bname" value="${brand.name }">
								</div>
							</div>
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label">*品牌logo</label>
								<div class="col-lg-6">
									<input type="file" id="path" name="path"
										onchange='submitUpload()'> <%-- <input type='hidden'
										id='imgs' name='logoPath' value='${brand.logoPath }' reg2="^.+$"
										tip="亲！您忘记上传图片了。" /> <span></span> --%>
								</div>
							</div>
							<div class="form-group">

								<label for="web" class="col-lg-2 col-sm-2 control-label">官方网站</label>
								<div class="col-lg-6">
									<input name="website" type="text" class="form-control" id="web"
										placeholder="http://" tip="请以http://开头" reg1="http:///*" value="${brand.website }">
									<span></span>
								</div>
							</div>

							<div class="form-group">

								<label for="sort" class="col-lg-2 col-sm-2 control-label">*排序(a-z)</label>
								<div class="col-lg-6">
									<input name="sortOrder" type="text" class="form-control" value="${brand.sortOrder }"
										id="sort" placeholder="品牌首字母" reg1="[a-z]" tip="排序只能输入a-z的小写">
									<span></span>
								</div>
							</div>
							<div class="form-group">
								<label for="desc" class="col-lg-2 col-sm-2 control-label">品牌简介</label>
								<div class="col-lg-6">
									<textarea id="desc" name="desct" rows="6" class="form-control">${brand.desct}</textarea>
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
		function submitUpload() {
			var option = {
				url : "${path}/upload/uploadPic.do",
				dataType : "text",
				success : function(responseText) {
					//json字符串转json对象
					var jsonObj = $.parseJSON(responseText);
					$("#logo").attr("src", jsonObj.realPath);
					$("#imgs").val(jsonObj.relativePath);
					$("#lastPath").val(jsonObj.realPath);
				},
				error : function() {
					alert("系统错误");
				}
			};
			$("#form111").ajaxSubmit(option);
		}
		$(function() {
			$("#form111")
					.submit(
							function() {
								var isSubmit = true;
								
								var url = "#";
								if ($("#bid").val() != "") {
									url = "${path}/item/modifyBrand.do";
								} else {
									url = "${path}/item/addBrand.do"
								}
								$("#form111").attr("action", url);

								$(this)
										.find("[reg2]")
										.each(
												function() {
													var val = $(this).val();
													var tip = $(this).attr(
															"tip");
													var regStr = $(this).attr(
															"reg2");
													var reg = new RegExp(regStr);
													if (!reg.test($.trim(val))) {
														isSubmit = false;
														$(this)
																.next("span")
																.html(
																		"<font color='red'>"
																				+ tip
																				+ "</font>");
													} else {
														var inputName = $(this)
																.attr("name");
														if (inputName == "name") {
															var bname = $("#bname").val();
															if(bname != "" && bname == val){
																var result = "yes";
															}else{
																var result = validBrandName(val);
															}
															if (result == "no") {
																isSubmit = false;
																$(this)
																		.next(
																				"span")
																		.html(
																				"<font color='red'>品牌名称已存在</font>");
																return false;
															} else {
																$(this)
																		.next(
																				"span")
																		.html(
																				"");
															}
														} else {
															$(this)
																	.next(
																			"span")
																	.html("");
														}
													}
												});

								$(this).find("[reg1]").each(
										function() {
											var val = $(this).val();
											var tip = $(this).attr("tip");
											var regStr = $(this).attr("reg1");
											var reg = new RegExp(regStr);
											if (val != null
													&& $.trim(val) != ""
													&& !reg.test($.trim(val))) {
												isSubmit = false;
												$(this).next("span").html(
														"<font color='red'>"
																+ tip
																+ "</font>");
											} else {
												$(this).next("span").html("");
											}
										});

								if (isSubmit) {
									$.myloading({
										title : '正在提交，请稍等...'
									});

								}
								return isSubmit;
							});

			$("#form111")
					.find("[reg2]")
					.blur(
							function() {
								var val = $(this).val();
								var tip = $(this).attr("tip");
								var regStr = $(this).attr("reg2");
								var reg = new RegExp(regStr);
								if (!reg.test($.trim(val))) {
									$(this).next("span").html(
											"<font color='red'>" + tip
													+ "</font>");
								} else {
									var inputName = $(this).attr("name");
									if (inputName == "name") {
										var bname = $("#bname").val();
										if(bname != "" && bname == val){
											var result = "yes";
										}else{
											var result = validBrandName(val);
										}
										if (result == "no") {
											$(this)
													.next("span")
													.html(
															"<font color='red'>品牌名称已存在</font>");
										} else {
											$(this).next("span").html("");
										}
									} else {
										$(this).next("span").html("");
									}
								}
							});

			$("#form111").find("[reg1]").blur(
					function() {
						var val = $(this).val();
						var tip = $(this).attr("tip");
						var regStr = $(this).attr("reg1");
						var reg = new RegExp(regStr);
						if (val != null && $.trim(val) != ""
								&& !reg.test($.trim(val))) {
							$(this).next("span").html(
									"<font color='red'>" + tip + "</font>");
						} else {
							$(this).next("span").html("");
						}
					});
		});
		function validBrandName(brandName) {
			var result = "yes";
			var option = {
				url : "${path}/item/validBrandName.do",
				type : "post",
				dataType : "text",
				async : false,//修改为同步
				data : {
					brandName : brandName
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
			window.location.href = "${path}/item/brand.do";
		}
	</script>
</body>
</html>