package io.rai.example.utils;

import io.rai.example.utils.impl.JasperExcelExporter;
import io.rai.example.utils.impl.JasperHtmlExporter;
import io.rai.example.utils.impl.JasperPdfExporter;

public class JasperFactory {

  private static JasperExport htmlExporter = new JasperHtmlExporter();
  private static JasperExport pdfExporter = new JasperPdfExporter();
  private static JasperExport excelExporter = new JasperExcelExporter();

  public static JasperExport getExport(String t) {
    if ("html".equals(t)) {
      return htmlExporter;
    } else if ("pdf".equals(t)) {
      return pdfExporter;
    } else if ("excel".equals(t)) {
      return excelExporter;
    }
    return htmlExporter;
  }

}
