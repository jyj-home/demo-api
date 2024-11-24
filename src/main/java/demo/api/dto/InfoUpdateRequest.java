package demo.api.dto;

import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

/** XXXXXXXXXXXXX. */
@Data
public class InfoUpdateRequest implements Serializable {
  @Size(max = 10)
  private String name;
}
