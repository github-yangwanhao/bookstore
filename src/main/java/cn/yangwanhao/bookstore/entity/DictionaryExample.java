package cn.yangwanhao.bookstore.entity;

import java.util.ArrayList;
import java.util.List;

public class DictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DictionaryExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDicTypeIsNull() {
            addCriterion("dic_type is null");
            return (Criteria) this;
        }

        public Criteria andDicTypeIsNotNull() {
            addCriterion("dic_type is not null");
            return (Criteria) this;
        }

        public Criteria andDicTypeEqualTo(Integer value) {
            addCriterion("dic_type =", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotEqualTo(Integer value) {
            addCriterion("dic_type <>", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThan(Integer value) {
            addCriterion("dic_type >", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_type >=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThan(Integer value) {
            addCriterion("dic_type <", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dic_type <=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeIn(List<Integer> values) {
            addCriterion("dic_type in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotIn(List<Integer> values) {
            addCriterion("dic_type not in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeBetween(Integer value1, Integer value2) {
            addCriterion("dic_type between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_type not between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicDescIsNull() {
            addCriterion("dic_desc is null");
            return (Criteria) this;
        }

        public Criteria andDicDescIsNotNull() {
            addCriterion("dic_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDicDescEqualTo(String value) {
            addCriterion("dic_desc =", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescNotEqualTo(String value) {
            addCriterion("dic_desc <>", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescGreaterThan(String value) {
            addCriterion("dic_desc >", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescGreaterThanOrEqualTo(String value) {
            addCriterion("dic_desc >=", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescLessThan(String value) {
            addCriterion("dic_desc <", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescLessThanOrEqualTo(String value) {
            addCriterion("dic_desc <=", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescLike(String value) {
            addCriterion("dic_desc like", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescNotLike(String value) {
            addCriterion("dic_desc not like", value, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescIn(List<String> values) {
            addCriterion("dic_desc in", values, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescNotIn(List<String> values) {
            addCriterion("dic_desc not in", values, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescBetween(String value1, String value2) {
            addCriterion("dic_desc between", value1, value2, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicDescNotBetween(String value1, String value2) {
            addCriterion("dic_desc not between", value1, value2, "dicDesc");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyIsNull() {
            addCriterion("dic_parent_key is null");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyIsNotNull() {
            addCriterion("dic_parent_key is not null");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyEqualTo(Integer value) {
            addCriterion("dic_parent_key =", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyNotEqualTo(Integer value) {
            addCriterion("dic_parent_key <>", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyGreaterThan(Integer value) {
            addCriterion("dic_parent_key >", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_parent_key >=", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyLessThan(Integer value) {
            addCriterion("dic_parent_key <", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyLessThanOrEqualTo(Integer value) {
            addCriterion("dic_parent_key <=", value, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyIn(List<Integer> values) {
            addCriterion("dic_parent_key in", values, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyNotIn(List<Integer> values) {
            addCriterion("dic_parent_key not in", values, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyBetween(Integer value1, Integer value2) {
            addCriterion("dic_parent_key between", value1, value2, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicParentKeyNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_parent_key not between", value1, value2, "dicParentKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyIsNull() {
            addCriterion("dic_key is null");
            return (Criteria) this;
        }

        public Criteria andDicKeyIsNotNull() {
            addCriterion("dic_key is not null");
            return (Criteria) this;
        }

        public Criteria andDicKeyEqualTo(Integer value) {
            addCriterion("dic_key =", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyNotEqualTo(Integer value) {
            addCriterion("dic_key <>", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyGreaterThan(Integer value) {
            addCriterion("dic_key >", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_key >=", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyLessThan(Integer value) {
            addCriterion("dic_key <", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyLessThanOrEqualTo(Integer value) {
            addCriterion("dic_key <=", value, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyIn(List<Integer> values) {
            addCriterion("dic_key in", values, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyNotIn(List<Integer> values) {
            addCriterion("dic_key not in", values, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyBetween(Integer value1, Integer value2) {
            addCriterion("dic_key between", value1, value2, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicKeyNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_key not between", value1, value2, "dicKey");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNull() {
            addCriterion("dic_value is null");
            return (Criteria) this;
        }

        public Criteria andDicValueIsNotNull() {
            addCriterion("dic_value is not null");
            return (Criteria) this;
        }

        public Criteria andDicValueEqualTo(String value) {
            addCriterion("dic_value =", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotEqualTo(String value) {
            addCriterion("dic_value <>", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThan(String value) {
            addCriterion("dic_value >", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueGreaterThanOrEqualTo(String value) {
            addCriterion("dic_value >=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThan(String value) {
            addCriterion("dic_value <", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLessThanOrEqualTo(String value) {
            addCriterion("dic_value <=", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueLike(String value) {
            addCriterion("dic_value like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotLike(String value) {
            addCriterion("dic_value not like", value, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueIn(List<String> values) {
            addCriterion("dic_value in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotIn(List<String> values) {
            addCriterion("dic_value not in", values, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueBetween(String value1, String value2) {
            addCriterion("dic_value between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicValueNotBetween(String value1, String value2) {
            addCriterion("dic_value not between", value1, value2, "dicValue");
            return (Criteria) this;
        }

        public Criteria andDicSortIsNull() {
            addCriterion("dic_sort is null");
            return (Criteria) this;
        }

        public Criteria andDicSortIsNotNull() {
            addCriterion("dic_sort is not null");
            return (Criteria) this;
        }

        public Criteria andDicSortEqualTo(Integer value) {
            addCriterion("dic_sort =", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortNotEqualTo(Integer value) {
            addCriterion("dic_sort <>", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortGreaterThan(Integer value) {
            addCriterion("dic_sort >", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("dic_sort >=", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortLessThan(Integer value) {
            addCriterion("dic_sort <", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortLessThanOrEqualTo(Integer value) {
            addCriterion("dic_sort <=", value, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortIn(List<Integer> values) {
            addCriterion("dic_sort in", values, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortNotIn(List<Integer> values) {
            addCriterion("dic_sort not in", values, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortBetween(Integer value1, Integer value2) {
            addCriterion("dic_sort between", value1, value2, "dicSort");
            return (Criteria) this;
        }

        public Criteria andDicSortNotBetween(Integer value1, Integer value2) {
            addCriterion("dic_sort not between", value1, value2, "dicSort");
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