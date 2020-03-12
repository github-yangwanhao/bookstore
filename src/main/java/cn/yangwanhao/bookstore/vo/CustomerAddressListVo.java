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
 * @date 2019/12/26 17:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressListVo implements Serializable {

    private static final long serialVersionUID = -5546123220463015777L;

    private Long id;

    private Long customerId;

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private Integer isDefault;

    private String addressStr;

    public String getAddressStr() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.receiverName)
                .append("-")
                .append(this.receiverPhone)
                .append("-")
                .append(this.receiverAddress);
        return sb.toString();
    }
}
