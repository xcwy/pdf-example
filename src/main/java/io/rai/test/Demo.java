package io.rai.test;

/**
 * Subreporting demo. It compile jrxml to jasper-files and pass it to
 * generate report.
 */

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;
import io.rai.example.dto.User;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Demo {

  public static JasperPrint jasperPrint;
  public static JasperReport mainReport;

  final static String reportsDirName = System.getProperty("user.dir") + "/reports";
  final static String jasperDirName = "/jaspers";
  final static String[] jrxmlFiles = {"java_beans_datasource_report",
      "java_beans_datasource_report_subreport1"};

  public static String getJasperDir(String jrxmldir) {
    /**
     * Retutn directory for compiled report. Normally it jaspers directory
     * into reports directory. If jasper directoty missing it will create.
     */
    String result = System.getProperty("user.home");
    File jrxmlDir = new File(jrxmldir);
    File jasperDir = new File(jrxmldir + jasperDirName);
    if (jrxmlDir.exists() && jrxmlDir.isDirectory()) {
      boolean cdres = true;
      if (!jasperDir.exists()) {
        cdres = false;
        try {
          jasperDir.mkdir();
          cdres = true;
        } catch (SecurityException se) {
        }
      }
      if (cdres) {
        result = jrxmldir + jasperDirName;
      }
    }
    return result;
  }

  public static boolean compileJRXML() {
    /**
     *  Compile all jrxml from reports to jasper and pur it to getJasperDir result.
     */
    boolean result = false;
    String jdn = getJasperDir(reportsDirName);
    String s, o;
    File outf;
    try {
      for (String rep : jrxmlFiles) {
        s = reportsDirName + "/" + rep + ".jrxml";
        o = jdn + "/" + rep + ".jasper";
        JasperCompileManager.compileReportToFile(s, o);
        outf = new File(o);
        if (!outf.exists()) {
          return false;
        }
      }
    } catch (JRException e) {
      return false;
    }
    result = true;
    return result;
  }

  public static void main(String[] args) {
    try {
      //Parameters for pass to report
      HashMap<String, Object> parameters = new HashMap<String, Object>();
      //Organize datasource
      OrderFactory of = new OrderFactory();
      List<OrderBean> dataSource = of.create();
      JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
      //Compile jrxml to jasper-files.
      FileInputStream mainReportFile = null;
      if (compileJRXML()) {
        //If you haven't plans to compile jrxml scip this call. Alternate
        //way to create a mainReportFile
        //import net.sf.jasperreports.engine.design.JasperDesign mainDesign = JRXmlLoader.load("/path/to/jrxml");
        //JasperReport mainReportFile = JasperCompileManager.compileReport(mainDesign);
        try {
          String s = getJasperDir(reportsDirName) + "/" + jrxmlFiles[0] + ".jasper";
          mainReportFile = new FileInputStream(s);
          //pass directory with jasper-files as parameters
          parameters.put("SUBREPORT_DIR", getJasperDir(reportsDirName) + "/");
          //Fill report and view report.
          jasperPrint = JasperFillManager.fillReport(mainReportFile, parameters, beanDataSource);
          JasperViewer.viewReport(jasperPrint);
        } catch (Exception fise) {
          fise.printStackTrace();
        } finally {
          if (mainReportFile != null) {
            mainReportFile.close();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    fillTable();
  }

  private static ExportComponent component = new ExportComponent();

  private static void fillTable() {
    Map<String, Object> params = Maps.newConcurrentMap();
    String fileName = "TableTest" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    HashMap<String, Object> parameters = new HashMap<String, Object>();
    //Organize datasource
    OrderFactory of = new OrderFactory();
    List<OrderBean> dataSource = of.create();
    JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(dataSource);
    parameters.put("SUBREPORT_DIR", getJasperDir(reportsDirName) + "/");

    long startTime = System.currentTimeMillis();

    component.export("jasper/java_beans_datasource_report.jrxml", parameters, beanDataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }
}
