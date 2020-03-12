package cn.yangwanhao.bookstore.mapper.custom;

import cn.yangwanhao.bookstore.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomUserMapper {

    /**
     * Description: 通过userId获取角色列表
     * @param userId user id
     * @return string list
     * @author 杨万浩
     * @date 2020/3/3 14:55
     */
    List<String> selectRolesByUserId(@Param("userId") Long userId);

    /**
     * Description: 增加一次密码错误次数
     * @param userId userId
     * @return num
     * @author 杨万浩
     * @date 2020/3/3 21:53
     */
    Integer addPwdErrorCount(@Param("userId") Long userId);

    /**
     * Description: 通过当前登录用户获取userId
     * @param loginUserId loginUserId
     * @return
     * @author 杨万浩
     * @date 2020/3/11 8:07
     */
    UserInfoVo getUserInfoById(@Param("loginUserId") Long loginUserId);

}