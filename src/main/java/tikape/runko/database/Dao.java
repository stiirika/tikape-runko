package tikape.runko.database;

import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;

    void delete(K key) throws SQLException;

    int selectId(K key1, K key2) throws SQLException;
    
    int CountAll(K key1, K key2) throws SQLException;    

    void AddOne(T key) throws SQLException;
}
