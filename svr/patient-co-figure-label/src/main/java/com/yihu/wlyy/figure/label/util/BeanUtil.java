package com.yihu.wlyy.figure.label.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @project patient-co-figure-label
 * @author litaohong on 2018/3/27
 */
public class BeanUtil {

    /**
     * 获取类字段名
     * @param obj
     * @return
     */
    public static String[] getFiledName(Object obj) {
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                //id字段去掉
                if(StringUtils.equals("id",fields[i].getName())){
                    continue;
                }
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> beanToMap(Object obj) {
        Map<String, String> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                //id字段去掉
                if (StringUtils.equals("id", field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {

        if (map == null){
            return null;
        }

        Object bean = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(bean, map);
        return bean;
    }
}
