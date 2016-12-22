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
        	<form class="form-horizontal" id="form111" method="post" action="${path }/item/updateProduct.do">
        	<input type="hidden" name="proId" value="${info.proId }"> 
           <div class="row">
				<div class="col-lg-12">
					<section class="panel"> <header class="panel-heading">
					商品基本信息 </header>
					<div class="panel-body">
					
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">归属分类</label>
								  <div class="col-md-6 col-sm-6">
								<input type="text" class="form-control" value="${info.cateName }" >
								<input type="hidden" name="catId" class="form-control" value="${info.catId }" >
								</div>
							</div>
							<div class="form-group">
                             <label class="col-md-2 col-sm-2 control-label">商品品牌</label>
                             <div class="col-md-6 col-sm-6">
                                 <select id="bid" class="btn btn-default">
							<option value="">==请选择==</option>
							<c:forEach items="${charList }" var="cha">
							<option value="${cha }">${cha }</option>
							</c:forEach>
							</select>&nbsp;排序&nbsp;&nbsp;
			
							<select id="bnid" class="btn btn-default" name="brandId">
			
							<option value="">==请选择==</option>
							<c:forEach items="${brandList }" var="brand">
							<option value="${brand.id }" <c:if test="${brand.id == info.brandId }">selected='selected'</c:if>>${brand.name }</option>
							</c:forEach>
							</select>&nbsp;品牌名称
                              </div>
                          </div>
						
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">商品名称</label>
								  <div class="col-md-6 col-sm-6">
								<input name="pname" type="text" class="form-control" value="${info.pname }" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">标签</label>
								  <div class="col-md-6 col-sm-6">
								<input name="tag" type="text" class="form-control" value="${info.tag }" >
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label">促销语</label>
								  <div class="col-md-6 col-sm-6">
								<textarea name="shortDesc" rows="4" class="form-control">${info.shortDesc } </textarea>
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
								<img id="logo${imgs.sortOrder}" alt="logo" src="${filePath}${imgs.filepath}" height="150" width="200" />
								<input type="file" id="path" name="fileNames" onchange="submitUpload('${imgs.sortOrder}')"> 
								 <input type="hidden" id="lastPath${imgs.sortOrder}" name="lastPath${imgs.sortOrder}"> 
								<input type="hidden" id="imgs${imgs.sortOrder}" name="filePath${imgs.sortOrder}" value="${imgs.filepath}" /> 
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
					
					  <c:if test="${fn:length(commList) == 0}">
    						<p><label></label>无属性</p>
    						</c:if>
    						 <c:forEach items="${commList }" var="feature">               
                        	
                        	<div class="form-group">
								<label class="col-lg-2 col-sm-2 control-label" style="padding-top:0">${feature.attrName }:</label>
								<div class="col-lg-6">
								<c:if test="${feature.attrType == 0 }">
									<select name="${feature.paId }">
									<option value="">请选择</option>
										<c:forEach items="${feature.optionValues }" var="val">
										<option value="${val }" <c:if test="${val == feature.currentValue }">selected='selected'</c:if> >${val }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${feature.attrType == 1 }">
									<c:forEach items="${feature.optionValues }" var="val">
									<input type="radio" name="${feature.paId }" value="${val }" <c:if test="${val == feature.currentValue }">checked='checked'</c:if>  >&nbsp;${val }&nbsp;
									</c:forEach>
								</c:if>
								<c:if test="${feature.attrType == 2 }">
									<c:forEach items="${feature.optionValues }" var="val">
									<input type="checkbox" name="${feature.paId }" value="${val }" <c:forEach items="${feature.currentValue }" var="cur"><c:if test="${cur == val}">checked='true'</c:if> </c:forEach> >&nbsp;${val }&nbsp;
									</c:forEach>
								</c:if>
								<c:if test="${feature.attrType == 3 }">
									<input type="text" name="${feature.paId }" value="${feature.optionValues }">&nbsp;${val }&nbsp;
								</c:if>	
								</div>
							</div>
							</c:forEach>
					</section>
					
					<section class="panel"> <header class="panel-heading">
					商品规格 </header>
					<div class="panel-body">
							<div id="sp_0" style="display:none;">
							 <table class="table table-bordered table-striped table-condensed">
            <c:if test="${fn:length(specList) == 0}">
            <tr><th colspan="2" class="gray b">&nbsp;&nbsp;<b>默认</b></th></tr>
            </c:if>
            <c:forEach items="${specList }" var="feature">
            	<tr>
            		<td>${feature.attrName }</td>
            		<td>
            			<c:forEach items="${feature.optionValues }" var="val">
            				<input type="radio" name="${feature.paId }specradio0" value="${val }">&nbsp;${val }&nbsp;
            			</c:forEach>
            		</td>
            	</tr>
            </c:forEach>
         
            <tr><td colspan="2" style="padding:0">
                 <table class="table" style="margin-bottom:0">
                    <tr>
                        <th>商城价</th>
                        <th>市场价</th>
                        <th>库存</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    <tr>                        
      
                        <td><samp class="red">*</samp> <input reg1="^[0-9]{1,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字" type="text" id="shopPrice" name="shopPrice0" onblur="changePri(this)"/></td>
                        <td><input type="text" id="marketPrice" name="marketPrice0" class="text20" reg1="^[0-9]{0,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字"  onblur="changePri(this)"/></td>
                        <td><samp class="red">*</samp><input reg1="^(0|[1-9][0-9]{0,4})$" desc="5个字符以内非负整数" type="text" id="stock" name="stock0"  /></td>
                        <td><input type="text" id="skuName" name="skuName0" class="text20" reg1="^[a-zA-Z0-9_\u4e00-\u9fa5]{0,50}$" desc="50个字符以内" /></td>
                        <td><input type="button" value="-删除" class="btn btn-danger" onclick="clickRemove('#sp_0')"/></td>
                    </tr>
                </table>
            </td></tr>
        </table>
			</div>
							
		<c:forEach items="${info.skuList }" var="sku" varStatus="st">
		<div id="sp_${st.index + 1 }" >
        <table class="table table-bordered table-striped table-condensed">
            <c:if test="${fn:length(info.skuList) == 0}">
            <tr><th colspan="2" class="gray b">&nbsp;&nbsp;<b>默认</b></th></tr>
            </c:if>
            	
            	
            	 <%--  <c:forEach items="${sku.specList }" var="spec">
            		${spec.attrvalue }
            		</c:forEach> --%>
            <c:forEach items="${specList }" var="feature">
            	<tr>
        		<td>${feature.attrName }</td>
        		<td>
        			<c:forEach items="${feature.optionValues }" var="val">
        				<input type="radio" name="${feature.paId }specradio${st.index + 1 }" value="${val }" 
        				<c:forEach items="${sku.specList }" var="spec">
            		    <c:if test="${val == spec.attrvalue }">checked='checked'</c:if>
            		    </c:forEach>
        				>&nbsp;${val }&nbsp;
        			</c:forEach>
        		</td>
          		</tr>
            </c:forEach>
            	
            	
         
            <tr><td colspan="2" style="padding:0">
                 <table class="table" style="margin-bottom:0">
                    <tr>
                        <th>商城价</th>
                        <th>市场价</th>
                        <th>库存</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    <tr>                        
      
                        <td><samp class="red">*</samp> <input reg1="^[0-9]{1,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字" type="text" 
                        id="shopPrice" name="shopPrice${st.index + 1 }" onblur="changePri(this)" value="${sku.shopPrice }" /></td>
                        <td><input type="text" id="marketPrice" name="marketPrice${st.index + 1 }" class="text20" reg1="^[0-9]{0,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字" 
                         onblur="changePri(this)"  value="${sku.marketPrice }" /></td>
                        <td><samp class="red">*</samp><input reg1="^(0|[1-9][0-9]{0,4})$" desc="5个字符以内非负整数" type="text" 
                        id="stock" name="stock${st.index + 1 }" value="${sku.stock }" /></td>
                        <td><input type="text" id="skuName" name="skuName${st.index + 1 }" class="text20" reg1="^[a-zA-Z0-9_\u4e00-\u9fa5]{0,50}$" desc="50个字符以内" value="${sku.skuName }" /></td>
                        <td><input type="button" value="-删除" class="btn btn-danger" onclick="clickRemove('#sp_${st.index + 1 }')"/></td>
                    </tr>
                </table>
            </td></tr>
        </table>
        </div>
	</c:forEach>
    
    <c:if test="${fn:length(specList) != 0}">
        <div id="page_c" style="padding:10px"><input  type="hidden" id="divNum" name="divNum" value="${divNum }"/>
        <span><input type="button" value="+增加规格" id="button2" name="button2" class="btn btn-success"/></span></div>
    </c:if>
    <div class="form-group">

			<label for="logo" class="col-lg-2 col-sm-2 control-label"></label>

			<div class="col-lg-6">
				<button id="submitBut" type="submit" class="btn btn-primary">提交</button>
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
     editor.config.uploadImgUrl = '${path}/upload/uploadForEditor.do';
	 editor.create();
	 //编辑器注释 
	 
	//实现页面规格的自动增加和删除
   	var divNum = $("#divNum").val();
   		$("#button2").click(function(){
   			//把div编号++
   			divNum++;
   			//获得sp_0的div的内部代码
   			var htmlDiv = $("#sp_0").html();
   			//拼接div本身
   			htmlDiv = "<div id='sp_"+divNum+"'>"+htmlDiv+"</div>";
   			
   			htmlDiv = htmlDiv.replace(/specradio0/g, "specradio"+divNum);                 
  
   			htmlDiv = htmlDiv.replace(/shopPrice0/g, "shopPrice"+divNum);
   			htmlDiv = htmlDiv.replace(/marketPrice0/g, "marketPrice"+divNum);
   			htmlDiv = htmlDiv.replace(/stock0/g, "stock"+divNum);
   			htmlDiv = htmlDiv.replace(/skuName0/g, "skuName"+divNum);
   			htmlDiv = htmlDiv.replace(/#sp_0/g, "#sp_"+divNum);
   			//把获得到的html的代码追加到最后一个div的前面
   			$("#page_c").before(htmlDiv);
   			$("#divNum").val(divNum);
   			
   			//alert(htmlDiv)
   		});
   		
   		//商品规格的redio选中与取消
   	    $("#sp_0").find("input[type=radio]").live("dblclick",function(){
   	    	if($(this).attr('checked') == 'checked'){
   	    		$(this).removeAttr('checked');
   	    	}else{
   	    		$(this).attr('checked','checked');
   	    	}
   	    });
   		
   	 	
   	 $("#form111").submit(function(){
   		
			$.myloading({title : '正在提交，请稍等...'});
			return true;
   	 });
   	 
   	$("#bid").change(function(){
		var sortChar = $(this).val();
		loadBrand(sortChar);
	});
 });
 
 function changePri(obj){  	
 	var reg0=/^[0-9]{1,7}\.{0,1}[0-9]*$/;
 	var test=obj.value;
 	if(!reg0.test(test)){
 		return;
 	}
 	var test1=test.indexOf(".");
 	var firstSub=test.substring(0,test1);
 	var lastSub=test.substring(test1+1,test.length);
 	if(lastSub.length >= 3) {
 		lastSub=lastSub.substring(0, 2); 
 	}
 	if(lastSub.length==1){
 		lastSub=lastSub+'0';
 	}
 	if(lastSub.length==0){
 		lastSub='00';
 		}
 	if(test1==-1){
 		obj.value=test+".00";
 	}
 	else{
 		obj.value=firstSub+'.'+lastSub;
 	}
 }

 function loadBrand(sortChar){
		$.ajax({
			url:"${path}/item/loadBrand.do",
			type:"post",
			dataType:"text",
			data:{
				sortChar:sortChar
			},
			success:function(responseText){
				//清空要追加的select
				$("#bnid").empty();
				//把请选择的option给加回来
				$("#bnid").append("<option value=''>==请选择==</option>");

				//把json字符串转换成json对象
				var jsonObj = $.parseJSON(responseText);
				for(var i = 0; i < jsonObj.bList.length; i++){
					var opt = $("<option value='"+jsonObj.bList[i].id+"'>"+jsonObj.bList[i].name+"</option>");
					//追加option
					$("#bnid").append(opt);
				}
			},
			error:function(){
				alert("系统错误");
			}
		});
	}
 
 function clickRemove(id){
 	if(id == "#sp_0"){
 		alert("默认的最小销售单元不能删除");
 		return;
 	}
 	$(id).remove();
 }
 
 function submitUpload(id){
 	var option = {
 		url:"${path}/upload/uploadPics.do?picNum="+id, 
 		dataType:"text",
 		success:function(responseText){
 			//把json格式字符串转换成json对象
 			var jsonObj = $.parseJSON(responseText);
 			$("#logo"+id).attr("src", jsonObj.realPath);
 			$("#imgs"+id).val(jsonObj.relativePath);
 			$("#lastPath"+id).val(jsonObj.realPath);
 		},
 		error:function(){
 			alert("系统错误");
 		}
 		
 	};
 	$("#form111").ajaxSubmit(option);
 }
 </script>
</body>
</html>