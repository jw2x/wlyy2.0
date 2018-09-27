package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.constant.LoginInfo;
import com.yihu.jw.healthyhouse.constant.UserConstant;
import com.yihu.jw.healthyhouse.dao.user.UserDao;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.util.poi.ExcelUtils;
import com.yihu.jw.restmodel.web.Envelop;
import com.yihu.jw.restmodel.wlyy.HouseUserContant;
import com.yihu.jw.util.common.IdCardUtil;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.jw.util.security.MD5;
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.OutputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author HZY
 * @created 2018/9/18 19:48
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private FacilityUsedRecordService facilityUsedRecordService;

    public User findById(String id) {
        return userDao.findById(id);
    }

    public User findByCode(String code) {
        return userDao.findByLoginCode(code);
    }

    public User findByLoginCodeAndUserType(String loginCode,String userType) {
        return userDao.findByLoginCodeAndUserType(loginCode,userType);
    }


    /**
     * 分页获取用户列表
     *
     * @param page
     * @param pageSize
     * @param map
     * @return
     * @throws ManageException
     */
    public Page<User> userList(Integer page, Integer pageSize, Map<String, String> map, String order) throws ManageException {
        order = order == null ? "ASC" : order;
        // 排序
        Sort sort = new Sort(Sort.Direction.DESC, "facilityUsedCount");
        // 分页信息
        PageRequest pageRequest = new PageRequest(page - 1, pageSize, sort);
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        // 所在市区
        String cityCode = map.get("cityCode");
        if (!StringUtils.isEmpty(cityCode)) {
            filters.put("cityCode", new SearchFilter("cityCode", SearchFilter.Operator.EQ, cityCode));
        }
        // 激活状态
        String activated = map.get("activated");
        if (!StringUtils.isEmpty(activated)) {
            filters.put("activated", new SearchFilter("activated", SearchFilter.Operator.EQ, activated));
        }
        // 用户名称
        String name = map.get("name");
        if (!StringUtils.isEmpty(name)) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        // 电话号码
        String mobile = map.get("telephone");
        if (!StringUtils.isEmpty(mobile)) {
            filters.put("telephone", new SearchFilter("telephone", SearchFilter.Operator.LIKE, mobile));
        }

        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return userDao.findAll(spec, pageRequest);
    }


    /**
     *  不分页条件搜索
     * @param map
     * @param order
     * @return
     * @throws ManageException
     */
    public List<User> userList( Map<String, String> map, String order) throws ManageException {
        order = order == null ? "ASC" : order;
        // 排序
        Sort sort = new Sort(Sort.Direction.fromString(order), "facilityUsedCount");
        // 设置查询条件
        Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
        // 所在市区
        String cityCode = map.get("cityCode");
        if (!StringUtils.isEmpty(cityCode)) {
            filters.put("cityCode", new SearchFilter("cityCode", SearchFilter.Operator.EQ, cityCode));
        }
        // 激活状态
        String activated = map.get("activated");
        if (!StringUtils.isEmpty(activated)) {
            filters.put("activated", new SearchFilter("activated", SearchFilter.Operator.EQ, activated));
        }
        // 用户名称
        String name = map.get("name");
        if (!StringUtils.isEmpty(name)) {
            filters.put("name", new SearchFilter("name", SearchFilter.Operator.LIKE, name));
        }
        // 电话号码
        String mobile = map.get("telephone");
        if (!StringUtils.isEmpty(mobile)) {
            filters.put("telephone", new SearchFilter("telephone", SearchFilter.Operator.LIKE, mobile));
        }

        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        return userDao.findAll(spec,sort);
    }


    /**
     * 更改用户状态
     *
     * @param id       待删除id
     * @param userCode 修改者code
     * @param status   更改状态
     * @param reason   更改状态原因
     */
    @Transactional
    public void updateStatus(String id, String userCode, Integer status, String reason) {
        User user = userDao.findByLoginCode(userCode);
        User user1 = findById(id);
        user1.setActivated(status);
        user1.setActivatedContent(reason);
        if (user != null) {
            String userName = user.getName();
            user1.setUpdateUserName(userName);
        }
        user1.setUpdateUser(userCode);
        userDao.save(user1);
    }

    /**
     * 新增/修改用户
     *
     * @param user     用户信息
     * @param userCode 操作者编码
     * @return
     * @throws ManageException
     */
    public Envelop saveOrUpdate(User user, String userCode) throws ManageException {
        User loginUser = userDao.findByLoginCode(userCode);
        if (user.getId() == null) {//保存
            //判断登陆账号是否存在
            User user1 = userDao.findByLoginCode(userCode);
            if (user1 != null) {//登陆账号已存在
                throw new ManageException("该登陆账号已存在");
            }
            String salt = UUID.randomUUID().toString().replaceAll("-", "");
            user.setSalt(salt);
            String password = MD5.GetMD5Code(user.getPassword() + salt);
            user.setPassword(password);
            user.setCreateUser(userCode);
            user.setCreateTime(new Date());
            user.setActivated(1);
            userDao.save(user);
            return Envelop.getSuccess("保存成功");
        } else {//修改
            if (loginUser != null) {
                String userName = loginUser.getName();
                user.setUpdateUserName(userName);
            }
            user.setUpdateUser(userCode);
            userDao.save(user);
            return Envelop.getSuccess("修改成功");
        }
    }


    @Transactional
    public void updatePwd(String userId, String oldPwd,String newPwd) throws ManageException {
        User user = findById(userId);
        if (user==null) {
            throw new ManageException("该账号不存在");
        }

        if (!user.getPassword().equals(MD5.GetMD5Code(oldPwd + user.getSalt()))) {
            //保存登陆信息
            String message = "原密码错误";
            throw new ManageException(message);
        }

        String password = MD5.GetMD5Code(newPwd + user.getSalt());
        user.setPassword(password);
        userDao.save(user);
    }

    @Transactional
    public void updateSecurePhone(String userId, String phone) throws ManageException {
        User user = findById(userId);
        if (user==null) {
            throw new ManageException("该账号不存在");
        }

        user.setTelephone(phone);
        userDao.save(user);
    }

    /**
     * 获取 用户统计信息
     *
     * @return
     */
    public Map<String, Long> findUserStatistics() {
        Map<String, Long> result = new HashMap<>();
        //用户总数
        Long totalCount = userDao.countAllByUserType(LoginInfo.USER_TYPE_PATIENT);
        //今日新增数
        Date start = DateUtil.getDateStart();
        Date end = DateUtil.getDateEnd();
        Long newCount = userDao.countAllByCreateTimeBetween(start, end);
        //在线用户数
        Long activeCount = userDao.countAllByActivated(HouseUserContant.activated_active);
        //用户设施使用总次数
        Long usePricilityCount = facilityUsedRecordService.countAll();
        result.put("totalCount",totalCount);
        result.put("newCount",newCount);
        result.put("activeCount",activeCount);
        result.put("usePricilityCount",usePricilityCount);
        return result;
    }

    /**
     *  用户身份证认证
     * @param userId    用户ID
     * @param idCardNo  身份证
     * @throws ManageException
     */
    public void checkIdCardNo(String userId, String idCardNo) throws ManageException {
        User user1 = findById(userId);
        if (user1==null) {
            throw new ManageException("该账号不存在");
        }
        if(!IdCardUtil.cardCodeVerifySimple(idCardNo)){
            throw new ManageException("身份证号格式有误");
        }
        // 更新身份证验证字段
        user1.setRealnameAuthentication(UserConstant.AUTHORIZED);
        userDao.save(user1);
    }

   
    //excel中添加固定内容
    private void addStaticCell(Sheet sheet){
        //设置样式
        Workbook workbook = sheet.getWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());//設置背景色
        Font font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);
        ExcelUtils.addCellData(sheet,0,0,"序号",style);
        ExcelUtils.addCellData(sheet,1,0,"内部标识",style);
        ExcelUtils.addCellData(sheet,2,0,"登录账号",style);
        ExcelUtils.addCellData(sheet,3,0,"名称",style);
        ExcelUtils.addCellData(sheet,4,0,"身份证号",style);
        ExcelUtils.addCellData(sheet,5,0,"性别",style);
        ExcelUtils.addCellData(sheet,6,0,"电话",style);
        ExcelUtils.addCellData(sheet,7,0,"地址",style);
        ExcelUtils.addCellData(sheet,8,0,"设施使用次数",style);

    }

    /**
     *  导出用户列表excel
     * @param response  响应体
     * @param userList  用户列表
     * @throws ManageException
     */
    public void exportUsersExcel(HttpServletResponse response, List<User> userList) throws ManageException {
        try {
            String fileName = "健康小屋-用户列表";
            //设置下载
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String( fileName.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
            OutputStream os = response.getOutputStream();
            //获取导出数据集
            JSONObject order = new JSONObject();
            order.put("id","asc");

            //写excel
            Workbook workbook = new XSSFWorkbook();
            int k=0;
            User metaData = null;
            int row=0;
            //创建Excel工作表 指定名称和位置
            String streetName = "健康小屋-用户列表";
            Sheet sheet = workbook.createSheet(streetName);
            addStaticCell(sheet);

            //添加数据元信息
            WritableCellFormat wc = new WritableCellFormat();
            wc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.SKY_BLUE);//边框
            for(int j=0;k<userList.size(); j++,k++){
                metaData = userList.get(k);
                row=j+1;
                ExcelUtils.addCellData(sheet,0,row,j+1+"");//序号
                ExcelUtils.addCellData(sheet,1,row, metaData.getId());//内部id
                ExcelUtils.addCellData(sheet,2,row, metaData.getLoginCode());//登录名
                ExcelUtils.addCellData(sheet,3,row, metaData.getName());//名称
                ExcelUtils.addCellData(sheet,4,row, metaData.getIdCardNo());//身份证
                ExcelUtils.addCellData(sheet,5,row, metaData.getGender());//性别
                ExcelUtils.addCellData(sheet,6,row, metaData.getTelephone());//电话
                ExcelUtils.addCellData(sheet,7,row, metaData.getAddress());//地区
                ExcelUtils.addCellData(sheet,8,row, metaData.getFacilityUsedCount().toString());//使用设施次数

            }

            //写入工作表
            workbook.write(os);
            workbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            throw new ManageException("导出用户列表异常",e);
        }
    }



}
