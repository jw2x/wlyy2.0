package com.yihu.jw.web.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.web.model.*;
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

    protected Envelop success(String desc) {
        return success(desc, 200);
    }

    protected Envelop success(String desc, int code) {
        Envelop envelop = new Envelop();
        envelop.setDesc(desc);
        envelop.setCode(code);
        return envelop;
    }

    protected <J> ObjEnvelop<J> success(J data){
        return success(data, "success");
    }

    protected <J> ObjEnvelop<J> success(J data, String desc){
        return success(data, desc, 200);
    }

    protected <J> ObjEnvelop<J> success(J data, String desc, int code){
        ObjEnvelop<J> objEnvelop = new ObjEnvelop<>();
        objEnvelop.setDesc(desc);
        objEnvelop.setCode(code);
        objEnvelop.setData(data);
        return objEnvelop;
    }

    protected <T> ListEnvelop<T> success(List<T> contents){
        return success(contents, "success");
    }

    protected <T> ListEnvelop<T> success(List<T> contents, String desc){
        return success(contents, desc, 200);
    }

    protected <T> ListEnvelop<T> success(List<T> contents, String desc, int code){
        ListEnvelop<T> listEnvelop = new ListEnvelop<>();
        listEnvelop.setDesc(desc);
        listEnvelop.setCode(code);
        listEnvelop.setContents(contents);
        return listEnvelop;
    }

    protected <T> PageEnvelop success(List<T> contents, int totalCount, int currPage, int pageSize) {
        return success(contents, "success", totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop success(List<T> contents, String desc, int totalCount, int currPage, int pageSize) {
        return success(contents, desc, 200, totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop success(List<T> contents, String desc, int code, int totalCount, int currPage, int pageSize) {
        PageEnvelop<T> pageEnvelop = new PageEnvelop();
        pageEnvelop.setDesc(desc);
        pageEnvelop.setCode(code);
        pageEnvelop.setCurrPage(currPage);
        pageEnvelop.setPageSize(pageSize);
        pageEnvelop.setTotalCount(totalCount);
        pageEnvelop.setContents(contents);
        return pageEnvelop;
    }

    protected <T, J> MixEnvelop success(List<T> contents, J obj) {
        return success(contents, obj, "success");
    }

    protected <T, J> MixEnvelop success(List<T> contents, J obj, String desc) {
        return success(contents, obj, desc, 200);
    }

    protected <T, J> MixEnvelop success(List<T> contents, J data, String desc, int code) {
        return success(contents, data, desc, code, contents.size(), 1, contents.size());
    }

    protected <T, J> MixEnvelop success(List<T> contents, J data, String desc, int code, int totalCount, int currPage, int pageSize) {
        MixEnvelop<T, J> mixEnvelop = new MixEnvelop();
        mixEnvelop.setDesc(desc);
        mixEnvelop.setCode(code);
        mixEnvelop.setCurrPage(currPage);
        mixEnvelop.setPageSize(pageSize);
        mixEnvelop.setTotalCount(totalCount);
        mixEnvelop.setContents(contents);
        mixEnvelop.setData(data);
        return mixEnvelop;
    }

    protected Envelop failed(String desc) {
        return failed(desc, 500);
    }

    protected Envelop failed(String desc, int code) {
        Envelop envelop = new Envelop();
        envelop.setDesc(desc);
        envelop.setCode(code);
        return envelop;
    }

    protected <T> T toEntity(String json, Class<T> target) throws IOException {
        T entity = objectMapper.readValue(json, target);
        return entity;
    }

    protected <T> T convertToModel(Object source, Class<T> target, String... properties) {
        if (source == null) {
            return null;
        }
        T _target = BeanUtils.instantiate(target);
        BeanUtils.copyProperties(source, _target, propertyDiffer(properties, target));
        return _target;
    }

    protected <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> target){
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

    protected <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> target, String properties) {
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
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int pos = random.nextInt(str.length());
            buffer.append(str.charAt(pos));
        }
        return buffer.toString();
    }

}
