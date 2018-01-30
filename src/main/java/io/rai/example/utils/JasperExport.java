package io.rai.example.utils;

import com.ibm.icu.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public abstract class JasperExport {

  private static final Logger logger = LoggerFactory.getLogger(JasperExport.class);

  public void export(String jasperFileName, Map<String, Object> parameters,
      JRDataSource dataSource, String filename,
      HttpServletResponse response) {
    InputStream in = null;
    JasperPrint jasperPrint;
    try {
      in = getClass().getResourceAsStream("/".concat(jasperFileName));

      jasperPrint = JasperFillManager.fillReport(in, parameters,
          dataSource);
    } catch (JRException e) {
      e.printStackTrace();
      logger.error("JasperExport==>export填充jasper文件错误" + e);
      throw new RuntimeException("填充jasper文件错误", e);
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("JasperExport==>export==>关闭异常==>" + e);
      }
    }

    try {
      if (StringUtils.isNotBlank(filename)) {
        filename = URLEncoder.encode(filename, "UTF-8");
      } else {
        filename = new SimpleDateFormat("yyyyMMddHHmmss")
            .format(new Date());
      }
    } catch (UnsupportedEncodingException e) {
      logger.error("JasperExport==>export==>" + e);
      e.printStackTrace();
    }
    try {
      prepare(response, jasperPrint, filename);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("JasperExport==>export==>" + e);
      throw new RuntimeException(e);
    }
  }

  /**
   * 由子类来实现具体的导出逻辑 2017-5-2
   *
   * @param filename 导出的文件名
   */
  protected abstract void prepare(HttpServletResponse response,
      JasperPrint jasperPrint, String filename) throws Exception;


  public void export(String jasperFileName, Map<String, Object> parameters,
      JRDataSource dataSource, String filename) {
    InputStream in = null;
    JasperPrint jasperPrint;
    try {
      in = getClass().getResourceAsStream("/".concat(jasperFileName));
      jasperPrint = JasperFillManager.fillReport(in, parameters, dataSource);
    } catch (JRException e) {
      e.printStackTrace();
      logger.error("JasperExport==>export填充jasper文件错误" + e);
      throw new RuntimeException("填充jasper文件错误", e);
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
        logger.error("JasperExport==>export==>关闭异常==>" + e);
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
      logger.error("JasperExport==>export==>" + e);
      e.printStackTrace();
    }
    return filename;
  }

  public void export(JasperPrint print, String fileName) {
    try {
      File pdf = new File("pdf/" + fileName + ".pdf");
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
