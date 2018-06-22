package com.yihu.jw.rm.health.bank;

import javax.print.DocFlavor;

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
        public static final String findAccount = "/findAccount";
        public static final String findAccounByCondition ="/findAccounByCondition";
        public static final String createTask = "/createTask";
        public static final String deleteTask ="/deleteTask";
        public static final String findTask = "/findTask";
        public static final String updateTask ="/updateTask";
        public static final String selectByCode ="/selectByCode";
        public static final String createTaskDetail = "/createTaskDetail";
        public static final String createTaskRule = "/createTaskRule";
        public static final String updateTaskRule = "/updateTaskRule";
        public static final String findTaskRule ="/findTaskRule";
        public static final String createTaskDict = "/createTaskDict";
        public static final String updateTaskDict = "/updateTaskDict";
        public static final String findTaskDict ="/findTaskDict";
        public static final String createTaskGoods = "/createTaskGoods";
        public static final String updateTaskGoods = "/updateTaskGoods";
        public static final String findTaskGoods ="/findTaskGoods";
        public static final String createTaskRang = "/createTaskRang";
        public static final String updateTaskRang = "/updateTaskRang";
        public static final String findTaskRang = "/findTaskRang";
        public static final String attendTask = "/attendTask";
        public static final String selectByPatient = "/selectByPatient";
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
        public static final String selectByActivity = "/selectByActivity";
        public static final String selectByRanking = "/selectByRanking";
        public static final String selectByActivityRanking = "/selectByActivityRanking";
        public static final String selectByActivityRanking1 = "/selectByActivityRanking1";
        public static final String updateCreditsLogInfo ="/updateCreditsLogInfo";
        public static final String exchangeGoods = "/exchangeGoods";
        public static final String findExchangeGoods="/findExchangeGoods";
        public static final String updateRule = "/updateRule";
        public static final String insertRule = "/insertRule";
        public static final String deleteRule = "/deleteRule";
        public static final String findRules = "/findRules";
        public static final String createActiveRecord="/createActiveRecord";
        public static final String doctorAddIntegrate = "/doctorAddIntegrate";
        public static final String addStepIntegrate = "/addStepIntegrate";
    }
}
