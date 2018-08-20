package com.yihu.jw.util.wechat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Trick on 2018/8/20.
 */
@Component
public class PushMsgTask {

    private static Logger logger = LoggerFactory.getLogger(PushMsgTask.class);

    private static LinkedBlockingQueue<JSONObject> queue = new LinkedBlockingQueue<JSONObject>();




}
