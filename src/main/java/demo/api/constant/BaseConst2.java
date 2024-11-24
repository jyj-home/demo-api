package demo.api.constant;

/**
 * 定数定義ベースインタフェース.
 *
 * @param <V> 値
 */
public interface BaseConst2<V> {

  /**
   * 値を取得する.
   *
   * @return V 値
   */
  V getValue();

  /**
   * 表示名を取得する.
   *
   * @return String 表示名
   */
  String getDisplayName();

  /**
   * 値と一致するかを判断する.
   *
   * @param value 値
   * @return boolean true：一致、false：一致しない
   */
  boolean isSame(V value);
}
