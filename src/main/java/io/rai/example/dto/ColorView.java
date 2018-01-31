package io.rai.example.dto;

import com.google.common.collect.Lists;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class ColorView {

  private String color;

  private int count;

  public ColorView(String color, int count) {
    this.color = color;
    this.count = count;
  }

  public static List<ColorView> build() {
    List<ColorView> views = Lists.newArrayList();

    views.add(new ColorView("Red", 36));
    views.add(new ColorView("Green", 59));
    views.add(new ColorView("Blue", 27));
    views.add(new ColorView("Yellow", 8));
    views.add(new ColorView("Gray", 19));

    return views;
  }
}
