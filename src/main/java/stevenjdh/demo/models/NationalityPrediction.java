/**
 * This file is part of surmiser-app <https://github.com/StevenJDH/quarkus-surmiser-app>.
 * Copyright (C) 2020-2023 Steven Jenkins De Haro.
 *
 * surmiser-app is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * surmiser-app is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with surmiser-app.  If not, see <http://www.gnu.org/licenses/>.
 */

package stevenjdh.demo.models;

import java.util.List;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class NationalityPrediction {

    private final List<Country> country;

    private NationalityPrediction(List<Country> country) {
        this.country = country;
    }

    @JsonbCreator
    public static NationalityPrediction of(List<Country> country) {
        return new NationalityPrediction(country);
    }

    public List<Country> getCountry() {
        return country;
    }

    public static class Country {

        private final String countryId;
        private final float probability;
    
        Country(String countryId, float probability) {
            this.countryId = countryId;
            this.probability = probability;
        }
    
        @JsonbCreator
        public static Country of(@JsonbProperty("country_id") String countryId, float probability) {
            return new Country(countryId, probability);
        }
    
        public String getCountryId() {
            return countryId;
        }
    
        public float getProbability() {
            return probability;
        }
    }    
}

