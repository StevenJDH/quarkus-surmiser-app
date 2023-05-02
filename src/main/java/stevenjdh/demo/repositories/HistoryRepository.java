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

package stevenjdh.demo.repositories;

import java.time.Instant;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import stevenjdh.demo.models.History;

@ApplicationScoped
public class HistoryRepository implements PanacheRepository<History> {

    public List<History> findByName(String name) {
        return find("name", name).list();
    }

    public List<History> findByNameIgnoreCase(String name) {
        return streamAll().filter( p -> name.equalsIgnoreCase(p.name))
            .toList();
    }

    @Transactional
    public long saveRecord(String name) {
        var newEntry = History.of(name, Instant.now().toString());
        
        persist(newEntry);
        
        return newEntry.id;
    }
    
}