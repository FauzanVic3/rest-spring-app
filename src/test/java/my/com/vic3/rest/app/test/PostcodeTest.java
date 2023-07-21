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
package my.com.vic3.rest.app.test;


import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;



/**
 *
 * @author Fauzan
 */
@RunWith(SpringRunner.class)
public class PostcodeTest {
        
    @Test
    public void getPostcodesTest(){
        String endpoint = "http://localhost:8080/v1/postcode/all";
        var response =  
                given().
                    contentType(ContentType.JSON).
                when().
                    get(endpoint).
                then().
                    assertThat().
                        statusCode(200);

//        response.log().body();
    }
    
    @Test
    public void getPostcodeTest(){
        String endpoint = "http://localhost:8080/v1/postcode";
        var response = 
                given().
                    queryParam("postcode", "AB21 9LP").
                when().
                    get(endpoint).
                then().
                    assertThat().
                        statusCode(200).
                        body("responseBody.id", equalTo(172)).
                        body("responseBody.latitude", equalTo(57.1764840F)).
                        body("responseBody.longitude", equalTo(-2.1674830F));
                        
        
//        response.log().body();
    }
    
    @Test
    public void addPostcode_already_exist_Test(){
        String endpoint = "http://localhost:8080/v1/postcode";
        String body = 
                "{\n" +
                "    \"postcode\" : \"GL3 3BT\",\n" +
                "    \"latitude\" : \"51.86081\",\n" +
                "    \"longitude\" : \"-2.194289\"\n" +
                "}"; 
        var response = 
                given().
                    contentType(ContentType.JSON).
                    body(body).
                when().
                    post(endpoint).
                then().
                    assertThat().
                        statusCode(400).
                        body("errorMessage", equalTo("Postcode GL3 3BT exists"));
        
//        response.log().body();
                      
        
    }
}
