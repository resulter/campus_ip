package com.thinkgem.jeesite.modules.act.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseDataExample() {
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

        public Criteria andCampusNameIsNull() {
            addCriterion("campus_name is null");
            return (Criteria) this;
        }

        public Criteria andCampusNameIsNotNull() {
            addCriterion("campus_name is not null");
            return (Criteria) this;
        }

        public Criteria andCampusNameEqualTo(String value) {
            addCriterion("campus_name =", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotEqualTo(String value) {
            addCriterion("campus_name <>", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThan(String value) {
            addCriterion("campus_name >", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameGreaterThanOrEqualTo(String value) {
            addCriterion("campus_name >=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThan(String value) {
            addCriterion("campus_name <", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLessThanOrEqualTo(String value) {
            addCriterion("campus_name <=", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameLike(String value) {
            addCriterion("campus_name like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotLike(String value) {
            addCriterion("campus_name not like", value, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameIn(List<String> values) {
            addCriterion("campus_name in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotIn(List<String> values) {
            addCriterion("campus_name not in", values, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameBetween(String value1, String value2) {
            addCriterion("campus_name between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andCampusNameNotBetween(String value1, String value2) {
            addCriterion("campus_name not between", value1, value2, "campusName");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressIsNull() {
            addCriterion("network_address is null");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressIsNotNull() {
            addCriterion("network_address is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressEqualTo(String value) {
            addCriterion("network_address =", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressNotEqualTo(String value) {
            addCriterion("network_address <>", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressGreaterThan(String value) {
            addCriterion("network_address >", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressGreaterThanOrEqualTo(String value) {
            addCriterion("network_address >=", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressLessThan(String value) {
            addCriterion("network_address <", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressLessThanOrEqualTo(String value) {
            addCriterion("network_address <=", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressLike(String value) {
            addCriterion("network_address like", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressNotLike(String value) {
            addCriterion("network_address not like", value, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressIn(List<String> values) {
            addCriterion("network_address in", values, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressNotIn(List<String> values) {
            addCriterion("network_address not in", values, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressBetween(String value1, String value2) {
            addCriterion("network_address between", value1, value2, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andNetworkAddressNotBetween(String value1, String value2) {
            addCriterion("network_address not between", value1, value2, "networkAddress");
            return (Criteria) this;
        }

        public Criteria andMaskIsNull() {
            addCriterion("mask is null");
            return (Criteria) this;
        }

        public Criteria andMaskIsNotNull() {
            addCriterion("mask is not null");
            return (Criteria) this;
        }

        public Criteria andMaskEqualTo(String value) {
            addCriterion("mask =", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotEqualTo(String value) {
            addCriterion("mask <>", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskGreaterThan(String value) {
            addCriterion("mask >", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskGreaterThanOrEqualTo(String value) {
            addCriterion("mask >=", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLessThan(String value) {
            addCriterion("mask <", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLessThanOrEqualTo(String value) {
            addCriterion("mask <=", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskLike(String value) {
            addCriterion("mask like", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotLike(String value) {
            addCriterion("mask not like", value, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskIn(List<String> values) {
            addCriterion("mask in", values, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotIn(List<String> values) {
            addCriterion("mask not in", values, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskBetween(String value1, String value2) {
            addCriterion("mask between", value1, value2, "mask");
            return (Criteria) this;
        }

        public Criteria andMaskNotBetween(String value1, String value2) {
            addCriterion("mask not between", value1, value2, "mask");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentIsNull() {
            addCriterion("using_equipment is null");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentIsNotNull() {
            addCriterion("using_equipment is not null");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentEqualTo(String value) {
            addCriterion("using_equipment =", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentNotEqualTo(String value) {
            addCriterion("using_equipment <>", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentGreaterThan(String value) {
            addCriterion("using_equipment >", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("using_equipment >=", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentLessThan(String value) {
            addCriterion("using_equipment <", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentLessThanOrEqualTo(String value) {
            addCriterion("using_equipment <=", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentLike(String value) {
            addCriterion("using_equipment like", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentNotLike(String value) {
            addCriterion("using_equipment not like", value, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentIn(List<String> values) {
            addCriterion("using_equipment in", values, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentNotIn(List<String> values) {
            addCriterion("using_equipment not in", values, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentBetween(String value1, String value2) {
            addCriterion("using_equipment between", value1, value2, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingEquipmentNotBetween(String value1, String value2) {
            addCriterion("using_equipment not between", value1, value2, "usingEquipment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentIsNull() {
            addCriterion("using_department is null");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentIsNotNull() {
            addCriterion("using_department is not null");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentEqualTo(String value) {
            addCriterion("using_department =", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentNotEqualTo(String value) {
            addCriterion("using_department <>", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentGreaterThan(String value) {
            addCriterion("using_department >", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentGreaterThanOrEqualTo(String value) {
            addCriterion("using_department >=", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentLessThan(String value) {
            addCriterion("using_department <", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentLessThanOrEqualTo(String value) {
            addCriterion("using_department <=", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentLike(String value) {
            addCriterion("using_department like", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentNotLike(String value) {
            addCriterion("using_department not like", value, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentIn(List<String> values) {
            addCriterion("using_department in", values, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentNotIn(List<String> values) {
            addCriterion("using_department not in", values, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentBetween(String value1, String value2) {
            addCriterion("using_department between", value1, value2, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andUsingDepartmentNotBetween(String value1, String value2) {
            addCriterion("using_department not between", value1, value2, "usingDepartment");
            return (Criteria) this;
        }

        public Criteria andStoragePositionIsNull() {
            addCriterion("storage_position is null");
            return (Criteria) this;
        }

        public Criteria andStoragePositionIsNotNull() {
            addCriterion("storage_position is not null");
            return (Criteria) this;
        }

        public Criteria andStoragePositionEqualTo(String value) {
            addCriterion("storage_position =", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionNotEqualTo(String value) {
            addCriterion("storage_position <>", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionGreaterThan(String value) {
            addCriterion("storage_position >", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionGreaterThanOrEqualTo(String value) {
            addCriterion("storage_position >=", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionLessThan(String value) {
            addCriterion("storage_position <", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionLessThanOrEqualTo(String value) {
            addCriterion("storage_position <=", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionLike(String value) {
            addCriterion("storage_position like", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionNotLike(String value) {
            addCriterion("storage_position not like", value, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionIn(List<String> values) {
            addCriterion("storage_position in", values, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionNotIn(List<String> values) {
            addCriterion("storage_position not in", values, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionBetween(String value1, String value2) {
            addCriterion("storage_position between", value1, value2, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andStoragePositionNotBetween(String value1, String value2) {
            addCriterion("storage_position not between", value1, value2, "storagePosition");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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