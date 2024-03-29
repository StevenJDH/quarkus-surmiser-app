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

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbNillable;

@JsonbNillable // Added because JSON-B doesn't render null values by default, which hides gender property.
public class GenderPrediction {

    private final String gender;
    private final float probability;

    private GenderPrediction(String gender, float probability) {
        this.gender = gender;
        this.probability = probability;
    }

    @JsonbCreator
    public static GenderPrediction of(String gender, float probability) {
        return new GenderPrediction(gender, probability);
    }

    public String getGender() {
        return gender;
    }

    public float getProbability() {
        return probability;
    }
}