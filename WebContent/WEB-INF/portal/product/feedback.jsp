<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
function goPage(page){
	parent.window.gotofb();
	var level = $("#flevel").val();
	var pid = $("#fpid").val();
	if(level != ""){
		window.location.href = "${path}/product/listFeedback.do?pid="+pid+"&page="+page+"&level="+level;
	}else{
		window.location.href = "${path}/product/listFeedback.do?pid="+pid+"&page="+page;
	}
}
</script>
</head>
<body>
<div class="men">
	<div class="container">
	<input type="hidden" id="flevel" value="${flevel }">
	<input type="hidden" id="fpid" value="${fpid }">
	    <div class="register">
	    <c:forEach items="${pageBean.dataList }" var="feed">
				<div class="contact_box">
		 <div class="col-md-4 contact-top" style="float:left">
        <ul class="list">
			<li><img alt="" src="${path }/images1/feed.png" width="80px" height="80px"></li>
			<li> ${feed.custName }</li>	
		</ul>
	   </div>
	      <div class="col-md-8 contact-top">
	        <div class="contact_grid">
					<div class="text">
					<textarea readonly="readonly"> ${feed.content }</textarea>
	                  
	                </div>
	                <div class="text" style="font-size:14px">
	                   	规格：${feed.sku } &nbsp;&nbsp; &nbsp; 
	                   	评价时间：<fmt:formatDate value="${feed.createdAt }" pattern="yyyy/MM/dd HH:mm" />
	                </div>
					<div class="clearfix"></div>
               
           </div>
       </div>
     
	   <div class="clearfix"> </div>
	   
	  </div>
	   <HR style="border:1px dashed #987cb9;" width="100%" color="#987cb9" SIZE="1">
	  </c:forEach>
	  
	  <c:choose>
	  <c:when test="${fn:length(pageBean.dataList) == 0}">
	    暂无评价！
	  </c:when>
	  <c:otherwise>
	  <div class="mens-toolbar">
			   <div class="sort" >
                	第${pageBean.pageNum}页/共${pageBean.totalPage}页，${pageBean.totalCount}条记录</div>
	        <div class="pager">   
	       		
            	<ul style="float: right;font-size:12px;">
            	<c:if test="${pageBean.pageNum != 1 }">
            	<li><a href="javascript:void(0);" onclick="goPage('1')">首页</a></li>
              	<li><a href="javascript:void(0);" onclick="goPage('${pageBean.pageNum - 1 }')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li <c:if test="${pageBean.pageNum == i }">class='active'</c:if>><a href="javascript:void(0);" onclick="goPage('${i}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${pageBean.pageNum != pageBean.totalPage && pageBean.totalPage != 0}">
                    <li><a href="javascript:void(0);" onclick="goPage('${pageBean.pageNum + 1 }')" >»</a></li>
                    <li><a href="javascript:void(0);" onclick="goPage('${pageBean.totalPage }')">尾页</a></li>
                    </c:if>
                    </ul>
                 
	    	</div>
     	    <div class="clearfix"></div>
	     </div>
	  </c:otherwise>
	  </c:choose>
		</div>
	 </div>
</div>
</body>
</html>