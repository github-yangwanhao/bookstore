$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/order/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'orderId', index: 'orderId', width: 50, key: true, hidden: true},
            {label: '订单号', name: 'orderNo', index: 'orderNo', width: 120},
            {label: '订单总价', name: 'totalPriceDouble', index: 'totalPriceDouble', width: 60},
            {label: '订单状态', name: 'orderStatusString', index: 'orderStatusString', width: 80},
            {label: '支付方式', name: 'payType', index: 'payType', width: 80,formatter:payTypeFormatter},
            {label: '收件人地址', name: 'address', index: 'address', width: 10, hidden: true},
            {label: '创建时间', name: 'createTime', index: 'createTime', width: 120},
            {label: '操作', name: 'createTime', index: 'createTime', width: 120, formatter: operateFormatter}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.pageNum",
            total: "data.pages",
            records: "data.total"
        },
        prmNames: {
            page: "page",
            rows: "limit"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function operateFormatter(cellvalue, rowObject) {
        return "<a href=\'##\' onclick=openOrderItems(" + rowObject.rowId + ")>查看订单信息</a>" +
            "<br>" +
            "<a href=\'##\' onclick=openExpressInfo(" + rowObject.rowId + ")>查看收件人信息</a>";
    }

    function payTypeFormatter(cellvalue) {
        //支付类型:0.无 1.支付宝支付
        if (cellvalue == 0) {
            return "无";
        }
        if (cellvalue == 1) {
            return "支付宝沙箱支付";
        }
    }

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

});

/**
 * jqGrid重新加载
 */
function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/**
 * 查看订单项信息
 * @param orderId
 */
function openOrderItems(orderId) {
    $('.modal-title').html('订单详情');
    var rowData = $("#jqGrid").jqGrid("getRowData", orderId);
    $.ajax({
        type: 'GET',//方法类型
        url: '/admin/order/order-goods/' + rowData.orderNo,
        contentType: 'application/json',
        success: function (result) {
            if (result.code == 200) {
                $('#orderItemModal').modal('show');
                var itemString = '';
                for (i = 0; i < result.data.length; i++) {
                    itemString += result.data[i].goodsTitle + ' x ' + result.data[i].goodsNum + ' 商品编号 ' + result.data[i].goodsId + ";<br>";
                }
                $("#orderItemString").html(itemString);
            } else {
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
}

/**
 * 查看收件人信息
 * @param orderId
 */
function openExpressInfo(orderId) {
    var rowData = $("#jqGrid").jqGrid("getRowData", orderId);
    $('.modal-title').html('收件信息');
    $('#expressInfoModal').modal('show');
    $("#userAddressInfo").html(rowData.address);
}

function startOrder() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要发货订单吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/order/start",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 200) {
                            swal("发货成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    },
                    error: function (result) {
                        swal(result.responseJSON.message, {
                            icon: "error",
                        });
                    }
                });
            }
        }
    )
    ;
}


function reset() {
    $("#totalPrice").val(0);
    $("#userAddress").val('');
    $('#edit-error-msg').css("display", "none");
}