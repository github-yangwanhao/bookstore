$(function () {
    //修改个人信息
    $('#updateUserNameButton').click(function () {
        $("#updateUserNameButton").attr("disabled",true);
        var realname = $("#realname").val();
        var phone = $('#phone').val();
        var email = $('#email').val();
        if (validUserNameForUpdate(realname, phone, email)) {
            var data = {
                "realname": realname,
                "phone": phone,
                "email": email
            };
            $.ajax({
                type: "POST",
                url: "/admin/user/updateUserInfo",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (r) {
                    $("#updateUserNameButton").attr("disabled",false);
                    if (r.code == 200) {
                        swal("修改成功",{
                            icon: "success"
                        });
                    } else {
                        swal("修改失败",{
                            icon: "error"
                        });
                    }
                },
                error: function (r) {
                    $("#updateUserNameButton").attr("disabled",false);
                    swal(r.responseJSON.message,{
                        icon: "error"
                    });
                }
            });
        }
        $("#updateUserNameButton").attr("disabled",false);
    });
    //修改密码
    $('#updatePasswordButton').click(function () {
        $("#updatePasswordButton").attr("disabled",true);
        var oldPassword = $('#oldPassword').val();
        var newPassword = $('#newPassword').val();
        var reNewPassword = $('#reNewPassword').val();
        if (validPasswordForUpdate(oldPassword, newPassword, reNewPassword)) {
            var data = {
              "oldPassword": oldPassword,
              "newPassword": newPassword,
              "reNewPassword": reNewPassword
            };
            $.ajax({
                type: "POST",
                url: "/admin/user/resetPassword",
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (r) {
                    $("#updatePasswordButton").attr("disabled",false);
                    if (r.code == 200) {
                        swal("修改成功",{
                            icon: "success"
                        });
                        window.location.href = '/admin/login/logout';
                    } else {
                        swal("修改失败",{
                            icon: "error"
                        });
                    }
                },
                error: function (r) {
                    $("#updatePasswordButton").attr("disabled",false);
                    swal(r.responseJSON.message,{
                        icon: "error"
                    });
                }
            });
        }
        $("#updatePasswordButton").attr("disabled",false);
    });
})

/**
 * 名称验证
 */
function validUserNameForUpdate(realname, phone, email) {
    if (!isEmpty(realname, "真实姓名不能为空")) {
        return false;
    }
    if (!isEmpty(phone, "手机号码不能为空")) {
        return false;
    }
    if (!isEmpty(email, "邮箱地址不能为空")) {
        return false;
    }
    return true;
}

/**
 * 密码验证
 */
function validPasswordForUpdate(oldPassword, newPassword, reNewPassword) {
    if (!isEmpty(oldPassword, "旧密码不能为空")) {
        return false;
    }
    if (!isEmpty(newPassword, "新密码不能为空")) {
        return false;
    }
    if (!isEmpty(reNewPassword, "确认密码不能为空")) {
        return false;
    }
    if (newPassword !== reNewPassword) {
        swal("两次输入的密码不一致",{
           icon: "error"
        });
        return false;
    }
    return true;
}

function isEmpty(value, message) {
    if (isNull(value) || value.trim().length < 1) {
        swal(message,{
           icon: "error"
        });
        return false;
    }
    return true;
}
