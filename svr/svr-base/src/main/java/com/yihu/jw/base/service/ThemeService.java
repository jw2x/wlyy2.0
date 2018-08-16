package com.yihu.jw.base.service;

import com.yihu.jw.base.dao.ThemeDao;
import com.yihu.jw.entity.base.theme.ThemeDO;
import com.yihu.mysql.query.BaseJpaService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by progr1mmer on 2018/8/16.
 */
@Service
@Transactional
public class ThemeService extends BaseJpaService<ThemeDO, ThemeDao> {


}
