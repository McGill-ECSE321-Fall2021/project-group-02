package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.dao.UserEntityRepository;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Librarian;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

@ExtendWith(MockitoExtension.class)
public class TestCreateOnlineAccountService {
	@Mock
	private OnlineAccountRepository onlineAccountDao;
	@Mock
	private UserEntityRepository userDao;
	@Mock
	private PatronRepository patronDao;
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private HeadLibrarianRepository headLibrarianDao;
	
	@InjectMocks
	private CreateOnlineAccountService service;
	
	private static final String USERNAME_KEY1 = "ExistingUser";
	private static final String EMAIL_KEY1 = "Exist@gmail.com";
	private static final int ID_KEY_HEAD_LIB_NO_ONLINE_ACC = 1; // HeadLibrarian
	private static final int ID_KEY_HEAD_LIB_WITH_ONLINE_ACC = 2; // HeadLibrarian
	private static final int ID_KEY_LIB_NO_ONLINE_ACC = 3; // Librarian
	private static final int ID_KEY_LIB_WITH_ONLINE_ACC = 4; // Librarian
	private static final int ID_KEY_PATRON_NO_ONLINE_ACC = 5; // Patron
	private static final int ID_KEY_PATRON_WITH_ONLINE_ACC = 6; // Patron
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(onlineAccountDao.findOnlineAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) ->
		{
			if(invocation.getArgument(0).equals(USERNAME_KEY1)) {
	        	OnlineAccount account = new OnlineAccount();
	        	account.setUsername(USERNAME_KEY1);
	            return account;
	        } else {
	            return null;
	        }
		});
		
		lenient().when(onlineAccountDao.findOnlineAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) ->
		{
			if(invocation.getArgument(0).equals(EMAIL_KEY1)) {
	        	OnlineAccount account = new OnlineAccount();
	        	account.setEmail(EMAIL_KEY1);
	            return account;
	        } else {
	            return null;
	        }
		});
		
		lenient().when(userDao.findUserEntityById(anyInt())).thenAnswer((InvocationOnMock invocation) ->
		{
			if(invocation.getArgument(0).equals(ID_KEY_HEAD_LIB_NO_ONLINE_ACC)) {
	        	HeadLibrarian hlib = new HeadLibrarian();
	        	hlib.setId(ID_KEY_HEAD_LIB_NO_ONLINE_ACC);
	            return hlib;
	        } if(invocation.getArgument(0).equals(ID_KEY_HEAD_LIB_WITH_ONLINE_ACC)) {
	        	HeadLibrarian hlib = new HeadLibrarian();
	        	hlib.setId(ID_KEY_HEAD_LIB_WITH_ONLINE_ACC);
	        	OnlineAccount acc = new OnlineAccount();
	        	acc.setUsername(USERNAME_KEY1);
	        	acc.setEmail(EMAIL_KEY1);
	        	hlib.setOnlineAccount(acc);
	        	acc.setUser(hlib);
	            return hlib;
	        } if(invocation.getArgument(0).equals(ID_KEY_LIB_NO_ONLINE_ACC)) {
	        	Librarian lib = new Librarian();
	        	lib.setId(ID_KEY_LIB_NO_ONLINE_ACC);
	            return lib;
	        } if(invocation.getArgument(0).equals(ID_KEY_LIB_WITH_ONLINE_ACC)) {
	        	Librarian lib = new Librarian();
	        	lib.setId(ID_KEY_LIB_WITH_ONLINE_ACC);
	        	OnlineAccount acc = new OnlineAccount();
	        	acc.setUsername(USERNAME_KEY1);
	        	acc.setEmail(EMAIL_KEY1);
	        	lib.setOnlineAccount(acc);
	        	acc.setUser(lib);
	            return lib;
	        } if(invocation.getArgument(0).equals(ID_KEY_PATRON_NO_ONLINE_ACC)) {
	        	Patron patron = new Patron();
	        	patron.setId(ID_KEY_PATRON_NO_ONLINE_ACC);
	            return patron;
	        } if(invocation.getArgument(0).equals(ID_KEY_PATRON_WITH_ONLINE_ACC)) {
	        	Patron patron = new Patron();
	        	patron.setId(ID_KEY_PATRON_WITH_ONLINE_ACC);
	        	OnlineAccount acc = new OnlineAccount();
	        	acc.setUsername(USERNAME_KEY1);
	        	acc.setEmail(EMAIL_KEY1);
	        	patron.setOnlineAccount(acc);
	        	acc.setUser(patron);
	            return patron;
	        } else {
	            return null;
	        }
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(onlineAccountDao.save(any(OnlineAccount.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(userDao.save(any(UserEntity.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(patronDao.save(any(Patron.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(headLibrarianDao.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void TestCreateNewAccountValid() {
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Montreal";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(account);
		assertEquals(account.getUser().getFirstName(), "John");
		assertEquals(account.getUser().getLastName(), "Doe");
		assertEquals(account.getUser().getAddress(), "1234 McGill street");
		assertEquals(account.getUser().getCity(), "Montreal");
		assertEquals(account.getUser().getBalance(), 0);
		assertEquals(account.getUsername(), "John123");
		assertEquals(account.getPassword(), "pass1234");
		assertEquals(account.getEmail(), "John123@gmail.com");
		
	}
	
	@Test
	public void TestCreateNewAccountValidNotFromCity() {
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(account);
		assertEquals(account.getUser().getFirstName(), "John");
		assertEquals(account.getUser().getLastName(), "Doe");
		assertEquals(account.getUser().getAddress(), "1234 McGill street");
		assertEquals(account.getUser().getCity(), "Toronto");
		assertEquals(account.getUser().getBalance(), 50);
		assertEquals(account.getUsername(), "John123");
		assertEquals(account.getPassword(), "pass1234");
		assertEquals(account.getEmail(), "John123@gmail.com");
	}

	@Test
	public void TestCreateNewAccountEmptyFirstName() {
		String error = "";
		String firstName = "   ";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("First name cannot be empty or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountFirstNameTooLong() {
		String error = "";
		String firstName = "Abcdsklajfldsjfl;ajflkdsajlfk;sajd;klfjsda;lkfjsda;lkfjsad;lkfjsdafj10983087423801751298";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("First name cannot be empty or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountEmptyLastName() {
		String error = "";
		String firstName = "John";
		String lastName = "  ";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Last name cannot be empty or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountEmptyAddress() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "  ";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Address cannot be empty or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountEmptyCity() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "  ";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("City cannot be empty or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountUsernameAlreadyUsed() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = USERNAME_KEY1;
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Username is already used.\n", error);
	}

	@Test
	public void TestCreateNewAccountEmailAlreadyUsed() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = EMAIL_KEY1;
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Email is already used.\n", error);
	}

	@Test
	public void TestCreateNewAccountPasswordTooShort() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Password too short or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountPasswordTooLong() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "passwordtoolooooooooooooooooooooooong1";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Password too short or too long.\n", error);
	}

	@Test
	public void TestCreateNewAccountInvalidEmail1() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Invalid email address.\n", error);
	}

	@Test
	public void TestCreateNewAccountInvalidEmail2() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Invalid email address.\n", error);
	}

	@Test
	public void TestCreateNewAccountInvalidEmail3() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Invalid email address.\n", error);
	}

	@Test
	public void TestCreateNewAccountInvalidEmail5() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Invalid email address.\n", error);
	}
	
	@Test
	public void TestCreateNewAccountInvalidEmail6() {
		String error = "";
		String firstName = "John";
		String lastName = "Doe";
		String address = "1234 McGill street";
		String city = "Toronto";
		String username = "John123";
		String password = "pass1234";
		String email = "John12.3@";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountNewUser(firstName, lastName, address, city, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(account);
		assertEquals("Invalid email address.\n", error);
	}
	
	@Test
	public void TestCreateAccountExistingHeadLibrarianValid() {
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_HEAD_LIB_NO_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(account);
		assertNotNull(account.getUser());
		assertTrue(account.getUser() instanceof HeadLibrarian);
		assertEquals(account.getUser().getOnlineAccount(), account);
		assertEquals(account.getUser().getId(), ID_KEY_HEAD_LIB_NO_ONLINE_ACC);
		assertEquals(account.getUsername(), "John123");
		assertEquals(account.getPassword(), "pass1234");
		assertEquals(account.getEmail(), "John123@gmail.com");
	}
	
	@Test
	public void TestCreateAccountExistingLibrarianValid() {
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_LIB_NO_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(account);
		assertNotNull(account.getUser());
		assertTrue(account.getUser() instanceof Librarian);
		assertEquals(account.getUser().getOnlineAccount(), account);
		assertEquals(account.getUser().getId(), ID_KEY_LIB_NO_ONLINE_ACC);
		assertEquals(account.getUsername(), "John123");
		assertEquals(account.getPassword(), "pass1234");
		assertEquals(account.getEmail(), "John123@gmail.com");
	}
	
	@Test
	public void TestCreateAccountExistingPatronValid() {
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_PATRON_NO_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(account);
		assertNotNull(account.getUser());
		assertTrue(account.getUser() instanceof Patron);
		assertEquals(account.getUser().getOnlineAccount(), account);
		assertEquals(account.getUser().getId(), ID_KEY_PATRON_NO_ONLINE_ACC);
		assertEquals(account.getUsername(), "John123");
		assertEquals(account.getPassword(), "pass1234");
		assertEquals(account.getEmail(), "John123@gmail.com");
	}
	
	@Test
	public void TestCreateAccountExistingHeadLibrarianAlreadyHasOnlineAccount() {
		String error = "";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_HEAD_LIB_WITH_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();;
		}
		
		assertNull(account);
		assertEquals("ID has an associated online account already.\n", error);
	}
	
	@Test
	public void TestCreateAccountExistingLibrarianAlreadyHasOnlineAccount() {
		String error = "";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_LIB_WITH_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();;
		}
		
		assertNull(account);
		assertEquals("ID has an associated online account already.\n", error);
	}
	
	@Test
	public void TestCreateAccountExistingPatronAlreadyHasOnlineAccount() {
		String error = "";
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(ID_KEY_PATRON_WITH_ONLINE_ACC, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();;
		}
		
		assertNull(account);
		assertEquals("ID has an associated online account already.\n", error);
	}
	
	@Test
	public void TestCreateAccountExistingInvalidId() {
		String error = "";
		int id = 123;
		String username = "John123";
		String password = "pass1234";
		String email = "John123@gmail.com";
		OnlineAccount account = null;
		try {
			account = service.createOnlineAccountExistingUser(id, username, password, email);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();;
		}
		
		assertNull(account);
		assertEquals("ID does not exist.\n", error);
	}
}
