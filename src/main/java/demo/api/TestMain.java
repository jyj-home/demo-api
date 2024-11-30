package demo.api;

import java.util.Locale;
import org.apache.commons.validator.GenericValidator;

/**
 * xxxx <br>
 * yyyy <br>
 */
public class TestMain {

  /**
   * xxxx <br>
   * yyyy <br>
   *
   * @param args
   */
  public static void main(String[] args) throws Exception {

    System.out.println("-------------------------------");
    System.out.println(GenericValidator.isDate("2024/02/02 12:30:45.123", Locale.getDefault()));
    System.out.println(GenericValidator.isDate("2024/02/2", Locale.getDefault()));

    // File f = new File("");
    //
    // try (FileInputStream fis = new FileInputStream(f)) {
    // int line = fis.read();
    // System.out.println(line);
    // }
    // Integer a = 0;
    // int b = 0;
    // System.out.println(a == b);
    // System.out.println(a.equals(b));
  }

  public enum EnumTest {
    GET, POST;
  }
}
