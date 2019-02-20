/**
 * Created by yao on 2018/12/21.
 */
layui.use(['element','form','table'], function () {
    var element = layui.element;
    var form = layui.form;
    var table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    form.verify({
        pass: [/(.+){6,12}$/, '密码必须6到12位，且不能出现空格']
    });

    $(function () {
        //操作成功提示
        var msg=$("#msg").val();
        if (msg!= null && msg.trim() != '') {
            layer.msg(msg);
            if(msg=='密码修改成功，请重新登录！'){
                setTimeout("window.open('/index');", 3000);
            }
        }
    });

    $("#phone").change(function () {
        $.post('/data/queryUser', {phone: this.value}, function (res) {
            console.log(res)
            if (res.code == '1001') {
                $("#phone").val('');
                layer.msg('该手机号已存在！');
            }
        })
    });

    //监听提交
    form.on('submit(inputForm)', function(data){
        $("#inputForm").submit();
    });

    $("#newPwd").click(function () {
        var oldPwd = $("#oldPwd").val();
        var tipMsg = document.getElementById("tipMsg").innerText;
        if(oldPwd==null||oldPwd==''){
            document.getElementById("tipMsg").innerText= "原密码不能为空！";
            document.getElementById("tipMsg").style.color = "red";
        }else if(oldPwd!=null&&oldPwd!=''&&tipMsg!=null&&tipMsg!=''){
            document.getElementById("tipMsg").innerText = "";
        }
    })
    $("#oldPwd").click(function () {
        var tipMsg = document.getElementById("tipMsg").innerText;
        if(tipMsg!=null&&tipMsg!=''){
            document.getElementById("tipMsg").innerText = "";
        }
    })
    // 修改密码
    form.on('submit(updPdSubmit)', function (data) {
        var one = data.field.oldPwd;
        var two = data.field.newPwd;
        if (one == two) {
            document.getElementById("tipMsg").innerText= "新密码和原密码相同,请重新设置！";
            document.getElementById("tipMsg").style.color = "red";
            $("#newPwd").val('');
            return false;
        } else {
          var form =  $("updPdSubmit").submit();
        }
    });

    //监听提交
    form.on('submit(select)', function (data) {
        var index = layer.load(0, {shade: false});
        $.ajax({
            async:false,
            type: "POST",//方法类型
            dataType: "json",//服务器返回的数据类型
            contentType: "application/json",//提交数据格式
            url: "/data/menu/add" ,//url
            data:  JSON.stringify(data.field),
            success: function (result) {
                layer.close(index);
                if (result.code == '0000') {
                    layer.msg("添加成功！");
                } else {
                    layer.msg(result.msg);
            }
            },
            error : function() {
                layer.close(index);
                layer.msg("添加失败! ")
            }
        });

    });

});



