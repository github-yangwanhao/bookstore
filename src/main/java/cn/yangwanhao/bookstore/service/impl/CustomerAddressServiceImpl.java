package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.support.BasePageDto;
import cn.yangwanhao.bookstore.dto.CustomerAddressDto;
import cn.yangwanhao.bookstore.entity.CustomerAddress;
import cn.yangwanhao.bookstore.entity.CustomerAddressExample;
import cn.yangwanhao.bookstore.mapper.CustomerAddressMapper;
import cn.yangwanhao.bookstore.service.CustomerAddressService;
import cn.yangwanhao.bookstore.vo.CustomerAddressListVo;
import cn.yangwanhao.bookstore.vo.CustomerAddressVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 14:09
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Resource
    private CustomerAddressMapper customerAddressMapper;

    @Override
    public Integer addOneAddress(CustomerAddressDto dto, Long loginUserId) {
        CustomerAddress customerAddress = new CustomerAddress();
        BeanUtils.copyProperties(dto, customerAddress);
        customerAddress.setCustomerId(loginUserId);
        // 如果是默认地址,那么需要判断之前是否有默认地址
        if (GlobalConstant.YES == dto.getIsDefault()) {
            updateSetIsDefault(loginUserId);
        }
        return customerAddressMapper.insertSelective(customerAddress);
    }

    @Override
    public PageInfo<CustomerAddressListVo> listUserAddresses(BasePageDto dto, Long loginUserId) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        CustomerAddressExample example = new CustomerAddressExample();
        // 默认地址在最上边,其他的按照id排序
        example.setOrderByClause("is_default DESC, id ASC");
        CustomerAddressExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(loginUserId);
        List<CustomerAddress> customerAddressList = customerAddressMapper.selectByExample(example);
        List<CustomerAddressListVo> vos = new ArrayList<>(customerAddressList.size());
        for (CustomerAddress customerAddress : customerAddressList) {
            CustomerAddressListVo vo = new CustomerAddressListVo();
            BeanUtils.copyProperties(vo, customerAddress);
            vos.add(vo);
        }
        return new PageInfo<>(vos);
    }

    @Override
    public List<CustomerAddressListVo> listUserAddresses(Long loginUserId) {
        CustomerAddressExample example = new CustomerAddressExample();
        // 默认地址在最上边,其他的按照id排序
        example.setOrderByClause("is_default DESC, id ASC");
        CustomerAddressExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(loginUserId);
        List<CustomerAddress> customerAddressList = customerAddressMapper.selectByExample(example);
        List<CustomerAddressListVo> vos = new ArrayList<>(customerAddressList.size());
        for (CustomerAddress customerAddress : customerAddressList) {
            CustomerAddressListVo vo = new CustomerAddressListVo();
            BeanUtils.copyProperties(customerAddress, vo);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public Integer removeAddress(Long addressId, Long userId) {
        CustomerAddressExample example = new CustomerAddressExample();
        CustomerAddressExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerIdEqualTo(userId);
        criteria.andIdEqualTo(addressId);
        return customerAddressMapper.deleteByExample(example);
    }

    @Override
    public CustomerAddressVo getUserAddress(Long addressId, Long userId) {
        CustomerAddressExample example = new CustomerAddressExample();
        CustomerAddressExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(addressId);
        criteria.andCustomerIdEqualTo(userId);
        List<CustomerAddress> customerAddressList = customerAddressMapper.selectByExample(example);
        // 如果集合的长度为0
        if (customerAddressList != null || customerAddressList.size() == 0) {
            throw new GlobalException(ErrorCodeEnum.U5003002);
        }
        CustomerAddressVo vo = new CustomerAddressVo();
        BeanUtils.copyProperties(vo, customerAddressList.get(0));
        return vo;
    }

    @Override
    public Integer modifyAddress(CustomerAddressDto dto, Long loginUserId) {
        CustomerAddress customerAddress = new CustomerAddress();
        BeanUtils.copyProperties(dto, customerAddress);
        customerAddress.setCustomerId(loginUserId);
        // 如果是设为默认,那么需要判断之前是否有默认地址
        if (GlobalConstant.YES == dto.getIsDefault()) {
            updateSetIsDefault(loginUserId);
        }
        return customerAddressMapper.updateByPrimaryKeySelective(customerAddress);
    }

    @Override
    public Integer modifyAddressIsDefault(Long addressId, Integer isDefault, Long loginUserId) {
        // 如果该地址的isDefault与isDefault想同,抛出重复操作异常
        CustomerAddress customerAddress = customerAddressMapper.selectByPrimaryKey(addressId);
        if (customerAddress.getIsDefault().equals(isDefault)) {
            throw new GlobalException(ErrorCodeEnum.I5009006);
        }
        // 如果是设为默认,那么需要判断之前是否有默认地址
        if (GlobalConstant.YES == isDefault) {
           updateSetIsDefault(loginUserId);
        }
        // 设置新的默认地址/取消默认地址
        CustomerAddress address = new CustomerAddress();
        address.setId(addressId);
        address.setIsDefault(isDefault);
        return customerAddressMapper.updateByPrimaryKeySelective(address);
    }

    /**
     * Description: 当用户新增/修改/设为默认 收过地址的时候,查看用户是否有收货地址,如果有那么将原来的默认收货地址取消默认
     * @param loginUserId 当前登录用户di
     * @author 杨万浩
     * @createDate 2019/12/9 11:46
     */
    private void updateSetIsDefault(Long loginUserId) {
        // 找到原来的那个默认地址
        CustomerAddressExample defaultExample = new CustomerAddressExample();
        CustomerAddressExample.Criteria criteria = defaultExample.createCriteria();
        criteria.andCustomerIdEqualTo(loginUserId);
        criteria.andIsDefaultEqualTo(GlobalConstant.YES);
        List<CustomerAddress> customerAddressList = customerAddressMapper.selectByExample(defaultExample);
        // 存在默认地址
        if (customerAddressList != null && customerAddressList.size() != 0) {
            CustomerAddress lastDefaultAddress = new CustomerAddress();
            lastDefaultAddress.setId(customerAddressList.get(0).getId());
            lastDefaultAddress.setIsDefault(GlobalConstant.NO);
            // 修改为不是默认地址
            customerAddressMapper.updateByPrimaryKeySelective(lastDefaultAddress);
        }
    }
}
