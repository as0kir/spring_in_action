package com.apress.prospring4.ch6.annotation_jdbc.dao;

import com.apress.prospring4.ch6.annotation_jdbc.dao.support.*;
import com.apress.prospring4.ch6.annotation_jdbc.entities.Contact;
import com.apress.prospring4.ch6.annotation_jdbc.entities.ContactTelDetail;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("contactDao")
public class JdbcContactDao implements ContactDao {
    private Log log = LogFactory.getLog(JdbcContactDao.class);

    private DataSource dataSource;
    private SelectAllContacts selectAllContacts;
    private SelectContactByFirstName selectContactByFirstName;
    private UpdateContact updateContact;
    private InsertContact insertContact;
    private InsertContactTelDetail insertContactTelDetail;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllContacts = new SelectAllContacts(dataSource);
        this.selectContactByFirstName = new SelectContactByFirstName(dataSource);
        this.updateContact = new UpdateContact(dataSource);
        this.insertContact = new InsertContact(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public List<Contact> findAll() {
        return selectAllContacts.execute();
    }

    public List<Contact> findByFirstName(String firstName) {
        //return selectContactByFirstName.execute(firstName);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("first_name", firstName);
        return selectContactByFirstName.executeByNamedParam(params);
    }

    public String findFirstNameById(Long id) {
        return null;
    }

    public List<Contact> findAllWithDetail() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql  =
                "select c.id, c.first_name, c.last_name, c.birth_date, t.id as contact_tel_id, t.tel_type, t.tel_number " +
                "from contact c left join contact_tel_detail t on c.id = t.contact_id";
        return jdbcTemplate.query(sql, new ContactWithdetailExtractor());
    }

    public void insert(Contact contact) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("first_name", contact.getFirstName());
        params.put("last_name", contact.getLastName());
        params.put("birth_date", contact.getBirthDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertContact.updateByNamedParam(params, keyHolder);
        contact.setId(keyHolder.getKey().longValue());

        log.info("New contact inserted with id: " + contact.getId());
    }

    public void insertWithDetail(Contact contact) {
        this.insertContactTelDetail = new InsertContactTelDetail(dataSource);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("first_name", contact.getFirstName());
        params.put("last_name", contact.getLastName());
        params.put("birth_date", contact.getBirthDate());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertContact.updateByNamedParam(params, keyHolder);
        contact.setId(keyHolder.getKey().longValue());

        log.info("New contact inserted with id: " + contact.getId());

        List<ContactTelDetail> contactTelDetails = contact.getContactTelDetails();
        for (ContactTelDetail contactTelDetail : contactTelDetails) {
            params = new HashMap<String, Object>();
            params.put("contact_id", contact.getId());
            params.put("tel_type", contactTelDetail.getTelType());
            params.put("tel_number", contactTelDetail.getTelNumber());
            insertContactTelDetail.updateByNamedParam(params);
        }
        insertContactTelDetail.flush();
    }

    public void update(Contact contact) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("first_name", contact.getFirstName());
        params.put("last_name", contact.getLastName());
        params.put("birth_date", contact.getBirthDate());
        params.put("id", contact.getId());

        updateContact.updateByNamedParam(params);

        log.info("Existing contact updated with id: " + contact.getId());
    }

    private class ContactWithdetailExtractor implements ResultSetExtractor<List<Contact>> {
        public List<Contact> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

            Map<Long, Contact> map = new HashMap<Long, Contact>();
            Contact contact = null;

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                contact = map.get(id);

                if(contact == null){
                    contact = new Contact();
                    contact.setId(resultSet.getLong("id"));
                    contact.setFirstName(resultSet.getString("first_name"));
                    contact.setLastName(resultSet.getString("last_name"));
                    contact.setBirthDate(resultSet.getDate("birth_date"));
                    contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                    map.put(id, contact);
                }

                Long contactTelDetailId = resultSet.getLong("contact_tel_id");
                if(contactTelDetailId > 0){
                    ContactTelDetail contactTelDetail = new ContactTelDetail();
                    contactTelDetail.setId(contactTelDetailId);
                    contactTelDetail.setContactId(id);
                    contactTelDetail.setTelType(resultSet.getString("tel_type"));
                    contactTelDetail.setTelNumber(resultSet.getString("tel_number"));
                    contact.getContactTelDetails().add(contactTelDetail);
                }
            }

            return new ArrayList<Contact>(map.values());
        }
    }
}
