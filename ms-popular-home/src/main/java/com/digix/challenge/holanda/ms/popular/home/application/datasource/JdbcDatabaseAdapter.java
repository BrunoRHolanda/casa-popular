package com.digix.challenge.holanda.ms.popular.home.application.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class JdbcDatabaseAdapter implements DatabaseAdapter {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDatabaseAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(String sql, Object[] objects) {
        return this.jdbcTemplate.update(sql, objects);
    }

    public <T> T findOne(String sql, String id, Class<T> mappedClass) {
        try {
            return this.jdbcTemplate.queryForObject(
                    sql,
                    BeanPropertyRowMapper.newInstance(mappedClass),
                    id
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public <T> List<T> find(String sql, Class<T> mappedClass, Object ...args) {
        return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(mappedClass), args);
    }
}
