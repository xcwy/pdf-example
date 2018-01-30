package io.rai.example.component;

import com.ibm.icu.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 */
public class ExportComponent {

  public void export(String jasperFileName, Map<String, Object> parameters,
      JRDataSource dataSource, String filename) {
    InputStream in = null;
    JasperPrint jasperPrint;
    try {
      in = getClass().getResourceAsStream("/".concat(jasperFileName));
      JasperReport report = JasperCompileManager.compileReport(in);
      jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);
    } catch (JRException e) {
      e.printStackTrace();
      System.out.println("JasperExport==>export填充jasper文件错误");
      throw new RuntimeException("填充jasper文件错误", e);
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("JasperExport==>export==>关闭异常==>");
      }
    }

    filename = buildFileName(filename);

    export(jasperPrint, filename);
  }


  private String buildFileName(String filename) {
    try {
      if (StringUtils.isNotBlank(filename)) {
        filename = URLEncoder.encode(filename, "UTF-8");
      } else {
        filename = new SimpleDateFormat("yyyyMMddHHmmss")
            .format(new Date());
      }
    } catch (UnsupportedEncodingException e) {
      System.out.println("JasperExport==>export==>");
      e.printStackTrace();
    }
    return filename;
  }

  private void export(JasperPrint print, String fileName) {
    try {
      fileName = "pdf/" + fileName + ".pdf";
      File pdf = new File(fileName);
      System.out.println(pdf.getPath());
      JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (JRException e) {
      e.printStackTrace();
    }

  }
}
