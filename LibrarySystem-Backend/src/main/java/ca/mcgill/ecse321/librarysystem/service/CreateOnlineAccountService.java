package ca.mcgill.ecse321.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.dao.UserEntityRepository;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

@Service
public class CreateOnlineAccountService {
	// --------------------------------------------------------------
	// note: removed email verification as currently it is impossible to send emails
	@Autowired
	PatronRepository patronRepository;
	@Autowired
	OnlineAccountRepository onlineAccountRepository;
	@Autowired
	UserEntityRepository userEntityRepository;
	@Transactional

	public OnlineAccount createOnlineAccountNewUser(String firstName, String lastName, String address, String city, String username, String password, String email) throws IllegalArgumentException {
		String error = "";
		
		// create online account
		// verify empty strings
		if(!verifyStringLength(firstName)) error += "First name cannot be empty or too long.\n";
		if(!verifyStringLength(lastName)) error += "Last name cannot be empty or too long.\n";
		if(!verifyStringLength(address)) error += "Address cannot be empty or too long.\n";
		if(!verifyStringLength(city)) error += "City cannot be empty or too long.\n";
		if(!verifyStringLength(username)) error += "Username cannot be empty or too long.\n";
		// verify username, email exists
		if(!verifyUsernameExists(username)) error += "Username is already used.\n";
		if(!verifyEmailExists(email)) error += "Email is already used.\n";
		// verify email address
		if(!verifyEmail(email)) error += "Invalid email address.\n";
		// check password
		if(!checkPassword(password)) error += "Password too short or too long.\n";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		// create patron and online account
		Patron patron = new Patron();
		patron.setAddress(address);
		patron.setCity(city);
		patron.setFirstName(firstName);
		patron.setLastName(lastName);
		boolean withinCity = (city == "Montreal");
		if(!withinCity) {
			patron.setBalance(patron.getBalance() + 50);
		} else {
			
			patron.setBalance(0);
		}
		patronRepository.save(patron);
		
		OnlineAccount account = new OnlineAccount();
		account.setEmail(email);
		account.setPassword(password);
		account.setUsername(username);
		account.setUser(patron);
		onlineAccountRepository.save(account);
		
		patron.setOnlineAccount(account);
		patronRepository.save(patron);
//		onlineAccountRepository.save(account);
		
		return account;
	}
	
	public OnlineAccount createOnlineAccountExistingUser(int id, String username, String password, String email) throws IllegalArgumentException {
		String error = "";
		// verify ID
		UserEntity user = findUserById(id);
		if (user==null) {
			error += "User with ID ";
			error += id;
			error += " does not exist.\n";
			throw new IllegalArgumentException(error);
		}
		
		// user already has an online account
		if(user.getOnlineAccount()!=null) {
			error += "ID has an associated online account already.\n";
			throw new IllegalArgumentException(error);
		}
		
		// create online account
		// verify empty strings
		if(!verifyStringLength(username)) error += "Username cannot be empty or too long.\n";
		// verify username, email exists
		if(!verifyUsernameExists(username)) error += "Username is already used.\n";
		if(!verifyEmailExists(email)) error += "Email is already used.\n";
		// verify email address
		if(!verifyEmail(email)) error += "Invalid email address.\n";
		// check password
		if(!checkPassword(password)) error += "Password too short or too long.\n";
		if (error.length() > 0) throw new IllegalArgumentException(error);
		
		OnlineAccount account = new OnlineAccount();
		account.setEmail(email);
		account.setPassword(password);
		account.setUsername(username);
		account.setUser(user);
		onlineAccountRepository.save(account);
		user.setOnlineAccount(account);
		// --------------------------------------------------------------
		// IMPORTANT: not sure if this creates another user or if it updates original user
		userEntityRepository.save(user);
		return account;
	}
	
	private boolean verifyEmail(String email) {
		boolean valid = true;
	    if(!email.contains("@")) {
	      valid = false; 
	    } else if (!email.contains(".")) {
	      valid = false;
	    } else if (email.charAt(0)=='@') { // starts with @
	      valid = false;
	    } else if (email.charAt(email.length()-1) == '.') { // ends with .
	      valid = false;
	    } else if (email.charAt(email.length() - 1) == '@' || email.charAt(email.length() - 2) == '@') { // @ is last or second last character
	      valid = false;
	    } else if(!email.substring(email.indexOf('@') + 2, email.length() - 1).contains(".")) { 
	      // . has to be at least two characters after @ (need at least one character between them)
	      valid = false;
	    }
	    return valid;
	}
	
	private boolean verifyStringLength(String s) {
		if (s==null || s.trim().length()==0 || s.length() > 40) {
			return false;
		}
		return true;
	}
	
	private boolean verifyUsernameExists(String username) {
		boolean valid = true;
		OnlineAccount account = findAccountByUsername(username);
		// if username is already used
		if (account != null) valid = false;
		return valid;
	}
	
	private boolean verifyEmailExists(String email) {
		boolean valid = true;
		OnlineAccount account = findAccountByEmail(email);
		// if email is already used
		if (account != null) valid = false;
		return valid;
	}
	
	private boolean checkPassword(String password){
		boolean valid = true;
		int length = password.length();
		if (length<8 || length >16) valid = false;
		return valid;
	}
	
	public OnlineAccount findAccountByUsername(String username) {
		return onlineAccountRepository.findOnlineAccountByUsername(username);
	}
	
	public OnlineAccount findAccountByEmail(String email) {
		return onlineAccountRepository.findOnlineAccountByEmail(email);
	}
	
	public UserEntity findUserById(int id) {
		return userEntityRepository.findUserEntityById(id);
	}
}
