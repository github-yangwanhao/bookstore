package cn.yangwanhao.bookstore.mapper;

import cn.yangwanhao.bookstore.entity.Advertisements;
import cn.yangwanhao.bookstore.entity.AdvertisementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementsMapper {
    int countByExample(AdvertisementsExample example);

    int deleteByExample(AdvertisementsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertisements record);

    int insertSelective(Advertisements record);

    List<Advertisements> selectByExample(AdvertisementsExample example);

    Advertisements selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertisements record, @Param("example") AdvertisementsExample example);

    int updateByExample(@Param("record") Advertisements record, @Param("example") AdvertisementsExample example);

    int updateByPrimaryKeySelective(Advertisements record);

    int updateByPrimaryKey(Advertisements record);
}