package com.apress.prospring4.ch6.spring_jdbc.dao;

import com.apress.prospring4.ch6.spring_jdbc.entities.Contact;
import com.apress.prospring4.ch6.spring_jdbc.entities.ContactTelDetail;
import com.apress.prospring4.ch6.spring_jdbc.util.MySQLErrorCodesTranslator;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcContactDao implements ContactDao, InitializingBean {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public String findFirstNameById(Long id) {
        return jdbcTemplate.queryForObject("select first_name from contact where id = ?", new Object[]{id}, String.class);
    }

    public String findLastNameById(Long id) {
        String sql = "select last_name from contact where id = :contactId";
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("contactId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    public List<Contact> findAll() {
        String sql = "select id, first_name, last_name, birth_date from contact";
        return namedParameterJdbcTemplate.query(sql, new ContactMapper());
    }

    public List<Contact> findAllWithDetail() {
        String sql  =
                "select c.id, c.first_name, c.last_name, c.birth_date, t.id as contact_tel_id, t.tel_type, t.tel_number " +
                "from contact c left join contact_tel_detail t on c.id = t.contact_id";
        return namedParameterJdbcTemplate.query(sql, new ContactWithdetailExtractor());
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        MySQLErrorCodesTranslator mySQLErrorCodesTranslator = new MySQLErrorCodesTranslator();
        mySQLErrorCodesTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(mySQLErrorCodesTranslator);

        this.jdbcTemplate = jdbcTemplate;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void afterPropertiesSet() throws Exception {
        if (dataSource == null){
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }
        if (jdbcTemplate== null){
            throw new BeanCreationException("Null JdbcTemplate on ContactDao");
        }
        if (namedParameterJdbcTemplate== null){
            throw new BeanCreationException("Null NamedParameterJdbcTemplate on ContactDao");
        }
    }

    private static class ContactMapper implements RowMapper<Contact> {

        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
            Contact contact = new Contact();
            contact.setId(resultSet.getLong("id"));
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setLastName(resultSet.getString("last_name"));
            contact.setBirthDate(resultSet.getDate("birth_date"));


            return contact;
        }
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
