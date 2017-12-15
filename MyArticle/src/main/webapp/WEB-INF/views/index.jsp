<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>
<ul class="layui-nav layui-bg-blue" lay-filter="">
    <li class="layui-nav-item"><a href="">首页</a></li>
    <li class="layui-nav-item"><a href="<%=path %>/user/loginPage">用户登录</a></li>
</ul>


<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;


    });
</script>
</body>
</html>
