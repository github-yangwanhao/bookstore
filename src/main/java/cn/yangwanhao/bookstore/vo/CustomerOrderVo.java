package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/26 16:15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderVo implements Serializable {

    private static final long serialVersionUID = 2075475415019022005L;

    private Long orderId;

    private String orderNo;

}
