package cn.yangwanhao.bookstore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/11 20:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsListVo implements Serializable {

    private static final long serialVersionUID = 1232329922465301319L;

    private Long goodsId;

    private Integer goodsStatus;

    private Long price;

    private Double priceDouble;

    private Integer stock;

    private Integer category;

    private String categoryString;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private Long goodsBuyVersion;

    private String title;

    private String imgs;

    private String tags;

    private String author;

    private String publisher;

}
