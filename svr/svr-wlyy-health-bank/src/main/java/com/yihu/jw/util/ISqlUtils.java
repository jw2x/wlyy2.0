package com.yihu.jw.util;/**
 * Created by nature of king on 2018/4/27.
 */

import com.alibaba.fastjson.JSONObject;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangzhinan
 * @create 2018-04-27 12:47
 * @desc sql自定义编辑
 **/
public class ISqlUtils {
    public static String getSql(Object object,Integer page,Integer size,String isFlag){
        StringBuffer sb = new StringBuffer();
        Class c =object.getClass();
        Table table = (Table)c.getAnnotation(Table.class);
        String tableName = table.name();
        if (isFlag.equalsIgnoreCase("count")){
            sb.append("select count(1) AS total from ").append(tableName).append(" where 1=1");
        }else if(isFlag.equalsIgnoreCase("*")){
            sb.append("select * from ").append(tableName).append(" where 1=1 ");
        }
        JSONObject object1  = (JSONObject) JSONObject.toJSON(object);
        if (object1.getString("id") !=null){
            sb.append(" and id = '" + object1.getString("id")+"' ");
        }
        Field[] fArray= c.getDeclaredFields();
        for(Field f:fArray){
                //拿到字段后与实体类中的属性匹配，并得到其get方法，用来获取他的属性值
                String getMethodName ="";
                boolean isCExist =f.isAnnotationPresent(Column.class);
                if(isCExist){
                    Column mc =f.getAnnotation(Column.class);
                    String columeName =mc.name();  //字段对应数据库名字
                    String name =f.getName();       //字段名字
                    Class a= f.getType();          //字段类型
                    Object value=null;              //字段值
                    getMethodName="get"+name.substring(0,1).toUpperCase()+name.substring(1);//拼接属性的get方法
                    try {
                        Method m =c.getMethod(getMethodName);
                        value =(Object)m.invoke(object);     //拿到属性的值
                        if(value == null || "".equals(value)){  //如果属性没值，不拼接sql
                            continue;
                        }
                        else if(value instanceof String ){
                            value ="'%"+value+"%'";
                            sb.append(" and ").append(columeName +" like " ).append(value+"");
                        }else if (value instanceof Integer){
                            value = value;
                            sb.append(" and ").append(columeName +" = " ).append(value+"");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
        if(isFlag.equalsIgnoreCase("*")){
            sb.append(" ORDER BY update_time DESC ").append("LIMIT ").append((page-1)*size+",").append(size);
        }
        return sb.toString();
    }

    public static String getAllSql(Object object){
        StringBuffer sb = new StringBuffer();
        Class c =object.getClass();
        Table table = (Table)c.getAnnotation(Table.class);
        String tableName = table.name();
        sb.append("select * from ").append(tableName).append(" where 1=1 ");
        Field[] fArray= c.getDeclaredFields();
        for(Field f:fArray){
            //拿到字段后与实体类中的属性匹配，并得到其get方法，用来获取他的属性值
            String getMethodName ="";
            boolean isCExist =f.isAnnotationPresent(Column.class);
            if(isCExist){
                Column mc =f.getAnnotation(Column.class);
                String columeName =mc.name();  //字段对应数据库名字
                String name =f.getName();       //字段名字
                Object value=null;              //字段值
                getMethodName="get"+name.substring(0,1).toUpperCase()+name.substring(1);//拼接属性的get方法
                try {
                    Method m =c.getMethod(getMethodName);
                    value =(Object)m.invoke(object);     //拿到属性的值
                    if(value == null || "".equals(value) || value.equals(Integer.parseInt("0"))){  //如果属性没值，不拼接sql
                        continue;
                    }
                    else if(value instanceof String){
                        value ="'"+value+"'";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sb.append(" and ").append(columeName +"=" ).append(value+"");
            }
        }
        return sb.toString();
    }

    public static  String getUpdateSql(Object object){
        StringBuffer sb = new StringBuffer();
        Class c = object.getClass();
        Table table = (Table)c.getAnnotation(Table.class);
        String tableName = table.name();
        sb.append("update ").append(tableName).append(" set ");
        Field[] fArray = c.getDeclaredFields();
        for (Field f:fArray){
            String getMethoName = "";
            boolean isCExist = f.isAnnotationPresent(Column.class);
            if (isCExist){
                Column mc = f.getAnnotation(Column.class);
                String columeName = mc.name();
                String name = f.getName();
                Class a= f.getType();
                Object value= null;
                getMethoName = "get" + name.substring(0,1).toUpperCase()+name.substring(1);
                try {
                    Method m = c.getMethod(getMethoName);
                    if (Date.class.isAssignableFrom(a)){
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        value = sdf.format((Object)m.invoke(object));
                    }else {
                        value = (Object)m.invoke(object);
                    }
                    if (value == null || "".equals(value)||value.equals(Integer.parseInt("0"))){
                        continue;
                    }
                    else if (value instanceof  String){
                        value = "'"+value+"'";
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                sb.append(columeName + "=").append(value+"").append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
        sb.append(" where ").append("id = ").append("'"+jsonObject.get("id")+"'");
        return sb.toString();
    }
}
