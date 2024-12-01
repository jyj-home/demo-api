package demo.api.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.compare.ComparableUtils;

public class CheckUtils {

  private static final String REGEX_NUMERIC = "^[-+]?\\d+(\\.\\d+)?$";
  private static final String REGEX_NUMERIC_POSITIVE = "^[+]?\\d+(\\.\\d+)?$";
  private static final String REGEX_NUMERIC_NEGATIVE = "^[-]\\d+(\\.\\d+)?$";
  private static final String REGEX_INTEGER = "^[-+]?\\d+$";
  private static final String REGEX_INTEGER_POSITIVE = "^[+]?\\d+$";
  private static final String REGEX_INTEGER_NEGATIVE = "^[-]\\d+$";
  private static final String REGEX_NUMERIC_INTEGER = "^\\d+";
  private static final String REGEX_NUMERIC_FRACTION = "\\.(\\d+)$";

  public enum NullCompareMode {
    SKIP, GT, LT;
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

  public static <T extends Comparable<T>> boolean between(T o1, T o2, T o3) {
    if (o1 == null || o2 == null || o3 == null) {
      return true;
    }
    return ComparableUtils.between(o2, o3).test(o1);
  }

  public static boolean isNumeric(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_NUMERIC, value);
  }

  public static boolean isNumericPositive(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_NUMERIC_POSITIVE, value);
  }

  public static boolean isNumericNegative(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_NUMERIC_NEGATIVE, value);
  }

  public static boolean isInteger(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_INTEGER, value);
  }

  public static boolean isIntegerPositive(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_INTEGER_POSITIVE, value);
  }

  public static boolean isIntegerNegative(String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(REGEX_INTEGER_NEGATIVE, value);
  }

  public static boolean checkLengthInteger(BigDecimal value, int length) {
    if (value == null) {
      return true;
    }

    Matcher matcher = Pattern.compile(REGEX_NUMERIC_INTEGER).matcher(value.toString());

    return !(matcher.find() && matcher.group().length() > length);
  }

  public static boolean checkLengthFraction(BigDecimal value, int length) {
    if (value == null) {
      return true;
    }

    Matcher matcher = Pattern.compile(REGEX_NUMERIC_FRACTION).matcher(value.toString());

    return !(matcher.find() && matcher.group(1).length() > length);
  }

  public static boolean isDate(String value, String format) {
    if (value == null) {
      return true;
    }

    try {
      new SimpleDateFormat(format).parse(value);
    } catch (ParseException e) {
      return false;
    }

    return true;
  }

  public static boolean matche(String regex, String value) {
    if (value == null) {
      return true;
    }
    return Pattern.matches(regex, value);
  }

  public static boolean matcheSegment(String regex, String value) {
    if (value == null) {
      return true;
    }

    Matcher matcher = Pattern.compile(regex).matcher(value);
    return matcher.find();
  }

  public static boolean isNull(Object value) {
    return value == null;
  }

  public static boolean isEmpty(Object value) {
    return value == null || ObjectUtils.isEmpty(value);
  }

  private static <T extends Comparable<? super T>> boolean skip(T o1, T o2, NullCompareMode nullCompareMode) {
    if (nullCompareMode == NullCompareMode.SKIP && (o1 == null || o2 == null)) {
      return true;
    }
    return false;
  }
}
