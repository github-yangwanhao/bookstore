package cn.yangwanhao.bookstore.common.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨万浩
 * @description GoodsOperateLog类
 * @date 2020/3/15 21
 */
@Document
@ToString
public class GoodsOperateLog implements Serializable {

    /**
     * 文档的唯一标识,在mongodb中为ObjectId,它是唯一的,通过时间戳+机器标识+进程ID+自增计数器构成
     */
    @Id
    private String id;

    private Long operatorUserId;

    private Integer type;

    private Long goodsId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;

}
