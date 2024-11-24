package demo.api.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/** */
public class TypeConvUtils {

  /** */
  private TypeConvUtils() {
    // 処理なし
  }

  public static Integer createInteger(String value) {
    return StringUtils.isEmpty(value) ? null : new BigDecimal(value).intValue();
  }

  public static Integer createZeroInteger(String value) {
    return toInt(value);
  }

  public static int toInt(String value) {
    return StringUtils.isEmpty(value) ? 0 : new BigDecimal(value).intValue();
  }

  public static int toZeroInt(Integer value) {
    return value == null ? 0 : value;
  }

  public static Long createLong(String value) {
    return StringUtils.isEmpty(value) ? null : new BigDecimal(value).longValue();
  }

  public static Long createZeroLong(String value) {
    return toLong(value);
  }

  public static long toLong(String value) {
    return StringUtils.isEmpty(value) ? 0 : new BigDecimal(value).longValue();
  }

  public static long toZeroLong(Long value) {
    return value == null ? 0 : value;
  }

  public static BigDecimal createBigDecimal(String value) {
    return StringUtils.isEmpty(value) ? null : new BigDecimal(value);
  }

  public static BigDecimal createZeroBigDecimal(String value) {
    return StringUtils.isEmpty(value) ? new BigDecimal(0) : new BigDecimal(value);
  }

  public static BigDecimal createBigDecimal(String value, int scale, RoundingMode roundingMode) {
    return StringUtils.isEmpty(value) ? null : new BigDecimal(value).setScale(scale, roundingMode);
  }

  public static BigDecimal createZeroBigDecimal(
      String value, int scale, RoundingMode roundingMode) {
    return StringUtils.isEmpty(value)
        ? new BigDecimal(0).setScale(scale, roundingMode)
        : new BigDecimal(value).setScale(scale, roundingMode);
  }

  public static BigDecimal toZeroBigDecimal(BigDecimal value) {
    return value == null ? new BigDecimal(0) : value;
  }

  public static BigDecimal toZeroBigDecimal(
      BigDecimal value, int scale, RoundingMode roundingMode) {
    return value == null
        ? new BigDecimal(0).setScale(scale, roundingMode)
        : value.setScale(scale, roundingMode);
  }

  public static <T extends Object> T toNull(T value) {
    if (ObjectUtils.isEmpty(value)) {
      return null;
    }

    if ((value instanceof BigDecimal bigDecimal && new BigDecimal(0).compareTo(bigDecimal) == 0)
        || (value instanceof Integer integer && 0 == integer)
        || (value instanceof Long lng && 0 == lng)) {
      return null;
    }

    return value;
  }

  public static String[] toArray(String value) {
    return StringUtils.isEmpty(value) ? new String[0] : StringUtils.split(value);
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] toArray(List<T> value) {
    return value == null ? (T[]) new Object[0] : value.toArray((T[]) new Object[value.size()]);
  }

  public static List<String> toList(String value) {
    return StringUtils.isEmpty(value) ? new ArrayList<>() : Arrays.asList(StringUtils.split(value));
  }

  public static <T> List<T> toList(T[] value) {
    return value == null ? new ArrayList<>() : Arrays.asList(value);
  }

  public static String toString(Object value) {
    return Objects.toString(value, StringUtils.EMPTY);
  }

  public static String toString(Object value, String nullDefault) {
    return Objects.toString(value, nullDefault);
  }
}
