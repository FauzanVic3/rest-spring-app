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
package my.com.vic3.rest.app.dto;

import my.com.vic3.common.lib.JsonProperty;

/**
 *
 * @author Fauzan
 */
public class MapRequest extends JsonProperty{
    
    private String postalCodeA;
    private String postalCodeB;

    public String getPostalCodeA() {
        return postalCodeA;
    }

    public void setPostalCodeA(String postalCodeA) {
        this.postalCodeA = postalCodeA;
    }

    public String getPostalCodeB() {
        return postalCodeB;
    }

    public void setPostalCodeB(String postalCodeB) {
        this.postalCodeB = postalCodeB;
    }
    
}
