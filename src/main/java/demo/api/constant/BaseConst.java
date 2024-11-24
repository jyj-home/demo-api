package demo.api.constant;

/**
 * 定数定義ベースインタフェース.
 *
 * @param <V> 値
 */
public interface BaseConst<V> {

  /**
   * 値を取得する.
   *
   * @return V 値
   */
  V getValue();

  /**
   * 値と一致するかを判断する.
   *
   * @param value 値
   * @return boolean true：一致、false：一致しない
   */
  boolean isSame(V value);
}
