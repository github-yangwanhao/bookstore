package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.LoginTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.ValidateUtils;
import cn.yangwanhao.bookstore.dto.AddUserDto;
import cn.yangwanhao.bookstore.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class PortalUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseMessage<Integer> portalRegister(@RequestBody AddUserDto dto) {
        if (StringUtils.isBlank(dto.getPassword())) {
            throw new GlobalException(ErrorCodeEnum.U5001003);
        }
        if (StringUtils.isBlank(dto.getRePassword())) {
            throw new GlobalException(ErrorCodeEnum.U5001004);
        }
        if (!dto.getPassword().equals(dto.getRePassword())) {
            throw new GlobalException(ErrorCodeEnum.U5009003);
        }
        if (StringUtils.isBlank(dto.getLoginname())) {
            throw new GlobalException(ErrorCodeEnum.U5001006);
        }
        if (!ValidateUtils.isMobileNumber(dto.getPhone())) {
            throw new GlobalException(ErrorCodeEnum.U5002001);
        }
        if (userService.checkLoginName(dto.getLoginname())) {
            throw new GlobalException(ErrorCodeEnum.U5004001);
        }
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        dto.setRealname(GlobalConstant.INIT_REALNAME);
        dto.setEmail(GlobalConstant.INIT_EMAIL);
        dto.setSex(GlobalConstant.INIT_SEX.getKey());
        dto.setUserType(LoginTypeEnum.CUSTOMER.getCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(GlobalConstant.INIT_BIRTHDAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dto.setBirthday(date);
        return ResponseMessage.handleResult(userService.addUser(dto));
    }

}
