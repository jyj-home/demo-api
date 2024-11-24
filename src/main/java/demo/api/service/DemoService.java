package demo.api.service;

import demo.api.constant.EnumConst2;
import demo.api.dto.InfoCreateRequest;
import demo.api.dto.InfoCreateResponse;
import demo.api.dto.InfoDeleteRequest;
import demo.api.dto.InfoDeleteResponse;
import demo.api.dto.InfoGetRequest;
import demo.api.dto.InfoGetResponse;
import demo.api.dto.InfoUpdateRequest;
import demo.api.dto.InfoUpdateResponse;
import demo.api.gen.entity.Person;
import demo.api.logic.DemoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** XXXXXXXXXXXXX. */
@Service
public class DemoService {

  /** xxxxxxxxxxxx. */
  @Autowired DemoLogic demologic;

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoGetRequest xxxxxxxxxx
   * @return InfoGetResponse yyyy
   */
  public InfoGetResponse getInfo(InfoGetRequest infoGetRequest) {
    InfoGetResponse infoGetResponse = new InfoGetResponse();
    Person person = new Person();
    person.setPersonId(infoGetRequest.getPersonId());
    person.setName(infoGetRequest.getName());
    person.setAge(Integer.valueOf(infoGetRequest.getAge()));
    infoGetResponse.setPersonList(this.demologic.getInfo(person));
    infoGetResponse.setResult(EnumConst2.CONS001.getValue());
    return infoGetResponse;
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoCreateRequest xxxxxxxxxx
   * @return InfoCreateResponse yyyy
   */
  public InfoCreateResponse createInfo(InfoCreateRequest infoCreateRequest) {
    InfoCreateResponse infoCreateResponse = new InfoCreateResponse();
    infoCreateResponse.setResult(String.format("Hello %s!", infoCreateRequest.getName()));
    return infoCreateResponse;
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoUpdateRequest xxxxxxxxxx
   * @return InfoUpdateResponse yyyy
   */
  public InfoUpdateResponse updateInfo(InfoUpdateRequest infoUpdateRequest) {
    InfoUpdateResponse infoUpdateResponse = new InfoUpdateResponse();
    infoUpdateResponse.setResult(String.format("Hello %s!", infoUpdateRequest.getName()));
    return infoUpdateResponse;
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoDeleteRequest xxxxxxxxxx
   * @return InfoDeleteResponse yyyy
   */
  public InfoDeleteResponse deleteInfo(InfoDeleteRequest infoDeleteRequest) {
    InfoDeleteResponse infoDeleteResponse = new InfoDeleteResponse();
    infoDeleteResponse.setResult(String.format("Hello %s!", infoDeleteRequest.getName()));
    return infoDeleteResponse;
  }
}
