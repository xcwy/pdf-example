package io.rai.example.utils.impl;

import io.rai.example.utils.JasperExport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperExcelExporter extends JasperExport {

  private static final Logger logger = LoggerFactory.getLogger(JasperExcelExporter.class);

  @Override
  public void prepare(HttpServletResponse response, JasperPrint jasperPrint,
      String filename) throws Exception {
    ServletOutputStream out = null;
    try {
      response.setContentType("application/x-download; charset=utf-8");
      response.setHeader("Content-Disposition", "attachment;filename="
          + filename + ".xls");
      out = response.getOutputStream();
      JRXlsExporter exporter = new JRXlsExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
      exporter.exportReport();
      out.flush();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("JasperExcelExporter==>prepare异常：" + e);
      out.close();
    }


  }

}
