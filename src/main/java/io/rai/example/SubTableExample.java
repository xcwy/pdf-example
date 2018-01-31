package io.rai.example;

import com.google.common.collect.Maps;
import com.ibm.icu.text.SimpleDateFormat;

import io.rai.example.component.ExportComponent;
import io.rai.example.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 */
public class SubTableExample {
  private static ExportComponent component = new ExportComponent();

  public static void main(String[] args) throws JRException {
    fillTable();
  }

  private static void fillTable() throws JRException {
    Map<String, Object> params = Maps.newConcurrentMap();

//    List<User> users = User.build(10);

//    JasperReport subreport = (JasperReport) JRLoader.loadObjectFromFile("/Users/rai/it/project/pdf-example/src/main/resources/jasper/sub_example.jrxml");

//    params.put("SUB_REPORT", subreport);

//    Collection<Map<String, ?>> list = new ArrayList<Map<String, ?>>();
//    // 塞入測試資料
//    for (int i = 0; i < users.size(); i++) {
//      Map<String, Object> data = new HashMap<String, Object>();
//      data.put("id", users.get(i).getId());
//      data.put("name", users.get(i).getName());
//      data.put("age", users.get(i).getAge());
//      data.put("phone", users.get(i).getPhone());
//      list.add(data);
//    }

//    params.put("SUB_LIST", new JRBeanCollectionDataSource(users));

    JRDataSource dataSource = new JREmptyDataSource();

    String fileName = "TableTest" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    long startTime = System.currentTimeMillis();

    component.export("jasper/main.jrxml", params, dataSource, fileName);

    long endTime = System.currentTimeMillis();

    System.out.println("Export time: " + (endTime - startTime));
  }

}
