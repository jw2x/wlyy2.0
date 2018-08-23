package com.yihu.jw.util.common;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyr on 2016/08/10.
 */
public class QrcodeUtil {

    /**
     * 二维码图片生成
     *
     * @param content 二维码内容
     * @param imgType 图片类型
     * @param size    图片尺寸
     * @return
     */
    public static File QrcodeEncode(String content, String fileName, String path, String imgType, int size) throws Exception {
        File pathFile = new File(path);
        File outputFile = new File(path + File.separator + fileName + ".png");
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();
        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        // 内容所使用字符集编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        // 生成二维码
        MatrixToImageWriter.writeToFile(bitMatrix, imgType, outputFile);
        return outputFile;
    }

    /**
     * 创建二维码
     * @param content
     * @return
     */
    public static InputStream createQrcode(String content,int size,String imgType) {
        byte[] imagesStream=null;
        ByteArrayOutputStream os = null;
        try {
            HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
              BarcodeFormat.QR_CODE, size, size, hints);
             os = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, imgType, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
