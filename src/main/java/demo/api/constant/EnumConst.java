package demo.api.constant;

import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;

/** 共通定数定義. */
@Getter
public enum EnumConst implements BaseConst<String> {

  /** 名：値. */
  ZERO("0"),

  /** 名：値. */
  ONE("1");

  /** 値. */
  private final String value;

  /**
   * コンストラクター.
   *
   * @param value 値
   */
  EnumConst(String value) {
    this.value = value;
  }

  @Override
  public boolean isSame(String value) {
    return this.value.equals(value);
  }

  /**
   * 列挙型を取得する.
   *
   * @param value 値
   * @return EnumCons 列挙型
   */
  public static EnumConst toEnum(String value) {
    Optional<EnumConst> optional =
        Arrays.stream(values()).parallel().filter(arg -> arg.value.equals(value)).findAny();
    return optional.isPresent() ? optional.get() : null;
  }
}
