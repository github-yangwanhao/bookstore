package cn.yangwanhao.bookstore.controller.admin;

import cn.yangwanhao.bookstore.common.beans.ResponseMessage;
import cn.yangwanhao.bookstore.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author 杨万浩
 * @description PicUploadController类
 * @date 2020/3/7 09
 */
@Controller
@RequestMapping("/admin/pic")
public class PicUploadController {

    @Autowired
    private PicService picService;

    @RequestMapping("/upload")
    @ResponseBody
    public ResponseMessage<Map<String, String>> uploadPic(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseMessage.handleResult(picService.uploadPic(file));
    }

}
