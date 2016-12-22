<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="../common/head.jsp" %>
<link href="${path }/css/jquery.stepy.css" rel="stylesheet">
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
                   添加商品
                </li>
                <li>
                   填写商品信息
                </li>
            </ul>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
            	 <div class="row">
                <div class="col-md-12">
                    <h4 class="fw-title">请按照下列步骤添加商品信息</h4>
                    <div class="box-widget">
                        <div class="widget-head clearfix">
                            <div id="top_tabby" class="block-tabby pull-left">
                            </div>
                        </div>
                        <div class="widget-container">
                            <div class="widget-block">
                                <div class="widget-content box-padding">
                                    <form id="stepy_form" class="form-horizontal left-align form-well" method="post" enctype="multipart/form-data" action="">
                                        <fieldset title="第一步">
                                            <legend>填写基本信息</legend>
                                            <div class="form-group">
                                                <label class="col-md-2 col-sm-2 control-label">归属分类</label>
                                                <div class="col-md-6 col-sm-6">
                                                	<input type="hidden" name="catId" value="${cs.id }" >
                                                    <input type="text" class="form-control" value="${cs.name }" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 col-sm-2 control-label">*选择品牌</label>
                                                <div class="col-md-6 col-sm-6">
                                                    <select id="bid" class="btn btn-default">
													<option value="">==请选择==</option>
													<c:forEach items="${charList }" var="cha">
													<option value="${cha }">${cha }</option>
													</c:forEach>
													</select>&nbsp;排序&nbsp;&nbsp;
									
													<select id="bnid" class="btn btn-default" name="brandId">
									
													<option value="">==请选择==</option>
										
													</select>&nbsp;品牌名称
                                                </div>
                                            </div>
                                        <div class="form-group">

											<label class="col-lg-2 col-sm-2 control-label">*商品名称</label>
											<div class="col-lg-6">
											<input name="pname" type="text" class="form-control" value="" >
											</div>
									    </div>
									    <div class="form-group">

											<label class="col-lg-2 col-sm-2 control-label">标签</label>
											<div class="col-lg-6">
											<input name="tag" type="text" class="form-control" value="" >
											</div>
									    </div>
									    <div class="form-group">
											<label for="desc" class="col-lg-2 col-sm-2 control-label">促销语</label>
											<div class="col-lg-6">
											<textarea name="shortDesc" rows="4" class="form-control"></textarea>
											</div>
										</div>
										
                                        </fieldset>
                                        <fieldset title="第二步">
                                        
                                            <legend>添加图片和描述</legend>
                                
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label">*主图</label>
								<div class="col-lg-6">
								<img id="logo1" alt="logo" src="${path }/images/no.png" height="150" width="200" />
								<input type="file" id="path1" name="fileNames" onchange="submitUpload('1')"> 
								 <input type="hidden" id="lastPath1" name="lastPath1"> 
								<input type="hidden" id="imgs1" name="filePath1" /> 
								</div>
							</div>
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label">副图1</label>
								<div class="col-lg-6">
								<img id="logo2" alt="logo" src="${path }/images/no.png" height="150" width="200" />
								<input type="file" id="path2" name="fileNames" onchange="submitUpload('2')"> 
								 <input type="hidden" id="lastPath2" name="lastPath2"> 
								<input type='hidden' id='imgs2' name='filePath2' /> 
								</div>
							</div>
							<div class="form-group">

								<label for="logo" class="col-lg-2 col-sm-2 control-label">副图2</label>
								<div class="col-lg-6">
								<img id="logo3" alt="logo" src="${path }/images/no.png" height="150" width="200" />
								<input type="file" id="path3" name="fileNames" onchange="submitUpload('3')"> 
								 <input type="hidden" id="lastPath3" name="lastPath3"> 
								<input type="hidden" id="imgs3" name="filePath3" /> 
								</div>
							</div>
							<div class="form-group">
								<label for="desc" class="col-lg-2 col-sm-2 control-label">商品详情</label>
								<div class="col-lg-8">
									<textarea id="myeditor" name="desctext" style="height:300px;max-height:400px;"> </textarea>
								</div>
							</div>
							
                                        </fieldset>
                                         <fieldset title="第三步">
                                            <legend>设置商品属性</legend>
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
										<option value="${val }">${val }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${feature.attrType == 1 }">
									<c:forEach items="${feature.optionValues }" var="val">
									<input type="radio" name="${feature.paId }" value="${val }">&nbsp;${val }&nbsp;
									</c:forEach>
								</c:if>
								<c:if test="${feature.attrType == 2 }">
									<c:forEach items="${feature.optionValues }" var="val">
									<input type="checkbox" name="${feature.paId }" value="${val }">&nbsp;${val }&nbsp;
									</c:forEach>
								</c:if>
								<c:if test="${feature.attrType == 3 }">
									<input type="text" name="${feature.paId }" value="${feature.optionValues }">&nbsp;${val }&nbsp;
								</c:if>	
								</div>
							</div>
							</c:forEach>
                                
                                        </fieldset>
                                        <fieldset title="第四步">
                                            <legend>添加销售规格</legend>
         <div id="sp_0">
        <table class="table table-bordered table-striped table-condensed">
            <c:if test="${fn:length(specList) == 0}">
            <tr><th colspan="2" class="gray b">&nbsp;&nbsp;<b>默认</b></th></tr>
            </c:if>
            <c:forEach items="${specList }" var="feature">
            	<tr>
            		<td>${feature.attrName }</td>
            		<td>
            			<c:forEach items="${feature.optionValues }" var="val">
            				<input type="radio" name="${feature.paId }specradio1" value="${val }">&nbsp;${val }&nbsp;
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
      
                        <td><samp class="red">*</samp> <input reg1="^[0-9]{1,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字" type="text" id="shopPrice" name="shopPrice1" onblur="changePri(this)"/></td>
                        <td><input type="text" id="marketPrice" name="marketPrice1" class="text20" reg1="^[0-9]{0,7}\.{0,1}[0-9]{0,2}$" desc="保留2位小数，最多允许9位有效数字"  onblur="changePri(this)"/></td>
                        <td><samp class="red">*</samp><input reg1="^(0|[1-9][0-9]{0,4})$" desc="5个字符以内非负整数" type="text" id="stock" name="stock1"  /></td>
                        <td><input type="text" id="skuName" name="skuName1" class="text20" reg1="^[a-zA-Z0-9_\u4e00-\u9fa5]{0,50}$" desc="50个字符以内" /></td>
                        <td><input type="button" value="-删除" class="btn btn-danger" onclick="clickRemove('#sp_0')"/></td>
                    </tr>
                </table>
            </td></tr>
        </table>

    </div>
    
    <c:if test="${fn:length(specList) != 0}">
        <div id="page_c" style="padding:10px"><input  type="hidden" id="divNum" name="divNum" value="1"/>
        <span><input type="button" value="+增加规格" id="button2" name="button2" class="btn btn-success"/></span></div>
    </c:if>
                                        </fieldset>
                                        <button type="submit" class="finish btn btn-info btn-extend"> 提交</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
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
 <script src="${path }/js/jquery.validate.min.js"></script>
 <script src="${path }/js/jquery.stepy.js"></script>
 <script src="${path }/js/weditor/js/wangEditor.min.js"></script>
 <script type="text/javascript" src="${path}/js/jquery.form.js"></script>
 <script type="text/javascript" src="${path}/js/mydialog.js"></script>
 <script>
    $(function() {   
    	
        $('#stepy_form').stepy({
            backLabel: '上一步',
            nextLabel: '下一步',
            errorImage: true,
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#top_tabby',
            validate: true
        });
        
        $('#stepy_form').validate({
            errorPlacement: function(error, element) {
                $('#stepy_form div.stepy-error').append(error);
            },
            rules: {
                'brandId': 'required',
                'pname': 'required',
                'imgs1': 'required'
            },
            messages: {
                'brandId': {
                    required: '品牌必须选择！'
                },
                'pname': {
                    required: '商品名称必须填写！'
                },
                'imgs1': {
                    required: '主图必须上传！'
                }
            }
        });
        
        $("#bid").change(function(){
			var sortChar = $(this).val();
			loadBrand(sortChar);
		});
        
        
        //编辑器配置
        var editor = new wangEditor('myeditor');
        editor.config.uploadImgUrl = '${path}/upload/uploadForEditor.do';
   	 	editor.create();
   	 	//编辑器注释
   	 	
   	 	
   	 	$("input[reg1]").blur(function(){
   			var a=$(this);
   			var reg = new RegExp(a.attr("reg1"));
   			var objValue = a.val();
   			if(!reg.test(objValue)){
   				if(a.next("span").length ==0){
   				a.after("<span>"+a.attr("desc")+"</span>");
   				}
   			}else{
   				a.next("span").remove();
   				}
   			});
   	//实现页面规格的自动增加和删除
   	var divNum = 1;
   		$("#button2").click(function(){
   			//把div编号++
   			divNum++;
   			//获得sp_0的div的内部代码
   			var htmlDiv = $("#sp_0").html();
   			//拼接div本身
   			htmlDiv = "<div id='sp_"+divNum+"'>"+htmlDiv+"</div>";
   			
   			htmlDiv = htmlDiv.replace(/specradio1/g, "specradio"+divNum);                 
  
   			htmlDiv = htmlDiv.replace(/shopPrice1/g, "shopPrice"+divNum);
   			htmlDiv = htmlDiv.replace(/marketPrice1/g, "marketPrice"+divNum);
   			htmlDiv = htmlDiv.replace(/stock1/g, "stock"+divNum);
   			htmlDiv = htmlDiv.replace(/skuName1/g, "skuName"+divNum);
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
   		
   	 	
   	 $("#stepy_form").submit(function(){
   		
			$.myloading({title : '正在提交，请稍等...'});
			var url = "${path}/item/addProduct.do";
			$("#stepy_form").attr("action", url);
			
			return true;
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
    	$("#stepy_form").ajaxSubmit(option);
    }
</script>
</body>
</html>