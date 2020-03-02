package cn.yangwanhao.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

public class CartExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CartExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNull() {
            addCriterion("goods_num is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIsNotNull() {
            addCriterion("goods_num is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNumEqualTo(Integer value) {
            addCriterion("goods_num =", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotEqualTo(Integer value) {
            addCriterion("goods_num <>", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThan(Integer value) {
            addCriterion("goods_num >", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_num >=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThan(Integer value) {
            addCriterion("goods_num <", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumLessThanOrEqualTo(Integer value) {
            addCriterion("goods_num <=", value, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumIn(List<Integer> values) {
            addCriterion("goods_num in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotIn(List<Integer> values) {
            addCriterion("goods_num not in", values, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumBetween(Integer value1, Integer value2) {
            addCriterion("goods_num between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsNumNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_num not between", value1, value2, "goodsNum");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedIsNull() {
            addCriterion("goods_is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedIsNotNull() {
            addCriterion("goods_is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedEqualTo(Integer value) {
            addCriterion("goods_is_deleted =", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedNotEqualTo(Integer value) {
            addCriterion("goods_is_deleted <>", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedGreaterThan(Integer value) {
            addCriterion("goods_is_deleted >", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_is_deleted >=", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedLessThan(Integer value) {
            addCriterion("goods_is_deleted <", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("goods_is_deleted <=", value, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedIn(List<Integer> values) {
            addCriterion("goods_is_deleted in", values, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedNotIn(List<Integer> values) {
            addCriterion("goods_is_deleted not in", values, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("goods_is_deleted between", value1, value2, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_is_deleted not between", value1, value2, "goodsIsDeleted");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesIsNull() {
            addCriterion("goods_is_off_the_shelves is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesIsNotNull() {
            addCriterion("goods_is_off_the_shelves is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesEqualTo(Integer value) {
            addCriterion("goods_is_off_the_shelves =", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesNotEqualTo(Integer value) {
            addCriterion("goods_is_off_the_shelves <>", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesGreaterThan(Integer value) {
            addCriterion("goods_is_off_the_shelves >", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_is_off_the_shelves >=", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesLessThan(Integer value) {
            addCriterion("goods_is_off_the_shelves <", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesLessThanOrEqualTo(Integer value) {
            addCriterion("goods_is_off_the_shelves <=", value, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesIn(List<Integer> values) {
            addCriterion("goods_is_off_the_shelves in", values, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesNotIn(List<Integer> values) {
            addCriterion("goods_is_off_the_shelves not in", values, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesBetween(Integer value1, Integer value2) {
            addCriterion("goods_is_off_the_shelves between", value1, value2, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsIsOffTheShelvesNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_is_off_the_shelves not between", value1, value2, "goodsIsOffTheShelves");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionIsNull() {
            addCriterion("goods_version is null");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionIsNotNull() {
            addCriterion("goods_version is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionEqualTo(Long value) {
            addCriterion("goods_version =", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionNotEqualTo(Long value) {
            addCriterion("goods_version <>", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionGreaterThan(Long value) {
            addCriterion("goods_version >", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_version >=", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionLessThan(Long value) {
            addCriterion("goods_version <", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionLessThanOrEqualTo(Long value) {
            addCriterion("goods_version <=", value, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionIn(List<Long> values) {
            addCriterion("goods_version in", values, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionNotIn(List<Long> values) {
            addCriterion("goods_version not in", values, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionBetween(Long value1, Long value2) {
            addCriterion("goods_version between", value1, value2, "goodsVersion");
            return (Criteria) this;
        }

        public Criteria andGoodsVersionNotBetween(Long value1, Long value2) {
            addCriterion("goods_version not between", value1, value2, "goodsVersion");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}