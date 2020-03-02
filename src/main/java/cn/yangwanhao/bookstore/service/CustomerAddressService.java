package cn.yangwanhao.bookstore.service;

import cn.yangwanhao.bookstore.common.support.BasePageDto;
import cn.yangwanhao.bookstore.dto.CustomerAddressDto;
import cn.yangwanhao.bookstore.vo.CustomerAddressListVo;
import cn.yangwanhao.bookstore.vo.CustomerAddressVo;
import com.github.pagehelper.PageInfo;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 14:09
 */

public interface CustomerAddressService {

    /**
     * Description: 添加一个收货地址
     * @param dto dto
     * @param loginUserId 当前登录人id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 14:11
     */
    Integer addOneAddress(CustomerAddressDto dto, Long loginUserId);

    /**
     * Description: 查询用户所有的收货地址
     * @param dto dto
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 14:22
     */
    PageInfo<CustomerAddressListVo> listUserAddresses(BasePageDto dto, Long loginUserId);

    /**
     * Description: 删除用户的一个收货地址
     * @param addressId 地址id
     * @param userId 用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 14:38
     */
    Integer removeAddress(Long addressId, Long userId);

    /**
     * Description: 获取收货地址信息
     * @param addressId 地址id
     * @param userId 用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 14:49
     */
    CustomerAddressVo getUserAddress(Long addressId, Long userId);

    /**
     * Description: 修改地址
     * @param dto dto
     * @param loginUserId 登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 15:29
     */
    Integer modifyAddress(CustomerAddressDto dto, Long loginUserId);

    /**
     * Description: 修改是否置顶
     * @param addressId 地址id
     * @param isDefault 是否为默认:1是0否
     * @param loginUserId 登录人id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/7 15:30
     */
    Integer modifyAddressIsDefault(Long addressId, Integer isDefault, Long loginUserId);

}
