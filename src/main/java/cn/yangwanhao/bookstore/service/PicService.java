package cn.yangwanhao.bookstore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author 杨万浩
 * @description PicService类
 * @date 2020/3/7 15
 */
public interface PicService {

    /**
     * Description: 上传图片
     * @param file 文件
     * @return 图片的路径
     * @author 杨万浩
     * @date 2020/3/7 15:19
     */
    Map<String, String> uploadPic(MultipartFile file) throws IOException;

}
