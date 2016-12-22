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
	window.location.href = "${path}/item/toAddFeature.do";
}
function goPage(page,cid){
	window.location.href = "${path}/item/feature.do?pageNum="+page+"&cateId="+cid;
}
function del(fid){
	if (confirm("确定删除此记录 ?") == false) {
        return;
    }
	window.location.href = "${path}/item/delFeature.do?featId="+fid;
}
function modify(fid){
	window.location.href = "${path}/item/toEditFeature.do?featId="+fid;
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
                   属性设置
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
                		<span style="margin-left:50px">筛选</span> 
									<select id="catid" class="btn btn-default">
									<option value="-1">一级分类</option>
									<c:forEach items="${catList }" var="cat">
										<option value="${cat.id }" <c:if test="${cat.id == attr.catId }">selected='selected'</c:if>>${cat.name }</option>
										</c:forEach>
									</select>&nbsp;下&nbsp;&nbsp;
									
									<select id="cateid" class="btn btn-default" name="cateId">
									
										<option value="-1">二级分类</option>
										<c:forEach items="${cateList }" var="cate">
										<option value="${cate.id }" <c:if test="${cate.id == attr.cateId }">selected='selected'</c:if>>${cate.name }</option>
										</c:forEach>
									</select>&nbsp;的全部属性
								
            </header>
            <div class="panel-body">
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>所属分类</th>
                            <th>属性名称</th>
                            <th>录入类型</th>
                            <th>可选值</th>
                            <th>是否规格</th>
                            <th>是否筛选</th>
                            <th>操作</th>                      
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.dataList }" var="feat">
                        <tr>
                            <td>${feat.cateName }</td>
                            <td>${feat.attrName }</td>
                            <td>
                            <c:choose>
                            	<c:when test=" ${feat.attrType == 0 }">
                            	下拉列表
                            	</c:when>
                            	<c:when test="${feat.attrType == 1 }">
                            	单选按钮
                            	</c:when>
                            	<c:when test="${feat.attrType == 2 }">
                            	复选框
                            	</c:when>
                            	<c:when test="${feat.attrType == 3 }">
                            	文本框
                            	</c:when>
                            </c:choose>
                            </td>
                            <td>${feat.optionValues }</td>
                            <td>${feat.isSpec }</td>
                            <td>${feat.isSelect }</td>
                            <td>
                            <a href="#" onclick="modify('${feat.paId}')">修改</a>|
                            <a href="#" onclick="del('${feat.paId}')">删除</a>
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
            	<li><a href="#" onclick="goPage('1','${attr.cateId}')">首页</a></li>
              	<li><a href="#" onclick="goPage('${pageBean.pageNum - 1 }','${attr.cateId}')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li <c:if test="${pageBean.pageNum == i }">class='active'</c:if>><a href="#" onclick="goPage('${i}','${attr.cateId}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${pageBean.pageNum != pageBean.totalPage }">
                    <li><a href="#" onclick="goPage('${pageBean.pageNum + 1 }','${attr.cateId}')" >»</a></li>
                    <li><a href="#" onclick="goPage('${pageBean.totalPage }','${attr.cateId}')">尾页</a></li>
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
 
 <script type="text/javascript">
 $(function(){
	 
	 $("#catid").change(function(){
			var parentId = $(this).val();
			loadOption(parentId);
		})
		
	$("#cateid").change(function(){
			var cateId = $(this).val();
			window.location.href = "${path}/item/feature.do?cateId="+cateId;
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
				$("#cateid").append("<option value='-1'>二级分类</option>");

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
 </script>
</body>
</html>