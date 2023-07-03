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

import java.util.List;
import my.com.vic3.common.lib.BasicResponse;
import my.com.vic3.rest.app.model.Postcodelatlng;

/**
 *
 * @author Fauzan
 */
public class MapResponse extends BasicResponse{
    
    private List<Postcodelatlng> postcodelatlngList;
    private String distance;
    private String unit;

    public List<Postcodelatlng> getPostcodelatlngList() {
        return postcodelatlngList;
    }

    public void setPostcodelatlngList(List<Postcodelatlng> postcodelatlngList) {
        this.postcodelatlngList = postcodelatlngList;
    }
    
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
