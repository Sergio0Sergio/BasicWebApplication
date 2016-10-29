package ru.habrahabr.sergiosergio.dbService;

/**
 * Created by sg on 28.10.16.
 */
public class DBException extends Exception{
    public DBException(Throwable throwable) {
        super(throwable);
    }
}
