<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Gifty Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<style type="text/css">
a:hover {text-decoration:none;}
</style>
<script type="text/javascript">
$(function(){
	
	$("#narrow-by-list a").click(function(){
		/* var oldurl = window.location.href; */
		var oldurl = $("#urlpath").val();
		oldurl = oldurl.replace("&page=${page}","");
		var newurl = "";
		var ftype = $(this).attr("fType");
		var fvalue = $(this).attr("fValue");
		if(ftype != "feature"){
			newurl = oldurl+"&"+ftype+"="+fvalue;
		}else{
			var	oldfeat = "&para=" + fvalue;
			
			newurl = oldurl + oldfeat;
			
		}
		window.location.href = newurl;
	});
	
	$(".listfeat a i").click(function(){
		var val = $(this).prev().html();
		var type = $(this).prev().prev().html();
		var url = $("#urlpath").val();
		if(type != ""){
			url = url.replace("&price="+val,"");
		}else{
			val = encodeURI(val);
			url = url.replace("&para="+val,"");
		}
		window.location.href = url;
	});
	
	$(".sort-by label").click(function(){
		var sort = "";
		if($(this).html() == "价格降序"){
			sort = 1;
		}else if($(this).html() == "价格升序"){
			sort = 2;
		}
		var url = $("#urlpath").val();
		url = url.replace("&sort="+$("#sort").val(),"");
		if(sort != ""){
			url = url + "&sort=" + sort;
		}
		window.location.href = url;
	});
});

function goPage(num){
	var url = $("#urlpath").val();
	url = url.replace("&page=${page}","");
	url = url + "&page=" + num;
	window.location.href = url;
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">

	<div class="container">
	<div class="listselect">	
	<div class="listfeat">
	<input type="hidden" id="urlpath" value="${urlPath }">
	<input type="hidden" id="queryString" value="${queryString }">
	<input type="hidden" id="para" value="${para }">
	<input type="hidden" id="sort" value="${sort }"> 
	全部结果 > ${queryString } 
	<c:if test="${price != null}"> > <a href="javascript:void(0);" ><b>价格:</b>
		<em>${price }</em>
		<i></i></a></c:if>
	<c:forEach items="${para }" var="feat">
	 > <a href="javascript:void(0);"><b></b>
		<em>${feat }</em>
		<i></i></a>
	</c:forEach></div>
	</div>
	  <div class="col-md-3 sidebar">
	  	<div class="block block-layered-nav">
		    <div class="block-title">
		        <strong><span>筛选条件</span></strong>
		    </div>
    <div class="block-content">
                                    
            <dl id="narrow-by-list">
      <c:if test="${price == null }">
            <dt class="odd">价格</dt>
                    <dd class="odd">
<ol>
             <li>
                <a href="javascript:void(0);" fType="price" fValue="1-999"><span class="price1">￥&nbsp;1</span> - <span class="price1">￥&nbsp;999</span></a>
            </li>
    		<li>
                <a href="javascript:void(0);" fType="price" fValue="1000-1999"><span class="price1">￥&nbsp;1000</span> - <span class="price1">￥&nbsp;1999</span></a>
            </li>
    		<li>
                <a href="javascript:void(0);" fType="price" fValue="2000-2999"><span class="price1">￥&nbsp;2000</span> - <span class="price1">￥&nbsp;2999</span></a>
            </li>
    		<li>
                <a href="javascript:void(0);" fType="price" fValue="3000-3999"><span class="price1">￥&nbsp;3000</span> - <span class="price1">￥&nbsp;3999</span></a>
            </li>
    		<li>
                <a href="javascript:void(0);" fType="price" fValue="4000-*"><span class="price1">￥&nbsp;4000</span>以上</a>
            </li>
</ol>
</dd>
	</c:if>

               <dt class="last odd">选购热点</dt>
                    <dd class="last odd">
<ol>
    		<li>
    		<c:forEach items="${paraSet }" var="val">
    		<a href="javascript:void(0);" fType="feature" fValue="${val }">${val }</a>&nbsp;
    		</c:forEach>
    		</li>
</ol>
</dd>

</dl>
           
    </div>
</div>
</div>
<div class="col-md-9">
	<div class="mens-toolbar">
          <div class="sort">
               	<div class="sort-by">
		            <label>排序</label>
		            <label <c:if test="${sort == 1 }">class='actss'</c:if> >价格降序</label>
		            <label <c:if test="${sort == 2 }">class='actss'</c:if> >价格升序</label>
		        </div>
    		</div>
	       
	    	</div>
     	    <div class="clearfix"></div>
	     
	          
	          <c:forEach items="${result.productList }" var="item" varStatus="st">
	         <c:if test="${st.index % 3 == 0 }">
	         <div class="span_2">
	         </c:if>
	           
			  <div class="col_1_of_single1 span_1_of_single1">
	          	    <a href="${path }/product/detail.do?id=${item.proId }" target="_blank">
				     <img src="${filePath }${item.imgs}" class="img-responsive" alt=""/>
				     <span class="price">￥ ${item.price }</span>
				   	 <p></p>
				   	 <h3>${item.pname }</h3>
			         </a>  
				  </div> 
			 <c:if test="${st.last == false && (st.index + 1) % 3 == 0 }">
				  </div> 
	          <div class="clearfix"></div>
	          </c:if>
				 <c:if test="${st.last == true }">
				  </div> 
	          <div class="clearfix"></div>
	          </c:if>
				</c:forEach>  
				  
			   <div class="mens-toolbar">
			   <div class="sort" >
                	第${result.curPage}页/共${result.pageCount}页，${result.recordCount}条记录</div>
	        <div class="pager">   
	       		
            	<ul style="float: right;font-size:12px;">
            	<c:if test="${result.curPage != 1 }">
            	<li><a href="#" onclick="goPage('1')">首页</a></li>
              	<li><a href="#" onclick="goPage('${result.curPage - 1 }')">«</a></li>
              	</c:if>
              <%-- 	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li><a href="#" <c:if test="${pageBean.pageNum == i }">class='actss'</c:if> onclick="goPage('${i}')">${i }</a></li>
                 
                   </c:forEach> --%>
                   	<c:if test="${result.curPage != result.pageCount && result.pageCount != 0 }">
                    <li><a href="#" onclick="goPage('${result.curPage + 1 }')" >»</a></li>
                    <li><a href="#" onclick="goPage('${result.pageCount }')">尾页</a></li>
                    </c:if>
                    </ul>
                 
	    	</div>
     	    <div class="clearfix"></div>
	     </div>
      </div>
</div></div>
<%@include file="../common/footer.jsp" %>
</body>
</html>		