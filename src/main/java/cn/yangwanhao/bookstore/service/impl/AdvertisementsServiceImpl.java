package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.dto.AdvertisementsDto;
import cn.yangwanhao.bookstore.entity.Advertisements;
import cn.yangwanhao.bookstore.entity.AdvertisementsExample;
import cn.yangwanhao.bookstore.mapper.AdvertisementsMapper;
import cn.yangwanhao.bookstore.service.AdvertisementsService;
import cn.yangwanhao.bookstore.vo.AdvertisementsListVo;
import cn.yangwanhao.bookstore.vo.AdvertisementsVo;
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
 * @date 2019/11/29 20:14
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class AdvertisementsServiceImpl implements AdvertisementsService {

    @Resource
    private AdvertisementsMapper advertisementsMapper;

    @Override
    public Integer addAdvertisement(AdvertisementsDto dto) {
        Advertisements advertisements = new Advertisements();
        advertisements.setTitle(dto.getTitle());
        advertisements.setUrl(dto.getUrl());
        advertisements.setImage(dto.getImage());
        advertisements.setCategory(dto.getCategory());
        advertisements.setSort(dto.getSort());
        return advertisementsMapper.insertSelective(advertisements);
    }

    @Override
    public Integer removeAdvertisement(Integer id) {
        return advertisementsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer modifyAdvertisement(AdvertisementsDto dto) {
        Advertisements advertisements = new Advertisements();
        BeanUtils.copyProperties(dto, advertisements);
        return advertisementsMapper.updateByPrimaryKeySelective(advertisements);
    }

    @Override
    public AdvertisementsVo getOneById(Integer id) {
        AdvertisementsVo vo = new AdvertisementsVo();
        Advertisements advertisements = advertisementsMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(advertisements, vo);
        return vo;
    }

    @Override
    public List<AdvertisementsListVo> listAll(Integer category) {
        AdvertisementsExample advertisementsExample = new AdvertisementsExample();
        AdvertisementsExample.Criteria criteria = advertisementsExample.createCriteria();
        criteria.andCategoryEqualTo(category);
        List<Advertisements> advertisementsList = advertisementsMapper.selectByExample(advertisementsExample);
        List<AdvertisementsListVo> vos = new ArrayList<>();
        for (Advertisements advertisements : advertisementsList) {
            AdvertisementsListVo vo = new AdvertisementsListVo();
            BeanUtils.copyProperties(vo, advertisements);
            vos.add(vo);
        }
        return vos;
    }
}
