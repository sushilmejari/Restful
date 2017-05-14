package jdbc.optimistic.lock;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdbc.optimistic.lock.dao.Account;
import jdbc.optimistic.lock.dao.AccountDao;



@RestController
@RequestMapping(AccountController.ACCOUNT_BASE_URL)
public class AccountController {
	public static final String ACCOUNT_BASE_URL="svc/v1/accounts";
	private static final Logger log=org.slf4j.LoggerFactory.getLogger(AccountController.class);
   
	@Autowired
	private AccountService accounService;
			
	
   @Autowired
   private AccountDao accountDao;
	
	@RequestMapping(path="{accountId}")
	public String process(@PathVariable("accountId") final int accountId)
	{
		Account account= accountDao.selectBy(accountId);
		log.info("Account Lodded : %s	",account);
		
		
		return accounService.save(account);
		
	}
	
	
	
	
	
}
