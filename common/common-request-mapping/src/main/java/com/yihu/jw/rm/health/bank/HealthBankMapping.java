package com.yihu.jw.rm.health.bank;

/**
 * Created by Trick on 2018/2/7.
 */
public class HealthBankMapping {

    public static final String api_health_bank_common = "svr-health-bank";
    public static final Integer api_health_bank_fail = -1;
    public static final String api_success ="success";

    public static class healthBank{
        public static final String createAccount = "/createAccount";
        public static final String selectAccount = "/selectAccount";
        public static final String createTask = "/createTask";
        public static final String deleteTask ="/deleteTask";
        public static final String findTask = "/findTask";
        public static final String updateTask ="/updateTask";
        public static final String createTaskDetail = "/createTaskDetail";
        public static final String createActivity="/createActivity";
        public static final String findActivity="/findActivity";
        public static final String updateActivity="/updateActivity";
        public static final String createActivityInfo ="/createActivityInfo";
        public static final String findActivityInfo = "/findActivityInfo";
        public static final String updateActivityInfo ="/updateActivityInfo";
        public static final String createGoods = "/createGoods";
        public static final String findGoods ="/findGoods";
        public static final String updateGoods ="/updateGoods";
        public static final String createCreditsDetail = "/createCreditsDetail";
        public static final String findCreditsLogInfo ="/findCreditsLogInfo";
        public static final String selectByRanking = "/selectByRanking";
        public static final String updateCreditsLogInfo ="/updateCreditsLogInfo";
        public static final String exchangeGoods = "/exchangeGoods";
        public static final String findExchangeGoods="/findExchangeGoods";
    }
}
