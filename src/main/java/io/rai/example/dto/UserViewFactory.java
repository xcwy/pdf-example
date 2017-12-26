package io.rai.example.dto;
import java.util.Arrays;
import java.util.Collection;

public class UserViewFactory {

  /**
   *
   */
  private static UserView[] data = {
          new UserView("Berne", "James Schneider"),
          new UserView("Berne", "Bill Ott"),
          new UserView("Boston",  "Julia Heiniger"),
          new UserView("Boston", "Michael Ott"),
      };

  /**
   *
   */
  public static Object[] getBeanArray() {
    return data;
  }

  /**
   *
   */
  public static Collection<UserView> getBeanCollection() {
    return Arrays.asList(data);
  }


}
