package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.LoginTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.PublicUtils;
import cn.yangwanhao.bookstore.common.util.ValidateUtils;
import cn.yangwanhao.bookstore.dto.AddUserDto;
import cn.yangwanhao.bookstore.dto.ResetPasswordDto;
import cn.yangwanhao.bookstore.dto.UserInfoDto;
import cn.yangwanhao.bookstore.entity.User;
import cn.yangwanhao.bookstore.entity.UserExample;
import cn.yangwanhao.bookstore.mapper.UserMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomUserMapper;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import cn.yangwanhao.bookstore.vo.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/29 19:13
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private CustomUserMapper customUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Integer unlockAccount() {
        User user = new User();
        user.setPwdErrorCount(0);
        user.setIsLocked(0);
        return userMapper.updateByExampleSelective(user, new UserExample());
    }

    @Override
    public Integer resetPwdErrorCount(Long userId) {
        User user = new User();
        user.setPwdErrorCount(0);
        user.setId(userId);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer addPwdErrorCount(Long userId) {
        return customUserMapper.addPwdErrorCount(userId);
    }

    @Override
    public Integer lockAccount(Long userId) {
        User user = new User();
        user.setIsLocked(GlobalConstant.YES);
        user.setId(userId);
        user.setLastUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer updateLastLoginIp(Long userId, String ipAddress) {
        User user = new User();
        user.setId(userId);
        user.setLastLoginIp(ipAddress);
        user.setLastUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Boolean checkLoginName(String loginName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(example);
        return PublicUtils.isNotEmpty(users);
    }

    @Override
    public Integer addUser(AddUserDto dto) {
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
        if (this.checkLoginName(dto.getLoginname())) {
            throw new GlobalException(ErrorCodeEnum.U5004001);
        }
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
        User info = new User();
        info.setLoginname(dto.getLoginname());
        // 密码已经加密
        info.setPassword(passwordEncoder.encode(dto.getPassword()));
        info.setRealname(dto.getRealname());
        info.setSex(dto.getSex());
        info.setBirthday(dto.getBirthday());
        info.setPhone(dto.getPhone());
        info.setEmail(dto.getEmail());
        // 初始密码错误次数0次
        info.setPwdErrorCount(GlobalConstant.Number.ZERO_INT);
        // 初始账户是否锁定
        info.setIsLocked(GlobalConstant.NO);
        info.setUserType(dto.getUserType());
        Date date1 = new Date();
        info.setLastLoginIp(GlobalConstant.INIT_LOGIN_IP);
        info.setLastLoginTime(date1);
        info.setCreateTime(date1);
        info.setLastUpdateTime(date1);
        return userMapper.insertSelective(info);
    }

    @Override
    public LoginUserVo getPasswordByLoginNameAndLoginType(String loginName, Integer loginType) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        criteria.andUserTypeEqualTo(loginType);
        List<User> users = userMapper.selectByExample(example);
        if (PublicUtils.isEmpty(users)) {
            // throw new GlobalException(ErrorCodeEnum.U5009001);
            return null;
        }
        User user = users.get(0);
        LoginUserVo vo = new LoginUserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    @Override
    public List<String> getRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public String getPasswordById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new GlobalException(ErrorCodeEnum.U5003004);
        }
        return user.getPassword();
    }

    @Override
    public Integer resetPassword(ResetPasswordDto dto, Long loginUserId) {
        String oldPassword = dto.getOldPassword();
        String newPassword = dto.getNewPassword();
        String reNewPassword = dto.getReNewPassword();
        if (!newPassword.equals(reNewPassword)) {
            throw new GlobalException(ErrorCodeEnum.U5009003);
        }
        String dbPassword = this.getPasswordById(loginUserId);
        if (!passwordEncoder.matches(oldPassword, dbPassword)) {
            throw new GlobalException(ErrorCodeEnum.U5009012);
        }
        if (passwordEncoder.matches(newPassword, dbPassword)) {
            throw new GlobalException(ErrorCodeEnum.U5009004);
        }
        User user = new User();
        user.setId(loginUserId);
        user.setPassword(passwordEncoder.encode(newPassword));
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserInfoVo getUserInfoById(Long loginUserId) throws ParseException {
        UserInfoVo vo = customUserMapper.getUserInfoById(loginUserId);
        if (vo.getEmail().equals(GlobalConstant.INIT_EMAIL)) {
            vo.setEmail(null);
        }
        if (vo.getRealname().equals(GlobalConstant.INIT_REALNAME)) {
            vo.setRealname(null);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (vo.getBirthday().equals(sdf.parse(GlobalConstant.INIT_BIRTHDAY))) {
            vo.setBirthday(null);
        }
        return vo;
    }

    @Override
    public Integer updateUserInfo(UserInfoDto dto) {
        if (!ValidateUtils.isMobileNumber(dto.getPhone())) {
            throw new GlobalException(ErrorCodeEnum.U5002001);
        }
        if (!ValidateUtils.isEmail(dto.getEmail())) {
            throw new GlobalException(ErrorCodeEnum.U5002002);
        }
        User user = new User();
        user.setId(dto.getUserId());
        user.setRealname(dto.getRealname());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setSex(dto.getSex());
        user.setBirthday(dto.getBirthday());
        user.setLastUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
