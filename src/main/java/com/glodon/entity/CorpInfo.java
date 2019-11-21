package com.glodon.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CorpInfo {
    private String corpguid;

    private String corpcode;

    private String corpname;

    private String licensenum;

    private Integer provincenum;

    private Integer citynum;

    private Integer countynum;

    private String address;

    private String postalcode;

    private Integer economicnum;

    private BigDecimal regprin;

    private BigDecimal factregprin;

    private Integer principalunitnum;

    private String legalman;

    private String legalmanidcard;

    private String legalmanduty;

    private String legalmanprotitle;

    private String unitman;

    private String unitmanidcard;

    private String unitmanduty;

    private String unitmanprotitle;

    private String techman;

    private String techmanidcard;

    private String techmanduty;

    private String techmanprotitle;

    private Date corpbirthdate;

    private String officephone;

    private String fax;

    private String linkman;

    private String linktel;

    private String linkphone;

    private String email;

    private String url;

    private String description;

    public String getCorpguid() {
        return corpguid;
    }

    public void setCorpguid(String corpguid) {
        this.corpguid = corpguid == null ? null : corpguid.trim();
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode == null ? null : corpcode.trim();
    }

    public String getCorpname() {
        return corpname;
    }

    public void setCorpname(String corpname) {
        this.corpname = corpname == null ? null : corpname.trim();
    }

    public String getLicensenum() {
        return licensenum;
    }

    public void setLicensenum(String licensenum) {
        this.licensenum = licensenum == null ? null : licensenum.trim();
    }

    public Integer getProvincenum() {
        return provincenum;
    }

    public void setProvincenum(Integer provincenum) {
        this.provincenum = provincenum;
    }

    public Integer getCitynum() {
        return citynum;
    }

    public void setCitynum(Integer citynum) {
        this.citynum = citynum;
    }

    public Integer getCountynum() {
        return countynum;
    }

    public void setCountynum(Integer countynum) {
        this.countynum = countynum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode == null ? null : postalcode.trim();
    }

    public Integer getEconomicnum() {
        return economicnum;
    }

    public void setEconomicnum(Integer economicnum) {
        this.economicnum = economicnum;
    }

    public BigDecimal getRegprin() {
        return regprin;
    }

    public void setRegprin(BigDecimal regprin) {
        this.regprin = regprin;
    }

    public BigDecimal getFactregprin() {
        return factregprin;
    }

    public void setFactregprin(BigDecimal factregprin) {
        this.factregprin = factregprin;
    }

    public Integer getPrincipalunitnum() {
        return principalunitnum;
    }

    public void setPrincipalunitnum(Integer principalunitnum) {
        this.principalunitnum = principalunitnum;
    }

    public String getLegalman() {
        return legalman;
    }

    public void setLegalman(String legalman) {
        this.legalman = legalman == null ? null : legalman.trim();
    }

    public String getLegalmanidcard() {
        return legalmanidcard;
    }

    public void setLegalmanidcard(String legalmanidcard) {
        this.legalmanidcard = legalmanidcard == null ? null : legalmanidcard.trim();
    }

    public String getLegalmanduty() {
        return legalmanduty;
    }

    public void setLegalmanduty(String legalmanduty) {
        this.legalmanduty = legalmanduty == null ? null : legalmanduty.trim();
    }

    public String getLegalmanprotitle() {
        return legalmanprotitle;
    }

    public void setLegalmanprotitle(String legalmanprotitle) {
        this.legalmanprotitle = legalmanprotitle == null ? null : legalmanprotitle.trim();
    }

    public String getUnitman() {
        return unitman;
    }

    public void setUnitman(String unitman) {
        this.unitman = unitman == null ? null : unitman.trim();
    }

    public String getUnitmanidcard() {
        return unitmanidcard;
    }

    public void setUnitmanidcard(String unitmanidcard) {
        this.unitmanidcard = unitmanidcard == null ? null : unitmanidcard.trim();
    }

    public String getUnitmanduty() {
        return unitmanduty;
    }

    public void setUnitmanduty(String unitmanduty) {
        this.unitmanduty = unitmanduty == null ? null : unitmanduty.trim();
    }

    public String getUnitmanprotitle() {
        return unitmanprotitle;
    }

    public void setUnitmanprotitle(String unitmanprotitle) {
        this.unitmanprotitle = unitmanprotitle == null ? null : unitmanprotitle.trim();
    }

    public String getTechman() {
        return techman;
    }

    public void setTechman(String techman) {
        this.techman = techman == null ? null : techman.trim();
    }

    public String getTechmanidcard() {
        return techmanidcard;
    }

    public void setTechmanidcard(String techmanidcard) {
        this.techmanidcard = techmanidcard == null ? null : techmanidcard.trim();
    }

    public String getTechmanduty() {
        return techmanduty;
    }

    public void setTechmanduty(String techmanduty) {
        this.techmanduty = techmanduty == null ? null : techmanduty.trim();
    }

    public String getTechmanprotitle() {
        return techmanprotitle;
    }

    public void setTechmanprotitle(String techmanprotitle) {
        this.techmanprotitle = techmanprotitle == null ? null : techmanprotitle.trim();
    }

    public Date getCorpbirthdate() {
        return corpbirthdate;
    }

    public void setCorpbirthdate(Date corpbirthdate) {
        this.corpbirthdate = corpbirthdate;
    }

    public String getOfficephone() {
        return officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone == null ? null : officephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel == null ? null : linktel.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}