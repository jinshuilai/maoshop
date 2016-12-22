<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收货地址管理</title>
<%@include file="../common/taglib.jsp" %>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script type="text/javascript">
$(function(){
	
	$("#addrform").submit(function(){
		var isSubmit = true;
		var url = "#";
		var addrLength = $("#addrLength").val();
		var shipAddrId = $("#shipid").val();
		if(shipAddrId == null || shipAddrId == ""){
			if(addrLength == 10){
				alert("收货地址最后只能有10个");
				isSubmit = false;
			}
			url = "${path}/user/home/addShipAddr.do";
		}else{
			url = "${path}/user/home/modifyShipAddr.do";
		}
		$(this).attr("action",url);
		return isSubmit;
	})
	
	//选择省的选项的时的事件定义
	$("#province").change(function(){
		var parentId = $(this).val();
		loadOption(parentId, "#mycity");
	})
	$("#mycity").change(function(){
		var parentId = $(this).val();
		loadOption(parentId, "#district");
	})
});
function loadOption(parentId, selectId){
	$.ajax({
		url:"${path}/user/loadOption.do",
		type:"post",
		dataType:"text",
		async:false,
		data:{
			parentId:parentId
		},
		success:function(responseText){
			//清空要追加的select
			
			if(selectId == "#mycity"){
				//如果是选择其中一个省，加载该省下的所有的option时既要清空城市的option也要清空区县
				$(selectId).empty();
				$("#district").empty();
				//把城市和区县的请选择的option给加回来
				$(selectId).append("<option value='-1'>城市</option>");
				$("#district").append("<option value='-1'>区县</option>");
			}else{
				//如果是选择城市，加载城市下的区县，只清区县下的option即可
				$(selectId).empty();
				$(selectId).append("<option value='-1'>区县</option>");
			}
			//alert(responseText);
			//把json字符串转换成json对象
			var jsonObj = $.parseJSON(responseText);
			for(var i = 0; i < jsonObj.rList.length; i++){
				var opt = $("<option value='"+jsonObj.rList[i].regionId+"'>"+jsonObj.rList[i].regionName+"</option>");
				//追加option
				$(selectId).append(opt);
			}
		},
		error:function(){
			alert("系统错误");
		}
	})
}

function modify(shipid){
	window.location.href = "${path}/user/home/toShipAddr.do?id="+shipid;
}

function setdef(shipid){
	window.location.href = "${path}/user/home/changeAddrDef.do?id="+shipid;
}

function del(shipid,isdef){
	if(isdef){
		alert("默认地址不能删除！");
		return false;
	}
	window.location.href = "${path}/user/home/delShipAddr.do?id="+shipid;
}
</script>
</head>
<body>
<%@include file="../common/head.jsp" %>
<div class="men">
	<div class="wrap">
			<div class="vip_cont c100 clearfix">
				<!--左边列表导航  开始-->
				<div class="fl vip_left vip_magLeft">
					<dl>
						<dt>订单中心</dt>
						<dd>
							<p><a href="${path }/user/home/toOrder.do">我的订单</a></p>
							<p><a href="#" target="_blank">我的预售</a></p>
							<p><a href="#" target="_blank">评价晒单</a></p>
							<p><a href="#" target="_blank">取消订单记录</a></p>
						</dd>
					</dl>
					<dl>
						<dt>关注中心</dt>
						<dd>
							<p><a href="#" target="_blank">关注的商品</a></p>
							<p><a href="#" target="_blank">关注的店铺</a></p>
							<p><a href="#" target="_blank">关注的品牌</a></p>
							<p><a href="#" target="_blank">浏览历史</a></p>
						</dd>
					</dl>
					<dl>
						<dt>我的账号</dt>
						<dd>
							<p><a href="#" target="_blank">基本资料</a></p>
							<p><a href="${path }/user/home/toShipAddr.do" style="color:red;">收货地址</a></p>
							<p><a href="#" target="_blank">修改密码</a></p>
						</dd>
					</dl>
				</div>
				<!--左边列表导航  结束-->

				<!--右边列表内容  开始-->
				<div class="fr vip_right vip_magRight">
					<!--用户信息  开始 -->
					<div class="cus01">
				<h3>新增收货地址</h3>
				<form id="addrform" method="post" action="">
				<input type="hidden" id="shipid" name="shipid" value="${ship.shipid }">
					<ul class="shipaddr">
					<li id="errorName" style="display:none">${error}</li>
					<li>
						<samp>*</samp>收货人姓名：
						<span class="bg_text"><input type="text" name="consignee" required="required" maxLength="100" value="${ship.consignee }" /></span>
					</li>
					<li>
						 <samp>*</samp>&nbsp;地　　址：
						<span>
						<select id="province" name="provinceId">
							<option value="-1" selected="selected">请选择省</option>
							<c:forEach items="${rList }" var="region">
								<option value="${region.regionId }" <c:if test="${region.regionId == ship.provinceId}">selected='selected'</c:if> >${region.regionName }</option>
							</c:forEach>
						</select>
						<select id="mycity" name="cityId">
							<option value="-1" selected>城市</option>
							<c:forEach items="${cList }" var="city">
								<option value="${city.regionId }" <c:if test="${city.regionId == ship.cityId}">selected='selected'</c:if> >${city.regionName }</option>
							</c:forEach>
						</select>
						<select id="district" name="districtId">
							<option value="-1" selected>县/区</option>
							<c:forEach items="${dList }" var="dist">
								<option value="${dist.regionId }" <c:if test="${dist.regionId == ship.districtId}">selected='selected'</c:if> >${dist.regionName }</option>
							</c:forEach>
						</select></span>
					</li>
					<li>
						  <samp>*</samp>&nbsp;街道地址：
						<span class="bg_text"><input type="text" name="address" value="${ship.address }" maxLength="32" required="required" placeholder="具体到门牌号码"/></span>
					</li>
					<li>
						  <samp>*</samp>&nbsp;邮政编码：
						<span class="bg_text"><input type="text" name="postcode" value="${ship.postcode }" maxLength="6" required="required" placeholder="不清楚请填000000" /></span>
					</li>
					<li>
						  <samp>*</samp>&nbsp;联系电话：
						<span class="bg_text"><input type="text" name="phone" value="${ship.phone }" maxLength="11" required="required" placeholder="大陆手机号码"/></span>
					</li>
					<li>
						  <samp>&nbsp;</samp>&nbsp;地址备注：
						<span class="bg_text"><input type="text" name="remark" value="${ship.remark }" maxLength="11"/></span>
					</li>
					<li>
						<label for="statusAddr">&nbsp;</label>
						<span><input type="checkbox" name="isDefault" value="true" <c:if test="${ship.isDefault}">checked='checked'</c:if> />&nbsp;设为默认收货地址</span>
					</li>
					<li><label for="">&nbsp;</label><input type="submit" value="保存" class="hand btn66x23" /></li>
					</ul>
				</form>
						
					</div>
				
					<input id="addrLength" type="hidden" value="${fn:length(saList) }">
				
				<h3>已存收货地址列表<span>最多保存10个收货地址</span></h3>
				
				<table class="table table-borderded table-hover" style="font-size:14px;">
				<thead>
				<tr>
				<th>收货人</th>
				<th>所在地区</th>
				<th>街道地址</th>
				<th>邮编</th>
				<th>电话/手机</th>
				<th>备注</th>
				<th>&nbsp;</th>
				<th>操作</th>
				</tr>                                                          
				</thead>
				<tbody>
				
				<c:forEach items="${saList }" var="addr">
				<tr>
					<td>${addr.consignee }</td>
					<td>${addr.province }省&nbsp;${addr.city }市&nbsp;${addr.district }</td>
					<td>${addr.address }</td>
					<td>${addr.postcode }</td>
					<td>${addr.phone }</td>
					<td>${addr.remark }</td>
					<c:choose>
					<c:when test="${addr.isDefault }">
					<td class="moren">默认地址</td>
					</c:when>
					<c:otherwise>
					<td class="dispp"><a href="javascript:void(0);" onclick="setdef(${addr.shipid})">设为默认</a></td>
					</c:otherwise>
					</c:choose>			
					<td><a href="javascript:void(0);" title="修改" onclick="modify(${addr.shipid})">[修改]</a><a href="javascript:void(0);" title="删除" onclick="del(${addr.shipid},${addr.isDefault })">[删除]</a></td>
				</tr>
				</c:forEach>
				
					
				</tbody>
				</table>
				
					<!-- 用户信息  结束 -->
				<!--右边列表内容  结束-->
			    </div>
		</div>
      </div>
		<!-- 内容  结束-->
</div>
<%@include file="../common/footer.jsp" %>
</body>
</html>