package demo.api.dto;

import demo.api.validation.IntegerString;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/** XXXXXXXXXXXXX. */
@Data
public class InfoGetRequest implements Serializable {

  /** XXXXXXXXXXXXX. */
  private String personId;

  /** XXXXXXXXXXXXX. */
  //  @NotEmpty
  @Size(max = 10)
  private String name;

  /** XXXXXXXXXXXXX. */
  @IntegerString private String age;
}
