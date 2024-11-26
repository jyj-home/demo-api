package demo.api.utils;

import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

public class CheckUtils {

  @Getter
  public enum NullCompareMode {
    SKIP(0), GT(1), LT(-1);

    private int value;

    NullCompareMode(int value) {
      this.value = value;
    }

    public int getReverseValue() {
      return this.value * (-1);
    }
  }

  private CheckUtils() {
  }

  public static <T> boolean equals(T o1, T o2) {
    return Objects.equals(o1, o2);
  }

  /**
   * @param <T>
   * @param o1
   * @param o2
   * @return
   */
  public static <T extends Comparable<? super T>> int compare(T o1, T o2, NullCompareMode nullCompareMode) {
    switch (nullCompareMode) {
    case LT:
      return ObjectUtils.compare(o1, o2);
    case GT:
      return ObjectUtils.compare(o1, o2, true);
    default:
      if (o1 == null || o2 == null) {
	return 0;
      }
      return ObjectUtils.compare(o1, o2, true);
    }
  }

  public static <T extends Comparable<? super T>> int compare(T o1, T o2) {
    return compare(o1, o2, NullCompareMode.LT);
  }

  public static <T extends Comparable<? super T>> boolean le(T o1, T o2, NullCompareMode nullCompareMode) {
    if (skip(o1, o2, nullCompareMode)) {
      return true;
    }
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == 0 || retVal == -1;
  }

  public static <T extends Comparable<? super T>> boolean ge(T o1, T o2, NullCompareMode nullCompareMode) {
    if (skip(o1, o2, nullCompareMode)) {
      return true;
    }
    int retVal = compare(o1, o2, nullCompareMode);
    return retVal == 0 || retVal == 1;
  }

  public static <T extends Comparable<? super T>> boolean lt(T o1, T o2, NullCompareMode nullCompareMode) {
    if (skip(o1, o2, nullCompareMode)) {
      return true;
    }
    return !ge(o1, o2, nullCompareMode);
  }

  public static <T extends Comparable<? super T>> boolean gt(T o1, T o2, NullCompareMode nullCompareMode) {
    if (skip(o1, o2, nullCompareMode)) {
      return true;
    }
    return !le(o1, o2, nullCompareMode);
  }

  public static <T extends Comparable<? super T>> boolean le(T o1, T o2) {
    return le(o1, o2, NullCompareMode.LT);
  }

  public static <T extends Comparable<? super T>> boolean ge(T o1, T o2) {
    return ge(o1, o2, NullCompareMode.LT);
  }

  public static <T extends Comparable<? super T>> boolean lt(T o1, T o2) {
    return !ge(o1, o2);
  }

  public static <T extends Comparable<? super T>> boolean gt(T o1, T o2) {
    return !le(o1, o2);
  }

  private static <T extends Comparable<? super T>> boolean skip(T o1, T o2, NullCompareMode nullCompareMode) {
    if (nullCompareMode == NullCompareMode.SKIP && (o1 == null || o2 == null)) {
      return true;
    }
    return false;
  }
  // public static <T> boolean between(T o1, T o2) {
  // ComparableUtils.between(null, null).test(null);
  // int retVal = compare(o1, o2);
  // return retVal == -1;
  // }
}
