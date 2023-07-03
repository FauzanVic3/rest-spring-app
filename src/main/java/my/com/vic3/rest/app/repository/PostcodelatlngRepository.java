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
package my.com.vic3.rest.app.repository;

import java.util.List;
import my.com.vic3.rest.app.model.Postcodelatlng;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Fauzan
 * 
 * Repository for postcodelatlng model
 */
public interface PostcodelatlngRepository extends JpaRepository<Postcodelatlng, Integer>{
 
    // custom query to find by postcode
    @Query(value = "select * from postcodelatlng where postcode=:postcode", nativeQuery = true)    
    Postcodelatlng findByPostcode(@Param("postcode") String postcode);
    
}
