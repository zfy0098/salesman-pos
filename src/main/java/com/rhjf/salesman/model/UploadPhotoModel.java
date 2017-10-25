package com.rhjf.salesman.model;


/**
 * Created by hadoop on 2017/9/27.
 *
 * @author hadoop
 *
 */
public class UploadPhotoModel extends  BeanBase {


    /** 手持身份证照片 **/
    private String handheldIDPhoto;

    /** 身份证正面照片 **/
    private String IDCardFrontPhoto;

    /** 身份证反面照片 **/
    private String IDCardReversePhoto;

    /** 银行卡照片 **/
    private String bankCardPhoto;

    /** 营业执照照片 **/
    private String businessPhoto;

    /**  开户许可证  **/
    private String openingLicense;

    /**  店面照片 **/
    private String storeFront;

    /**  内景 **/
    private String interior;

    /** 街道  **/
    private String street;

    /**  其他 **/
    private String other;


    public String getHandheldIDPhoto() {
        return handheldIDPhoto;
    }

    public void setHandheldIDPhoto(String handheldIDPhoto) {
        this.handheldIDPhoto = handheldIDPhoto;
    }

    public String getIDCardFrontPhoto() {
        return IDCardFrontPhoto;
    }

    public void setIDCardFrontPhoto(String IDCardFrontPhoto) {
        this.IDCardFrontPhoto = IDCardFrontPhoto;
    }

    public String getIDCardReversePhoto() {
        return IDCardReversePhoto;
    }

    public void setIDCardReversePhoto(String IDCardReversePhoto) {
        this.IDCardReversePhoto = IDCardReversePhoto;
    }

    public String getBankCardPhoto() {
        return bankCardPhoto;
    }

    public void setBankCardPhoto(String bankCardPhoto) {
        this.bankCardPhoto = bankCardPhoto;
    }

    public String getBusinessPhoto() {
        return businessPhoto;
    }

    public void setBusinessPhoto(String businessPhoto) {
        this.businessPhoto = businessPhoto;
    }


    public String getOpeningLicense() {
        return openingLicense;
    }

    public void setOpeningLicense(String openingLicense) {
        this.openingLicense = openingLicense;
    }

    public String getStoreFront() {
        return storeFront;
    }

    public void setStoreFront(String storeFront) {
        this.storeFront = storeFront;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
