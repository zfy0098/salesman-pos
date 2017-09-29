package com.rhjf.salesman.model;


/**
 * Created by hadoop on 2017/9/27.
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
}
