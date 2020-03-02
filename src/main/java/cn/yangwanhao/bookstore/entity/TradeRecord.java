package cn.yangwanhao.bookstore.entity;

import java.io.Serializable;
import java.util.Date;

public class TradeRecord implements Serializable {
    private Long id;

    private Long userId;

    private Long orderId;

    private String orderNo;

    private Integer tradeType;

    private Long money;

    private String alipayTransactionNum;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getAlipayTransactionNum() {
        return alipayTransactionNum;
    }

    public void setAlipayTransactionNum(String alipayTransactionNum) {
        this.alipayTransactionNum = alipayTransactionNum == null ? null : alipayTransactionNum.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}