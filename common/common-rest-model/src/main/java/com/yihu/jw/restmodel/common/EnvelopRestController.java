package com.yihu.jw.restmodel.common;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.yihu.jw.exception.ApiException;
import com.yihu.jw.exception.code.ExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
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
public class EnvelopRestController {
    private Logger logger = LoggerFactory.getLogger(EnvelopRestController.class);
    @Value("${spring.profiles}")
    private String profile;

    protected final static String ResourceCount = "X-Total-Count";
    protected final static String ResourceLink = "Link";

    protected void error(Exception e) {
        logger.error(e.getMessage());
        if (!"prod".equals(profile)) {
            e.printStackTrace();
        }
    }

    /**
     * 返回一个信封对象。信封对象的返回场景参见 Envelop.
     *
     * @param modelList
     * @param totalCount
     * @return
     */
    protected Envelop getResult(List modelList, int totalCount) {
        Envelop envelop = new Envelop();
        envelop.setDetailModelList(modelList);
        envelop.setTotalCount(totalCount);

        return envelop;
    }

    //Json转实体类
    public <T> T toEntity(String json, Class<T> entityCls) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            T entity = objectMapper.readValue(json, entityCls);
            return entity;
        } catch (IOException ex) {
            throw new ApiException("Unable to parse json, " + ex.getMessage(), ExceptionCode.common_error_params_code);
        }
    }

    /**
     * 将实体集合转换为模型集合。
     *
     * @param <T>
     * @param sources
     * @param targets
     * @param targetCls
     * @param properties @return
     */
    public <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> targetCls, String properties) {
        if (sources == null) {
            return null;
        }
        Iterator iterator = sources.iterator();
        while (iterator.hasNext()) {
            Object source = iterator.next();

            T target = (T) BeanUtils.instantiate(targetCls);
            BeanUtils.copyProperties(source, target, propertyDiffer(StringUtils.isEmpty(properties) ? null : properties.split(","), targetCls));
            targets.add(target);
        }

        return targets;
    }

    /**
     * 将实体集合转换为模型集合。
     * @param sources
     * @param targets
     * @param targetCls
     * @param <T>
     * @return
     */
    public <T> List<T> convertToModels(Collection sources, List<T> targets, Class<T> targetCls){
        sources.forEach(one -> {
            T target = (T) BeanUtils.instantiate(targetCls);
            BeanUtils.copyProperties(one, target);
            targets.add(target);
        });
        return targets;
    }

    /**
     * 将实体转换为模型。
     *
     * @param source
     * @param targetCls
     * @param properties
     * @param <T>
     * @return
     */
    public <T> T convertToModel(Object source, Class<T> targetCls, String... properties) {
        if (source == null) {
            return null;
        }
        T target = BeanUtils.instantiate(targetCls);
        BeanUtils.copyProperties(source, target, propertyDiffer(properties, targetCls));
        return target;
    }

    /**
     * 计算不在类中的属性。
     *
     * @return
     */
    protected String[] propertyDiffer(String[] properties, Class targetCls) {
        if (properties == null || properties.length == 0) return null;

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(targetCls);
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

    /**
     * 客户端调用REST接口时，若返回的是分页结果，则需要在响应头中添加资源的总数量及其他资源的分页导航。
     * EHR平台使用响应头中的 X-Total-Count 字段记录资源的总数量，link header作为其他资源的分页导航。
     *
     * @return
     */
    public void pagedResponse(
            HttpServletRequest request,
            HttpServletResponse response,
            Long resourceCount, Integer currentPage, Integer pageSize) {
        if (request == null || response == null) return;

        response.setHeader(ResourceCount, Long.toString(resourceCount));
        if (resourceCount == 0) return;

        if (currentPage == null) currentPage = new Integer(PageArg.DefaultPage);
        if (pageSize == null) pageSize = new Integer(PageArg.DefaultSize);


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

        response.setHeader(ResourceLink, linkMap(map));
    }

    private String linkMap(Map<String, String> map) {
        StringBuffer links = new StringBuffer("");
        for (String key : map.keySet()) {
            links.append(map.get(key)).append("; ").append(key);
        }

        return links.toString();
    }
}
