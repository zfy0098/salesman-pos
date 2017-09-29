package com.rhjf.salesman.service;

import com.rhjf.salesman.constant.Constants;
import com.rhjf.salesman.constant.RespCode;
import com.rhjf.salesman.db.InternetMerchantDB;
import com.rhjf.salesman.model.ResponseData;
import com.rhjf.salesman.model.SalesmanLogin;
import com.rhjf.salesman.model.UploadPhotoModel;
import com.rhjf.salesman.utils.Image64Bit;
import com.rhjf.salesman.utils.ObjectMapUtils;
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
 */
@Service("uploadPhotoService")
@Transactional
public class UploadPhotoService {


    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${imgPath}")
    private String imgPath;

    @Value("${imgPath}")
    private String imgUrl;

    @Autowired
    private InternetMerchantDB internetMerchantDB;


    public void uploadPhoto(SalesmanLogin user , Map params , ResponseData response){

        UploadPhotoModel uploadPhotoModel ;
        try {
            uploadPhotoModel = ObjectMapUtils.mapToObject(params, UploadPhotoModel.class);
        }catch (Exception e){

            log.error("转换实体类异常：" , e);
            response.setRespCode(RespCode.NETWORKError[0]);
            response.setRespDesc(RespCode.NETWORKError[1]);

            return ;
        }


        /** 手持身份证照片  **/
        String handheldIDPhoto = uploadPhotoModel.getHandheldIDPhoto();

        /** 身份证正面照片 **/
        String IDCardFrontPhoto = uploadPhotoModel.getIDCardFrontPhoto();

        /** 身份证反面照片 **/
        String IDCardReversePhoto = uploadPhotoModel.getIDCardReversePhoto();

        /** 银行卡照片 **/
        String bankCardPhoto = uploadPhotoModel.getBankCardPhoto();

        /** 营业执照照片**/
        String businessPhoto = uploadPhotoModel.getBusinessPhoto();


        String handheldIDurl = "", iDCardFront = "", iDCardReverse = "", bankCard = "", business = "";

        try {

            log.info(user.getLoginID() + "保存照片信息,保存顺序：手持身份证照片，身份证正面照照片，身份证反面照片，银行卡照片，营业执照照片");

            String imgName = UtilsConstant.getUUID();
            String postfix = ".jpg";

            if (!new File(imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator).exists()) {
                log.info(uploadPhotoModel.getMerchantLoginID() + "保存图片的文件夹不存在，将创建文件 ，文件夹名称为该用户的手机号");
                new File(imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator).mkdirs();
            }


            if (!UtilsConstant.strIsEmpty(handheldIDPhoto)) {
                Image64Bit.GenerateImage(handheldIDPhoto.replace("\n", "").replace("\t", ""), imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix);
                handheldIDurl = imgUrl + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix;
                log.info(user.getLoginID() + "保存手持身份证照片成功");
            } else {
                log.info(user.getLoginID() + "手持身份证照片为空");
            }


            if (!UtilsConstant.strIsEmpty(IDCardFrontPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(IDCardFrontPhoto.replace("\n", "").replace("\t", ""), imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix);
                iDCardFront = imgUrl + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix;

                log.info(user.getLoginID() + "保存身份证正面照片成功");
            } else {
                log.info(user.getLoginID() + "身份证正面照片为空");
            }


            if (!UtilsConstant.strIsEmpty(IDCardReversePhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(IDCardReversePhoto.replace("\n", "").replace("\t", ""), imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix);
                iDCardReverse = imgUrl + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix;

                log.info(user.getLoginID() + "保存身份证反面照片成功");
            } else {
                log.info(user.getLoginID() + "身份证反面照片为空");
            }


            if (!UtilsConstant.strIsEmpty(bankCardPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(bankCardPhoto.replace("\n", "").replace("\t", ""), imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix);
                bankCard = imgUrl + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix;

                log.info(user.getLoginID() + "保存银行卡照片成功");
            } else {
                log.info(user.getLoginID() + "银行卡照片为空");
            }


            if (!UtilsConstant.strIsEmpty(businessPhoto)) {
                imgName = UtilsConstant.getUUID();
                Image64Bit.GenerateImage(businessPhoto.replace("\n", "").replace("\t", ""), imgPath + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + ".jpg");
                business = imgUrl + uploadPhotoModel.getMerchantLoginID() + File.separator + imgName + postfix;

                log.info(user.getLoginID() + "保存营业执照照片成功");
            } else {
                log.info(user.getLoginID() + "营业执照照片为空");
            }


        } catch (IOException e) {
            log.error(user.getLoginID() + "照片信息保存失败", e);
            log.info(user.getLoginID() + "照片信息保存失败");
            response.setRespCode(RespCode.IMGSAVEError[0]);
            response.setRespDesc(RespCode.IMGSAVEError[1]);
            return ;
        }

        Map<String, String> photoMap = new HashMap<>();
        photoMap.put("HandheldIDPhoto", handheldIDurl);
        photoMap.put("IDCardFrontPhoto", iDCardFront);
        photoMap.put("IDCardReversePhoto", iDCardReverse);
        photoMap.put("BankCardPhoto", bankCard);
        photoMap.put("BusinessPhoto", business);
        photoMap.put("loginID", uploadPhotoModel.getMerchantLoginID());


        int ret = internetMerchantDB.updateMerchantPhotoInfo(new Object[]{iDCardFront , iDCardReverse , handheldIDurl ,uploadPhotoModel.getMerchantLoginID()});

        if (ret > 0) {
            log.info(uploadPhotoModel.getMerchantLoginID() + "上传照片成功");
            response.setRespCode(RespCode.SUCCESS[0]);
            response.setRespDesc(RespCode.SUCCESS[1]);
        } else {
            log.info(uploadPhotoModel.getMerchantLoginID() + "上传照片更新数据库失败");
            response.setRespCode(RespCode.ServerDBError[0]);
            response.setRespDesc(RespCode.ServerDBError[1]);
        }


        System.gc();
        System.runFinalization();

    }
}

