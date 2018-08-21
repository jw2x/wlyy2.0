package com.yihu.jw.restmodel.web.endpoint;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.yihu.jw.restmodel.web.ListEnvelop;
import com.yihu.jw.restmodel.web.MixEnvelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.Envelop;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * REST风格控控制器基类。
 * 基于Envelop的返回结果封装
 * HTTP响应体格式为JSON。
 * + 成功：根据各业务逻辑自行决定要返回的数据，各业务模块的返回结构不尽相同。
 * + 失败：{"status":错误码, "message":"错误原因"}
 *
 * @author Progr1mmer
 */
public abstract class EnvelopRestEndpoint {

    @Autowired
    protected ObjectMapper objectMapper;

    protected Envelop success(String message) {
        return success(message, 200);
    }

    protected Envelop success(String message, int status) {
        Envelop envelop = new Envelop();
        envelop.setMessage(message);
        envelop.setStatus(status);
        return envelop;
    }

    protected <J> ObjEnvelop<J> success(J obj){
        return success("success", obj);
    }

    protected <J> ObjEnvelop<J> success(String message, J obj){
        return success(message, 200, obj);
    }

    protected <J> ObjEnvelop<J> success(String message, int status, J obj){
        ObjEnvelop<J> objEnvelop = new ObjEnvelop<>();
        objEnvelop.setMessage(message);
        objEnvelop.setStatus(status);
        objEnvelop.setObj(obj);
        return objEnvelop;
    }

    protected <T> ListEnvelop<T> success(List<T> contents){
        return success("success", contents);
    }

    protected <T> ListEnvelop<T> success(String message, List<T> contents){
        return success(message, 200, contents);
    }

    protected <T> ListEnvelop<T> success(String message, int status, List<T> contents){
        ListEnvelop<T> listEnvelop = new ListEnvelop<>();
        listEnvelop.setMessage(message);
        listEnvelop.setStatus(status);
        listEnvelop.setDetailModelList(contents);
        return listEnvelop;
    }

    protected <T> PageEnvelop<T> success(List<T> contents, int totalCount, int currPage, int pageSize) {
        return success("success", contents, totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop<T> success(String message, List<T> contents, int totalCount, int currPage, int pageSize) {
        return success(message, 200, contents, totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop<T> success(String message, int status,  List<T> contents, int totalCount, int currPage, int pageSize) {
        PageEnvelop<T> pageEnvelop = new PageEnvelop();
        pageEnvelop.setMessage(message);
        pageEnvelop.setStatus(status);
        pageEnvelop.setCurrPage(currPage);
        pageEnvelop.setPageSize(pageSize);
        pageEnvelop.setTotalCount(totalCount);
        pageEnvelop.setDetailModelList(contents);
        return pageEnvelop;
    }

    protected <T, J> MixEnvelop<T, J> success(List<T> contents, J obj) {
        return success("success", contents, obj);
    }

    protected <T, J> MixEnvelop<T, J> success(String message, List<T> contents, J obj) {
        return success(message, 200, contents, obj);
    }

    protected <T, J> MixEnvelop<T, J> success(String message, int status, List<T> contents, J obj) {
        return success(message, status, contents, obj, contents.size(), 1, contents.size());
    }

    protected <T, J> MixEnvelop<T, J> success(String message, int status, List<T> contents, J obj, int totalCount, int currPage, int pageSize) {
        MixEnvelop<T, J> mixEnvelop = new MixEnvelop();
        mixEnvelop.setMessage(message);
        mixEnvelop.setStatus(status);
        mixEnvelop.setCurrPage(currPage);
        mixEnvelop.setPageSize(pageSize);
        mixEnvelop.setTotalCount(totalCount);
        mixEnvelop.setDetailModelList(contents);
        mixEnvelop.setObj(obj);
        return mixEnvelop;
    }

    protected <E extends Envelop> E failed(String message, Class<E> clazz) {
        return failed(message, -10000, clazz);
    }

    protected <E extends Envelop> E failed(String message, int status, Class<E> clazz) {
        try {
            E envelop = clazz.newInstance();
            envelop.setMessage(message);
            envelop.setStatus(status);
            return envelop;
        } catch (Exception e) {
            return null;
        }
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
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int pos = random.nextInt(str.length());
            builder.append(str.charAt(pos));
        }
        return builder.toString();
    }

    /**
     * 客户端调用REST接口时，若返回的是分页结果，则需要在响应头中添加资源的总数量及其他资源的分页导航。
     * EHR平台使用响应头中的 X-Total-Count 字段记录资源的总数量，link header作为其他资源的分页导航。
     *
     * @return
     */
    @Deprecated
    public void pagedResponse(
            HttpServletRequest request,
            HttpServletResponse response,
            Long resourceCount, Integer currentPage, Integer pageSize) {
        if (request == null || response == null) return;

        response.setHeader("X-Total-Count", Long.toString(resourceCount));
        if (resourceCount == 0) return;

        if (currentPage == null) currentPage = new Integer(1);
        if (pageSize == null) pageSize = new Integer(15);


        String baseUri = "<" + request.getRequestURL().append("?").toString() + request.getQueryString() + ">";
        long firstPage = currentPage == 1 ? -1 : 1;
        long prevPage = currentPage == 1 ? -1 : currentPage - 1;

        long lastPage = resourceCount % pageSize == 0 ? resourceCount / pageSize : resourceCount / pageSize + 1;
        long nextPage = currentPage == lastPage ? -1 : currentPage + 1;

        lastPage = currentPage == lastPage ? -1 : lastPage;

        Map<String, String> map = new HashMap<>();
        if (firstPage != -1)
            map.put("rel='first',", baseUri.replaceAll("page=\\d+", "page=" + Long.toString(firstPage)));
        if (prevPage != -1) map.put("rel='prev',", baseUri.replaceAll("page=\\d+", "page=" + Long.toString(prevPage)));
        if (nextPage != -1) map.put("rel='next',", baseUri.replaceAll("page=\\d+", "page=" + Long.toString(nextPage)));
        if (lastPage != -1) map.put("rel='last',", baseUri.replaceAll("page=\\d+", "page=" + Long.toString(lastPage)));

        response.setHeader("Link", linkMap(map));
    }

    private String linkMap(Map<String, String> map) {
        StringBuffer links = new StringBuffer("");
        for (String key : map.keySet()) {
            links.append(map.get(key)).append("; ").append(key);
        }

        return links.toString();
    }
}
