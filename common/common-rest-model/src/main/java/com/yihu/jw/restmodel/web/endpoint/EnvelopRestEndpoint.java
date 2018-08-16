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
 * REST风格控控制器基类。此控制器用于对API进行校验，并处理平台根层级的业务，如API参数校验，错误及返回码设定等。
 * <p>
 * 根层级的校验，如果是正确的，直接返回HTTP代码200，若出错，则会将HTTP返回代码设置为1X或2X，并在HTTP响应体中包含响应的信息。
 * HTTP响应体格式为JSON。
 * + 成功：会根据各业务逻辑自行决定要返回的数据，各业务模块的返回结构不同。
 * + 失败：{"code":"错误代码", "message":"错误原因"}
 *
 * @author zhiyong
 * @author Sand
 */
public abstract class EnvelopRestEndpoint {

    @Autowired
    protected ObjectMapper objectMapper;

    protected Envelop success(String desc) {
        return success(desc, 200);
    }

    protected Envelop success(String desc, int code) {
        Envelop envelop = new Envelop();
        envelop.setMessage(desc);
        envelop.setStatus(code);
        return envelop;
    }

    protected <J> ObjEnvelop<J> success(J data){
        return success(data, "success");
    }

    protected <J> ObjEnvelop<J> success(J data, String desc){
        return success(data, desc, 200);
    }

    protected <J> ObjEnvelop<J> success(J data, String desc, int code){
        ObjEnvelop<J> envelop = new ObjEnvelop<>();
        envelop.setMessage(desc);
        envelop.setStatus(code);
        envelop.setObj(data);
        return envelop;
    }

    protected <T> ListEnvelop<T> success(List<T> contents){
        return success(contents, "success");
    }

    protected <T> ListEnvelop<T> success(List<T> contents, String desc){
        return success(contents, desc, 200);
    }

    protected <T> ListEnvelop<T> success(List<T> contents, String desc, int code){
        ListEnvelop<T> envelop = new ListEnvelop<>();
        envelop.setMessage(desc);
        envelop.setStatus(code);
        envelop.setDetailModelList(contents);
        return envelop;
    }

    protected <T> PageEnvelop success(List<T> contents, int totalCount, int currPage, int pageSize) {
        return success(contents, "success", totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop success(List<T> contents, String desc, int totalCount, int currPage, int pageSize) {
        return success(contents, desc, 200, totalCount, currPage, pageSize);
    }

    protected <T> PageEnvelop success(List<T> contents, String desc, int code, int totalCount, int currPage, int pageSize) {
        PageEnvelop<T> envelop = new PageEnvelop();
        envelop.setMessage(desc);
        envelop.setStatus(code);
        envelop.setCurrPage(currPage);
        envelop.setPageSize(pageSize);
        envelop.setTotalCount(totalCount);
        envelop.setDetailModelList(contents);
        return envelop;
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
        MixEnvelop<T, J> envelop = new MixEnvelop();
        envelop.setMessage(desc);
        envelop.setStatus(code);
        envelop.setCurrPage(currPage);
        envelop.setPageSize(pageSize);
        envelop.setTotalCount(totalCount);
        envelop.setDetailModelList(contents);
        envelop.setObj(data);
        return envelop;
    }

    protected Envelop failed(String desc) {
        return failed(desc, 500);
    }

    protected Envelop failed(String desc, int code) {
        Envelop envelop = new Envelop();
        envelop.setMessage(desc);
        envelop.setStatus(code);
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
