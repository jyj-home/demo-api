package demo.api.dto;

import demo.api.gen.entity.Person;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/** XXXXXXXXXXXXX. */
@Data
public class InfoGetResponse implements Serializable {
  private String result;
  private List<Person> personList;
}
