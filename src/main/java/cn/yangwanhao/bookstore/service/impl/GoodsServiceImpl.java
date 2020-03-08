package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.GoodsStatusEnum;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.common.util.IdUtils;
import cn.yangwanhao.bookstore.common.util.PicNameUtils;
import cn.yangwanhao.bookstore.dto.GoodsBooksDto;
import cn.yangwanhao.bookstore.dto.GoodsListDto;
import cn.yangwanhao.bookstore.dto.GoodsSearchDto;
import cn.yangwanhao.bookstore.entity.*;
import cn.yangwanhao.bookstore.mapper.GoodsBaseMapper;
import cn.yangwanhao.bookstore.mapper.GoodsBooksMapper;
import cn.yangwanhao.bookstore.mapper.GoodsInfoMapper;
import cn.yangwanhao.bookstore.mapper.custom.CustomGoodsMapper;
import cn.yangwanhao.bookstore.service.CartService;
import cn.yangwanhao.bookstore.service.GoodsService;
import cn.yangwanhao.bookstore.vo.GoodsListVo;
import cn.yangwanhao.bookstore.vo.GoodsSearchListVo;
import cn.yangwanhao.bookstore.vo.GoodsVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 17:48
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomGoodsMapper customGoodsMapper;
    @Resource
    private GoodsBaseMapper goodsBaseMapper;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private GoodsBooksMapper goodsBooksMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${pic.host}")
    private String picHost;

    @Override
    public GoodsVo getGoodsInfo(Long goodsId) {
        GoodsVo vo = customGoodsMapper.selectGoods(goodsId);
        vo.setPriceDouble(BigDecimalUtils.movePointLeft(String.valueOf(vo.getPrice()), 2).doubleValue());
        // 缩略图
        vo.setThumbnailImage(PicNameUtils.getThumbnailName(vo.getImages()));
        return vo;
    }

    @Override
    public Integer addBook(GoodsBooksDto dto, Long loginUserId) {
        int result = 0;
        // 插入goods_base(需要返回生成的id)
        GoodsBase goodsBase = new GoodsBase();
        // id是生成的
        goodsBase.setId(IdUtils.getSnowFlakeId());
        goodsBase.setGoodsStatus(GoodsStatusEnum.NORMAL.getValue());
        goodsBase.setPrice(dto.getPrice());
        goodsBase.setStock(dto.getStock());
        goodsBase.setCategory(dto.getCategory());
        goodsBase.setGoodsVersion(0L);
        goodsBase.setGoodsBuyVersion(0L);
        goodsBase.setCreateTime(new Date());
        goodsBase.setCreateUserId(loginUserId);
        goodsBase.setUpdateTime(new Date());
        goodsBase.setUpdateUserId(loginUserId);
        result += customGoodsMapper.insertGoodsBaseGenerateId(goodsBase);
        // 插入goods_info
        GoodsInfo goodsInfo = new GoodsInfo();
        // id就是goods_base的id
        goodsInfo.setId(goodsBase.getId());
        goodsInfo.setTitle(dto.getTitle());
        goodsInfo.setImgs(dto.getImages());
        goodsInfo.setTags(dto.getTags());
        goodsInfo.setDetailImgs(dto.getDetailImages());
        goodsInfo.setDetail(dto.getDetail());
        result += goodsInfoMapper.insert(goodsInfo);
        // 插入goods_books
        GoodsBooks goodsBooks = new GoodsBooks();
        // goodsBooks.setId(); id是自动生成
        goodsBooks.setGoodsId(goodsBase.getId());
        goodsBooks.setAuthor(dto.getAuthor());
        goodsBooks.setPublisher(dto.getPublisher());
        goodsBooks.setIsbn(dto.getIsbn());
        result += goodsBooksMapper.insertSelective(goodsBooks);
        return result == 3 ? 1:0;
    }

    @Override
    public Integer modifyBook(GoodsBooksDto dto, Long loginUserId) {
        int result = 0;
        // 修改goods_base
        GoodsBase goodsBase = new GoodsBase();
        goodsBase.setId(dto.getId());
        goodsBase.setGoodsStatus(GoodsStatusEnum.NORMAL.getValue());
        goodsBase.setPrice(dto.getPrice());
        goodsBase.setStock(dto.getStock());
        goodsBase.setCategory(dto.getCategory());
        goodsBase.setGoodsVersion(goodsBaseMapper.selectByPrimaryKey(dto.getId()).getGoodsVersion()+1);
        goodsBase.setUpdateTime(new Date());
        goodsBase.setUpdateUserId(loginUserId);
        result += goodsBaseMapper.updateByPrimaryKeySelective(goodsBase);
        // 修改goods_info
        GoodsInfo goodsInfo = new GoodsInfo();
        // id就是goods_base的id
        goodsInfo.setId(dto.getId());
        goodsInfo.setTitle(dto.getTitle());
        goodsInfo.setImgs(dto.getImages());
        goodsInfo.setTags(dto.getTags());
        goodsInfo.setDetailImgs(dto.getDetailImages());
        goodsInfo.setDetail(dto.getDetail());
        result += goodsInfoMapper.updateByPrimaryKeyWithBLOBs(goodsInfo);
        // 修改goods_books
        GoodsBooksExample example = new GoodsBooksExample();
        GoodsBooksExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(dto.getId());
        GoodsBooks goodsBooks = new GoodsBooks();
        goodsBooks.setGoodsId(goodsBase.getId());
        goodsBooks.setAuthor(dto.getAuthor());
        goodsBooks.setPublisher(dto.getPublisher());
        goodsBooks.setIsbn(dto.getIsbn());
        result += goodsBooksMapper.updateByExampleSelective(goodsBooks, example);
        return result == 3 ? 1:0;
    }

    @Override
    public Integer modifyGoodsStock(Long goodsId, Integer stock, Long loginUserId) {
        GoodsBase goodsBase = new GoodsBase();
        goodsBase.setId(goodsId);
        goodsBase.setStock(stock);
        goodsBase.setUpdateTime(new Date());
        goodsBase.setUpdateUserId(loginUserId);
        return goodsBaseMapper.updateByPrimaryKeySelective(goodsBase);
    }

    @Override
    public Integer onShelfGoods(Long[] goodsIds, Long loginUserId) {
        // 购物车上架商品
        cartService.onShelfGoods(goodsIds);
        return customGoodsMapper.onShelves(goodsIds, loginUserId);
    }

    @Override
    public Integer offShelfGoods(Long[] goodsIds, Long loginUserId) {
        // 购物车下架商品
        cartService.offShelfGoods(goodsIds);
        return customGoodsMapper.offShelves(goodsIds, loginUserId);
    }

    @Override
    public Integer removeGoods(Long goodsId, Long loginUserId) {
        int result = 0;
        // 删除goods_base
        result += goodsBaseMapper.deleteByPrimaryKey(goodsId);
        // 删除goods_info
        result += goodsInfoMapper.deleteByPrimaryKey(goodsId);
        // 删除goods_books
        GoodsBooksExample example = new GoodsBooksExample();
        GoodsBooksExample.Criteria criteria = example.createCriteria();
        criteria.andGoodsIdEqualTo(goodsId);
        result += goodsBooksMapper.deleteByExample(example);
        // 购物车删除商品
        cartService.removeCartGoods(goodsId, loginUserId);
        return result == 3 ? 1:0;
    }

    @Override
    public PageInfo<GoodsListVo> listGoods(GoodsListDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<GoodsListVo> goodsListVos = customGoodsMapper.selectGoodsList(dto);
        ObjectMapper objectMapper = new ObjectMapper();
        goodsListVos.forEach(vo -> {
            // 设置价格
            vo.setPriceDouble(BigDecimalUtils.movePointLeft(vo.getPrice().toString(), 2).doubleValue());
            // 设置图片
            // vo.setImgs(StringUtils.isBlank(vo.getImgs()) ? null:vo.getImgs().split(",")[0]);
            vo.setImgs(StringUtils.isBlank(vo.getImgs()) ? null: PicNameUtils.getThumbnailName(vo.getImgs()));
            /*String json = stringRedisTemplate.opsForValue().get(GlobalConstant.RedisPrefixKey.DICTIONARY_PREFIX+vo.getCategory());
            Category category = null;
            try {
                category = objectMapper.readValue(json, Category.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            vo.setCategoryString(category.getName());*/
        });
        return new PageInfo<>(goodsListVos);
    }

    @Override
    public Boolean categoryHasGoods(Integer categoryId) {
        GoodsBase goodsBase = customGoodsMapper.selectOneGoodsByCategory(categoryId);
        return goodsBase != null;
    }

    @Override
    public PageInfo<GoodsSearchListVo> searchGoods(GoodsSearchDto dto) {
        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        List<GoodsSearchListVo> list = customGoodsMapper.searchGoods(dto);
        list.forEach(vo-> {
            vo.setImage(picHost + vo.getImage());
            vo.setPriceDouble(BigDecimalUtils.movePointLeft(String.valueOf(vo.getPrice()), 2).doubleValue());
            if (vo.getTitle().length() > 28) {
                vo.setTitle(vo.getTitle().substring(0,28) + "...");
            }
        });
        return new PageInfo<>(list);
    }
}
