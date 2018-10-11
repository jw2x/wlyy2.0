package com.yihu.jw.healthyhouse.service.user;

import com.yihu.jw.exception.business.ManageException;
import com.yihu.jw.healthyhouse.dao.user.FeedBackDao;
import com.yihu.jw.healthyhouse.model.user.FeedBack;
import com.yihu.jw.healthyhouse.model.user.User;
import com.yihu.jw.healthyhouse.util.poi.ExcelUtils;
import com.yihu.jw.util.date.DateUtil;
import com.yihu.mysql.query.BaseJpaService;
import jxl.write.Colour;
import jxl.write.WritableCellFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SQLQuery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

/**
 * 意见反馈.
 *
 * @author zdm
 * @version 1.0
 * @created 2018.09.21
 */
@Service
@Transactional
public class FeedBackService extends BaseJpaService<FeedBack, FeedBackDao> {

    @Autowired
    private FeedBackDao feedBackDao;

    public FeedBack findById(String id) {
        return  feedBackDao.findById(id);
    }


    /**
     * excel中添加固定内容
     * @param sheet
     */
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
        ExcelUtils.addCellData(sheet,1,0,"反馈ID",style);
        ExcelUtils.addCellData(sheet,2,0,"反馈类型",style);
        ExcelUtils.addCellData(sheet,3,0,"反馈用户",style);
        ExcelUtils.addCellData(sheet,4,0,"反馈时间",style);
        ExcelUtils.addCellData(sheet,5,0,"联系电话",style);
        ExcelUtils.addCellData(sheet,6,0,"反馈内容",style);
        ExcelUtils.addCellData(sheet,7,0,"回复",style);

    }

    /**
     *  导出用户反馈列表excel
     * @param response  响应体
     * @param feedBacks  用户反馈列表
     * @throws ManageException
     */
    public void exportUsersExcel(HttpServletResponse response, List<FeedBack> feedBacks) throws ManageException {
        try {
            String fileName = "健康小屋-用户反馈列表";
            //设置下载
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String( fileName.getBytes("gb2312"), "ISO8859-1" )+".xlsx");
            OutputStream os = response.getOutputStream();
            //获取导出的数据
            JSONObject order = new JSONObject();
            order.put("id","asc");

            //写excel
            Workbook workbook = new XSSFWorkbook();
            int k=0;
            FeedBack metaData = null;
            int row=0;
            //创建Excel工作表 指定名称和位置
            String streetName = "健康小屋-用户反馈列表";
            Sheet sheet = workbook.createSheet(streetName);
            addStaticCell(sheet);

            //添加表格字段信息
            WritableCellFormat wc = new WritableCellFormat();
            wc.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, Colour.SKY_BLUE);//边框
            for(int j=0;k<feedBacks.size(); j++,k++){
                metaData = feedBacks.get(k);
                row=j+1;
                ExcelUtils.addCellData(sheet,0,row,j+1+"");//序号
                ExcelUtils.addCellData(sheet,1,row, metaData.getId());//内部id
                ExcelUtils.addCellData(sheet,2,row, metaData.getFeedTypeValue());//反馈类型
                ExcelUtils.addCellData(sheet,3,row, metaData.getCreateUserName());//反馈用户
                ExcelUtils.addCellData(sheet,4,row, DateUtil.dateToStr(metaData.getCreateTime(),DateUtil.YYYY_MM_DD_HH_MM_SS));//反馈时间
                ExcelUtils.addCellData(sheet,5,row, metaData.getUserTelephone());//电话
                ExcelUtils.addCellData(sheet,6,row, metaData.getContent());//反馈内容
                ExcelUtils.addCellData(sheet,7,row, metaData.getReplyContent());//回复内容

            }

            //写入工作表
            workbook.write(os);
            workbook.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            throw new ManageException("导出用户反馈列表异常",e);
        }
    }


    /**
     * 统计
     *
     * @param userId
     * @return
     */
    public boolean getAppFeedBackByUserIdAndReadFlag(String userId) {
        String sql = "select count(1) from feedback  f where  f.create_user=:userId and f.read_flag=0";
        SQLQuery sqlQuery = currentSession().createSQLQuery(sql);
        sqlQuery.setParameter("userId", userId);
        BigInteger count = (BigInteger) sqlQuery.uniqueResult();
        return count.compareTo(new BigInteger("0")) > 0;
    }

}
