<%--
  Created by IntelliJ IDEA.
  User: 娃娃鱼
  Date: 2017/12/13
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加文章</title>
    <link rel="stylesheet" href="<%=path %>/static/layui/css/layui.css" media="all"/>
</head>
<body>
<div class="layui-container">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>编写文章</legend>
    </fieldset>
    <div class="layui-row">
        <div class="layui-col-md12">
            <form id="addArticleForm" class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" lay-verify autocomplete="off" placeholder="请输入标题"
                               class="layui-input">
                    </div>
                </div>
                <input type="hidden" name="userId" value="${user.id }"/>
                <div class="layui-form-item">
                    <label class="layui-form-label">选择类别</label>
                    <div class="layui-input-block">
                        <select name="typeId" lay-filter="typeId">
                            <option value=""></option>
                            <option value="1">java</option>
                            <option value="2">js</option>
                            <%-- <script id="demo" type="text/html">
                                {{#  layui.each(d, function(index, type){ }}
                                <option value={{ type.id }}>{{ type.typeName }}</option>
                                {{#  }); }}
                            </script> --%>
                        </select>
                    </div>
                </div>
                <div class="layui-upload">
                    <label class="layui-label">
                        <button type="button" class="layui-btn" id="test0">封面图</button>
                    </label>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="demo1" width="200" height="200">
                        <p id="demoText"></p>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="fabu">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=path %>/static/layui/layui.js"></script>
<script>
    layui.use(['form', 'laytpl', 'layedit', 'upload'], function () {

        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var laytpl = layui.laytpl;
        var layedit = layui.layedit;
        var upload = layui.upload;

        layedit.set({
            uploadImage: {
                url: '<%=path %>/file/upload' //接口url
                ,type: 'post' //默认post
            }
        });
        //创建一个编辑器
        var editIndex = layedit.build('content', {
            tool: [
                'strong' //加粗
                ,'left' //左对齐
                ,'center' //居中对齐
                ,'link' //超链接
                ,'unlink' //清除链接
                ,'face' //表情
                ,'image' //插入图片
            ]
        });

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#test0'
            ,url: '<%=path %>/file/upload'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res > 0){
                    return layer.msg('失败！');
                } else {
                    return layer.msg('成功');
                }
                //上传成功
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

        form.on('select(typeId)', function (data) {
            data.value //得到被选中的值
        });

        //提交文章
        form.on('submit(fabu)', function (data) {
            data.field.content = layedit.sync(editIndex);
            $.post('<%=path %>/article/save',
                $('#addArticleForm').serialize(),
                function (res) {
                    if (res.result === "success") {
                        layer.msg('提交成功', {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            location.reload(true);
                        });
                    } else {
                        layer.msg(res.message);
                    }
                }, 'json'
            );
            return false;
        });

        // 获取类别
        var getTpl = demo.innerHTML
            , view = document.getElementById('type');
        $.get('<%=path %>/type/all', function (data) {
            laytpl(getTpl).render(data, function (html) {
                view.innerHTML = html;
            });
//            var strOptions = "";
//            for (var i = 0; i < data.length; i++) {
//                strOptions = strOptions + '<option value="' + data[i].id + '">' + data[i].typeName + '</option>';
//            }
//            $('#type').append(strOptions);
        });

    });
</script>
</body>
</html>
