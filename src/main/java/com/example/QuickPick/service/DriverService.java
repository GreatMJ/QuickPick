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

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {


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


    // method to get driver with age greater than given age
    public List<DriverResponse> getDriverWithAgeGreaterThan(Integer age){
        List<Driver> drivers=driverRepository.getDriverWithAgeGreaterThan(age);
        // handle empty list
        if(drivers==null||drivers.isEmpty()) throw new ResourceNotFoundException(String.format("No drivers with age greater than %d is available.",age));
        List<DriverResponse> driverResponses=new ArrayList<>(drivers.size());
        for(Driver driver:drivers)driverResponses.add(DriverTransoformer.driverToDriverResponse(driver));
        return driverResponses;
    }


}
