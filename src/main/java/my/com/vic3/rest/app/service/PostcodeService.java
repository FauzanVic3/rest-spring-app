/*
 * Copyright (C) 2023 Fauzan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package my.com.vic3.rest.app.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import my.com.vic3.common.lib.Validator;
import my.com.vic3.common.lib.ModelResponse;
import my.com.vic3.common.lib.handler.response.ResponseHandler;
import my.com.vic3.rest.app.dto.GenericResponse;
import my.com.vic3.rest.app.dto.MapRequest;
import my.com.vic3.rest.app.dto.MapResponse;
import my.com.vic3.rest.app.model.Postcodelatlng;
import my.com.vic3.rest.app.repository.PostcodelatlngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author Fauzan
 * 
 * Service class for postcodelatlng
 * 
 */
@Service
@Slf4j
public class PostcodeService{
    
    @Autowired
    private PostcodelatlngRepository postcodelatlngRepository;
    
    private Validator validator; // request validator
    private final static double EARTH_RADIUS = 6371; // radius in kilometers    
    private final DecimalFormat df = new DecimalFormat("0.000"); // decimal format in 3 decimal places
    
    /**
     * Function to validate request body mapRequest received 
     * Failed validation will add list of failures into Validator
     * 
     * @param mapRequest
     * @return true if have failure during validation, false if otherwise
     */
    public boolean validateRequest(MapRequest mapRequest){
        
        this.validator = new Validator();        
        
        if(mapRequest == null){
            this.validator.addFailure("JSON Request Body is empty");
        }
        if(!StringUtils.hasText(mapRequest.getPostalCodeA())){
            this.validator.addFailure("postalCodeA is empty");
        }
        if(!StringUtils.hasText(mapRequest.getPostalCodeB())){
            this.validator.addFailure("postalCodeB is empty");
        }
        
        return this.validator.validate();
    }

    /**
     * Getter for Validator
     * 
     * @return Validator
     */
    public Validator getValidator() {
        return this.validator;
    }
    
    /**
     * Function to create postcode
     * 
     * @param requestPostcodelatlng
     * @return 
     */
    public ModelResponse createPostcode(Postcodelatlng requestPostcodelatlng){
        
        GenericResponse genericResponse = new GenericResponse();
        
        // find existing postcode
        Postcodelatlng existingPostcodelatlng = postcodelatlngRepository.findByPostcode(requestPostcodelatlng.getPostcode());
        
        // if requested postcode does not exists then create
        if(existingPostcodelatlng == null){
            genericResponse.setResponseBody(postcodelatlngRepository.save(requestPostcodelatlng));
        }else{
            log.error("Postcode "+requestPostcodelatlng.getPostcode()+" exists");
            new ResponseHandler().throwBadRequestExceptionResponse("Postcode "+requestPostcodelatlng.getPostcode()+" exists");
        }
        
        return genericResponse;
    }
    
    /**
     * Function to update existing postcode
     * 
     * @param requestPostcodelatlng
     * @return 
     */
    public ModelResponse updatePostcode(Postcodelatlng requestPostcodelatlng){
        
        GenericResponse genericResponse = new GenericResponse();
               
        // find existing postcode
        Postcodelatlng existingPostcodelatlng = postcodelatlngRepository.findByPostcode(requestPostcodelatlng.getPostcode());
        
        // throw error if postcode does not exist
        if(existingPostcodelatlng == null){
            new ResponseHandler().throwNotFoundExceptionResponse("Postcode "+requestPostcodelatlng.getPostcode()+" not found");
        }else{
            
            log.info("Updating Postcode ID: "+existingPostcodelatlng.getId()+" Postcode: "+existingPostcodelatlng.getPostcode());
            
            // if postcode exists then perform update if new info is provided
            if(requestPostcodelatlng.getPostcode() != null){
                log.info("Update postcode: "+existingPostcodelatlng.getPostcode() + " to "+requestPostcodelatlng.getPostcode());
                existingPostcodelatlng.setPostcode(requestPostcodelatlng.getPostcode());
            }
            if(requestPostcodelatlng.getLatitude() != null){
                log.info("Update latitude: "+existingPostcodelatlng.getLatitude() + " to "+requestPostcodelatlng.getLatitude());
                existingPostcodelatlng.setLatitude(requestPostcodelatlng.getLatitude());
            }
            if(requestPostcodelatlng.getLongitude() != null){
                log.info("Update longitude: "+existingPostcodelatlng.getLongitude() + " to "+requestPostcodelatlng.getLongitude());
                existingPostcodelatlng.setLongitude(requestPostcodelatlng.getLongitude());
            }            
            
            genericResponse.setResponseBody(postcodelatlngRepository.save(existingPostcodelatlng));
            
            log.info("Postcode ID: "+ existingPostcodelatlng.getId()+" Postcode: "+existingPostcodelatlng.getPostcode() +" updated");
        }
        
        return genericResponse;
    }
        
    /**
     * Function to find all postcodes
     * 
     * @return 
     */
    public ModelResponse findAll(){
        
        GenericResponse genericResponse = new GenericResponse();
        
        // find all postcodes
        Iterable<Postcodelatlng> postcodelatlngList = postcodelatlngRepository.findAll();
        
        // if no postcode in DB then throw response not found
        if(postcodelatlngList == null){
            log.error("No postcode found");
            new ResponseHandler().throwNotFoundExceptionResponse("No postcode found");
        }
        
        genericResponse.setResponseBody(postcodelatlngList);
        
        return genericResponse;
    }
    
    /**
     * Function to find a postcode
     * 
     * @param postcode
     * @return 
     */
    public ModelResponse findPostcode(String postcode){
        
        GenericResponse genericResponse = new GenericResponse();
        
        // throw bad request response if request postcode not provided
        if(postcode == null){
            new ResponseHandler().throwBadRequestExceptionResponse("No postcode request");
        }
        
        // find postcode based on postcode request
        Postcodelatlng postcodelatlng = postcodelatlngRepository.findByPostcode(postcode);
        
        // throw not found response if postcode not found
        if(postcodelatlng == null){
            log.error("No postcode found");
            new ResponseHandler().throwNotFoundExceptionResponse("No postcode found");
        }
        
        genericResponse.setResponseBody(postcodelatlng);
        
        return genericResponse;
    }
    
    /**
     * Function to get distance based on 2 provided postcodes
     * 
     * @param mapRequest
     * @return 
     */
    public ModelResponse getDistance(MapRequest mapRequest){
        
        // initialize parameters
        double distance = 0.0;        
        MapResponse mapResponse = new MapResponse();
        List<Postcodelatlng> postcodelatlngList = new ArrayList<>();

        log.info("postalCodeA : "+mapRequest.getPostalCodeA());
        log.info("postalCodeB : "+mapRequest.getPostalCodeB());

        // retrieve data from database
        Postcodelatlng postcodelatlngA = postcodelatlngRepository.findByPostcode(mapRequest.getPostalCodeA());
        Postcodelatlng postcodelatlngB = postcodelatlngRepository.findByPostcode(mapRequest.getPostalCodeB());
        
        if(postcodelatlngA == null){ 
            new ResponseHandler().throwNotFoundExceptionResponse("postcodelatlngA not found");
        }
        if(postcodelatlngB == null){
            new ResponseHandler().throwNotFoundExceptionResponse("postcodelatlngB not found");
        }
        
        // populate list with retrieved data
        postcodelatlngList.add(postcodelatlngA);
        postcodelatlngList.add(postcodelatlngB);
        
        // perform calculateDistance
        distance = calculateDistance(
                postcodelatlngA.getLatitude().doubleValue(), 
                postcodelatlngA.getLongitude().doubleValue(), 
                postcodelatlngB.getLatitude().doubleValue(), 
                postcodelatlngB.getLongitude().doubleValue());
        
        // set response parameters
        mapResponse.setDistance(df.format(distance));        
        mapResponse.setUnit("km");
        mapResponse.setPostcodelatlngList(postcodelatlngList);

        return mapResponse;
    }
    
    /**
     * Function to calculate distance of 2 coordinates
     * 
     * @param latitude
     * @param longitude
     * @param latitude2
     * @param longitude2
     * @return distance
     */
    private double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
        log.info(latitude+" , "+longitude+" - "+latitude2+" , "+longitude2);
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        log.info("lon1Radians "+lon1Radians);
        log.info("lon2Radians "+lon2Radians);
        log.info("lat1Radians "+lat1Radians);
        log.info("lat2Radians "+lat2Radians);
        double a = haversine(lat1Radians, lat2Radians)
        + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        log.info("a "+a);
        log.info("c "+c);
        log.info("distance = "+EARTH_RADIUS * c);
        
        return EARTH_RADIUS * c;
    }
    
    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }
    
    private double square(double x) {
        return x * x;
    }    
}
