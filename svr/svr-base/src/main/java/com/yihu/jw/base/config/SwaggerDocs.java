package com.yihu.jw.base.config;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by progr1mmer on 2018/8/21.
 */
public class SwaggerDocs {
    public static void main(String[] args) throws Exception {
        //1.请求 http://ip:port/swagger-resources获取group
        String group = "Default";
        //2.定义请求地址 new URL("http://ip:port/v2/api-docs?group=" + groupName)
        URL remoteSwaggerFile = new URL("http://127.0.0.1:10020/v2/api-docs?group=" + group); //项目的swagger-ui地址
        //3.定义文件输出路径
        String prefix = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Path outputFile = Paths.get(prefix.substring(prefix.lastIndexOf(":") + 1, prefix.indexOf("target") - 1) + "/build/" + group); //文档输出地址

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                //.withBasePathPrefix()
                .build();

        Swagger2MarkupConverter converter = Swagger2MarkupConverter.from(remoteSwaggerFile)
                .withConfig(config)
                .build();

        converter.toFile(outputFile);
    }
}