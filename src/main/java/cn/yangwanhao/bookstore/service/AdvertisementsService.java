package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.dto.AdvertisementsDto;
import cn.yangwanhao.bookstore.vo.AdvertisementsListVo;
import cn.yangwanhao.bookstore.vo.AdvertisementsVo;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/29 20:14
 */

public interface AdvertisementsService {
    
    /**
     * Description: 插入一条广告
     * @param dto dto
     * @return 
     * @author 杨万浩
     * @createDate 2019/11/30 14:13
     */
    Integer addAdvertisement(AdvertisementsDto dto);

    /**
     * Description: 删除一个广告
     * @param id id
     * @return
     * @author 杨万浩
     * @createDate 2019/12/2 14:05
     */
    Integer removeAdvertisement(Integer id);
    
    /**
     * Description: 修改一个广告
     * @param dto dto
     * @return 
     * @author 杨万浩
     * @createDate 2019/12/2 14:30
     */
    Integer modifyAdvertisement(AdvertisementsDto dto);

    /**
     * Description: 通过id获取一个
     * @param id id
     * @return advertisement
     * @author 杨万浩
     * @createDate 2019/12/5 20:02
     */
    AdvertisementsVo getOneById(Integer id);

    /**
     * Description: 获取全部广告
     * @return list
     * @author 杨万浩
     * @createDate 2019/12/5 20:04
     */
    List<AdvertisementsListVo> listAll(Integer category);
    
}
