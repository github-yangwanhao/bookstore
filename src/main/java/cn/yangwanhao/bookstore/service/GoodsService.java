package cn.yangwanhao.bookstore.service;

import cn.yangwanhao.bookstore.dto.GoodsBooksDto;
import cn.yangwanhao.bookstore.dto.GoodsListDto;
import cn.yangwanhao.bookstore.dto.GoodsSearchDto;
import cn.yangwanhao.bookstore.vo.GoodsListVo;
import cn.yangwanhao.bookstore.vo.GoodsSearchListVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import com.github.pagehelper.PageInfo;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 17:48
 */

public interface GoodsService {

    /**
     * Description: 通过id获取商品信息
     * @param goodsId 商品id
     * @return vo
     * @author 杨万浩
     * @createDate 2019/12/9 17:48
     */
    GoodsVo getGoodsInfo(Long goodsId);
    
    /**
     * Description: 添加一个书本商品
     * @param dto dto
     * @param loginUserId 当前登录人id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 10:37
     */
    Integer addBook(GoodsBooksDto dto, Long loginUserId);

    /**
     * Description: 修改一个书本商品
     * @param dto dto
     * @param loginUserId 当前登录人Id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 17:04
     */
    Integer modifyBook(GoodsBooksDto dto, Long loginUserId);

    /**
     * Description: 修改商品库存数
     * @param goodsId 商品id
     * @param stock 库存数
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 17:36
     */
    Integer modifyGoodsStock(Long goodsId, Integer stock, Long loginUserId);

    /**
     * Description: 上架商品
     * @param goodsIds 商品id数组
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 17:42
     */
    Integer onShelfGoods(Long[] goodsIds, Long loginUserId);

    /**
     * Description: 下架商品
     * @param goodsIds 商品id数组
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 17:42
     */
    Integer offShelfGoods(Long[] goodsIds, Long loginUserId);

    /**
     * Description: 删除商品
     * @param goodsId 商品id
     * @param loginUserId 当前登录用户id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 17:42
     */
    Integer removeGoods(Long goodsId, Long loginUserId);

    /**
     * Description: 查看商品列表
     * @param dto dto
     * @return
     * @author 杨万浩
     * @createDate 2019/12/11 20:16
     */
    PageInfo<GoodsListVo> listGoods(GoodsListDto dto);

    /**
     * Description: 查看该分类下是否有商品
     * @param categoryId 分类id
     * @return 有:true/无:false
     * @author 杨万浩
     * @date 2020/2/26 17:50
     */
    Boolean categoryHasGoods(Integer categoryId);

    /**
     * Description: 商品搜索
     * @param dto dto
     * @return
     * @author 杨万浩
     * @date 2020/3/8 16:15
     */
    PageInfo<GoodsSearchListVo> searchGoods(GoodsSearchDto dto);

}
