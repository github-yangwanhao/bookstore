package cn.yangwanhao.bookstore.entity;

import java.io.Serializable;

public class Dictionary implements Serializable {
    private Integer id;

    private Integer dicType;

    private String dicDesc;

    private Integer dicParentKey;

    private Integer dicKey;

    private String dicValue;

    private Integer dicSort;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDicType() {
        return dicType;
    }

    public void setDicType(Integer dicType) {
        this.dicType = dicType;
    }

    public String getDicDesc() {
        return dicDesc;
    }

    public void setDicDesc(String dicDesc) {
        this.dicDesc = dicDesc == null ? null : dicDesc.trim();
    }

    public Integer getDicParentKey() {
        return dicParentKey;
    }

    public void setDicParentKey(Integer dicParentKey) {
        this.dicParentKey = dicParentKey;
    }

    public Integer getDicKey() {
        return dicKey;
    }

    public void setDicKey(Integer dicKey) {
        this.dicKey = dicKey;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue == null ? null : dicValue.trim();
    }

    public Integer getDicSort() {
        return dicSort;
    }

    public void setDicSort(Integer dicSort) {
        this.dicSort = dicSort;
    }
}