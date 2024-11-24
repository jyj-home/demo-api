package demo.api.utils;

import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

public class CheckUtils {

  @Getter
  public enum NullCompareMode {
    SKIP(0),
    GT(1),
    LT(-1);

    private int value;

    NullCompareMode(int value) {
      this.value = value;
    }

    public int getReverseValue() {
      return this.value * (-1);
    }
  }

  private CheckUtils() {}

  public static <T> boolean equals(T o1, T o2) {
    return Objects.equals(o1, o2);
  }

  /**
   * @param <T>
   * @param o1
   * @param o2
   * @return
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static <T> int compare(T o1, T o2, NullCompareMode nullCompareMode) {
    return Objects.compare(
        o1,
        o2,
        (a, b) -> {
          if (a == null && b == null) {
            return 0;
          }

          if (a == null) {
            return nullCompareMode.getValue();
          }

          if (b == null) {
            return nullCompareMode.getReverseValue();
          }

          if (a instanceof Comparable<?> a1 && b instanceof Comparable<?> b1) {
            return ObjectUtils.compare((Comparable) a1, (Comparable) b1);
          }

          return a.toString().compareTo(b.toString());
        });
  }

  public static <T> boolean ge(T o1, T o2, NullCompareMode nullCompareMode) {
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == 0 || retVal == 1 || nullCompareMode == NullCompareMode.SKIP;
  }

  public static <T> boolean le(T o1, T o2, NullCompareMode nullCompareMode) {
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == 0 || retVal == -1 || nullCompareMode == NullCompareMode.SKIP;
  }

  public static <T> boolean gt(T o1, T o2, NullCompareMode nullCompareMode) {
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == 1 || nullCompareMode == NullCompareMode.SKIP;
  }

  public static <T> boolean lt(T o1, T o2, NullCompareMode nullCompareMode) {
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == -1 || nullCompareMode == NullCompareMode.SKIP;
  }

  public static <T> int compare(T o1, T o2) {
    return compare(o1, o2, NullCompareMode.LT);
  }

  public static <T> boolean ge(T o1, T o2) {
    int retVal = compare(o1, o2);
    return retVal == 0 || retVal == 1;
  }

  public static <T> boolean le(T o1, T o2) {
    int retVal = compare(o1, o2);
    return retVal == 0 || retVal == -1;
  }

  public static <T> boolean gt(T o1, T o2) {
    int retVal = compare(o1, o2);
    return retVal == 1;
  }

  public static <T> boolean lt(T o1, T o2) {
    int retVal = compare(o1, o2);
    return retVal == -1;
  }

  //  public static <T> boolean between(T o1, T o2) {
  //    ComparableUtils.between(null, null).test(null);
  //    int retVal = compare(o1, o2);
  //    return retVal == -1;
  //  }
}
