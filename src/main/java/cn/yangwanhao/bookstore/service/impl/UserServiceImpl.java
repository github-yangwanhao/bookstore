package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.PublicUtils;
import cn.yangwanhao.bookstore.dto.AddUserDto;
import cn.yangwanhao.bookstore.entity.User;
import cn.yangwanhao.bookstore.entity.UserExample;
import cn.yangwanhao.bookstore.mapper.UserMapper;
import cn.yangwanhao.bookstore.service.UserService;
import cn.yangwanhao.bookstore.vo.LoginUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    public Boolean checkLoginName(String loginName) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginnameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(example);
        return PublicUtils.isNotEmpty(users);
    }

    @Override
    public Integer addUser(AddUserDto dto) {
        if (checkLoginName(dto.getLoginname())) {
            throw new GlobalException(ErrorCodeEnum.U5004001);
        }
        User info = new User();
        info.setLoginname(dto.getLoginname());
        // 密码已经加密
        info.setPassword(dto.getPassword());
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
        Date date = new Date();
        info.setLastLoginIp(GlobalConstant.INIT_LOGIN_IP);
        info.setLastLoginTime(date);
        info.setCreateTime(date);
        info.setLastUpdateTime(date);
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
            throw new GlobalException(ErrorCodeEnum.U5009001);
        }
        User user = users.get(0);
        LoginUserVo vo = new LoginUserVo();
        vo.setId(user.getId());
        vo.setLoginName(user.getLoginname());
        vo.setPassword(user.getPassword());
        return vo;
    }
}
