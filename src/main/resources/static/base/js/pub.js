/**
 *  工具类
 *  1.getGridDataSource 获取表格数据源
 *  2.getDropDownDataSource 获取下拉框数据源
 *
 */
function getGridDataSource(url) {
    var data = new kendo.data.DataSource({
        schema: {
            total: "total",
            data: "data",
            groups: "data",
            aggregates: "aggregates",
            type: "json",
            errors: "errors"
        },
        serverPaging: true,
        serverFiltering: true,
        serverSorting: true,
        pageSize: 20,
        page: 1,
        transport: {
            read: {
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                url: url
            }, parameterMap: function (e) {
                return JSON.stringify(e);
            }
        }
    });
    return data;
}

function getGridDataSourceByNOPagin(url) {
    var data = new kendo.data.DataSource({
        schema: {
            total: "total",
            data: "data",
            groups: "data",
            aggregates: "aggregates",
            type: "json",
            errors: "errors"
        },
        serverFiltering: true,
        serverSorting: true,
        transport: {
            read: {
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                url: url
            }, parameterMap: function (e) {
                return JSON.stringify(e);
            }
        }
    });
    return data;
}

/**
 * 通过url获取下拉框数据源
 * @param url
 * @returns {true.DataSource}
 */
function getDropDownDataSource(url) {
    var dataSource = new kendo.data.DataSource({
        transport: {
            read: {
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                url: url
            },
            batch: true,
            parameterMap: function (e) {
                return JSON.stringify(e);
            }
        }
    });
    return dataSource;
}

/**
 * 通过通用数据源
 * @param key
 * @returns {true.DataSource}
 */
function getStaticDataSourceByCommon(key) {
    var dataSource = new kendo.data.DataSource({
        transport: {
            read: {
                dataType: "json",
                type: "POST",
                contentType: "application/json",
                url: "/common/getComboModelDataSource?key=" + key
            },
            batch: true,
            parameterMap: function (e) {
                return JSON.stringify(e);
            }
        }
    });
    return dataSource;
}

/**
 * 通过数据源将code转换为name
 * @param dataSource
 * @param code
 */
function transCodeToNameByDataSource(dataSource, code) {
    var info = "";
    if (isNotEmpty(code)) {
        var datas = dataSource.data();
        var infos = {};
        for (var i = 0; i < datas.length; i++) {
            infos[datas[i].id] = datas[i].text;
        }
        info = infos[code];
        if (isEmpty(info)) {
            info = code;
        }
    }
    return info;
}

// 格式化输入框
function formatText(parms) {
    if (parms == undefined) {
        $.core.showErrogMsg("请输入参数!");
        return;
    }
    var width = "120px";
    var value = "";
    var tagName = "";
    var type = "text";
    var gridName = "";
    if (isEmpty(parms.tagName)) {
        $.core.showErrogMsg("格式化输入框中【tagName】不能为空!");
        return;
    }
    if (isEmpty(parms.gridName)) {
        $.core.showErrogMsg("格式化输入框中【gridName】不能为空!");
        return;
    }
    if (undefined != parms.value) {
        value = parms.value;
    }
    if (undefined != parms.type) {
        type = parms.type;
    }
    if (undefined != parms.width) {
        width = parms.width;
    }
    tagName = parms.tagName;
    gridName = parms.gridName;
    var opt = "";
    if (undefined == parms.tagId) {
        opt = "<input style='color: #ff0000;width: " + width + "'  type='" + type + "' class='k-textbox' name='" + tagName + "'  style='color:red;' value='" + value + "' onchange='textChangeGrid(this,\"" + gridName + "\");'>";
    } else {
        opt = "<input style='color: #ff0000;width: " + width + "' type='" + type + "' class='k-textbox' name='" + tagName + "'  style='color:red;' value='" + value + "' id='" + parms.tagId + "' onchange='textChangeGrid(this,\"" + gridName + "\");'>";
    }
    return opt;
}

// 格式化复选框
function formatCheckBox(parms) {
    // 调用的时候要注意再template中将值初始化为N
    if (parms == undefined) {
        $.core.showErrogMsg("请输入参数!");
        return;
    }
    var tagName = "";
    var tagId = "";
    var gridName = "";
    if (isEmpty(parms.tagName)) {
        $.core.showErrogMsg("格式化复选框中【tagName】不能为空!");
        return;
    }

    if (isEmpty(parms.tagId)) {
        $.core.showErrogMsg("格式化复选框中【tagId】不能为空!");
        return;
    }
    if (isEmpty(parms.gridName)) {
        $.core.showErrogMsg("格式化复选框中【gridName】不能为空!");
        return;
    }
    gridName = parms.gridName;
    tagId = parms.tagId;
    tagName = parms.tagName;
    var opt = "";
    opt = '<input onchange="checkBoxChangeGrid(this,' + gridName + ')" type="checkbox" name="' + tagName + '" class="k-checkbox" data-type="ck" id="' + tagId + '" /><label for="' + tagId + '" class="k-checkbox-label" ></label>';
    return opt;
}

function textChangeGrid(c, gridName) {
    var b = $("#" + gridName).data("kendoGrid").dataItem($(c).closest("tr"));
    b[$(c).attr("name")] = $(c).val()
}

function checkBoxChangeGrid(c, gridName) {
    var b = $(gridName).data("kendoGrid").dataItem($(c).closest("tr"));
    if ($(c).is(':checked')) {
        b[$(c).attr("name")] = "Y";
    } else {
        b[$(c).attr("name")] = "N";
    }
}

function isNotEmpty(value) {
    if (value != undefined && value != "" && value != null) {
        return true;
    } else {
        return false;
    }
}

function isEmpty(value) {
    if (value != undefined && value != "" && value != null) {
        return false;
    } else {
        return true;
    }
}

$(function () {
    $("body").find("form").each(function () {
        $(this).validationEngine("attach");
    });
    kendo.init($(document.body));
});

// 表格数据源初始化的时候移除掉commond的链接效果
function gridOnDataBound() {
    $(document.body).find("tr").each(function () {
        $(this).css("height", "0px")
    })
    $(document.body).find("td a").not(".k-icon").each(function () {
        $(this).css("padding-right", "10px");
        $(this).removeClass("k-button");
        $(this).removeClass("k-button-icontext");
    });
    $(document.body).find("td button").each(function () {
        $(this).css("background-color", "transparent");
        $(this).css("border", "0px");
        $(this).css("padding", "0px");
        $(this).css("min-width", "0");
    });
}


// 删除
function actionInfo(params) {
    var url = params.url;
    var gridName = params.gridName;
    if (isEmpty(url)) {
        $.core.showErrogMsg("url不能为空！");
        return;
    }
    $.core.showLoading();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        contentTdype: "application/json",
        data: {
            ids: params.ids
        },
        success: function (res) {
            $.core.hideLoading();
            if (res.status) {
                if(isNotEmpty(gridName)){
                    if (params.tree) {
                        $.core.refreshTree(gridName);
                    } else {
                        $.core.refresh(gridName)
                    }
                }
                $.core.showMsg(res.msg);
                params.callback && params.callback()
            } else {
                $.core.showErrogMsg(res.msg);
            }
        }, error: function (res) {
            $.core.hideLoading();
            $.core.showErrogMsg("网络错误!")
        }
    });
}

/**
 * 批量删除
 * @param params
 */
function batchActionByIds(params) {
    var url = params.url;
    var gridName = params.gridName;
    if (isEmpty(url)) {
        $.core.showErrogMsg("url不能为空！");
        return;
    }
    if (isEmpty(gridName)) {
        $.core.showErrogMsg("gridName不能为空！");
        return;
    }
    var grid = $("#" + gridName).data("kendoGrid");
    var checked = grid.getChecked();
    if (checked != "" && checked.length >= 1) {
        var ids = [];
        for (var i = 0; i < checked.length; i++) {
            ids.push(checked[i].id);
        }
        ids = ids.join(",")
        actionInfo({
            url: url,
            gridName: gridName,
            ids: ids,
            callback: params.callback
        })
    } else {
        $.core.showErrogMsg("请选择需要操作的数据！")
    }
}

/**
 * 通过id获取模型数据
 * @param id
 * @param url
 * @param callback 回调函数
 */
function getDataInfoById(url, callback) {
    $.core.showLoading();
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        contentType: "application/json",
        success: function (res) {
            $.core.hideLoading();
            callback && callback(res)
        }
    })
}

/**
 * 设置为不可输入
 * @param id
 */
function readOnlyById(id) {
    $("#" + id).attr("readonly", "readonly");
    $("#" + id).css("background-color", "#eee");
}