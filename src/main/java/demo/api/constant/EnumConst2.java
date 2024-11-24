package demo.api.constant;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

/** 定数定義Cons. */
@Getter
public enum EnumConst2 implements BaseConst2<String> {

  /** 名：値. */
  CONS001("a", "AAA"),

  /** 名：値. */
  CONS002("b", "BBB"),

  /** 名：値. */
  CONS003("c", "CCC");

  /** 値. */
  private final String value;

  /** 表示名. */
  private final String displayName;

  /**
   * コンストラクター.
   *
   * @param value 値
   * @param displayName 表示名
   */
  EnumConst2(String value, String displayName) {
    this.value = value;
    this.displayName = displayName;
  }

  @Override
  public boolean isSame(String value) {
    return this.value.equals(value);
  }

  /**
   * 列挙型を取得する.
   *
   * @param value 値
   * @return EnumCons2 列挙型
   */
  public static EnumConst2 toEnum(String value) {
    Optional<EnumConst2> optional =
        Arrays.stream(values()).parallel().filter(arg -> arg.value.equals(value)).findAny();
    return optional.isPresent() ? optional.get() : null;
  }
}
