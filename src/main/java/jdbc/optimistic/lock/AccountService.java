package jdbc.optimistic.lock;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jdbc.optimistic.lock.dao.Account;
import jdbc.optimistic.lock.dao.AccountDao;
@Service
public class AccountService {
    @Autowired
    private AccountDao accountDao;
    
    @Transactional
	public String save(final Account account) {
		System.out.println("In Service");
	    int updateCount= accountDao.update(account);
	   
	    if(updateCount!=1)
	    {
	    	return  new String("Data is Stale");
	    }
	   
	   return "Update Count"+updateCount;
	    
	    
	    
	}

}
