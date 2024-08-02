package com.hulutas.customer_management.service;

import com.hulutas.customer_management.mernis.REOKPSPublicSoap12;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MernisService {
    private static final Logger logger = LoggerFactory.getLogger(MernisService.class);
    public boolean validateCitizen(Long citizenId, String name, String surname, int birthYear){
        REOKPSPublicSoap12 client = new REOKPSPublicSoap12();
        boolean result = false;

        try {
            result =  client.TCKimlikNoDogrula(citizenId, name, surname, birthYear);
            logger.info("MERNIS validation result for TC: {} is: {}", citizenId, result);
        }catch (Exception e){
            logger.error("Error during MERNIS validation for TC: {}", citizenId, e);
        }

        return result;
    }
}
