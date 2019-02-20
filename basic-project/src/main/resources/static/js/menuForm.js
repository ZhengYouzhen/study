/**
 * Created by yao on 2019/1/1.
 */
layui.use(['form', 'layedit', 'iconPicker'], function () {
    var form = layui.form;
    var icon = layui.iconPicker;

    icon.render({
        // 选择器，推荐使用input
        elem: '#icon',
        // 数据类型：fontClass/unicode，推荐使用fontClass
        type: 'fontClass',
        // 是否开启搜索：true/false
        search: true,
        // 点击回调
        click: function (data) {
            // console.log(data);
        }
    });

    /**
     * 选中图标 （常用于更新时默认选中图标）
     * @param filter lay-filter
     * @param iconName 图标名称，自动识别fontClass/unicode
     */
    var iconValue = $("#icon").val();
    if(iconValue!=null&&iconValue!=''){
        icon.checkIcon('iconPicker', iconValue);
    }else {
        icon.checkIcon('iconPicker', 'layui-icon-star-fill');
        $("#icon").attr('value', 'layui-icon-star-fill');
    }


    /**
     * 选择父菜单时把父id集合更新
     */
    form.on('select(parentMenu)', function (data) {
        var parentIds = $("#parentIds").val();
        var result = parentIds.split(",");
        $("#parentIds").val(parentIds.replace(result[result.length - 2], data.value));
    });

    //监听提交
    form.on('submit(inputForm)', function (data) {
        $("#inputForm").submit();
    });

});