package io.rai.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by rai on 2017/12/26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
  private String username;

  private String birthday;
}
