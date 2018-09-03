package com.yihu.jw.base.dao.theme;

import com.yihu.jw.entity.base.theme.ThemeDO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Dao - 主题风格
 * Created by progr1mmer on 2018/8/16.
 */
public interface ThemeDao extends PagingAndSortingRepository<ThemeDO, Integer> {
}
