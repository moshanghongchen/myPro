package com.lch.my_air_pan.model.user.entity;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseNameIsNull() {
            addCriterion("use_name is null");
            return (Criteria) this;
        }

        public Criteria andUseNameIsNotNull() {
            addCriterion("use_name is not null");
            return (Criteria) this;
        }

        public Criteria andUseNameEqualTo(String value) {
            addCriterion("use_name =", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameNotEqualTo(String value) {
            addCriterion("use_name <>", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameGreaterThan(String value) {
            addCriterion("use_name >", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameGreaterThanOrEqualTo(String value) {
            addCriterion("use_name >=", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameLessThan(String value) {
            addCriterion("use_name <", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameLessThanOrEqualTo(String value) {
            addCriterion("use_name <=", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameLike(String value) {
            addCriterion("use_name like", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameNotLike(String value) {
            addCriterion("use_name not like", value, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameIn(List<String> values) {
            addCriterion("use_name in", values, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameNotIn(List<String> values) {
            addCriterion("use_name not in", values, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameBetween(String value1, String value2) {
            addCriterion("use_name between", value1, value2, "useName");
            return (Criteria) this;
        }

        public Criteria andUseNameNotBetween(String value1, String value2) {
            addCriterion("use_name not between", value1, value2, "useName");
            return (Criteria) this;
        }

        public Criteria andPasspowdIsNull() {
            addCriterion("passpowd is null");
            return (Criteria) this;
        }

        public Criteria andPasspowdIsNotNull() {
            addCriterion("passpowd is not null");
            return (Criteria) this;
        }

        public Criteria andPasspowdEqualTo(String value) {
            addCriterion("passpowd =", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdNotEqualTo(String value) {
            addCriterion("passpowd <>", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdGreaterThan(String value) {
            addCriterion("passpowd >", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdGreaterThanOrEqualTo(String value) {
            addCriterion("passpowd >=", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdLessThan(String value) {
            addCriterion("passpowd <", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdLessThanOrEqualTo(String value) {
            addCriterion("passpowd <=", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdLike(String value) {
            addCriterion("passpowd like", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdNotLike(String value) {
            addCriterion("passpowd not like", value, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdIn(List<String> values) {
            addCriterion("passpowd in", values, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdNotIn(List<String> values) {
            addCriterion("passpowd not in", values, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdBetween(String value1, String value2) {
            addCriterion("passpowd between", value1, value2, "passpowd");
            return (Criteria) this;
        }

        public Criteria andPasspowdNotBetween(String value1, String value2) {
            addCriterion("passpowd not between", value1, value2, "passpowd");
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