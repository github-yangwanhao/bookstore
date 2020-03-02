package cn.yangwanhao.bookstore.common.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/10 19:52
 */

@Document
@ToString
public class LogMessage implements Serializable {

    private static final long serialVersionUID = -664847323716650975L;

    /**
     * 文档的唯一标识,在mongodb中为ObjectId,它是唯一的,通过时间戳+机器标识+进程ID+自增计数器构成
     */
    @Id
    private String id;

    /**
     * 登录人id
     */
    private Long loginUserId;

    /**
     * 登录人姓名
     */
    private String loginUserName;

    /**
     * uri
     */
    private String uri;

    /**
     * 请求方法: GET/POST/PUT/DELETE(如果是GET不保存)
     */
    private String httpMethod;

    /**
     * 访问的方法名
     */
    private String methodName;

    /**
     * 操作是否成功
     */
    private Boolean operateSuccess;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operateTime;

    public LogMessage() {}

    @PersistenceConstructor
    public LogMessage(String id, Long loginUserId, String loginUserName, String uri, String httpMethod, String methodName, Boolean operateSuccess, Date operateTime) {
        this.id = id;
        this.loginUserId = loginUserId;
        this.loginUserName = loginUserName;
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.methodName = methodName;
        this.operateSuccess = operateSuccess;
        this.operateTime = operateTime;
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

    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Boolean getOperateSuccess() {
        return operateSuccess;
    }

    public void setOperateSuccess(Boolean operateSuccess) {
        this.operateSuccess = operateSuccess;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
