package com.glodon.entity;

import java.util.Date;

public class Enterprise {
    private String id;

    private String enterpriseName;

    private String creditCode;

    private String legalName;

    private String legalIdentityNum;

    private String operator;

    private String operatorTel;

    private String provinceCode;

    private String cityCode;

    private String areaCode;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String certificate;

    private String regFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

    private Integer isDel;

    private Integer dataSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalIdentityNum() {
        return legalIdentityNum;
    }

    public void setLegalIdentityNum(String legalIdentityNum) {
        this.legalIdentityNum = legalIdentityNum == null ? null : legalIdentityNum.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperatorTel() {
        return operatorTel;
    }

    public void setOperatorTel(String operatorTel) {
        this.operatorTel = operatorTel == null ? null : operatorTel.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getRegFlag() {
        return regFlag;
    }

    public void setRegFlag(String regFlag) {
        this.regFlag = regFlag == null ? null : regFlag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

	public Enterprise(String id, String enterpriseName, String creditCode, String legalName, String legalIdentityNum,
			String provinceCode, String cityCode, String areaCode) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.creditCode = creditCode;
		this.legalName = legalName;
		this.legalIdentityNum = legalIdentityNum;
		this.provinceCode = provinceCode;
		this.cityCode = cityCode;
		this.areaCode = areaCode;
	
	}

	public Enterprise() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}