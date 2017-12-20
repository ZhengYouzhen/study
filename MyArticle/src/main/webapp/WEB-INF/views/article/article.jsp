<%--
  Created by IntelliJ IDEA.
  User: 娃娃鱼
  Date: 2017/12/15
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>文章内容</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>
<ul class="layui-nav layui-bg-blue" lay-filter="">
    <li class="layui-nav-item"><a href="">首页</a></li>
    <li class="layui-nav-item"><a href="<%=path %>/user/loginPage">用户登录</a></li>
</ul>
<div class="layui-container">
    <div id="view">
        <script id="demo" type="text/html">
            <h3>{{ d.title }}</h3>
            <p>{{ d.author }}</p>
            <p>{{ d.typeName }}</p>
            {{#  if(d.firstImg !== null){ }}
            <img src="<%=path %>/{{ d.firstImg }}" alt="d.firstImg"/>
            {{#  } else { }}
            <span>-----------------</span>
            {{#  } }}
            <div>
                {{ d.content }}
            </div>
            <p><div>{{ formatDate(d.createdTime)}}</div></p>
        </script>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script src="<%=path %>/static/js/public.js"></script>
<script>
    //获取url上的值,获取页面传过来的值
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) {
            return unescape(r[2]);
        }
        return null;
    }
    var articleId = GetQueryString("articleId");

    layui.use(['element', 'laytpl'], function () {
        var $ = layui.$;
        var element = layui.element;
        var laytpl = layui.laytpl;

        var getTpl = demo.innerHTML
            , view = document.getElementById('view');
        $.get('<%=path %>/article/details?articleId=' + articleId,
            function (data) {
                laytpl(getTpl).render(data, function (html) {
                    view.innerHTML = html;
                });
            });

    });
</script>
</body>
</html>
