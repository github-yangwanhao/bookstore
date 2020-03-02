package cn.yangwanhao.bookstore.service.impl;

import cn.yangwanhao.bookstore.entity.Dictionary;
import cn.yangwanhao.bookstore.entity.DictionaryExample;
import cn.yangwanhao.bookstore.mapper.DictionaryMapper;
import cn.yangwanhao.bookstore.service.DictionaryService;
import cn.yangwanhao.bookstore.vo.DictionaryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/10 21:07
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<DictionaryVo> listDictionaries() {
        List<Dictionary> dictionaryList = dictionaryMapper.selectByExample(new DictionaryExample());
        List<DictionaryVo> vos = new ArrayList<>();
        for (Dictionary dictionary : dictionaryList) {
            DictionaryVo vo = new DictionaryVo();
            BeanUtils.copyProperties(vo, dictionary);
            vos.add(vo);
        }
        return vos;
    }
}
