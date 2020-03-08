package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.common.constant.GlobalConstant;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.util.PicNameUtils;
import cn.yangwanhao.bookstore.service.PicService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨万浩
 * @description PicServicceImpl类
 * @date 2020/3/7 15
 */

@Slf4j
@Service
public class PicServiceImpl implements PicService {

    @Autowired
    private FastFileStorageClient storageClient;;

    @Override
    public Map<String, String> uploadPic(MultipartFile file) throws IOException {
        // 获取后缀名
        String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        if (!GlobalConstant.PIC_EXTENSION.contains(extension)) {
            log.info("上传失败，文件内容不符合要求");
            throw new GlobalException(ErrorCodeEnum.U5009010);
        }
        BufferedImage image = ImageIO.read(file.getInputStream());
        if (image == null) {
            log.info("上传失败，文件内容不符合要求");
            throw new GlobalException(ErrorCodeEnum.U5009010);
        }
        // 上传文件
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), extension, null);
        // 返回缩略图完整路径
        Map<String, String> map = new HashMap<>();
        map.put("originalImg", storePath.getFullPath());
        map.put("thumbnailImg", PicNameUtils.getThumbnailName(storePath.getFullPath()));
        return map;
    }
}
