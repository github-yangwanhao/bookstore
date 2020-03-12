package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.LoginTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.ValidateUtils;
import cn.yangwanhao.bookstore.dto.AddUserDto;
import cn.yangwanhao.bookstore.dto.ResetPasswordDto;
import cn.yangwanhao.bookstore.dto.UserInfoDto;
import cn.yangwanhao.bookstore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 杨万浩
 * @description PortalUserController类
 * @date 2020/3/10 09
 */
@Controller
@RequestMapping("/store/user")
public class PortalUserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseMessage<Integer> portalRegister(@RequestBody AddUserDto dto) {
        return ResponseMessage.handleResult(userService.addUser(dto));
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResponseMessage<Integer> resetPassword(@RequestBody ResetPasswordDto dto, HttpServletRequest request) {
        return ResponseMessage.handleResult(userService.resetPassword(dto, super.getLoginUserId(request)));
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResponseMessage<Integer> updateUserInfo(@RequestBody UserInfoDto dto, HttpServletRequest request) {
        dto.setUserId(super.getLoginUserId(request));
        return ResponseMessage.handleResult(userService.updateUserInfo(dto));
    }

}
