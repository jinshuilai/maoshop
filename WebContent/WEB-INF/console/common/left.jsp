<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="#"><img src="${path }/images/logo.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="#"><img src="${path }/images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->

        <div class="left-side-inner">

            <!-- visible to small devices only -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media logged-user">
                    <img alt="" src="${path }/images/photos/user-avatar.png" class="media-object">
                    <div class="media-body">
                        <h4><a href="#">管理人</a></h4>
                        <span>"Hello There..."</span>
                    </div>
                </div>

                <h5 class="left-nav-title">Account Information</h5>
                <ul class="nav nav-pills nav-stacked custom-nav">
                  <li><a href="#"><i class="fa fa-user"></i> <span>个人信息</span></a></li>
                  <li><a href="#"><i class="fa fa-cog"></i> <span>系统设置</span></a></li>
                  <li><a href="#"><i class="fa fa-sign-out"></i> <span>退出</span></a></li>
                </ul>
            </div>

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li><a href="${path }/admin/toIndex.do"><i class="fa fa-home"></i> <span>首页</span></a></li>
                <li class="menu-list"><a href="#"><i class="fa fa-sitemap"></i> <span>分类管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="${path }/category/show.do"> 一级分类</a></li>
                        <li><a href="${path }/category/secondShow.do"> 二级分类</a></li>
                        <li><a href="leftmenu_collapsed_view.html"> 分类品牌关联</a></li>
                        <li><a href="horizontal_menu.html"> 树状结构编辑</a></li>

                    </ul>
                </li>
                <li class="menu-list"><a href="#"><i class="fa fa-tasks"></i> <span>商品管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="${path }/item/product.do"> 商品录入/编辑</a></li>
                        <li><a href="${path }/item/saleProduct.do?showStatus=0"> 商品上下架/发布</a></li>
                        <li><a href="${path }/item/feature.do"> 属性管理</a></li>
                        <li><a href="${path }/item/brand.do"> 品牌管理</a></li>
                    </ul>
                </li>
                <li class="menu-list"><a href="#"><i class="fa fa-file-text-o"></i> <span>订单管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="grids.html"> 修改订单信息（未付款）</a></li>
						<li><a href="${path }/orders/show.do?type=1"> 发货（已付款）</a></li>
                    </ul>
                </li>

                <li class="menu-list"><a href="#"><i class="fa fa-users"></i> <span>会员管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="mail.html"> 重置密码</a></li>
                    </ul>
                </li>

                <li class="menu-list"><a href="#"><i class="fa fa-cogs"></i> <span>系统管理</span></a>
                    <ul class="sub-menu-list">
                        <li><a href="form_layouts.html"> 查看日志</a></li>
                    </ul>
                </li>
               
            </ul>
            <!--sidebar nav end-->

        </div>
    </div>
    <!-- left side end-->
</body>
</html>