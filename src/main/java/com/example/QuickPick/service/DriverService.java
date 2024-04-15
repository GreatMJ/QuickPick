package com.example.QuickPick.service;

import com.example.QuickPick.dto.request.DriverRequest;
import com.example.QuickPick.dto.response.DriverResponse;
import com.example.QuickPick.exception.ResourceNotFoundException;
import com.example.QuickPick.models.Cab;
import com.example.QuickPick.models.Driver;
import com.example.QuickPick.repository.DriverRepository;
import com.example.QuickPick.transformers.CabTransformer;
import com.example.QuickPick.transformers.DriverTransoformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

//    @Autowired
//    DriverRepository driverRepository;

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }



    // ADD DRIVER
    public String addDriver(DriverRequest driverRequest){

        Driver driver = DriverTransoformer.driverRequestToDriver(driverRequest);

        Cab cab= CabTransformer.cabRequestToCab(driverRequest.getCab());
        driver.setCab(cab);
        cab.setDriver(driver);

        Driver savedDriver=driverRepository.save(driver); // we dont need to save cab as we are using cascade==>(cascade.type=All)
        return "Driver and Cab saved successfully!";
    }

    // DELETE DRIVER

    public DriverResponse deleteDriverByMobileNumber(String mobNo){

        // check weather the driver with given number is registered or not
      //  Boolean isDriverPresent=driverRepository.existsByMobNo(mobNo);

//        if(!isDriverPresent){
//            throw new ResourceNotFoundException(String.format("Sorry, there is no driver registered in our system with the mobile number: %s",mobNo));
//        }
        // get the required driver from database
        Driver driver= driverRepository.findByMobNo(mobNo);

        if(driver==null){
          throw new ResourceNotFoundException(String.format("Sorry, there is no driver registered in our system with the mobile number: %s",mobNo));
     }

        // convert to driverResponse
        DriverResponse driverResponse=DriverTransoformer.driverToDriverResponse(driver);

        //delete the driver

        driverRepository.delete(driver);

        return driverResponse;
    }
}
