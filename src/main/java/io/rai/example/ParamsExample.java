package io.rai.example;

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;

import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * 参数填充，生成PDF
 */
public class ParamsExample {

  private static ExportComponent component = new ExportComponent();

  public static void main(String[] args) {
    fillParams();
  }

  private static void fillParams() {
    Map<String, Object> params = Maps.newConcurrentMap();

    params.put("userName", "菲菲");
    params.put("class", "高三一班");
    params.put("school", "信宜中学");

    JRDataSource dataSource = new JREmptyDataSource();

    String fileName = "测试" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/param_example.jrxml", params, dataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }
}
