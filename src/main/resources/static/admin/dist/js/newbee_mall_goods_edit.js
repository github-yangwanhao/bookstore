//KindEditor变量
var editor;

$(function () {

    //详情编辑器
    editor = KindEditor.create('textarea[id="editor"]', {
        items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'multiimage',
            'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink'],
        uploadJson: '/admin/pic/upload',
        filePostName: 'file'
    });

    new AjaxUpload('#uploadGoodsCoverImg', {
        action: '/admin/pic/upload',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.code == 200) {
                $("#picName").val(r.data.originalImg);
                $("#goodsCoverImg").attr("src", 'http://121.36.150.173:8888/'+r.data.thumbnailImg);
                $("#goodsCoverImg").attr("style", "width: 128px;height: 128px;displ4ay:block;");
                return false;
            } else {
                alert("error");
            }
        }
    });
});

function confirmData () {
    var category = $('#category option:selected').val();
    var title = $('#title').val();
    var priceDouble = $('#priceDouble').val();
    var stock = $('#stock').val();
    var tags = $('#tags').val();
    var author = $('#author').val();
    var publisher = $('#publisher').val();
    var isbn = $('#isbn').val();
    var images = $('#picName').val();
    var detail = editor.html();
    if (isNull(category)) {
        swal("请选择分类", {
            icon: "error",
        });
        return;
    }
    if (isNull(title)) {
        swal("请输入商品名称", {
            icon: "error",
        });
        return;
    }
    if (!validLength(title, 100)) {
        swal("请输入商品名称", {
            icon: "error",
        });
        return;
    }
    if (isNull(tags)) {
        swal("请输入商品小标签", {
            icon: "error",
        });
        return;
    }
    if (!validLength(tags, 100)) {
        swal("标签过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(priceDouble) || priceDouble < 0) {
        swal("请输入正确的商品价格", {
            icon: "error",
        });
        return;
    }
    if (isNull(stock) || stock < 0) {
        swal("请输入商品库存数", {
            icon: "error",
        });
        return;
    }
    if (isNull(detail)) {
        swal("请输入商品介绍", {
            icon: "error",
        });
        return;
    }
    if (isNull(author)) {
        swal("请输入书籍作者", {
            icon: "error",
        });
        return;
    }
    if (isNull(publisher)) {
        swal("请输入出版社名称", {
            icon: "error",
        });
        return;
    }
    if (isNull(isbn)) {
        swal("请输入书籍ISBN", {
            icon: "error",
        });
        return;
    }
    if (isNull(images)) {
        swal("请上传商品主图", {
            icon: "error",
        });
        return;
    }
}

$('#saveButton').click(function () {
    confirmData();
    var id = $('#id').val();
    var category = $('#category option:selected').val();
    var title = $('#title').val();
    var priceDouble = $('#priceDouble').val();
    var stock = $('#stock').val();
    var tags = $('#tags').val();
    var author = $('#author').val();
    var publisher = $('#publisher').val();
    var isbn = $('#isbn').val();
    var images = $('#picName').val();
    var detail = editor.html();
    var url = '/admin/goods/save';
    var swlMessage = '保存成功';
    var data = {
        "priceDouble": priceDouble,
        "stock": stock,
        "category": category,
        "title": title,
        "images": images,
        "tags": tags,
        "detail": detail,
        "author": author,
        "publisher": publisher,
        "isbn": isbn,
    };
    if (id > 0) {
        url = '/admin/goods/update';
        swlMessage = '修改成功';
        data = {
            "id": id,
            "priceDouble": priceDouble,
            "stock": stock,
            "category": category,
            "title": title,
            "images": images,
            "tags": tags,
            "detail": detail,
            "author": author,
            "publisher": publisher,
            "isbn": isbn,
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.code == 200) {
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回商品列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/page/goods";
                })
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
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/page/goods";
});

/*$('#levelOne').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.resultCode == 200) {
                var levelTwoSelect = '';
                var secondLevelCategories = result.data.secondLevelCategories;
                var length2 = secondLevelCategories.length;
                for (var i = 0; i < length2; i++) {
                    levelTwoSelect += '<option value=\"' + secondLevelCategories[i].categoryId + '\">' + secondLevelCategories[i].categoryName + '</option>';
                }
                $('#levelTwo').html(levelTwoSelect);
                var levelThreeSelect = '';
                var thirdLevelCategories = result.data.thirdLevelCategories;
                var length3 = thirdLevelCategories.length;
                for (var i = 0; i < length3; i++) {
                    levelThreeSelect += '<option value=\"' + thirdLevelCategories[i].categoryId + '\">' + thirdLevelCategories[i].categoryName + '</option>';
                }
                $('#levelThree').html(levelThreeSelect);
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
});*/

/*$('#levelTwo').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.resultCode == 200) {
                var levelThreeSelect = '';
                var thirdLevelCategories = result.data.thirdLevelCategories;
                var length = thirdLevelCategories.length;
                for (var i = 0; i < length; i++) {
                    levelThreeSelect += '<option value=\"' + thirdLevelCategories[i].categoryId + '\">' + thirdLevelCategories[i].categoryName + '</option>';
                }
                $('#levelThree').html(levelThreeSelect);
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
});*/
