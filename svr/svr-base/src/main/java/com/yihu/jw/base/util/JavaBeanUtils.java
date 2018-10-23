package com.yihu.jw.base.util;




import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author litaohong on 2018/9/10
 * @project jw2.0
 */
public class JavaBeanUtils {

    private static JavaBeanUtils javaBeanUtils = null;

    private JavaBeanUtils(){}

    public static JavaBeanUtils getInstance(){
        if (javaBeanUtils == null) {
            synchronized (JavaBeanUtils.class) {
                if (javaBeanUtils == null) {
                    javaBeanUtils = new JavaBeanUtils();
                }
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                }
            }
        }
        return javaBeanUtils;
    }

    private static ObjectMapper objectMapper = null;
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     *
     * @param type
     *            要转化的类型
     * @param map
     *            包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("unchecked")
    public  Object map2Bean(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        BeanUtils.populate(obj, map);
        return obj;
    }

    /**
     * 将一个 JavaBean 对象转化为一个 Map
     *
     * @param bean
     *            要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("unchecked")
    public  Map bean2Map(Object bean) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将对象集合转为集合map
     *
     * @describe：TODO
     * @param beans
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @time:2014年9月3日上午10:37:32
     */
    @SuppressWarnings("unchecked")
    public  List<Map> beans2Maps(List<Object> beans)
            throws IllegalAccessException, InvocationTargetException,
            IntrospectionException {
        List<Map> maps = new ArrayList<Map>();
        for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
            Object bean = (Object) iterator.next();
            maps.add(bean2Map(bean));
        }
        return maps;
    }

    /**
     * 将对多个Map转对对象集合返回
     *
     * @describe：TODO
     * @param type
     * @param maps
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @time:2014年9月3日上午10:40:00
     */
    @SuppressWarnings("unchecked")
    public  List<Object> mapstoBeans(Class type, List<Map> maps)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        List<Object> beans = new ArrayList<Object>();
        for (Map map : maps) {
            beans.add(map2Bean(type, map));
        }
        return beans;
    }

    /**
     * 对象复制
     *
     * @describe：TODO
     * @param toBean
     *            目标对象
     * @param fromBean
     *            对象来源
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @time:2014年9月3日上午11:47:45
     */
    public  Object copyProperties(Object toBean, Object fromBean)
            throws IllegalAccessException, InvocationTargetException {
        if (fromBean == null) {
            return null;
        }
        org.springframework.beans.BeanUtils.copyProperties(toBean, fromBean);
        return toBean;
    }

    /**
     * 对象复制(将给定的对象转化为给定的Class 类型对象并返回)
     *
     * @describe：TODO
     * @param toClassBean
     * @param fromBean
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @time:2014年9月3日下午12:05:23
     */
    public  Object copyProperties(Class toClassBean, Object fromBean)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException {
        if (fromBean == null) {
            return null;
        }
        Object toBean = Class.forName(toClassBean.getCanonicalName())
                .newInstance();
        return copyProperties(toBean, fromBean);
    }

    /**
     * 将给定的对象集合转换为指定的类对象集合
     *
     * @describe：TODO
     * @param toClassBean
     *            类 类型
     * @param beans
     *            对象集合
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @time:2014年9月3日下午12:33:24
     */
    public List copyProperties(Class toClassBean, List beans)
            throws IllegalAccessException, InvocationTargetException,
            InstantiationException, ClassNotFoundException {
        List list = new ArrayList();
        for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
            Object object = (Object) iterator.next();
            list.add(copyProperties(toClassBean, object));
        }
        return list;
    }


    /**
     * map转为json
     * @return
     */
    public String mapJson(Map<String, Object> map) throws Exception {
        if (CollectionUtils.isEmpty(map)) {
            return "paramter is null";
        }
        List<Map<String, Object>> result = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = JSONObject.parseObject(objectMapper.writeValueAsString(map));
        return jsonArray.toJSONString();
    }


    /**
     * map转为json
     * @return
     */
    public JSONArray mapListJson(List<Map<String, Object>> mapList) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (CollectionUtils.isEmpty(mapList)) {
            return jsonArray;
        }
        for(Map<String, Object> map : mapList){
            JSONObject jsonObject = JSONObject.parseObject(objectMapper.writeValueAsString(map));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
