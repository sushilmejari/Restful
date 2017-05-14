package jdbc.optimistic.lock.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Account {
private String name;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int accountId;
@Version
private int version;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}
	
@Override
public String toString() {
	return "Account [name=" + name + ", accountId=" + accountId + ", version=" + version + "]";
}
}
