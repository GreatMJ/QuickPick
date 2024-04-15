package com.example.QuickPick.transformers;

import com.example.QuickPick.dto.request.DriverRequest;
import com.example.QuickPick.dto.response.DriverResponse;
import com.example.QuickPick.models.Driver;

public class DriverTransoformer {

  public static Driver driverRequestToDriver(DriverRequest driverRequest){
      return Driver.builder().mobNo(driverRequest.getMobNo())
              .name(driverRequest.getName())
              .panNumber(driverRequest.getPanNumber())
              .age(driverRequest.getAge())
              .rating(0)
              .build();
  }

  public static DriverResponse driverToDriverResponse(Driver driver){
      return DriverResponse.builder()
              .name(driver.getName())
              .age(driver.getAge())
              .panNumber(driver.getPanNumber())
              .mobNo(driver.getMobNo())
              .build();
  }
}
