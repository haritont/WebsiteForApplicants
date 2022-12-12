package website.applicants.dao;

import java.util.List;

public interface Dao<T> {
    int size();
    T get(int id);
    List<T> getAll();
    void save(T t);
    void delete(T t);
}

