package cn.yangwanhao.bookstore.service;

import cn.yangwanhao.bookstore.dto.TradeRecordDto;

/**
 * @author 杨万浩
 * @description TradeService类
 * @date 2020/3/13 17
 */
public interface TradeRecordService {

    /**
     * Description: 插入交易表
     * @param dto dto
     * @return
     * @author 杨万浩
     * @date 2020/3/13 19:57
     */
    Integer saveTradeRecord(TradeRecordDto dto);

}
