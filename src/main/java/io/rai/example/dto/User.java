package io.rai.example.dto;

import com.google.common.collect.Lists;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class User {

  private int id;

  private String name;

  private int age;

  private String phone;

  public static List<User> build(int i) {
    List<User> users = Lists.newArrayList();

    int flag = 1;
    while (flag <= i) {
      User user = new User();
      user.setId(flag);
      user.setName("UserName" + flag);
      user.setAge(flag * 8);
      user.setPhone("UserPhone" + flag);
      users.add(user);
      flag++;
    }

    return users;
  }
}
