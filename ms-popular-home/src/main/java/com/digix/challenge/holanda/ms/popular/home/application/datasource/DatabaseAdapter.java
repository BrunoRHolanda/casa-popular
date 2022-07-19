package com.digix.challenge.holanda.ms.popular.home.application.datasource;

import java.util.List;

public interface DatabaseAdapter {
    public int save(String sql, Object[] objects);
    public <T> T findOne(String sql, String id, Class<T> mappedClass);
    public <T> List<T> find(String sql, Class<T> mappedClass, Object ...args);
}
