package com.bondevalue.marketplaceservice.resource;

import com.bondevalue.marketplaceservice.common.ApiResponse;
import com.bondevalue.marketplaceservice.exeptions.MatchNotFoundException;
import com.bondevalue.marketplaceservice.service.BuySellMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.bondevalue.marketplaceservice.common.Constants.*;

import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/match")
public class MatchResource {

  private static final Logger LOGGER = Logger.getLogger(MatchResource.class.getName());

  @Autowired
  private BuySellMatchService buySellMatchService;


  @GetMapping
  public ApiResponse findMatch() {
    LOGGER.info("REST: request to find buy Sell Matches");
    ApiResponse apiResponse;

    try {
      Map matchMap = buySellMatchService.match();
      apiResponse = new ApiResponse(matchMap, null, SUCCESS, 200, "");

    } catch (MatchNotFoundException ex) {
      LOGGER.info("Match Not Found Reason: " + ex.getMessage());
      apiResponse = new ApiResponse(null, null, FAILURE, 200, ex.getMessage());
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGGER.info("Exception: "+ ex.getMessage());
      apiResponse = new ApiResponse(null, null, FAILURE, 500, ex.getMessage());
    }

    return apiResponse;
  }
}
