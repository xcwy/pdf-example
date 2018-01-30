package io.rai.example;

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;
import io.rai.example.dto.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Created by rai on 2017/12/26.
 */
public class TableExample {

  private static ExportComponent component = new ExportComponent();

  public static void main(String[] args) {
    fillTable();
  }

  private static void fillTable() {
    Map<String, Object> params = Maps.newConcurrentMap();

    params.put("title", "表格 - 用于测试");

    List<User> users = User.build(10);

    JRDataSource dataSource = new JRBeanCollectionDataSource(users);

    String fileName = "测试" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/table_example.jrxml", params, dataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }

}
