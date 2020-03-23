package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.dto.GoodsSearchDto;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.GoodsListVo;
import cn.yangwanhao.bookstore.vo.GoodsSearchListVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Random;

/**
 * @author 杨万浩
 * @description PortalGoodsController类
 * @date 2020/3/8 16
 */
@Controller
@RequestMapping("/store/goods")
public class PortalGoodsController {

    @Autowired
    private GoodsService goodsService;
    @Value("${pic.host}")
    private String picHost;

    @RequestMapping("/search")
    public String search(GoodsSearchDto dto, Model model) {
        PageInfo<GoodsSearchListVo> page = goodsService.searchGoods(dto);
        model.addAttribute("page", page);
        model.addAttribute("keyword", dto.getQ());
        return "mall/search";
    }

    @RequestMapping("/detail/{id}")
    public String goodsDetail(@PathVariable("id") Long id, Model model) {
        GoodsVo vo = goodsService.getGoodsInfo(id);
        vo.setImages(picHost + vo.getImages());
        // 生成一个随机数,用于展示划掉横线的更高的价格
        Random r = new Random();
        model.addAttribute("vo", vo);
        model.addAttribute("random", r.nextInt(15));
        return "mall/detail";
    }

}
