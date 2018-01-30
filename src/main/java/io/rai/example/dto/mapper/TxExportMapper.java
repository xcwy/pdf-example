package io.rai.example.dto.mapper;

import com.google.common.collect.Lists;

import io.rai.example.dto.TtExportView;

import java.util.List;

/**
 *
 */
public final class TxExportMapper {
  public static TtExportView buildTtExportView(int flag) {
    TtExportView view = new TtExportView();

    view.setCmb("cmb" + flag);
    view.setContainerno("containerno" + flag);
    view.setCreatetime("2091083847");
    view.setCtn("ctn" + flag);
    view.setEta("eta" + flag);
    view.setEtd("etd" + flag);
    view.setFroremark("froremark" + flag);
    view.setLoadtime("loadtime" + flag);
    view.setOrderno("order" + flag);
    view.setRctime("rctime" + flag);
    view.setRealcmb("realcmb" + flag);
    view.setRemark("remark" + flag);
    view.setSale("sale" + flag);
    view.setShipcompany("shipcompany");
    view.setTt("tt" + flag);
    view.setType("type" + flag);
    view.setUsername("username" + flag);

    return view;
  }

  public static List<TtExportView> buildTtExportViews(int size) {
    List<TtExportView> views = Lists.newArrayList();

    for (int i = 0; i < size; i++) {
      views.add(buildTtExportView(i + 1));
    }

    return views;
  }
}
