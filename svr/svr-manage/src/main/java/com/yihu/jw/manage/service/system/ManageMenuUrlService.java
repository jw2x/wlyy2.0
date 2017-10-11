package com.yihu.jw.manage.service.system;

import com.yihu.jw.manage.dao.system.MenuUrlDao;
import com.yihu.jw.manage.model.system.ManageMenuUrl;
import com.yihu.jw.mysql.query.BaseJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/3 0003.
 */
@Service
public class ManageMenuUrlService extends BaseJpaService<ManageMenuUrl,MenuUrlDao> {

    @Autowired
    private MenuUrlDao menuUrlDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ManageMenuUrl> getListByMenuCode(String menuCode){
        return menuUrlDao.getListByMenuCode(menuCode);
    }

    public List<Map<String, Object>> getUrlByMenuCode(String menuCode){
        String sql = "select mmu.url,mmu.method from manage_menu_url mmu where mmu.menu_code = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, menuCode);
        return maps;
    }

    @Transactional
    public void saveOrUpdate(String menuCode,List<String> urls,List<String> methods){
        menuUrlDao.delete(menuCode);
        List<ManageMenuUrl> manageMenuUrls = new ArrayList<>();
        int i =0;
        if(urls!=null){
            for(String url:urls){
                ManageMenuUrl manageMenuUrl = new ManageMenuUrl();
                manageMenuUrl.setUrl(url);
                manageMenuUrl.setCode(UUID.randomUUID().toString().replaceAll("-",""));
                manageMenuUrl.setMenuCode(menuCode);
                if(methods!=null){ //methods要么为空,要么与urls成对出现
                    manageMenuUrl.setMethod(methods.get(i));
                    i++;
                }
                manageMenuUrls.add(manageMenuUrl);
            }
        }
        menuUrlDao.save(manageMenuUrls);
    }
}
