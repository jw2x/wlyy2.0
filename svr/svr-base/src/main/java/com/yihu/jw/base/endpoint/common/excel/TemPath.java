package com.yihu.jw.base.endpoint.common.excel;

import com.yihu.utils.date.DateUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * @author lincl
 * @version 1.0
 * @created 2016/6/19
 */
public class TemPath {
    public static Logger logger  = Logger.getLogger(TemPath.class);
    public final static String tmpdir = System.getProperty("java.io.tmpdir");
    public final static String separator = System.getProperty("file.separator");
    public final static String defPath =separator;

    public static String createFileName( String type, String parentFile, String fileType) throws IOException {
        File file = new File( tmpdir + separator + defPath + parentFile + separator);
        if(!file.exists()) {
            file.mkdirs();
        }
        String curPath = DateUtil.getCurrentString("yyyy_MM_dd") + separator;
        String fileUrl = tmpdir + separator + defPath + parentFile + separator + curPath;
        logger.warn("upload addrr = " + fileUrl);
        file = new File( fileUrl );
        if(!file.exists()) {
            file.mkdirs();
        }
        return curPath + DateUtil.getCurrentString("HHmmss") + "_" + type + fileType;
    }

    public static String getFullPath(String fileName, String parent){

        return tmpdir + separator + defPath + parent + separator + fileName;
    }

}
