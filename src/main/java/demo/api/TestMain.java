package demo.api;

import demo.api.constant.EnumConst;
import demo.api.constant.EnumConst2;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.compare.ComparableUtils;
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
    System.out.println(EnumConst.ZERO.name());
    System.out.println(EnumConst.ZERO.getValue());
    System.out.println(EnumConst.ZERO.isSame("0"));
    System.out.println(EnumConst.toEnum("1"));
    System.out.println("-------------------------------");
    System.out.println(EnumConst2.CONS001.name());
    System.out.println(EnumConst2.CONS001.getValue());
    System.out.println(EnumConst2.CONS001.getDisplayName());
    System.out.println(EnumConst2.CONS001.isSame("a"));
    System.out.println(EnumConst2.toEnum(null));
    System.out.println("-------------------------------");
    try {
      Integer a = 0;
      BigDecimal b = new BigDecimal("0").setScale(6, RoundingMode.DOWN);
      BigDecimal c = new BigDecimal(0);
      System.out.println(b);
      System.out.println(c);
      System.out.println(b.compareTo(c));
      System.out.println(StringUtils.trimToEmpty("\na bc\n"));
      System.out.println(StringUtils.stripToEmpty("\tab c\t"));
      System.out.println(StringUtils.split((String) null));
      System.out.println(Objects.toString(null, null));
      System.out.println(Objects.equals("", null));
      System.out.println(ComparableUtils.between("", "").test(null));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    System.out.println("-------------------------------");
    System.out.println(GenericValidator.isInt("01"));
    System.out.println("-------------------------------");
    System.out.println(new BigDecimal("1.e0"));
    System.out.println("-------------------------------");
    System.out.println(GenericValidator.isDate("2024/02/28 12:30:45 ", null));

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
