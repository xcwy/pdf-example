package io.rai.example;

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;

import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * Created by rai on 2017/12/26.
 */
public class ImageExample {

  private static ExportComponent component = new ExportComponent();

  public static void main(String[] args) {
    // 需要注意路径问题  <imageExpression><![CDATA["jasper/img/coffee.jpg"]]></imageExpression>
    // 动态图片只找到这一种方案，在jasper文件设置一个parameter，然后在程序中指向图片的地址，只能是当前目录下的
    fillImage();
  }

  private static void fillImage() {
    Map<String, Object> params = Maps.newConcurrentMap();

    params.put("imageName", "jasper/img/coffee.jpg");

    JRDataSource dataSource = new JREmptyDataSource();

    String fileName = "测试" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/image_example.jrxml", params, dataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }

}
