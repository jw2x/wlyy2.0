//package com.yihu.jw.base.endpoint.message;
//
//import com.yihu.jw.restmodel.web.Envelop;
//import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.activemq.command.ActiveMQTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import javax.jms.Destination;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// * Endpoint - 测试接口
// * Created by progr1mmer on 2018/8/16.
// */
//@RestController
//@RequestMapping(value = "/test")
//@Api(value = "测试接口", description = "消息测试接口", tags = {"wlyy基础服务 - 消息测试接口"})
//public class TestEndpoint extends EnvelopRestEndpoint {
//
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @PostMapping(value = "/send")
//    @ApiOperation(value = "发送消息")
//    public Envelop send (
//            @ApiParam(name = "topic", value = "主题", required = true)
//            @RequestParam(value = "topic") String topic,
//            @ApiParam(name = "message", value = "消息", required = true)
//            @RequestParam(value = "message") String message) throws Exception {
//        Destination destination = new ActiveMQTopic(topic);
//        jmsMessagingTemplate.convertAndSend(destination, message);
//        return success("success");
//    }
//
//    /*@PostMapping(value = "/es", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ApiOperation(value = "创建")
//    public Envelop es (
//            @ApiParam(name = "index", value = "索引", required = true)
//            @RequestParam(value = "index") String index,
//            @ApiParam(name = "type", value = "类型", required = true)
//            @RequestParam(value = "type") String type) throws Exception {
//        Map<String, Object> data = new HashMap<>();
//        data.put("sn1212121", "测试");
//        List<Object> dataList = new ArrayList<>();
//        dataList.add(data);
//        elastricSearchHelper.save(index, type, dataList);
//        return success("success");
//    }
//
//    @PostMapping(value = "/sql")
//    @ApiOperation(value = "查询")
//    public List<Map<String, Object>> sql (
//            @ApiParam(name = "query", value = "语句", required = true)
//            @RequestParam(value = "query") String query) throws Exception {
//        List<Map<String, Object>> result = elastricSearchHelper.executeSQL(query);
//        return result;
//    }
//
//    @PostMapping(value = "/sql_count")
//    @ApiOperation(value = "查询")
//    public Integer sqlCount (
//            @ApiParam(name = "query", value = "语句", required = true)
//            @RequestParam(value = "query") String query) throws Exception {
//        Integer result = elastricSearchHelper.executeCountSQL(query);
//        return result;
//    }*/
//
//}
