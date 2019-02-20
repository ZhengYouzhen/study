//后台列表通用js
layui.config({
    base: '/layui_exts/' //配置 layui 第三方扩展组件存放的基础目录
}).extend({
    treetable: 'treeTable/treetable' //以 regionSelect 组件为例，定义该组件模块名
}).use(['layer', 'table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var layer = layui.layer;
    var treetable = layui.treetable;
    // 渲染表格
    var renderTable = function () {
        layer.load(2);
        treetable.render({
            treeColIndex: 1,
            treeSpid: 0,
            treeIdName: 'id',
            treePidName: 'parentId',
            treeDefaultClose: true,
            treeLinkage: false,
            elem: '#menuTable',
            url: '/data/menu/list',
            page: false,
            con_val: {
             open: "&#xe619;",
             close: "&#xe61a;"
             },
             space: 4,
            cols: [[
                {type: 'numbers'},
                {field: 'name', title: '名称'},
                {field: 'id',  title: 'id'},
                {field: 'href',  title: '链接'},
                {field: 'sort',  templet: function (d) {
                        return '<input class="layui-input" value="' + d.sort + '"/>';
                    }, title: '排序'},
                {
                    field: 'isShow',  align: 'center', templet: function (d) {
                        if (d.isShow == 0) {
                            return '<span class="layui-badge layui-bg-gray">已激活</span>';
                        } else {
                            return '<span class="layui-badge layui-bg-gray">已冻结</span>';
                        }
                    }, title: '状态'
                },
                {toolbar: '#oper-col', title: '操作'}
                // {templet: '#oper-col', title: '操作'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    };

    renderTable();

    //提示
    var msg = $("#msg").val();
    if (msg != null && msg != '') {
        layer.msg(msg);
    }

    $('#btn-expand').click(function () {
        treetable.expandAll('#menuTable');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#menuTable');
    });

    $('#btn-refresh').click(function () {
        renderTable();
    });

    $('#btn-search').click(function () {
        var keyword = $('#edt-search').val();
        var searchCount = 0;
        $('#menuTable').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
            $(this).css('background-color', 'transparent');
            var text = $(this).text();
            if (keyword != '' && text.indexOf(keyword) >= 0) {
                $(this).css('background-color', 'rgba(250,230,160,0.5)');
                if (searchCount == 0) {
                    treetable.expandAll('#menuTable');
                    $('html,body').stop(true);
                    $('html,body').animate({scrollTop: $(this).offset().top - 150}, 500);
                }
                searchCount++;
            }
        });
        if (keyword == '') {
            layer.msg("请输入搜索内容", {icon: 5});
        } else if (searchCount == 0) {
            layer.msg("没有匹配结果", {icon: 5});
        }
    });
    //监听工具条
    table.on('tool(menuTable)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'addChild') {
            window.document.location.href="/page/menu/addChild?id="+data.id;
        } else if (layEvent === 'edit') {
            window.document.location.href="/page/menu/form?id="+data.id;
        }
    });
});