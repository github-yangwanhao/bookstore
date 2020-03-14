package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.dto.ResetPasswordDto;
import cn.yangwanhao.bookstore.dto.UserInfoDto;
import cn.yangwanhao.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨万浩
 * @description AdminUserController类
 * @date 2020/3/14 11
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMessage<Integer> updateUserInfo(@RequestBody UserInfoDto dto, HttpServletRequest request) {
        dto.setUserId(super.getLoginUserId(request));
        return ResponseMessage.handleResult(userService.updateUserInfo(dto));
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResponseMessage<Integer> resetPassword(@RequestBody ResetPasswordDto dto, HttpServletRequest request) {
        return ResponseMessage.handleResult(userService.resetPassword(dto, super.getLoginUserId(request)));
    }

}
