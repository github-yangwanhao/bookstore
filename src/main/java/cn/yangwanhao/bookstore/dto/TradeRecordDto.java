package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨万浩
 * @description TradeRecordDto类
 * @date 2020/3/13 19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeRecordDto implements Serializable {

    private Long userId;

    private Long orderId;

    private String orderNo;

    private Long money;

    private Integer tradeType;

    private String aliPayTradeNo;

}
