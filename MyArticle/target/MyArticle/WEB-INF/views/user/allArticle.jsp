<%--
  Created by IntelliJ IDEA.
  User: 娃娃鱼
  Date: 2017/12/14
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <title>所有文章</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>

<div class="layui-btn-group demoTable">
    <button class="layui-btn" data-type="getCheckData">获取选中行数据</button>
    <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
    <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div>

<table class="layui-table"
       lay-data="{width: 900, height:500, url:'<%=path %>/article/pager', page:true, id:'idTest',
        response: {
        statusName: 'status'
      ,statusCode: 0
      ,msgName: 'message'
      ,countName: 'total'
      ,dataName: 'rows'}
      }" lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'title', width:80}">标题</th>
        <th lay-data="{field:'author', width:80}">作者</th>
        <th lay-data="{field:'firstImg', width:120}">封面</th>
        <th lay-data="{field:'typeName', width:80}">类别</th>
        <th lay-data="{field:'createdTime', width:150, sort: true, templet:formatDate()}">创建时间</th>
    </tr>
    </thead>
</table>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script src="<%=path %>/static/js/public.js"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;

    });
</script>
</body>
</html>
