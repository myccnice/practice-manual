package com.myccnice.manual.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFTemplateExport {
    
    private String templatePdfPath;
    private String fontName = "simsun.ttc,1";
 
    public PDFTemplateExport(String templatePdfPath) {
        this.templatePdfPath = templatePdfPath;
    }
 
    public PDFTemplateExport(String templatePdfPath, String fontName) {
        this.templatePdfPath = templatePdfPath;
        this.fontName = fontName;
    }
 
    /**
     * 根据模版导出PDF文档
     * @param os 输出流
     * @param textFields 文本字段
     * @param barcodeFields 条码字段
     * @param qrcodeFields 二维码字段
     * @param tableFields 表格字段
     * @throws Exception
     */
    public void export(OutputStream os, Map<String, Object> textFields, Map<String, Object> barcodeFields, Map<String, Object> qrcodeFields,Map<String, PDFTableDto> tableFields) throws Exception {
        //读取模版
        PdfReader reader = new PdfReader(templatePdfPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);
 
        //使用中文字体
        BaseFont bf = BaseFont.createFont(getFontPath(fontName), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);
 
        AcroFields s = ps.getAcroFields();
        s.setSubstitutionFonts(fontList);
        
        //遍历表单字段
        for (Map.Entry<String, Object> entry : textFields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            s.setFieldProperty(key, "textfont", bf, null);
            s.setField(key, getBlank(value));
        }
        
        //遍历条码字段
        for (Map.Entry<String, Object> entry : barcodeFields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 获取属性的类型
            if(value != null && s.getField(key) != null){
                //获取位置(左上右下)
                FieldPosition fieldPosition = s.getFieldPositions(key).get(0);
                //绘制条码
                Barcode39 barcode39 = new Barcode39();
                //字号
                barcode39.setSize(12);
                //条码高度
                barcode39.setBarHeight(30);
                //条码与数字间距
                barcode39.setBaseline(10);
                //条码值
                barcode39.setCode(value.toString());
                barcode39.setStartStopText(false);
                barcode39.setExtended(true);
                //绘制在第一页
                PdfContentByte cb = ps.getOverContent(1);
                //生成条码图片
                Image image128 = barcode39.createImageWithBarcode(cb, null, null);
                //左边距(居中处理)
                float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
                //条码位置
                image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
                //加入条码
                cb.addImage(image128);
            }
        }
        
        //遍历二维码字段
        for (Map.Entry<String, Object> entry : qrcodeFields.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // 获取属性的类型
            if(value != null && s.getField(key) != null){
                //获取位置(左上右下)
                FieldPosition fieldPosition = s.getFieldPositions(key).get(0);
                //绘制二维码
                float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
                BarcodeQRCode pdf417 = new BarcodeQRCode(value.toString(), (int)width, (int)width, null);
                //生成二维码图像
                Image image128 = pdf417.getImage();
                //绘制在第一页
                PdfContentByte cb = ps.getOverContent(1);
                //左边距(居中处理)
                float marginLeft = (fieldPosition.position.getRight() - fieldPosition.position.getLeft() - image128.getWidth()) / 2;
                //条码位置
                image128.setAbsolutePosition(fieldPosition.position.getLeft() + marginLeft, fieldPosition.position.getBottom());
                //加入条码
                cb.addImage(image128);
            }
        }
        
        //遍历表格字段
        Font keyfont = new Font(bf, 8, Font.BOLD);// 设置字体大小 
        Font textfont = new Font(bf, 8, Font.NORMAL);// 设置字体大小 
        for (Map.Entry<String, PDFTableDto> entry : tableFields.entrySet()) {
            String key = entry.getKey();
            PDFTableDto tableDto = entry.getValue();
            // 获取属性的类型
            if(tableDto != null && tableDto.getColFields() != null && s.getField(key) != null){
                //获取位置(左上右下)
                FieldPosition fieldPosition = s.getFieldPositions(key).get(0);
                float width = fieldPosition.position.getRight() - fieldPosition.position.getLeft();
                //创建表格
                String[] thread = tableDto.getColNames() != null ? tableDto.getColNames().split(",") : tableDto.getColFields().split(",");
                PdfPTable table = new PdfPTable(thread.length);
                try{ 
                    table.setTotalWidth(width); 
                    table.setLockedWidth(true); 
                    table.setHorizontalAlignment(Element.ALIGN_CENTER);      
                    table.getDefaultCell().setBorder(1); 
                }catch(Exception e){
                    e.printStackTrace(); 
                }
                //创建表头
                for (String col : thread) {
                    table.addCell(createCell(col, keyfont, Element.ALIGN_CENTER));
                }
                //创建表体
                String[] fields = tableDto.getColFields().split(",");
                List<Map<String, Object>> dataList = tableDto.getDataList();
                if(dataList != null && dataList.size() > 0){
                    for(int i=0;i<dataList.size();i++){
                        Map<String, Object> row = dataList.get(i);
                        for (String field : fields) {
                            table.addCell(createCell(row.get(field), textfont));
                        }
                    }
                }
                //插入文档
                PdfContentByte cb = ps.getOverContent(1);
                table.writeSelectedRows(0, -1, 0, -1, fieldPosition.position.getLeft(), fieldPosition.position.getTop(), cb);
            }
        }
 
        ps.setFormFlattening(true);
        ps.close();
        
        os.write(bos.toByteArray());
        os.flush();
        os.close();
        
        bos.close();
        reader.close();
    }
    
    /**
     * 创建单元格
     * @param value 显示内容
     * @param font 字体
     * @param align 对齐方式
     * @return
     */
    private static PdfPCell createCell(Object value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(getBlank(value), font));
        return cell;
    }
 
    /**
     * 创建单元格
     * @param value 显示内容
     * @param font 字体
     * @return
     */
    private static PdfPCell createCell(Object value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPhrase(new Phrase(getBlank(value), font));
        return cell;
    }
    
    /**
     * 非空处理
     * @param value
     * @return
     */
    private static String getBlank(Object value) {
        if(value != null){
            return value.toString();
        }
        return "";
    }
    
    /**
     * 获取字体文件
     * @param fontName
     * @return
     */
    private String getFontPath(String fontName) {
        String fontPath = "C:\\Windows\\Fonts\\" + fontName;
 
        // 判断系统类型，加载字体文件
        java.util.Properties prop = System.getProperties();
        String osName = prop.getProperty("os.name").toLowerCase();
        if (osName.indexOf("linux") > -1) {
            fontPath = "/usr/share/fonts/" + fontName;
        }
        return fontPath;
    }
    
    public static void main(String[] args) throws Exception {
        File outputFile = new File("C:\\Users\\XiongRx\\Desktop\\export.pdf");
        Map<String, Object> textFields = new HashMap<String, Object>();
        textFields.put("ifCode", "ZC-TXJ-01");
        textFields.put("ifName", "获取单台或多台车实时位置及油耗数据");
        textFields.put("ifSource", "天行健");
        textFields.put("ifFrequency", "实时");
        textFields.put("ifType", "WebService");
        textFields.put("ifDesc", "当整车系统需要获取某台或多台车的实时位置时，即可实时调用此接口获取所查询车辆的坐标数据、油耗数据等");
 
        Map<String, Object> barcodeFields = new HashMap<String, Object>();
        barcodeFields.put("ifLogic", "12312312312");
        
        Map<String, Object> qrcodeFields = new HashMap<String, Object>();
        qrcodeFields.put("qrCode", "http://blog.csdn.net/ruixue0117/article/details/77599808");
        
        PDFTableDto tableDto = new PDFTableDto();
        tableDto.setColNames("第1列,第2列,第3列,第4列,第5列");
        tableDto.setColFields("col1,col2,col3,col4,col5");
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        for(int i=0;i<15;i++){
            Map<String, Object> row = new HashMap<String, Object>();
            for(int j=1;j<5;j++){
                row.put("col"+j, "col"+j);
            }
            dataList.add(row);
        }
        tableDto.setDataList(dataList);
        Map<String, PDFTableDto> tableFields = new HashMap<String, PDFTableDto>();
        tableFields.put("table", tableDto);
        
        outputFile.createNewFile();
        new PDFTemplateExport("C:\\Users\\XiongRx\\Desktop\\Simple1.pdf").export(new FileOutputStream(outputFile), textFields, barcodeFields, qrcodeFields, tableFields);
    }
}
