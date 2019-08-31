package com.winway.purchase.print.jasperreport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hotent.base.query.QueryFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 * 类名: PrintBase <br/>
 * 类描述: 打印基类. <br/>
 * date: 2019-4-29 下午6:38:04 <br/>
 *
 * @author 王浩伟
 * @version 2.0
 * @since JDK 1.8
 */
public abstract class PrintBase {
    private static Logger logger = LoggerFactory.getLogger(PrintBase.class);

    public  <T> List<PrintObject> getPrintDataByObj(T t){return  null;}

    public abstract List<PrintObject> getPrintData(Map<String, Object> map);

    public abstract boolean beforePrint(Map<String, Object> map);

    public abstract void afterPrint(Map<String, Object> map);

    /**
     * <b>类名:PrintBase</b><br>
     * <b>方法名:webPriewPdfPrint</b><br>
     * <b>详细描述</b>:web端PDF(直接/预览)打印<br>
     * 预览打印时，浏览器电脑需要安装Adobe PDF reader;<br>
     * 直接打印时，是服务端打印机打印。<br>
     *
     * @param prints   List<PrintObject>
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param isPriew  是否预览,此参数只有在prints数组长度为1时有意义;否则失效。默认为预览打印，设置isPriew为false且prints数组长度为1时可直接打印
     *                 void
     * @throws
     * @since 1.0.0
     */
    public void webPriewPdfPrint(List<PrintObject> prints, HttpServletRequest request, HttpServletResponse response, Boolean isPriew) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //校验打印数据
            if (prints == null || prints.size() == 0) {
                response.getWriter().print("<script>parent.alert('打印数据为空')</script>");
                return;
            }
            //组装打印数据
            List<JasperPrint> jasperPrintlist = new ArrayList<JasperPrint>();
            for (PrintObject printobject : prints) {
                File file = null;
//				ClassPathResource mbResource = new ClassPathResource("jaspertemplate" + File.separator + printobject.getPrintMbname() + ".jasper");
                ClassPathResource mbResource = new ClassPathResource("jaspertemplate" + File.separator + printobject.getPrintMbname() + ".jrxml");
                InputStream inputStream = mbResource.getInputStream();
//				try {
//					file = mbResource.getFile();	
//				}catch(IOException e) {
//					response.getWriter().print("<script>parent.alert('模板["+printobject.getPrintMbname()+"]不存在')</script>");
//				    return;
//				}
                JasperReport jasperReport;
                /*try{*/
//				file = new File("C:\\AoMeiTest\\workspace\\scm_code\\scm-start\\target\\classes\\jaspertemplate\\scm_xs_bigcontractallot.jasper");

//				jasperReport = (JasperReport)JRLoader.loadObjectFromFile(file.getPath());	
                jasperReport = JasperCompileManager.compileReport(inputStream);
//					jasperReport = (JasperReport)JRLoader.loadObject(inputStream);	
                /*}*//*catch(Exception e) {
					InputStream inputStream = getClass().getResourceAsStream("jaspertemplate" + File.separator + printobject.getPrintMbname() + ".jrxml");
		            JasperDesign jd = JRXmlLoader.load(inputStream);
		            jasperReport = (JasperCompileManager.compileReport(jd));
				}*/

                Map<String, Object> parameterValue = null;
                if (printobject.getParameters() == null) {
                    parameterValue = new HashMap<String, Object>();
                } else {
                    parameterValue = printobject.getParameters();
                }
                JRDataSource dataSource = null;
                if (printobject.getDatasource() == null) {
                    dataSource = new JREmptyDataSource();
                } else {
                    dataSource = new XyjJRDataSource(printobject.getDatasource());
                }
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterValue, dataSource);
                jasperPrintlist.add(jasperPrint);
            }
            //默认为预览打印
            boolean priew = true;
            if (isPriew == false && jasperPrintlist.size() == 1) {
                priew = false;
            }

            if (priew) {
                //输出pdf文档流
                response.setContentType("application/pdf");
                response.setCharacterEncoding("UTF-8");
                ServletOutputStream ouputStream = response.getOutputStream();
                // 使用JRPdfExproter导出器导出pdf
                JRPdfExporter exporter = new JRPdfExporter();
		        /*//方法已过时
		         * exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintlist);
		        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);*/
                exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintlist));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ouputStream));
                exporter.exportReport();
                ouputStream.flush();
                ouputStream.close();
            } else {
                //直接打印
                JasperPrintManager.printReport(jasperPrintlist.get(0), true);
            }
        } catch (Exception e) {
            logger.error("PrintBase:webPriewPdfPrint error", e);
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
