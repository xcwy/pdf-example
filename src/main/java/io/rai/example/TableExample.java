package io.rai.example;

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;
import io.rai.example.dto.ColorView;
import io.rai.example.dto.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Created by rai on 2017/12/26.
 */
public class TableExample {

  private static ExportComponent component = new ExportComponent();

  public static void main(String[] args) {
    fillTable2();
  }

  private static void fillTable() {
    Map<String, Object> params = Maps.newConcurrentMap();

    params.put("title", "表格 - 用于测试");

    List<User> users = User.build(10);

    JRDataSource dataSource = new JRBeanCollectionDataSource(users);

    String fileName = "TableTest" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/table_example.jrxml", params, dataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }

  private static void fillTable2() {
    Map<String, Object> params = Maps.newConcurrentMap();

    List<User> users = User.build(10);

    JRDataSource dataSource = new JRBeanCollectionDataSource(users);

    JRDataSource colorData = new JRBeanCollectionDataSource(ColorView.build());

    params.put("USER_REPORT_CONNECTION", dataSource);
    params.put("COLOR_REPORT_CONNECTION", colorData);
    String fileName = "TableTest" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/table_example2.jrxml", params, new JREmptyDataSource(), fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }

}
