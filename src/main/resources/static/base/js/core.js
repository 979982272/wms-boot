var winCount = 0;
/**
 * 方法目录
 * 1.显示错误信息:$.core.showErrogMsg('输入信息');
 * 2.显示提示信息:$.core.showMsg('输入信息');
 * 3.打开一个新窗口:$.core.open('链接地址','标题',{width:宽度,height:高度,close:function()关闭窗口的回调});
 * 4.显示等待图标:$.core.showLoading();
 * 5.隐藏等待图标:$.core.hideLoading();
 * 6.通过url获取表格数据源:$.core.getGridDataSource(url)
 */
$.core = {
    _data: "_EIDP_WINDOW_DATA",
    showErrogMsg: function (msg) {
        top.$.core._notification("错误信息", msg, "error-info");
    },
    showMsg: function (msg) {
        top.$.core._notification("提示信息", msg, "success-info");
    },
    open: function (url, title, params) {
        top.$.core._open(url, title, params)
    },
    _open: function (url, title, params) {
        if (url == undefined) {
            top.$.core.showErrogMsg("使用弹出框必须要输入Url！");
            return;
        }
        if (title == undefined) {
            top.$.core.showErrogMsg("使用弹出框必须要输入窗口标题！");
            return;
        }
        var idTag = "window" + winCount;
        var $idTag = "#" + idTag;
        $("body").append('<div id="' + idTag + '"></div>')
        var width = params == undefined ? 560 : params.width != undefined ? parseInt(params.width) : 560;
        var height = params == undefined ? 380 : params.height != undefined ? parseInt(params.height) : 380;
        var winWidth = document.documentElement.clientWidth;
        var winHeight = document.documentElement.clientHeight;
        var posWidth = (winWidth - width) / 2 - 80;
        var posHeight = (winHeight - height) / 2;
        $($idTag).kendoWindow({
            actions: ["Refresh", "Close"],
            position: {
                top: posHeight,
                left: posWidth
            },
            content: url,
            iframe: true,
            width: width,
            height: height,
            title: title,
            modal: true,
            resizable: false
        });
        var $win = $($idTag).data("kendoWindow");
        $win.bind("close", function () {
            if (params != undefined) {
                params.close && params.close()
            }
        });
        winCount = winCount + 1;
        var windoeInstance = $.core.data("windoeInstance");
        if (undefined == windoeInstance) {
            windoeInstance = new Array();
        }
        windoeInstance.push($win)
        $.core.data("windoeInstance", windoeInstance)
    },
    // 这里存在一定的问题，但是能力问题暂时先这样.不断调用的话在浏览器的source中会不断增加文件夹
    closeWin: function () {
        var a = top.$.core.data("windoeInstance").pop();
        a.close();
        a.destroy()
    },
    showLoading: function () {
        var MASK = $("#win_mask");
        if (MASK.length > 0) {

        } else {
            MASK = $("<div id=\"win_mask\" class=\"c-mask\"><div id=\"loading\" class=\"loading\">请稍等...</div></div>");
            $(document.body).append(MASK);
        }
        MASK.show();
    },
    hideLoading: function () {
        $("#win_mask").hide();
    },
    refresh: function (gridName) {
        $("#" + gridName).data("kendoGrid").dataSource.read();
        $("#" + gridName).data("kendoGrid").refresh()
    }, refreshTree: function (gridName) {
        $("#" + gridName).data("kendoTreeList").dataSource.read();
        $("#" + gridName).data("kendoTreeList").refresh()
    },
    _notification: function (title, msg, type) {
        kendo.bottom = 5
        if ($(".k-notification").length > 0) {
            $(".k-notification").each(function () {
                kendo.bottom = kendo.bottom + 140;
            });
        }
        var f = "<div></div>";
        var e = $(f).kendoNotification({
            position: {pinned: true, bottom: kendo.bottom, right: 5},
            width: 280,
            height: 130,
            button: true,
            autoHideAfter: 3000,
            stacking: "up",
            templates: [{
                type: "error-info",
                template: '<div class="error-pass"><h3>#= title #</h3><p>#= message #</p></div>'
            }, {
                type: "success-info",
                template: '<div class="success-pass"><h3>#= title #</h3><p>#= message #</p></div>'
            }]
        }).data("kendoNotification");
        e.show({title: title, message: msg}, type);
        $(".k-notification-" + type).each(function () {
            $(this).attr("class", "k-notification k-notification-" + type);
        });
    }, parent: function () {
        var a = window, b = function (c) {
            try {
                var f = window[c].document;
                f.getElementsByTagName
            } catch (d) {
                return false
            }
            return f.getElementsByTagName("frameset").length === 0
        };
        if (b("top")) {
            a = window.top
        } else {
            if (b("parent")) {
                a = window.parent
            }
        }
        return a
    }, data: function (b, d) {
        var c = $.core.parent(), a = c[$.core._data] || {};
        c[$.core._data] = a;
        if (d != undefined) {
            a[b] = d
        } else {
            return a[b]
        }
        return a
    },
    removeData: function (b) {
        var a = $.core.parent()[$.core._data];
        if (a && a[b]) {
            delete a[b]
        }
    }
}