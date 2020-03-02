package cn.yangwanhao.bookstore.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
    private Integer id;

    private Long userId;

    private Integer roleId;

    private Integer isStarted;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(Integer isStarted) {
        this.isStarted = isStarted;
    }
}