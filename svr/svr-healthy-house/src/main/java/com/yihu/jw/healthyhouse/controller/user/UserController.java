package com.yihu.jw.healthyhouse.controller.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.cache.WlyyRedisVerifyCodeService;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.service.user.UserService;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.web.ObjEnvelop;
import com.yihu.jw.restmodel.web.PageEnvelop;
import com.yihu.jw.restmodel.web.endpoint.EnvelopRestEndpoint;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.log4j.spi.ErrorCode;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用户相关 接口
 * @author HZY
 * @created 2018/9/19 17:29
 */
@Api(value = "UserController", description = "用户信息", tags = {"用户"})
@RequestMapping("/user")
@RestController
public class UserController  extends EnvelopRestEndpoint {

    @Autowired
    private UserService userService;
    @Autowired
    private WlyyRedisVerifyCodeService wlyyRedisVerifyCodeService;

    @GetMapping("/userList")
    @ApiOperation(value = "获取用户列表")
    public PageEnvelop userList(
            @ApiParam(name = "page", value = "页数", required = true)@RequestParam(required = true, name = "page") Integer page,
            @ApiParam(name = "pageSize", value = "每页数量", required = true)@RequestParam(required = true, name = "pageSize") Integer pageSize,
            @ApiParam(name = "city", value = "所在市区", required = false)@RequestParam(required = false, name = "city") String city,
            @ApiParam(name = "activated", value = "用户状态", required = false)@RequestParam(required = false, name = "activated") String activated ,
            @ApiParam(name = "name", value = "姓名/手机号", required = false)@RequestParam(required = false, name = "name") String name ,
            @ApiParam(name = "order", value = "使用次数排序", required = false)@RequestParam(required = false, name = "order") String order  ) throws ManageException {
        Map<String, String> map = new HashMap<>();
        map.put("cityCode",city);
        map.put("activated",activated);
        map.put("name",name);
        map.put("telephone",name);
        Page<User> users = userService.userList(page, pageSize, map, order);
        return PageEnvelop.getSuccessListWithPage("列表获取成功",users.getContent(),page,pageSize,users.getTotalElements());
    }


    @GetMapping("/userDetail")
    @ApiOperation(value = "获取用户详情")
    public ObjEnvelop userDetail(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ) {
        User user = userService.findById(userId);
        return ObjEnvelop.getSuccess("获取成功",user);
    }


    @GetMapping("/usedFacilityCount")
    @ApiOperation(value = "获取用户统计信息")
    public ObjEnvelop usedFacilityCount(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ) {
        Map<String, Long> userStatistics = userService.findUserStatistics();
        return ObjEnvelop.getSuccess("获取成功",userStatistics);
    }

    @PostMapping("/activateUser")
    @ApiOperation(value = "用户激活")
    public Envelop activeUser(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "operator", value = "操作者", required = true)@RequestParam(required = true, name = "operator") String operator ) {
         userService.updateStatus(userId,operator, HouseUserContant.activated_active,null);
        return ObjEnvelop.getSuccess("激活成功");
    }


    @PostMapping("/freezeUser")
    @ApiOperation(value = "用户冻结")
    public Envelop freezeUser(
            @ApiParam(name = "userId", value = "用户id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "reason", value = "冻结原因", required = true)@RequestParam(required = true, name = "reason") String reason ,
            @ApiParam(name = "operator", value = "操作者", required = true)@RequestParam(required = true, name = "operator") String operator ) {

        userService.updateStatus(userId,operator, HouseUserContant.activated_lock,reason);
        return ObjEnvelop.getSuccess("冻结成功");
    }


    @PostMapping("/facilityUseUpdate")
    @ApiOperation(value = "更新设施使用次数")
    public Envelop facilityUseUpdate(
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "facilityId", value = "设施Id", required = true)@RequestParam(required = true, name = "facilityId") String facilityId ) throws ManageException {

        userService.updateFacilityUse(userId,facilityId);
        return ObjEnvelop.getSuccess("更新用户使用设施次数成功");
    }

    @PostMapping("/updatePwd")
    @ApiOperation(value = "更新密码")
    public Envelop updatePwd(
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "oldPwd", value = "原密码", required = true)@RequestParam(required = true, name = "oldPwd") String oldPwd ,
            @ApiParam(name = "newPwd", value = "新密码", required = true)@RequestParam(required = true, name = "newPwd") String newPwd ) throws ManageException {

        userService.updatePwd(userId,oldPwd,newPwd);
        return ObjEnvelop.getSuccess("更新密码成功");
    }

    @PostMapping("/updatePhone")
    @ApiOperation(value = "更新安全手机号码")
    public Envelop updatePhone(
            @ApiParam(name = "clientId", value = "应用id", required = true)@RequestParam(required = true, name = "clientId") String clientId,
            @ApiParam(name = "userId", value = "用户Id", required = true)@RequestParam(required = true, name = "userId") String userId ,
            @ApiParam(name = "newPhone", value = "新安全手机号", required = true)@RequestParam(required = true, name = "newPhone") String newPhone ,
            @ApiParam(name = "captcha", value = "短信验证码", required = true)@RequestParam(required = true, name = "captcha") String captcha ) throws ManageException {

        //验证码
        if (wlyyRedisVerifyCodeService.verification(clientId, newPhone, captcha)) {
            userService.updateSecurePhone(userId,newPhone);
            return ObjEnvelop.getSuccess("更新安全手机号码成功");
        } else {
            return ObjEnvelop.getError("验证码错误");
        }
    }

    /******************************   导入导出   ***********************************/

    @RequestMapping("/exportToExcel")
    public void exportToExcel(
            HttpServletResponse response,
            @ApiParam(name = "city", value = "所在市区", required = false)@RequestParam(required = false, name = "city") String city,
            @ApiParam(name = "activated", value = "用户状态", required = false)@RequestParam(required = false, name = "activated") String activated ,
            @ApiParam(name = "name", value = "姓名/手机号", required = false)@RequestParam(required = false, name = "name") String name ,
            @ApiParam(name = "sort", value = "使用次数排序", required = false)@RequestParam(required = false, name = "sort") String sort) throws ManageException {
        Envelop envelop = new Envelop();
        //获取用户数据
        Map<String, String> map = new HashMap<>();
        map.put("cityCode",city);
        map.put("activated",activated);
        map.put("name",name);
        map.put("telephone",name);
        List<User> userList = userService.userList( map, sort);
        try {
            String fileName = "标准数据集"+"_";
            //设置下载
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String( fileName.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
            OutputStream os = response.getOutputStream();
            //获取导出数据集
            JSONObject order = new JSONObject();
            order.put("id","asc");
            //获取导出数据元
            String ids="";
            User userInfo;
            for(int i=0;i<userList.size();i++){
                userInfo =   userList.get(i);
                if(i!=0){
                    ids+=",";
                }
                ids +=userInfo.getId();
            }

            //写excel
            Workbook workbook = new XSSFWorkbook();
            int k=0;
            User metaData = null;
            int row=0;
                //创建Excel工作表 指定名称和位置
                String streetName = "健康小屋-用户列表";
                Sheet sheet = workbook.createSheet(streetName);
                addStaticCell(sheet);
                addCell(sheet,1,0,"测试行");//名称

                //添加数据元信息
                WritableCellFormat wc = new WritableCellFormat();
                wc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.SKY_BLUE);//边框
                for(int j=0;k<userList.size(); j++,k++){
                    metaData = userList.get(k);
                    row=j+2;
                    addCell(sheet,0,row,j+1+"");//序号
                    addCell(sheet,1,row, metaData.getId());//内部id
                    addCell(sheet,2,row, metaData.getLoginCode());//登录名
                    addCell(sheet,3,row, metaData.getName());//名称
                    addCell(sheet,4,row, metaData.getGender());//性别
                    addCell(sheet,5,row, metaData.getTelephone());//电话
                    addCell(sheet,6,row, metaData.getCityName() + metaData.getAreaName() + metaData.getStreet());//地区
                    addCell(sheet,7,row, metaData.getFacilityUsedCount().toString());//使用设施次数
                    addCell(sheet,8,row, metaData.getIdCardNo());//身份证

                }

            //写入工作表
            workbook.write(os);
            workbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            Envelop.getError("导出用户列表失败!");
        }
    }

    //添加单元格内容
    private void addCell(Sheet sheet,int column,int row,String data){
        Row sheetRow = sheet.getRow(row);
        if(sheetRow==null){
            sheetRow = sheet.createRow(row);
        }
        Cell cell= sheetRow.createCell(column);
        cell.setCellValue(data);
    }
    //添加单元格内容带样式
    private void addCell(Sheet sheet,int column,int row,String data,CellStyle cellStyle){
        Row sheetRow = sheet.getRow(row);
        if(sheetRow==null){
            sheetRow = sheet.createRow(row);
        }
        Cell cell= sheetRow.createCell(column);
        cell.setCellValue(data);
        cell.setCellStyle(cellStyle);
    }

    //excel中添加固定内容
    private void addStaticCell(Sheet sheet){
        addCell(sheet,0,0,"名称");
        addCell(sheet,0,1,"标识");
        addCell(sheet,0,2,"参考");
        addCell(sheet,0,3,"备注");

        //设置样式
        Workbook workbook = sheet.getWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());//設置背景色
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        addCell(sheet,0,4,"序号",style);
        addCell(sheet,1,4,"内部标识",style);
        addCell(sheet,2,4,"数据元编码",style);
        addCell(sheet,3,4,"数据元名称",style);
        addCell(sheet,4,4,"数据元定义",style);
        addCell(sheet,5,4,"数据类型",style);
        addCell(sheet,6,4,"表示形式",style);
        addCell(sheet,7,4,"术语范围值",style);
        addCell(sheet,8,4,"列名",style);
        addCell(sheet,9,4,"列类型",style);
        addCell(sheet,10,4,"列长度",style);
        addCell(sheet,11,4,"主键",style);
        addCell(sheet,12,4,"可为空",style);
    }






}
