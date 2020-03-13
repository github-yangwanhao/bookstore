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
 * @description OrderListVo类
 * @date 2020/3/13 08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListVo implements Serializable {

    private Long orderId;

    private String orderNo;

    private Integer orderStatus;

    private String orderStatusString;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer totalPrice;

    private Double totalPriceDouble;

    private Integer payType = 0;

    private String address;

    private List<OrderGoodsListVo> goods;

}
