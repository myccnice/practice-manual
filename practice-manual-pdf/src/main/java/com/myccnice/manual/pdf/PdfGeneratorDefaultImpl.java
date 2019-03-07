package com.myccnice.manual.pdf;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

public class PdfGeneratorDefaultImpl implements PdfGenerator {
    
    // 定义日志接口
    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorDefaultImpl.class);
 
    /**
     * 生成Pdf文件到下载输出流
     * @param generator
     * @param request
     * @param response
     */
    @Override
    public void generatePdf(@SuppressWarnings("rawtypes") PDFGeneratorDto generator, HttpServletRequest request, HttpServletResponse response) {
        // 如果Data中没有数据，则返回错误
        if (generator == null) {
            throw new RuntimeException("No Pdf Data !");
        }
 
        OutputStream outputStream = null;
        PDFTemplateExport pdfExport = null;
        try {
            // 初始化输出流
            String fileName = StringUtils.getString(generator.getFileName(), UUID.randomUUID());
            outputStream = initOutputStream(request, response, fileName, generator.isOnlineView());
            // 初始化模版
            String fontPath = StringUtils.getString(generator.getFontPath());
            String tempPath = StringUtils.getString(generator.getTempPath());
            String rootDir = request.getSession().getServletContext().getRealPath("/");
            tempPath = rootDir + "assets" + File.separator + "pdf" + File.separator + tempPath;
            if (StringUtils.isNullOrEmpty(fontPath)) {
                pdfExport = new PDFTemplateExport(tempPath);
            } else {
                pdfExport = new PDFTemplateExport(tempPath, fontPath);
            }
            // 写入数据
            Map<String, Object> textFields = generator.getTextFields();
            Map<String, Object> barcodeFields = generator.getBarcodeFields();
            Map<String, Object> qrcodeFields = generator.getQrcodeFields();
            Map<String, PDFTableDto> tableFields = generator.getTableFields();
            pdfExport.export(outputStream, textFields, barcodeFields, qrcodeFields, tableFields);
        } catch (Exception exception) {
            logger.warn(exception.getMessage(), exception);
        } finally {
            IOUtils.closeStream(outputStream);
        }
    }
 
    /**
     * 初始化输出流
     * @param request
     * @param response
     * @param fileName
     * @param isOnLine
     * @return
     * @throws UtilException
     */
    private OutputStream initOutputStream(HttpServletRequest request, HttpServletResponse response, String fileName, boolean isOnLine) throws UtilException {
        try {
            // 中文文件名兼容性调整
            String enableFileName = "";
            String agent = (String) request.getHeader("USER-AGENT");
            if (agent != null && agent.indexOf("like Gecko") != -1) {// IE11
                enableFileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            }else if (agent != null && agent.indexOf("MSIE") == -1) {// FF
                enableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            } else { // IE
                enableFileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
            }
 
            // 输出文件流
            response.reset(); // 非常重要
            if (isOnLine) { // 在线打开方式
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + enableFileName);
            } else { // 纯下载方式
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
            }
            return response.getOutputStream();
        } catch (Exception e) {
            throw new UtilException("pdf 流初始化失败", e);
        }
    }
}
