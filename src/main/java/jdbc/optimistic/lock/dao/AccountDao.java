package jdbc.optimistic.lock.dao;

import java.util.Collections;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jdbc.optimistic.lock.AccountController;

@Repository
public class AccountDao {
	private static final Logger log=org.slf4j.LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	
	private static final String UPDATE_SQL="update ACCOUNT set NAME = :name,VERSION= (:version +1 ) where VERSION= :version and ACCOUNT_ID= :accountId";
	private static final String SELECT_SQL=" select ACCOUNT_ID,NAME,VERSION from ACCOUNT  where ACCOUNT_ID= :accountId";
	
	public Account selectBy(final int accountId) {
		
		return jdbc.queryForObject(SELECT_SQL, Collections.singletonMap("accountId", accountId),(rs,rowNum)->
		{
			Account account= new Account();
			log.info("Result count",rs.getFetchSize());
			account.setAccountId(rs.getInt("ACCOUNT_ID"));
			account.setName(rs.getString("NAME"));
			account.setVersion(rs.getInt("VERSION"));
			return account;
		});
	}

	public int update(Account account) {
        log.info("Update");
		return jdbc.update(UPDATE_SQL, new BeanPropertySqlParameterSource(account));
	}

}
