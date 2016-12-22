<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
    if ('WebSocket' in window) {
        Chat.socket = new WebSocket(host);
    } else if ('MozWebSocket' in window) {
        Chat.socket = new MozWebSocket(host);
    } else {
    	alert("error");
        return;
    }

    Chat.socket.onopen = function () {
       
    };
    
    Chat.socket.onclose = function () {
        
    };

    Chat.socket.onmessage = function (message) {
   	 var str = message.data;
   	 $("#wsMsg").css('display','block');
   	 $("#wsMsg a").html(str);
    };
});
Chat.initialize = function() {
    if (window.location.protocol == 'http:') {
        Chat.connect('ws://' + window.location.host + '/maoshop/websocket.do');
    } else {
        Chat.connect('wss://' + window.location.host + '/maoshop/websocket.do');
    }
};
Chat.initialize();
</script>
</head>
<body>
  <!-- header section start-->
        <div class="header-section">

            <!--toggle button start-->
            <a class="toggle-btn"><i class="fa fa-bars"></i></a>
            <!--toggle button end-->

            <!--search start-->
        
                <div id="wsMsg" class="tipt" style="display:none;">
                  <img alt="" src="${path }/images/tip.gif"><a href="${path }/orders/show.do?type=1"> ${message } </a>
                 </div>
 
            <!--search end-->

            <!--notification menu start -->
            <div class="menu-right">
                <ul class="notification-menu">
                    <li>
                        <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <img src="${path }/images/photos/user-avatar.png" alt="" />
                            管理员
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-usermenu pull-right">
                            <li><a href="#"><i class="fa fa-user"></i>  个人信息</a></li>
                            <li><a href="#"><i class="fa fa-cog"></i>  系统设置</a></li>
                            <li><a href="#"><i class="fa fa-sign-out"></i> 退出</a></li>
                        </ul>
                    </li>

                </ul>
            </div>
            <!--notification menu end -->

        </div>
        <!-- header section end-->
</body>
</html>