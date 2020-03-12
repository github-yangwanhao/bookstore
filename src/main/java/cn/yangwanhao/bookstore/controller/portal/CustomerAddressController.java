package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.dto.CustomerAddressDto;
import cn.yangwanhao.bookstore.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨万浩
 * @description CustomerAddressController类
 * @date 2020/3/11 08
 */
@Controller
@RequestMapping("/store/address")
public class CustomerAddressController extends BaseController {

    @Autowired
    private CustomerAddressService customerAddressService;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseMessage<Integer> add(@RequestBody CustomerAddressDto dto, HttpServletRequest request) {
        Integer result = customerAddressService.addOneAddress(dto, super.getLoginUserId(request));
        return ResponseMessage.handleResult(result);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResponseMessage<Integer> delete(@PathVariable("id") Long id, HttpServletRequest request) {
        Integer result = customerAddressService.removeAddress(id, super.getLoginUserId(request));
        return ResponseMessage.handleResult(result);
    }

    @RequestMapping("/makeDefault/{id}")
    @ResponseBody
    public ResponseMessage<Integer> makeDefault(@PathVariable("id") Long id, HttpServletRequest request) {
        Integer result = customerAddressService.modifyAddressIsDefault(id, 1, super.getLoginUserId(request));
        return ResponseMessage.handleResult(result);
    }

}
