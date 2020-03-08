package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.dto.GoodsBooksDto;
import cn.yangwanhao.bookstore.dto.GoodsListDto;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.GoodsListVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨万浩
 * @description AdminGoodsController类
 * @date 2020/3/7 20
 */
@Controller
@RequestMapping("/admin/goods")
public class AdminGoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/save")
    @ResponseBody
    public ResponseMessage<Integer> saveOneGoods(@RequestBody GoodsBooksDto dto, HttpServletRequest request) {
        Integer result = goodsService.addBook(dto, getLoginUserId(request));
        return ResponseMessage.handleResult(result);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseMessage<Integer> updateOneGoods(@RequestBody GoodsBooksDto dto, HttpServletRequest request) {
        Integer result = goodsService.modifyBook(dto, getLoginUserId(request));
        return ResponseMessage.handleResult(result);
    }

    @RequestMapping("/list")
    @ResponseBody
    public ResponseMessage<PageInfo<GoodsListVo>> adminListGoods(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize) {
        GoodsListDto dto = new GoodsListDto();
        dto.setPageNum(pageNum);
        dto.setPageSize(pageSize);
        return ResponseMessage.handleResult(goodsService.listGoods(dto));
    }

    @RequestMapping("/status/{operate}")
    @ResponseBody
    public ResponseMessage<Integer> operateStatus(@RequestBody Long[] ids, @PathVariable("operate") Integer operate
            , HttpServletRequest request) {
        // 1上架 0下架
        if (operate == 1) {
            return ResponseMessage.handleResult(goodsService.onShelfGoods(ids, getLoginUserId(request)));
        } else if (operate == 0) {
            return ResponseMessage.handleResult(goodsService.offShelfGoods(ids, getLoginUserId(request)));
        } else {
            throw new GlobalException(ErrorCodeEnum.G500101);
        }
    }

}
