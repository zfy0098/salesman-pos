package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.CustomerDB;
import com.rhjf.salesman.db.CustomerDetailDB;
import com.rhjf.salesman.db.InternetMerchantDB;
import com.rhjf.salesman.db.MerchantPhotoDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.model.UploadPhotoModel;
import com.rhjf.salesman.utils.Image64Bit;
import com.rhjf.salesman.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *      业务员上传商户图片
 *
 * Created by hadoop on 2017/9/27.
 *
 *
 * @author hadoop
 *
 */
@Service("uploadPhotoService")
@Transactional
public class UploadPhotoService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${imgPath}")
    private String imgPath;

    @Value("${imgUrl}")
    private String imgUrl;

    @Autowired
    private MerchantPhotoDB merchantPhotoDB;

    @Autowired
    private CustomerDetailDB customerDetailDB;


    @Autowired
    private CustomerDB customerDB;



    public void uploadPhoto(SalesmanLogin user , Map params , ResponseData response){


        UploadPhotoModel uploadPhotoModel ;
        try {
            uploadPhotoModel = UtilsConstant.mapToBean(params, UploadPhotoModel.class);
        }catch (Exception e){

            log.error("转换实体类异常：" , e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);

            return ;
        }

        String customer_no = uploadPhotoModel.getCustomerNo();


                /** 手持身份证照片  **/
        String handheldIDPhoto = uploadPhotoModel.getHandheldIDPhoto();

        /** 身份证正面照片 **/
        String IDCardFrontPhoto = uploadPhotoModel.getIDCardFrontPhoto();

        /** 身份证反面照片 **/
        String IDCardReversePhoto = uploadPhotoModel.getIDCardReversePhoto();

        /** 营业执照照片**/
        String businessPhoto = uploadPhotoModel.getBusinessPhoto();

        /**  开户许可证 **/
        String openingLicensePhoto = uploadPhotoModel.getOpeningLicense();

        /**  店面照片 **/
        String storeFontPhoto = uploadPhotoModel.getStoreFront();

        /**  店内内景 **/
        String interiorPhoto = uploadPhotoModel.getInterior();

        /**  街道 **/
        String streetPhoto = uploadPhotoModel.getStreet();

        /**  其他照片 **/
        String otherPhoto = uploadPhotoModel.getOther();


        String handheldIDURL = "", IDCardFront = "", IDCardReverse = "", bankCard = "", business = "" , openingLicense = "" ,
                storeFont = "" , interior = "",  street = "" , other = "";

        try {

            log.info(customer_no + "保存照片信息,保存顺序：手持身份证照片，身份证正面照照片，身份证反面照片，银行卡照片，营业执照照片");

            String imgName = UtilsConstant.getUUID();
            String postfix = ".jpg";

            if (!new File(imgPath + customer_no + File.separator).exists()) {
                log.info(customer_no + "保存图片的文件夹不存在，将创建文件 ，文件夹名称为该用户的pos 商户号");
                new File(imgPath + customer_no + File.separator).mkdirs();
            }

            //imgUrl + customer_no + File.separator +
            if (!UtilsConstant.strIsEmpty(handheldIDPhoto)) {
                Image64Bit.GenerateImage(handheldIDPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + postfix);
                handheldIDURL =  imgName + postfix;
                log.info(user.getLoginID() + "保存手持身份证照片成功");
            } else {
                log.info(user.getLoginID() + "手持身份证照片为空");
            }


            if (!UtilsConstant.strIsEmpty(IDCardFrontPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(IDCardFrontPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + postfix);
                IDCardFront =  imgName + postfix;

                log.info(user.getLoginID() + "保存身份证正面照片成功");
            } else {
                log.info(user.getLoginID() + "身份证正面照片为空");
            }


            if (!UtilsConstant.strIsEmpty(IDCardReversePhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(IDCardReversePhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + postfix);
                IDCardReverse =  imgName + postfix;

                log.info(user.getLoginID() + "保存身份证反面照片成功");
            } else {
                log.info(user.getLoginID() + "身份证反面照片为空");
            }

            if (!UtilsConstant.strIsEmpty(businessPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(businessPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                business =  imgName + postfix;

                log.info(user.getLoginID() + "保存营业执照照片成功");
            } else {
                log.info(user.getLoginID() + "营业执照照片为空");
            }


            if (!UtilsConstant.strIsEmpty(openingLicensePhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(openingLicensePhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                openingLicense =  imgName + postfix;

                log.info(user.getLoginID() + "保存开户许可证成功");
            } else {
                log.info(user.getLoginID() + "开户许可证照片为空");
            }

            if (!UtilsConstant.strIsEmpty(storeFontPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(storeFontPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                storeFont =  imgName + postfix;

                log.info(user.getLoginID() + "保存店面照片成功");
            } else {
                log.info(user.getLoginID() + "店面照片为空");
            }


            if (!UtilsConstant.strIsEmpty(interiorPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(interiorPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                interior =  imgName + postfix;

                log.info(user.getLoginID() + "保存店内内景成功");
            } else {
                log.info(user.getLoginID() + "店内内景为空");
            }


            if (!UtilsConstant.strIsEmpty(streetPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(streetPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                street =  imgName + postfix;

                log.info(user.getLoginID() + "保存街道照片成功");
            } else {
                log.info(user.getLoginID() + "街道照片为空");
            }


            if (!UtilsConstant.strIsEmpty(otherPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(otherPhoto.replace("\n", "").replace("\t", ""), imgPath + customer_no + File.separator + imgName + ".jpg");
                other =  imgName + postfix;

                log.info(user.getLoginID() + "保存其他照片成功");
            } else {
                log.info(user.getLoginID() + "其他为空");
            }

        } catch (IOException e) {
            log.error(user.getLoginID() + "照片信息保存失败", e);
            log.info(user.getLoginID() + "照片信息保存失败");
            response.setRespCode(RespCode.IMGSAVEError[0]);
            response.setRespDesc(RespCode.IMGSAVEError[1]);
            return ;
        }



        Map<String,Object> customerMap = customerDB.customerInfo(new Object[]{uploadPhotoModel.getCustomerNo()});


        String sql = "update CUSTOMER_DETAIL set ID_PHOTO=? , LICENSE_PHOTO=?,SPOT_PHOTO=?,OPENING_PHOTO=?,OTHER_PHOTO=? , BANNER_PHOTO=? , COUNTER_PHOTO=? , SPOT_PHOTO=? " +
                " , OWNER_PHOTO =?  where CUSTOMER_ID=?";

        int ret = customerDetailDB.updateCustomerPhoto(new Object[]{ IDCardFront , business , storeFont , openingLicense  , other  , interior , street ,
                handheldIDURL,  customerMap.get("ID")});


        if (ret > 0) {
            log.info(customer_no + "上传照片成功");
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        } else {
            log.info(customer_no + "上传照片更新数据库失败");
            response.setRespCode(RespCode.ServerDBError[0]);
            response.setRespDesc(RespCode.ServerDBError[1]);
        }

        System.gc();
        System.runFinalization();


//        int ret = merchantPhotoDB.updateMerchantIDCardPhotoInfo(new Object[]{IDCardFront , IDCardReverse , handheldIDurl ,uploadPhotoModel.getCustomerNo()});
//
//        if (ret > 0) {
//            log.info(uploadPhotoModel.getCustomerNo() + "上传照片成功");
//            response.setRespCode(RespCode.SUCCESS[0]);
//            response.setRespDesc(RespCode.SUCCESS[1]);
//        } else {
//            log.info(uploadPhotoModel.getCustomerNo() + "上传照片更新数据库失败");
//            response.setRespCode(RespCode.ServerDBError[0]);
//            response.setRespDesc(RespCode.ServerDBError[1]);
//        }


        System.gc();
        System.runFinalization();

    }
}

