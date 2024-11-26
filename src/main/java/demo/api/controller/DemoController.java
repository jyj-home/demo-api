package demo.api.controller;

import demo.api.dto.InfoCreateRequest;
import demo.api.dto.InfoCreateResponse;
import demo.api.dto.InfoDeleteRequest;
import demo.api.dto.InfoDeleteResponse;
import demo.api.dto.InfoGetRequest;
import demo.api.dto.InfoGetResponse;
import demo.api.dto.InfoUpdateRequest;
import demo.api.dto.InfoUpdateResponse;
import demo.api.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** XXXXXXXXXXXXX. */
@RestController
@Validated
public class DemoController {

  /** xxxxxxxxxxxx. */
  @Autowired
  DemoService demoService;

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoGetRequest xxxxxxxxxx
   * @return InfoGetResponse yyyy
   */
  @GetMapping("/info")
  public ResponseEntity<InfoGetResponse> getInfo(@Validated @ModelAttribute InfoGetRequest infoGetRequest) {
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
    return ResponseEntity.ok(this.demoService.createInfo(infoCreateRequest));
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoUpdateRequest xxxxxxxxxx
   * @return InfoUpdateResponse yyyy
   */
  @PostMapping("/info")
  public ResponseEntity<InfoUpdateResponse> updateInfo(@RequestBody @Validated InfoUpdateRequest infoUpdateRequest) {
    return ResponseEntity.ok(this.demoService.updateInfo(infoUpdateRequest));
  }

  /**
   * ｘｘｘｘｘｘｘｘｘｘｘｘｘｘ.
   *
   * @param infoDeleteRequest xxxxxxxxxx
   * @return InfoDeleteResponse yyyy
   */
  @DeleteMapping("/info")
  public ResponseEntity<InfoDeleteResponse> deleteInfo(@RequestBody @Validated InfoDeleteRequest infoDeleteRequest) {
    return ResponseEntity.ok(this.demoService.deleteInfo(infoDeleteRequest));
  }
}
