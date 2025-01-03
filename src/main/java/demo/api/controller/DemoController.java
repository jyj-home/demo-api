package demo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.api.dto.InfoCreateRequest;
import demo.api.dto.InfoCreateResponse;
import demo.api.dto.InfoDeleteRequest;
import demo.api.dto.InfoDeleteResponse;
import demo.api.dto.InfoGetRequest;
import demo.api.dto.InfoGetResponse;
import demo.api.dto.InfoUpdateRequest;
import demo.api.dto.InfoUpdateResponse;
import demo.api.log.CustomSlf4j;
import demo.api.service.DemoService;
import demo.api.service.MyDbTestService;
import java.io.FileInputStream;
import java.io.IOException;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/** XXXXXXXXXXXXX. */
@RestController
@Validated
@CustomSlf4j
public class DemoController {

  /** xxxxxxxxxxxx. */
  @Autowired
  DemoService demoService;

  @Autowired
  MyDbTestService myDbTestService;

  private static final Marker FATAL = MarkerFactory.getMarker("FATAL");
  private static final demo.api.log.CustomLogger logger = demo.api.log.CustomLoggerFactory
      .getLogger(DemoController.class);

//  private static final CustomLogger customLogger = CustomLoggerFactory.getLogger(DemoController.class);

//  @Autowired
//  RestTemplate RestTemplate;

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoGetRequest xxxxxxxxxx
   * @return InfoGetResponse yyyy
   */
  @GetMapping("/info")
  public ResponseEntity<InfoGetResponse> getInfo(@Validated @ModelAttribute InfoGetRequest infoGetRequest) {

//    customLogger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//    customLogger.error(FATAL, "yyyyyyyyyyyyyyyyyyyyyyyyyy");
    ObjectMapper om = new ObjectMapper();
    InfoCreateRequest infoCreateRequest = null;
    try {
      infoCreateRequest = om.readValue(new FileInputStream("data_create.json"), InfoCreateRequest.class);
    } catch (IOException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }
    HttpEntity<InfoCreateRequest> httpEntity = new HttpEntity<>(infoCreateRequest);

    RestTemplate restTemplate = new RestTemplate();
    String ret = null;
    try {
      ret = restTemplate.exchange("http://localhost:8080/info", HttpMethod.PUT, httpEntity, String.class).getBody();
    } catch (RestClientException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }

    System.out.println(ret);
    return ResponseEntity.ok(this.demoService.getInfo(infoGetRequest));
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoCreateRequest xxxxxxxxxx
   * @return InfoCreateResponse yyyy
   */
  @PutMapping("/info")
  public ResponseEntity<InfoCreateResponse> createInfo(@Validated @RequestBody InfoCreateRequest infoCreateRequest) {

    myDbTestService.operateDs1();
    myDbTestService.operateDs2();
    myDbTestService.operateBoth();
    return null;
//    return ResponseEntity.ok(this.demoService.createInfo(infoCreateRequest));
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoUpdateRequest xxxxxxxxxx
   * @return InfoUpdateResponse yyyy
   */
  @PostMapping("/info")
  public ResponseEntity<InfoUpdateResponse> updateInfo(@Validated @RequestBody InfoUpdateRequest infoUpdateRequest)
      throws Exception {
    myDbTestService.operateCreateBoth();
    return null;
//    return ResponseEntity.ok(this.demoService.updateInfo(infoUpdateRequest));
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoDeleteRequest xxxxxxxxxx
   * @return InfoDeleteResponse yyyy
   */
  @DeleteMapping("/info")
  public ResponseEntity<InfoDeleteResponse> deleteInfo(@Validated @RequestBody InfoDeleteRequest infoDeleteRequest) {
    return ResponseEntity.ok(this.demoService.deleteInfo(infoDeleteRequest));
  }
}
