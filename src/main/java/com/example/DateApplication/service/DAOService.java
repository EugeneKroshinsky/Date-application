package com.example.DateApplication.service;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DAOService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DAOService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate =  jdbcTemplate;
    }

    public List<DateIdea> findDateIdeas(FindRequest request) {
        String query = buildQuery(request);
        List<Object> params =  addParameters(request);
        System.out.println(query);
        params.forEach(System.out::println);
        System.out.println(params.size());
        if (params.isEmpty()) {
            return jdbcTemplate.query("SELECT * FROM date_idea", new BeanPropertyRowMapper<>(DateIdea.class));
        }
        return jdbcTemplate.query(query, params.toArray(), new BeanPropertyRowMapper<>(DateIdea.class));
    }

    private String buildQuery(FindRequest request) {
        StringBuilder query = new StringBuilder("SELECT * FROM date_idea WHERE 1=1");

        if (request.getCity() != null && !request.getCity().isEmpty()) {
            query.append(" AND city_id=?");
        } else if (request.getRegion() != null && !request.getRegion().isEmpty()) {
            query.append(" AND region_id=?");
        } else if (request.getCountry() != null && !request.getCountry().isEmpty()) {
            query.append(" AND country_id=?");
        }

        if (!request.getIsAnyType()) {
            query.append(" AND type_id=?");
        }

        if (!request.getIsAnyPrice()) {
            query.append(" AND minPrice<=? AND maxPrice>=?");
        }

        return query.toString();
    }

    private List<Object> addParameters(FindRequest request) {
        List<Object> params = new ArrayList<>();
        if (request.getCity() != null && !request.getCity().isEmpty()) {
            params.add(Integer.parseInt(request.getCity()));
        } else if (request.getRegion() != null && !request.getRegion().isEmpty()) {
            params.add(Integer.parseInt(request.getRegion()));
        } else if (request.getCountry() != null && !request.getCountry().isEmpty()) {
            params.add(Integer.parseInt(request.getCountry()));
        }
        if (!request.getIsAnyType()) {
            params.add(Integer.parseInt(request.getType()));
        }

        if (!request.getIsAnyPrice()) {
            params.add(request.getPrice());
            params.add(request.getPrice());
        }
        return params;
    }
}