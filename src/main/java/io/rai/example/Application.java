package io.rai.example;

import io.rai.example.dto.UserViewFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

/**
 * Created by rai on 2017/12/26.
 */
@SpringBootApplication
public class Application {

  @Value(value = "classpath:reports/test.jrxml")
  private Resource companiesXml;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void init() {
    fill();
  }

  public void fill() {
    try {
      long start = System.currentTimeMillis();

      InputStream reportStream = getClass().getResourceAsStream("/".concat("test.jrxml"));
      JasperReport report = JasperCompileManager.compileReport(reportStream);

      Map<String, Object> parameters = new HashMap<String, Object>();
      JRDataSource dataSource = new JRBeanArrayDataSource(UserViewFactory.getBeanArray());

      JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

      File pdf = File.createTempFile("output.", ".pdf");
      System.out.println(pdf.getPath());
      JasperExportManager.exportReportToPdfStream(print, new FileOutputStream(pdf));

      System.err.println("Filling time : " + (System.currentTimeMillis() - start));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
