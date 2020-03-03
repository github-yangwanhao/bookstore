package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.dto.AddUserDto;
import cn.yangwanhao.bookstore.vo.LoginUserVo;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/29 19:13
 */

public interface UserService {

    /**
     * Description: 解锁用户，清零pwd_error_count
     * @return
     * @author 杨万浩
     * @createDate 2019/11/29 19:16
     */
    Integer unlockAccount();

    /**
     * Description: 清零一个用户的pwdErrorCount
     * @param userId 用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/11/30 14:52
     */
    Integer resetPwdErrorCount(Long userId);

    /**
     * Description: 用户密码错误次数+1
     * @param userId userId
     * @return num
     * @author 杨万浩
     * @date 2020/3/3 21:51
     */
    Integer addPwdErrorCount(Long userId);

    /**
     * Description: 锁定账户
     * @param userId userId
     * @return num
     * @author 杨万浩
     * @date 2020/3/3 21:52
     */
    Integer lockAccount(Long userId);

    /**
     * Description: 更改最后登录ip
     * @param userId userId
     * @param ipAddress ip地址
     * @return num
     * @author 杨万浩
     * @date 2020/3/3 22:00
     */
    Integer updateLastLoginIp(Long userId, String ipAddress);

    /**
     * Description: 检查登录名是否已经存在
     * @param loginName 登录名
     * @return 存在true/不存在false
     * @author 杨万浩
     * @date 2020/2/27 9:39
     */
    Boolean checkLoginName(String loginName);


    /**
     * Description: 添加一个用户(后台添加管理员/前台用户注册)
     * @param dto dto
     * @return num
     * @author 杨万浩
     * @date 2020/2/27 9:50
     */
    Integer addUser(AddUserDto dto);

    /**
     * Description: 根据用户名和用户类型返回密码
     * @param loginName 登录名
     * @param loginType 登录用户类型
     * @return
     * @author 杨万浩
     * @date 2020/2/27 15:36
     */
    LoginUserVo getPasswordByLoginNameAndLoginType(String loginName, Integer loginType);

    /**
     * Description: 通过id获取角色列表
     * @param userId 用户id
     * @return string list
     * @author 杨万浩
     * @date 2020/3/3 14:49
     */
    List<String> getRolesByUserId(Long userId);

}
