package demo.api.dto;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/** XXXXXXXXXXXXX. */
@Data
public class InfoGetRequest implements Serializable {

  /** XXXXXXXXXXXXX. */
  private String personId;

  /** XXXXXXXXXXXXX. */
  // @NotEmpty
  @Size(max = 10)
  private String name;

  /** XXXXXXXXXXXXX. */
  private String age;
}
