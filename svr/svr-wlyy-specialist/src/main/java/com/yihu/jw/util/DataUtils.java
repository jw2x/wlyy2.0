package com.yihu.jw.util;/**
 * Created by nature of king on 2018/9/17.
 */

/**
 * @author wangzhinan
 * @create 2018-09-17 15:32
 * @desc 数据转换工具
 **/
public class DataUtils {

    public static Integer doubleToInt(Double d) {
        if (d == null) {
            return 0;
        }
        String currency = String.valueOf(d);
        int index = currency.indexOf(".");
        int length = currency.length();
        Integer amLong = 0;
        if (index == -1) {
            amLong = Integer.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Integer.valueOf((currency.substring(0, index + 3)).replace(".", ""));
            if (length - index > 3) {
                if (Integer.valueOf(currency.substring(index + 3, index + 4)) >= 5) {
                    amLong++;
                }
            }
        } else if (length - index == 2) {
            amLong = Integer.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Integer.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong;
    }


    public static String integerTransferDouble(Integer amount){
        try {
            if(!amount.toString().matches("\\-?[0-9]+"));
        }catch (Exception e) {
            e.printStackTrace();
        }
        int flag = 0;
        String amString = amount.toString();
        if(amString.charAt(0)=='-'){
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if(amString.length()==1){
            result.append("0.0").append(amString);
        }else if(amString.length() == 2){
            result.append("0.").append(amString);
        }else{
            String intString = amString.substring(0,amString.length()-2);
            for(int i=1; i<=intString.length();i++){
                if( (i-1)%3 == 0 && i !=1){
//                    result.append(",");
                }
                result.append(intString.substring(intString.length()-i,intString.length()-i+1));
            }
            result.reverse().append(".").append(amString.substring(amString.length()-2));
        }
        if(flag == 1){
            return "-"+result.toString();
        }else{
            return result.toString();
        }
    }
}
