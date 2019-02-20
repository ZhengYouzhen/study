/**
 * Created by yao on 2018/12/17.
 */
//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element','form'], function () {
    var element = layui.element;
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    //自定义验证规则
    form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
            if(/^\d$/.test(value)){
                return '用户名不能全为数字';
            }
            if(/^\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        },
        pass: [/(.+){6,12}$/, '密码必须6到12位，且不能出现空格']
    });
    //监听提交
    form.on('submit(login)', function (data) {
        var index = layer.load(0, {shade: false});
        $.post('/ajax/login',
            data.field,
            function (res) {
                if (res.code === '0000') {
                    // window.location.href = 'home';
                    layer.msg("登录成功")
                    $("#loginBtn").attr('onclick','home()');
                    $("#loginBtn").text('个人中心');
                    layer.closeAll('page');
                } else {
                    layer.msg(res.msg);
                }
                layer.close(index);
            }, 'json'
        );
        return false;
    });

    form.on('submit(register)', function (data) {
        var index =layer.load(0, {shade: false});
        $.post('/ajax/register',
            data.field,
            function (res) {
                if (res.code === '0000') {
                    // window.location.href = 'home';
                    layer.confirm('注册成功，是否登录？', {
                        btn: ['是','否'] //按钮
                    }, function(){
                        login();
                    }, function(){
                        layer.closeAll('page');
                    });
                } else {
                    if(res.code == '1001'){
                        layer.confirm('用戶已存在，是否登录？', {
                            btn: ['是','否'] //按钮
                        }, function(){
                            login();
                        }, function(){
                            layer.closeAll('page');
                        });
                    }else{
                        layer.msg("程序异常！");
                    }
                }
                layer.close(index);
            }, 'json'
        );
        return false;
    });

        $("#loginBtn").click(function () {
            var isTrue=document.getElementById("loginBtn").hasAttribute('onclick');
            if(!isTrue){
                layer.closeAll('page');
                layer.open({
                    type: 1,
                    title: ['登录', 'font-size:18px;'],
                    area: ['40%', '70%'],
                    shadeClose: true,
                    isOutAnim: false,
                    anim: -1,
                    shade: 0,
                    content: $("#login")
                });
            }
        })

    $("#loginBtns").click(function () {
        var isTrue=document.getElementById("loginBtn").hasAttribute('onclick');
        if(!isTrue){
            layer.closeAll('page');
            layer.open({
                type: 1,
                title: ['登录', 'font-size:18px;'],
                area: ['40%', '70%'],
                shadeClose: true,
                isOutAnim: false,
                anim: -1,
                shade: 0,
                content: $("#login")
            });
        }
    })
    $("#registers").click(function () {
        layer.closeAll('page');
        layer.open({
            type: 1,
            title: ['注册', 'font-size:18px;'],
            area: ['40%', '70%'],
            shadeClose: true,
            isOutAnim: false,
            anim: -1,
            shade: 0,
            content: $("#register")
        })
    })

});
function home() {
    window.location.href = 'home';
}