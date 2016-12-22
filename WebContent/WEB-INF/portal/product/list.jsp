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
	$.getUrlParam = function(name)
	{
	 var reg = new RegExp("(^|&)"+name +"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if (r!=null) 
		 return unescape(r[2]); 
	 return null;
	}
	
	$("#narrow-by-list a").click(function(){
		/* var oldurl = window.location.href; */
		var oldurl = $("#urlpath").val();
		oldurl = oldurl.replace("&page=${pageBean.pageNum}","");
		var newurl = "";
		var ftype = $(this).attr("fType");
		var fvalue = $(this).attr("fValue");
		if(ftype != "feature"){
			newurl = oldurl+"&"+ftype+"="+fvalue;
		}else{
			/* var oldfeat = $.getUrlParam("para"); */
			
			/* if(oldfeat != null && oldfeat != ""){
				oldurl = oldurl.substr(0,oldurl.length - oldfeat.length);
				alert("截取后"+oldurl);
				oldfeat = decodeURI(oldfeat);
				oldfeat = oldfeat + "," + fvalue;alert("para"+oldfeat);
				oldfeat = "&para=" + encodeURI(encodeURI(oldfeat));
			}else{ */
				fvalue = encodeURI(encodeURI(fvalue));
			var	oldfeat = "&para=" + fvalue;
			
			newurl = oldurl + oldfeat;
			
		}
		window.location.href = newurl;
	});
	
	$(".listfeat a i").click(function(){
		var val = $(this).prev().html();
		var type = $(this).prev().prev().html();
		var url = $("#urlpath").val();
		if(type == "价格:"){
			url = url.replace("&price="+val,"");
		}else if(type == "品牌:"){
			var bid = $(this).attr("b");
			url = url.replace("&bid="+bid,"");
		}else{
			var pid = $(this).attr("p");
			val = pid + ":" + encodeURI(encodeURI(val));
			url = url.replace("&para="+val,"");
		}
		window.location.href = url;
	});
});

function goPage(num){
	var url = $("#urlpath").val();
	url = url.replace("&page=${pageBean.pageNum}","");
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
	全部结果 > ${cs.name } 
	<c:if test="${price != null}"> > <a href="javascript:void(0);" ><b>价格:</b>
		<em>${price }</em>
		<i></i></a></c:if>
	<c:if test="${brand != null }"> > <a href="javascript:void(0);" ><b>品牌:</b>
		<em>${brand.name }</em>
		<i b="${brand.id }"></i></a></c:if>
	<c:forEach items="${sList }" var="feat">
	 > <a href="javascript:void(0);"><b>${feat.attrName }:</b>
		<em>${feat.optionValues }</em>
		<i p="${feat.paId }"></i></a>
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
                <a href="javascript:void(0);" fType="price" fValue="4000-100000"><span class="price1">￥&nbsp;4000</span>以上</a>
            </li>
</ol>
</dd>
	</c:if>
	<c:if test="${bList != null }">
              <dt class="even">品牌</dt>
                    <dd class="even">
<ol>
		<li>         
		<c:forEach items="${bList}" var="brand">	
                <a href="javascript:void(0);" fType="bid" fValue="${brand.id}">${brand.name }</a>&nbsp;
         </c:forEach>
         </li>
</ol>
</dd>
</c:if>
<c:forEach items="${fList }" var="feat">
               <dt class="last odd">${feat.attrName }</dt>
                    <dd class="last odd">
<ol>
    		<li>
    		<c:forEach items="${feat.optionValues }" var="val">
    		<a href="javascript:void(0);" fType="feature" fValue="${feat.paId }:${val }">${val }</a>&nbsp;
    		</c:forEach>
    		</li>
</ol>
</dd>
</c:forEach>
</dl>
           
    </div>
</div>
</div>
<div class="col-md-9">
	<div class="mens-toolbar">
          <div class="sort">
               	<div class="sort-by">
		            <label>排序</label>
		            <select>
		                            <option value="">
		                    新品优先               </option>
		                            <option value="">
		                    销量优先               </option>
		                            <option value="">
		                   价格升序               </option>
		            </select>
		        </div>
    		</div>
	       
	    	</div>
     	    <div class="clearfix"></div>
	     
	          
	          <c:forEach items="${pageBean.dataList }" var="item" varStatus="st">
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
                	第${pageBean.pageNum}页/共${pageBean.totalPage}页，${pageBean.totalCount}条记录</div>
	        <div class="pager">   
	       		
            	<ul style="float: right;font-size:12px;">
            	<c:if test="${pageBean.pageNum != 1 }">
            	<li><a href="#" onclick="goPage('1')">首页</a></li>
              	<li><a href="#" onclick="goPage('${pageBean.pageNum - 1 }')">«</a></li>
              	</c:if>
              	<c:forEach begin="${pageBean.startNum }" end="${pageBean.endNum }" var="i">
               	
               	<li><a href="#" <c:if test="${pageBean.pageNum == i }">class='actss'</c:if> onclick="goPage('${i}')">${i }</a></li>
                 
                   </c:forEach>
                   	<c:if test="${pageBean.pageNum != pageBean.totalPage && pageBean.totalPage != 0 }">
                    <li><a href="#" onclick="goPage('${pageBean.pageNum + 1 }')" >»</a></li>
                    <li><a href="#" onclick="goPage('${pageBean.totalPage }')">尾页</a></li>
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