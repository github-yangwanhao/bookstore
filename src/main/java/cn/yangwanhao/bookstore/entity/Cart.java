package cn.yangwanhao.bookstore.entity;

import java.io.Serializable;

public class Cart implements Serializable {
    private Long id;

    private Long userId;

    private Long goodsId;

    private Integer goodsNum;

    private Integer goodsIsDeleted;

    private Integer goodsIsOffTheShelves;

    private Long goodsVersion;

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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getGoodsIsDeleted() {
        return goodsIsDeleted;
    }

    public void setGoodsIsDeleted(Integer goodsIsDeleted) {
        this.goodsIsDeleted = goodsIsDeleted;
    }

    public Integer getGoodsIsOffTheShelves() {
        return goodsIsOffTheShelves;
    }

    public void setGoodsIsOffTheShelves(Integer goodsIsOffTheShelves) {
        this.goodsIsOffTheShelves = goodsIsOffTheShelves;
    }

    public Long getGoodsVersion() {
        return goodsVersion;
    }

    public void setGoodsVersion(Long goodsVersion) {
        this.goodsVersion = goodsVersion;
    }
}