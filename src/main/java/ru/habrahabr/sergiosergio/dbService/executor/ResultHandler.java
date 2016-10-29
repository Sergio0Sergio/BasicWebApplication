package ru.habrahabr.sergiosergio.dbService.executor;

/**
 * Created by sg on 29.10.16.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
