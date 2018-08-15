package com.yihu.jw.web.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.web.model.BaseEnvelop;
import com.yihu.jw.web.model.ListEnvelop;
import com.yihu.jw.web.model.MultiEnvelop;
import com.yihu.jw.web.model.ObjEnvelop;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by progr1mmer on 2018/8/15.
 */
public abstract class EnvelopRestEndpoint {

    @Autowired
    protected ObjectMapper objectMapper;

    protected BaseEnvelop success(String message) {
        return success(message, 200);
    }

    protected BaseEnvelop success(String message, int status) {
        BaseEnvelop baseEnvelop = new BaseEnvelop();
        baseEnvelop.setMessage(message);
        baseEnvelop.setStatus(status);
        return baseEnvelop;
    }

    protected <J> ObjEnvelop<J> success(J obj){
        return success(obj, "success", 200);
    }

    protected <J> ObjEnvelop<J> success(J obj, String message){
        return success(obj, message, 200);
    }

    protected <J> ObjEnvelop<J> success(J obj, String message, Integer status){
        ObjEnvelop<J> objEnvelop = new ObjEnvelop<>();
        objEnvelop.setMessage(message);
        objEnvelop.setStatus(status);
        objEnvelop.setObj(obj);
        return objEnvelop;
    }

    protected <T> ListEnvelop<T> success(List<T> detailModelList){
        return success(detailModelList, "message", 200);
    }

    protected <T> ListEnvelop<T> success(List<T> detailModelList, String message){
        return success(detailModelList, message, 200);
    }

    protected <T> ListEnvelop<T> success(List<T> detailModelList, String message, Integer status){
        ListEnvelop<T> listEnvelop = new ListEnvelop<>();
        listEnvelop.setMessage(message);
        listEnvelop.setStatus(status);
        listEnvelop.setDetailModelList(detailModelList);
        return listEnvelop;
    }

    protected <T> MultiEnvelop success(List<T> detailModelList, int totalCount, int currPage, int rows) {
        return success(detailModelList, "success", 200, totalCount, currPage, rows);
    }

    protected <T> MultiEnvelop success(List<T> detailModelList, String message, int totalCount, int currPage, int rows) {
        return success(detailModelList, message, 200, totalCount, currPage, rows);
    }

    protected <T> MultiEnvelop success(List<T> detailModelList, String message, int status, int totalCount, int currPage, int rows) {
        return success(detailModelList, null, message, status, totalCount, currPage, rows);
    }

    protected <T, J> MultiEnvelop success(List<T> detailModelList, J obj, String message, int status, int totalCount, int currPage, int rows) {
        MultiEnvelop multiEnvelop = new MultiEnvelop();
        multiEnvelop.setMessage(message);
        multiEnvelop.setStatus(status);
        multiEnvelop.setDetailModelList(detailModelList);
        multiEnvelop.setObj(obj);
        multiEnvelop.setTotalCount(totalCount);
        multiEnvelop.setCurrPage(currPage);
        multiEnvelop.setPageSize(rows);
        if (totalCount % rows > 0) {
            multiEnvelop.setTotalPage((totalCount / rows) + 1);
        } else {
            multiEnvelop.setTotalPage(totalCount / rows);
        }
        return multiEnvelop;
    }

    public <T> T toEntity(String json, Class<T> target) throws IOException {
        T entity = objectMapper.readValue(json, target);
        return entity;
    }

    public <T> T convertToModel(Object source, Class<T> target, String... properties) {
        if (source == null) {
            return null;
        }
        T _target = BeanUtils.instantiate(target);
        BeanUtils.copyProperties(source, _target, propertyDiffer(properties, target));
        return _target;
    }

    public <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> target){
        if (null == sources) {
            return null;
        }
        sources.forEach(item -> {
            T _target = BeanUtils.instantiate(target);
            BeanUtils.copyProperties(item, _target);
            targets.add(_target);
        });
        return targets;
    }

    public <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> target, String properties) {
        if (null == sources) {
            return null;
        }
        sources.forEach(item -> {
            T _target = BeanUtils.instantiate(target);
            BeanUtils.copyProperties(item, _target, propertyDiffer(StringUtils.isEmpty(properties) ? null : properties.split(","), target));
            targets.add(_target);
        });
        return targets;
    }

    protected String[] propertyDiffer(String[] properties, Class target) {
        if (properties == null || properties.length == 0) {
            return null;
        }
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target);
        List<String> propertiesList = Arrays.asList(properties);
        List<String> arrayList = new ArrayList<>();
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && !propertiesList.contains(targetPd.getName())) {
                arrayList.add(targetPd.getName());
            }
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    protected String randomString(int length) {
        String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());//0~61
            buffer.append(str.charAt(number));
        }
        return buffer.toString();
    }

}
