package ru.habrahabr.sergiosergio.dbService.dao;

/**
 * Created by sg on 29.10.16.
 */

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.habrahabr.sergiosergio.dbService.dataSets.UsersDataSet;

public class UsersDao {

    private Session session;

    public UsersDao(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException{
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserID(String name) throws HibernateException{
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public UsersDataSet getUserByName(String name) throws HibernateException{
        //Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) session.get(UsersDataSet.class, name));
    }

    public long insertUser(String name, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name, password));
    }
}
