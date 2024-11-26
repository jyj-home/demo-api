package demo.api.dto;

import demo.api.validation.NumericString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/** XXXXXXXXXXXXX. */
@Data
public class InfoCreateRequest implements Serializable {
  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.age
   *
   * @mbg.generated
   */
  private Integer age;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.birth
   *
   * @mbg.generated
   */
  private Date birth;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.sex
   *
   * @mbg.generated
   */
  @NumericString
  private String sex;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.salary
   *
   * @mbg.generated
   */
  private BigDecimal salary;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.description
   *
   * @mbg.generated
   */
  private String description;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.ref_timestamp_zone
   *
   * @mbg.generated
   */
  private Date refTimestampZone;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.create_user
   *
   * @mbg.generated
   */
  private String createUser;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.create_date
   *
   * @mbg.generated
   */
  private Date createDate;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.update_user
   *
   * @mbg.generated
   */
  private String updateUser;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.update_date
   *
   * @mbg.generated
   */
  private Date updateDate;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.is_active
   *
   * @mbg.generated
   */
  private Boolean isActive;

  /**
   *
   * This field was generated by MyBatis Generator. This field corresponds to the
   * database column person.is_delete
   *
   * @mbg.generated
   */
  private Boolean isDelete;
}
