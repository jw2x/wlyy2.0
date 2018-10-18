//package com.yihu.jw.business.sms.service;
//
//import com.yihu.jw.base.sms.BaseSmsDO;
//import com.yihu.jw.business.sms.dao.SmsDao;
//import com.yihu.jw.business.sms.vo.SMSHttpVo;
//import com.yihu.jw.exception.ApiException;
//import com.yihu.jw.restmodel.common.Envelop;
//import com.yihu.jw.restmodel.common.base.BaseEnvelop;
//import com.yihu.jw.util.common.SmsValidateCodeUtils;
//import com.yihu.jw.util.date.DateUtil;
//import com.yihu.jw.vo.ValidateCode;
//import com.yihu.mysql.query.BaseJpaService;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by chenweida on 2017/5/22.
// */
//@Service
//public class SmsService  extends BaseJpaService<BaseSmsDO, SmsDao> {
//    @Autowired
//    private SmsDao smsDao;
//    @Autowired
//    private SMSHttpVo smsHttpVo;
//
//
////    @Transactional
////    public BaseSmsDO createSms(BaseSmsDO sms) throws ApiException {
////
////        return smsDao.save(sms);
////    }
////
////    @Transactional
////    public BaseSmsDO updateSms(BaseSmsDO sms) {
////        return smsDao.save(sms);
////    }
//
//
//    /**
//     * 发送验证码（不走权限判断，包括：注册、找回密码）
//     * @param mobile
//     * @param ip
//     * @param type
//     * @return
//     * @throws Exception
//     */
//    @Transactional
//    public BaseEnvelop send(String mobile, String ip, int type,String saasId) throws Exception {
//        // 1、同一手机一天不允许超过5条短信
//        //1.1获取一天的开始和结束时间
//        String today = DateUtil.getStringDateShort();
//        Date begin = DateUtil.strToDate(today + " 00:00:00", DateUtil.YYYY_MM_DD_HH_MM_SS);
//        Date end = DateUtil.strToDate(today + " 23:59:59", DateUtil.YYYY_MM_DD_HH_MM_SS);
//        int smsCount = smsDao.countByMobile(mobile,begin,end);
//        if(smsCount>=5){
//            return Envelop.getError("您的验证码次数已用完，请明天再尝试！",-1);
//        }
//        // 2、60秒之内不允许重复发送
////        PageRequest pageRequest = new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "id"));
//        List<BaseSmsDO> page = smsDao.findByMobileType(mobile,type,saasId);
//        if (page.size()>0) {
////            for (BaseSmsDO sms : page) {
////                if (sms == null) {
////                    continue;
////                }
//                // 计算间隔时间
//                Date temp = DateUtil.getNextMin(page.get(0).getCreateTime(), 1);
//                long leftTime = (temp.getTime() - System.currentTimeMillis()) / 1000;
//                if (leftTime > 0) {
//                    return BaseEnvelop.getError("发送短信验证码间隔时间为：60秒！");
//                }
////            }
//        }
//        //3、生成验证码
//        ValidateCode vc = SmsValidateCodeUtils.generate(6,1);
//
//        //4、保存验证码(1微信端注册，2微信端找回密码，3医生端找回密码，4患者登录，5医生登录)
//        BaseSmsDO sms = new BaseSmsDO();
//        sms.setMobile(mobile);
//        sms.setIp(ip);
//        sms.setType(type);
//        sms.setCaptcha(vc.getCode());
//        switch (type){
//            case 1:sms.setContent("您的注册验证码为："+vc.getCode());break;
//            case 2:sms.setContent("您找回密码验证码为："+vc.getCode());break;
//            case 3:sms.setContent("您找回密码验证码为："+vc.getCode());break;
//        }
//        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//        sms.setDeadline(s.parse(vc.getCreateTimeString()));
//        sms.setStatus(1);
//        sms.setOrgCode(saasId);
//        //5、 调用发送信息的接口
////        String result = httpClientUtil.post(smsHttpVo.getUrl(), buildSmsParams(sms.getContent(), mobile), "GBK");
////        JSONObject json = toJson(result);
////        if (json == null) {
////            // 发送失败
////            return BaseEnvelop.getError("短信接口请求失败！");
////        } else if (json.getInt("result") != 0) {
////            return BaseEnvelop.getError(json.getString("description"));
////        } else {
////            //发送成功，保存到数据库
////        }
//        smsDao.save(sms);
//        if(smsCount>=2&&smsCount<=5){
//           return  BaseEnvelop.getSuccess("今日可重发验证码剩余"+(5-smsCount)+"次，请尽快完成验证。！");
//        }
//        return BaseEnvelop.getSuccess("验证码发送成功！");
//    }
//
//    //封装发送短信的参数
//    public  List<NameValuePair> buildSmsParams(String content, String mobile) {
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("SpCode", smsHttpVo.getCode()));
//        params.add(new BasicNameValuePair("LoginName", smsHttpVo.getName()));
//        params.add(new BasicNameValuePair("Password", smsHttpVo.getPassword()));
//        params.add(new BasicNameValuePair("MessageContent", content));
//        params.add(new BasicNameValuePair("UserNumber", mobile));
//        params.add(new BasicNameValuePair("SerialNumber", String.valueOf(System.currentTimeMillis())));
//        params.add(new BasicNameValuePair("ScheduleTime", ""));
//        params.add(new BasicNameValuePair("f", "1"));
//        return params;
//    }
//
//    //校验验证码是否正确
//    public Envelop checkSms(String mobile,String saasId,int type,String captcha){
//        PageRequest pageRequest = new PageRequest(0, 1, new Sort(Sort.Direction.DESC, "id"));
//        List<BaseSmsDO> page = smsDao.findByMobileType(mobile,type,saasId);
////        if (page != null) {
////            for (BaseSmsDO sms : page) {
//                BaseSmsDO sms = page.get(0);
//                String toCaptcha = sms.getCaptcha();
//                Date deadline = sms.getDeadline();
//                if(new Date().before(deadline)){
//                    //对比校验码是否正确
//                    if(captcha.equals(toCaptcha)){
//                        Envelop.getSuccess("验证码校验成功！");
//                    }else{
//                        return Envelop.getError("验证码错误！");
//                    }
//                }else{
//                    //过期
//                    return Envelop.getError("验证码过期！");
//                }
//
////            }
////        }
//        return Envelop.getError("未发送验证码！");
//    }
//
//    //解析短信返回的json数据
//    public JSONObject toJson(String result) {
//        JSONObject json = new JSONObject();
//        try {
//            String[] temps = result.split("&");
//            for (String temp : temps) {
//                if (temp.split("=").length != 2) {
//                    continue;
//                }
//                String key = temp.split("=")[0];
//                String value = temp.split("=")[1];
//                json.put(key, value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return json;
//    }
//
//
//}