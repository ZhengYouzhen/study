layui.config({
    base: '/module/'
}).extend({
    iconPicker: 'iconPicker/iconPicker'
});
layui.use(['layer'], function () {
    var layer = layui.layer;
    //提示
    var msg = $("#msg").val();
    if (msg!=null&&msg.trim()!='') {
        layer.msg(msg);
    }
});