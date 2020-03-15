package cn.yangwanhao.bookstore.common.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨万浩
 * @description UserLoginLog类
 * @date 2020/3/15 20
 */
@Document
@ToString
public class UserLoginLog implements Serializable {

    /**
     * 文档的唯一标识,在mongodb中为ObjectId,它是唯一的,通过时间戳+机器标识+进程ID+自增计数器构成
     */
    @Id
    private String id;

    private Long loginUserId;

    private String loginname;

    private String ipAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    public UserLoginLog() {
    }

    @PersistenceConstructor
    public UserLoginLog(String id, Long loginUserId, String loginname, String ipAddress, Date loginTime) {
        this.id = id;
        this.loginUserId = loginUserId;
        this.loginname = loginname;
        this.ipAddress = ipAddress;
        this.loginTime = loginTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
