package com.apress.prospring4.ch6.annotation_jdbc.dao;

import com.apress.prospring4.ch6.annotation_jdbc.entities.Contact;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Repository("contactDao")
public class JdbcContactDao implements ContactDao {
    private Log log = LogFactory.getLog(JdbcContactDao.class);

    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public List<Contact> findAll() {
        return null;
    }

    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    public String findFirstNameById(Long id) {
        return null;
    }

    public List<Contact> findAllWithDetail() {
        return null;
    }

    public void insert(Contact contact) {

    }

    public void insertWithDetail(Contact contact) {

    }

    public void update(Contact contact) {

    }
}
