<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<link href="${path }/js/weditor/css/wangEditor.min.css" rel="stylesheet">
<link href="${path }/css/mydialog.css" rel="stylesheet" />
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
                   <a href="${path }/item/product.do">商品列表</a>
                </li>
                <li>
                   查看商品&修改
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
        	<form class="form-horizontal" id="form111" method="post" action="${path }/item/toEditProduct.do">
           <div class="row">
				<div class="col-lg-12">
					<section class="panel"> <header class="panel-heading">
					商品基本信息 </header>
					<div class="panel-body">
					
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">归属分类</label>
								  <div class="col-md-6 col-sm-6">
								${info.cateName }
								</div>
							</div>
							<div class="form-group">
                             <label class="col-md-2 col-sm-2 control-label">商品品牌</label>
                             <div class="col-md-6 col-sm-6">
                                ${info.brandName }
							
                              </div>
                          </div>
						
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">商品名称</label>
								  <div class="col-md-6 col-sm-6">
								${info.pname }
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">标签</label>
								  <div class="col-md-6 col-sm-6">
								${info.tag }
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">促销语</label>
								  <div class="col-md-6 col-sm-6">
								<textarea readonly="readonly" rows="4" class="form-control">${info.shortDesc } </textarea>
								</div>
							</div>
					</div>
					</section>
					
					<section class="panel"> <header class="panel-heading">
					商品图片和描述 </header>
					<div class="panel-body">
					<c:forEach items="${info.imageList }" var="imgs">
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label">
								<c:choose>
								<c:when test="${imgs.sortOrder == 1 }">主图</c:when>
								<c:otherwise>副图</c:otherwise>
								</c:choose>
								</label>
								<div class="col-lg-6">
								<img alt="logo" src="${filePath}${imgs.filepath}" height="150" width="200" />
								
								</div>
							</div>
					</c:forEach>		
							<div class="form-group">
								<label for="desc" class="col-lg-2 col-sm-2 control-label">商品详情</label>
								<div class="col-lg-8">
									<textarea id="myeditor" name="desctext" style="height:300px;max-height:400px;">${info.desctext } </textarea>
								</div>
							</div>
					</div>
					</section>
					
					<section class="panel"> <header class="panel-heading">
					商品参数 </header>
					<div class="panel-body">
							<c:if test="${fn:length(info.paraList) == 0}">
    						<p><label></label>无属性</p>
    						</c:if>
    						 <c:forEach items="${info.paraList }" var="feature">               
                        	
                        	<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label" style="padding-top:0">${feature.featureName }:</label>
								<div class="col-lg-6">
								${feature.attrValue }
								</div>
								
							</div></c:forEach>
					</div>
					</section>
					
					<section class="panel"> <header class="panel-heading">
					商品规格 </header>
					<div class="panel-body">
							<div id="sp_0">
		<c:forEach items="${info.skuList }" var="sku">
        <table class="table table-bordered table-striped table-condensed">
            <c:if test="${fn:length(info.skuList) == 0}">
            <tr><th colspan="2" class="gray b">&nbsp;&nbsp;<b>默认</b></th></tr>
            </c:if>
            	<tr>
            	<td>
            	  <c:forEach items="${sku.specList }" var="spec">
            		${spec.attrvalue }
            		</c:forEach>
            		</td>
            	</tr>
         
            <tr><td colspan="2" style="padding:0">
                 <table class="table" style="margin-bottom:0">
                    <tr>
                        <th>商城价</th>
                        <th>市场价</th>
                        <th>库存</th>
                        <th>备注</th>
                       
                    </tr>
                    <tr>                        
      
                        <td>
                        ${sku.shopPrice }</td>
                        <td>
                        ${sku.marketPrice }</td>
                        <td>
                        ${sku.stock }</td>
                        <td>${sku.skuName }</td>
                      
                    </tr>
                </table>
            </td></tr>
        </table>
	</c:forEach>
    </div>
    
    <div class="form-group">

			<label for="logo" class="col-lg-2 col-sm-2 control-label"></label>

			<div class="col-lg-6">
				<span style="margin-left: 100px">
					<button class="btn btn-info" type="button" onclick="history.go(-1)">返回</button>
				</span>
			</div>
		</div>
					</div>
					</section>
				</div>
				</div>
		</form>
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
  <script src="${path }/js/weditor/js/wangEditor.min.js"></script>
 <script type="text/javascript" src="${path}/js/jquery.form.js"></script>
 <script type="text/javascript" src="${path}/js/mydialog.js"></script>
 <script type="text/javascript">
 $(function(){
	//编辑器配置
     var editor = new wangEditor('myeditor');
	 editor.create();
	 //编辑器注释 
 });
 </script>
</body>
</html>