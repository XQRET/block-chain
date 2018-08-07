package cn.inbs.blockchainpurse.service.photo.impl;

import cn.inbs.blockchain.common.property.PropertyUtils;
import cn.inbs.blockchain.common.third.FileUpload2AliYun;
import cn.inbs.blockchain.dao.purse.photo.PursePhotoUrlMapper;
import cn.inbs.blockchain.dao.purse.po.PursePhotoUrl;
import cn.inbs.blockchain.dao.purse.po.PurseUser;
import cn.inbs.blockchainpurse.common.constants.PurseConfigPropertyConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessErrorConstants;
import cn.inbs.blockchainpurse.common.exception.PurseBusinessException;
import cn.inbs.blockchainpurse.controller.user.inputparam.UploadUserPhotoInput;
import cn.inbs.blockchainpurse.controller.user.outputparam.UploadUserPhotoOutput;
import cn.inbs.blockchainpurse.service.photo.IPursePhotoService;
import cn.inbs.blockchainpurse.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @Description: //TODO
 * @Package: cn.inbs.blockchainpurse.service.photo.impl
 * @ClassName: PursePhotoServiceImpl
 * @Author: zhangmingyang
 * @CreateDate: 2018/7/27 12:20
 * @Version: 1.0
 */
@Service("pursePhotoService")
public class PursePhotoServiceImpl implements IPursePhotoService {
    private static Logger logger = LoggerFactory.getLogger(PursePhotoServiceImpl.class);

    @Resource
    private IUserService userService;

    @Resource
    private PursePhotoUrlMapper pursePhotoUrlMapper;

    @Override
    public UploadUserPhotoOutput updateByUploadUserPhoto(UploadUserPhotoInput input) {
        UploadUserPhotoOutput output = new UploadUserPhotoOutput();

        //查询用基本信息
        PurseUser purseUser = userService.queryPurseUserByToken(input.getPurseToken());

        //获取文件存储路劲上传文件到阿里云
        String fileFolder = PropertyUtils.getStringValue(PurseConfigPropertyConstants.ALIYUN_CONTRACT_FILE_PHOTO_PATH, null);
        String fileUrl;
        try {
            fileUrl = FileUpload2AliYun.photoFileUpload(input.getUserPhoto(), fileFolder);
        } catch (IOException e) {
            if (logger.isWarnEnabled()) {
                logger.warn("用户:[{}]上传文件到阿里云失败:", purseUser.getPurseUserName(), e);
            }
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0010);
        }

        //添加用户图片信息
        createPhotoUrl(purseUser, fileUrl);

        //组装出参并返回
        output.setUserPhotoUrl(fileUrl);

        if (logger.isInfoEnabled()) {
            logger.info("用户:[{}]上传图片成功", purseUser.getPurseUserName());
        }

        return output;
    }

    /**
     * 添加用户图片信息
     *
     * @param purseUser
     * @param fileUrl
     */
    private void createPhotoUrl(PurseUser purseUser, String fileUrl) {
        //记录文件信息
        Date insertDate = new Date();
        PursePhotoUrl insert = new PursePhotoUrl();
        insert.setPurseUserId(purseUser.getId());
        insert.setType(PursePhotoUrl.PURSE_USER_PHOTO_URL);
        insert.setUrl(fileUrl);
        insert.setCreateTime(insertDate);
        insert.setUpdateTime(insertDate);
        int count = pursePhotoUrlMapper.insertPursePhotoUrl(insert);
        if (1 != count) {
            throw new PurseBusinessException(PurseBusinessErrorConstants.PURSE_USER_0010);
        }
    }
}
