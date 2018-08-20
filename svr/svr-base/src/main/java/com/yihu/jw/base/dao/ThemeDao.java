package com.yihu.jw.base.dao;

import com.yihu.jw.entity.base.theme.ThemeDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by progr1mmer on 2018/8/16.
 */
public interface ThemeDao extends PagingAndSortingRepository<ThemeDO, Integer> {
}
