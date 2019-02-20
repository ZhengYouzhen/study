//判断数据是否为空
//    var _obj = "";
//    var _obj = " ";
//    var _obj = null;
//    var _obj = undefined;
//    var _obj = [];
//    var _obj = {};
//    var _obj = NaN;
function isBlank(_obj) {
    if (_obj === undefined) { // 只能用 === 运算来测试某个值是否是未定义的
        return true;
    }

    if (_obj == null) { // 等同于 _obj === undefined || _obj === null
        return true;
    }

    // String
    if (_obj === "") { // "",null,undefined
        return true;
    }

    if (!_obj) { // "",null,undefined,N_objN
        return true;
    }

    if (!$.trim(_obj)) { // "",null,undefined
        return true;
    }

    // _objrr_objy
    if (_obj.length === 0) { // "",[]
        return true;
    }

    if (!_obj.length) { // "",[]
        return true;
    }

    // Object {}
    if ($.isEmptyObject(_obj)) { // 普通对象使用 for...in 判断，有 key 即为 f_objlse
        return true;
    }

    return false;
}

//非空
function isNotBlank(_obj) {
    return !isBlank(_obj);
}

// 格式化时间 年-月-日 时：分：秒
function formatDate(value) {
    if (value === undefined || value === null || value === '') {
        return "";
    } else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }
}

// 格式化时间 年-月-日
function formatDateTime(value) {
    if (value === undefined || value === null || value === '') {
        return "";
    } else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }
}

/**参数说明：
 * 根据长度截取先使用字符串，超长部分追加…
 * str 对象字符串
 * len 目标字节长度
 * 返回值： 处理结果字符串
 */
function cutString(str, len) {
    //length属性读出来的汉字长度为1
    if (str.length * 2 <= len) {
        return str;
    }
    var strlen = 0;
    var s = "";
    for (var i = 0; i < str.length; i++) {
        s = s + str.charAt(i);
        if (str.charCodeAt(i) > 128) {
            strlen = strlen + 2;
            if (strlen >= len) {
                return s.substring(0, s.length - 1) + "...";
            }
        } else {
            strlen = strlen + 1;
            if (strlen >= len) {
                return s.substring(0, s.length - 2) + "...";
            }
        }
    }
    return s;
}

//后台列表通用js
layui.use(['laypage', 'layer','laydate','form', 'table'], function () {
    var laypage = layui.laypage;
    var layer = layui.layer;
    var laydate = layui.laydate;
    var form = layui.form;
    var table = layui.table;
    //转换静态表格
    table.init('table-data', {});

    //分页组件
    laypage.render({
        elem: 'paging' //注意，这里的 paging 是 ID，不用加 # 号
        , count: $("#pageTotal").val()//[[${page.total}]] //数据总数，从服务端得到
        , curr: $("#pageNo").val() //[[${page.pageNum}]] //当前页数
        , limit: $("#pageSize").val() //[[${page.pageSize}]] //当前页数
        , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
        , jump: function (obj, first) {
            //点击分页触发效果，注以下ID名称在列表页请统一
            if (!first) {
                $("#pageNo").val(obj.curr);
                $("#pageSize").val(obj.limit);
                $("#inputForm").submit();
            }

        }
    });
    laydate.render({
        elem: '#createDate' //指定元素
    });
    laydate.render({
        elem: '#startCreateDate' //指定元素
    });
    laydate.render({
        elem: '#endCreateDate' //指定元素
    });
    //提示
    var msg = $("#msg").val();
    if (isNotBlank(msg)) {
        layer.msg(msg);
    }
});