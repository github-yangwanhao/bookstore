package cn.yangwanhao.bookstore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 杨万浩
 * @description OrderVo类
 * @date 2020/3/12 14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVo implements Serializable {

    private Long orderId;

    private String orderNo;

    private Integer orderStatus;

    private String orderStatusString;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Long totalPrice;

    private Double totalPriceDouble;

    private List<OrderGoodsListVo> goodsList;

    private CustomerAddressListVo address;

}
