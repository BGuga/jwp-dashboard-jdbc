package com.techcourse.dao;

import com.techcourse.domain.UserHistory;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserHistoryDao {

    private static final Logger log = LoggerFactory.getLogger(UserHistoryDao.class);

    private final JdbcTemplate jdbcTemplate;

    public UserHistoryDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public UserHistoryDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void log(final UserHistory userHistory) {
        final var sql = "insert into user_history (user_id, account, password, email, created_at, created_by) values (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
            userHistory.getUserId(),
            userHistory.getAccount(),
            userHistory.getPassword(),
            userHistory.getEmail(),
            userHistory.getCreatedAt(),
            userHistory.getCreatedBy()
        );
    }

    public UserHistory findById(Long id){
        String sql = "select * from user_history where id = ?";
        return jdbcTemplate.queryForObject(sql, UserHistory.class, id);
    }
}
