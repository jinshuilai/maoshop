<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城后台管理</title>
<%@include file="./common/head.jsp" %>
</head>
<body class="sticky-header">

<section>
    <%@include file="./common/left.jsp" %>
    
    <!-- main content start-->
    <div class="main-content" >
	<%@include file="./common/header.jsp" %>
        
		 <!-- page heading start-->
        <div class="page-heading">
            	首页
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
            <h3>添加商品的说明</h3>
            <p>首先请确保商品的前置属性存在，包括如下：</p>
            <p>1、一级分类，如：电子产品。在 <span style="color:red">分类管理->一级分类</span> 进行管理</p>
            <p>2、商品所属的二级分类，如：手机。在<span style="color:red">分类管理->二级分类</span>  进行管理</p>
            <p>3、该二级分类的属性集合，如：上市时间，cpu，ram等等。在<span style="color:red">商品管理->属性管理 </span> 进行操作</p>
            <p>4、商品的品牌，如：苹果。在<span style="color:red">商品管理->品牌管理</span>  进行操作</p>
            <h4>确保上述属性存在后，在<span style="color:red">商品管理->商品录入/发布</span>    进行商品添加操作</h4>
            <p>商品添加至少有一张主图，商品的属性不选择等于不存在这属性，双击单选按钮可取消选择</p>
            <p>商品添加后可先查看是否有错，在<span style="color:red">商品管理->商品上下架</span>中进行上架后商品才会在前台进行显示，上架后不可修改</p>
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
 
 <%@include file="./common/js.jsp" %>
 <script type="text/javascript">
 $(function(){
		var txt = $("#wsMsg a").html();
		if(txt != ""){
			 $("#wsMsg").css('display','block');
		}
	});
 </script>
</body>
</html>