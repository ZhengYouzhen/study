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
<ul class="layui-nav layui-bg-blue" id="navigation" lay-filter="">
    <li class="layui-nav-item"><a href="<%=path %>/">首页</a></li>
    <li class="layui-nav-item">
        <%--<a href="<%=path %>/user/loginPage">用户登录</a>--%>
    </li>
</ul>

<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-md8">
            <div id="allArticle">
                <script type="text/html" id="demo">
                    {{#  layui.each(d, function(index, article){ }}
                    <div style="border: solid 1px red">
                        <h2><a href="<%=path %>/article/detailsPage?articleId={{ article.id }}" target="_blank">{{
                            article.title
                            }}</a></h2>
                        <p>{{ article.author }}</p>
                        <p>{{ article.typeName }}</p>
                        {{# if(article.firstImg !== null && article.firstImg != ''){ }}.
                        <img src="<%=path %>/{{ article.firstImg }}" alt="article.firstImg"/>
                        {{# } else { }}
                        <span>-----------------</span>
                        {{# } }}
                        <div>
                            {{ article.summary }}
                        </div>
                        <p>
                        <div>{{ formatDate(article.createdTime)}}</div>
                        </p>
                    </div>
                    {{#  }); }}
                </script>
            </div>
            <div id="allpage"></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script src="<%=path %>/static/js/public.js"></script>
<script>
    layui.use(['element', 'laypage', 'laytpl'], function () {
        var $ = layui.$;
        var element = layui.element;
        var laypage = layui.laypage;
        var laytpl = layui.laytpl;

        //判断用户是否登录
        var navData = "${user }";
        var navigation = $("#navigation");
        if(navData != null && navData != '') {
            navigation.append('<li class="layui-nav-item"><a href="<%=path %>/user/home">${user.name }</a></li>');
        } else {
            navigation.append('<li class="layui-nav-item"><a href="<%=path %>/user/loginPage">用户登录</a></li>');
        }

        var page = 1; // 第一页开始
        var limit = 10; // 每页十个数据，laypage默认也是十个

        var getTpl = $('#demo').html()
            , view = document.getElementById('allArticle');
        // 获取数据
        $.get('<%=path %>/article/pagerCriteria', {
            page: page,
            limit: limit
        }, function (data) {
            fenye(data.rows);
            pageTotal(data.total)
        });

        // 渲染数据
        function fenye(data) {
            laytpl(getTpl).render(data, function (html) {
                view.innerHTML = html;
            });
        }

        // 分页组件
        function pageTotal(total) {
            laypage.render({
                elem: 'allpage'
                , count: total
                , curr: location.hash.replace('#!page=', '') //获取起始页
                , hash: 'page' //自定义hash值
                , jump: function (obj, first) {
                    //obj包含了当前分页的所有参数，比如：
                    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                    console.log(obj.limit); //得到每页显示的条数

                    //在点击页号和上下页的时候重新加载数据
                    if (!first) {
                        $.get('<%=path %>/article/pagerCriteria', {
                            page: obj.curr,
                            limit: obj.limit
                        }, function (data) {
                            fenye(data.rows);
                        });
                    }
                }
            });
        }


    });
</script>
</body>
</html>
