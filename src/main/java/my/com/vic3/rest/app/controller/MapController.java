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
package my.com.vic3.rest.app.controller;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import my.com.vic3.common.lib.handler.response.ResponseHandler;
import my.com.vic3.rest.app.dto.MapRequest;
import my.com.vic3.rest.app.model.Postcodelatlng;
import my.com.vic3.rest.app.service.PostcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fauzan
 */
@RestController
public class MapController{
    
    @Autowired
    PostcodeService postcodeService;

    /**
     * Function to get distance between 2 postcodes
     * 
     * @param mapRequest
     * @return 
     */
    @PostMapping("/v1/postcode/distance")
    public @ResponseBody ResponseEntity getDistance(@RequestBody @Valid MapRequest mapRequest){
   
        // Validate request body, Validator will add to list of errors if any is found        
        if(postcodeService.validateRequest(mapRequest)){
            // Return Bad Request and display error if any error found during validateRequest 
            return new ResponseHandler().throwBadRequestExceptionResponse(postcodeService.getValidator().getErrorMessage());
        }
        
        return new ResponseHandler().responseOk(postcodeService.getDistance(mapRequest));
    }
    
    /**
     * Function to find all postcodes
     * 
     * @return 
     */
    @GetMapping("/v1/postcode/all")
    public @ResponseBody ResponseEntity getPostcodelatlng(){
        return new ResponseHandler().responseOk(postcodeService.findAll());
    }
    
    /**
     * Function to find specific postcode
     * 
     * @param postcode
     * @return 
     */
    @GetMapping("/v1/postcode")
    public @ResponseBody ResponseEntity getByPostcode(@QueryParam("postcode") String postcode){        
        return new ResponseHandler().responseOk(postcodeService.findPostcode(postcode));
    }
    
    /**
     * Function to save postcode
     * 
     * @param postcodelatlng
     * @return 
     */
    @PostMapping("/v1/postcode")
    public @ResponseBody ResponseEntity createPostcode(@RequestBody @Valid Postcodelatlng postcodelatlng){
        return new ResponseHandler().responseOk(postcodeService.createPostcode(postcodelatlng));
    }
    
    /**
     * Function to update postcode
     * 
     * @param postcodelatlng
     * @return 
     */
    @PutMapping("/v1/postcode")
    public @ResponseBody ResponseEntity updatePostcode(@RequestBody @Valid Postcodelatlng postcodelatlng){
        return new ResponseHandler().responseOk(postcodeService.updatePostcode(postcodelatlng));
    }
}
