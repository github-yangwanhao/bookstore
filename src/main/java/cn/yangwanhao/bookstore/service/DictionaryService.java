package cn.yangwanhao.bookstore.service;


import cn.yangwanhao.bookstore.vo.DictionaryVo;

import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/10 21:06
 */

public interface DictionaryService {

    /**
     * Description: 查询整个字典表
     * @return
     * @author 杨万浩
     * @createDate 2019/12/10 21:08
     */
    List<DictionaryVo> listDictionaries();

}
