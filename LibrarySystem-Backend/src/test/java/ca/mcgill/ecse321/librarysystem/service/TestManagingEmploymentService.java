package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.model.*;

@ExtendWith(MockitoExtension.class)
public class TestManagingEmploymentService {
	@Mock
	private LibrarianRepository librarianDao;
	@Mock
	private HeadLibrarianRepository headLibrarianDao;
	@Mock
	private OnlineAccountRepository onlineAccountDao;

	@InjectMocks
	private ManagingEmploymentService service;
	private String error;

	
	private static final int HEADLIBRARIAN_VALIDID = 1;
	private static final int HEADLIBRARIAN_INVALIDID = 10000;
	private static final String HEADLIBRARIAN_FIRSTNAME = "HLFirstName";
	private static final String HEADLIBRARIAN_LASTNAME = "HLLastName";
	private static final String HEADLIBRARIAN_ADDRESS = "HLAddress";
	private static final String HEADLIBRARIAN_CITY = "HLCity";
	private static final String HEADLIBRARIAN_EMAIL = "HLEmail";
	private static final String HEADLIBRARIAN_USERNAME = "HLUsername";
	private static final String HEADLIBRARIAN_PASSWORD = "HLPassword";

	
	private static final int LIBRARIAN_VALIDID = 3; // Get after creating a librarian
	private static final int LIBRARIAN_INVALIDID = 50000;
	private static final String LIBRARIAN_FIRSTNAME = "lFirstName";
	private static final String LIBRARIAN_LASTNAME = "lLastName";
	private static final String LIBRARIAN_ADDRESS = "lAddress";
	private static final String LIBRARIAN_CITY = "lCity";
	private static final String LIBRARIAN_EMAIL = "lEmail";
	private static final String LIBRARIAN_USERNAME = "lUsername";
	private static final String LIBRARIAN_PASSWORD = "lPassword";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(librarianDao.findLibrarianByAddress(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEADLIBRARIAN_FIRSTNAME)) {
	            HeadLibrarian hl = new HeadLibrarian();
	            hl.setFirstName(HEADLIBRARIAN_FIRSTNAME);
	            hl.setLastName(HEADLIBRARIAN_LASTNAME);
	            hl.setAddress(HEADLIBRARIAN_ADDRESS);
	            hl.setCity(HEADLIBRARIAN_CITY);
	            hl.setBalance(0);
	            OnlineAccount oa = new OnlineAccount();
	            oa.setUsername(HEADLIBRARIAN_USERNAME);
	            oa.setEmail(HEADLIBRARIAN_EMAIL);
	            oa.setPassword(HEADLIBRARIAN_PASSWORD);
	            oa.setUser(hl);
	            return hl;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(headLibrarianDao.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(onlineAccountDao.save(any(OnlineAccount.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateLibrarianValidID() {
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		assertEquals(0,service.getAllLibrarians(hlid).size());
		Librarian l = null;
		try {
			l = service.createLibrarian(hlid, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY, LIBRARIAN_EMAIL, LIBRARIAN_USERNAME, LIBRARIAN_PASSWORD);
		} catch (IllegalArgumentException e){
			fail();
		}
		assertNotNull(l);
		assertEquals(LIBRARIAN_FIRSTNAME, l.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, l.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, l.getAddress());
		assertEquals(LIBRARIAN_CITY, l.getCity());
		assertEquals(LIBRARIAN_EMAIL, l.getOnlineAccount().getEmail());
		assertEquals(LIBRARIAN_USERNAME, l.getOnlineAccount().getUsername());
		assertEquals(LIBRARIAN_PASSWORD, l.getOnlineAccount().getPassword());
	}
	
	@Test
	public void testCreateLibrarianInvalidID() {
		int hlid=HEADLIBRARIAN_INVALIDID;
		assertEquals(0,service.getAllLibrarians(hlid).size());
		Librarian l = null;
		try {
			l = service.createLibrarian(hlid, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY, LIBRARIAN_EMAIL, LIBRARIAN_USERNAME, LIBRARIAN_PASSWORD);
		} catch (IllegalArgumentException e){
			 error= e.getMessage();
		}
		assertNotNull("Must be a head librarian to proceed.",error);	
	}
	
	@Test
	public void testCreateLibrarianOnlineAccountValidID() {
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();;
		assertEquals(0,service.getAllLibrarians(hlid).size());
		OnlineAccount oa = new OnlineAccount();
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		Librarian l = null;
		try {
			l = service.createLibrarianOnlineAccount(hlid, oa,LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			 fail();
		}
		assertNotNull(l);
		assertEquals(LIBRARIAN_FIRSTNAME, l.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, l.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, l.getAddress());
		assertEquals(LIBRARIAN_CITY, l.getCity());
		assertEquals(LIBRARIAN_EMAIL, l.getOnlineAccount().getEmail());
		assertEquals(LIBRARIAN_USERNAME, l.getOnlineAccount().getUsername());
		assertEquals(LIBRARIAN_PASSWORD, l.getOnlineAccount().getPassword());
	}
	
	@Test
	public void testCreateLibrarianOnlineAccountInvalidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		assertEquals(0,service.getAllLibrarians(hlid).size());
		OnlineAccount oa = new OnlineAccount();
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		Librarian l = null;
		try {
			l = service.createLibrarianOnlineAccount(hlid,oa,LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			 error= e.getMessage();
		}
		assertNotNull("Must be a head librarian to proceed.",error);	
	}
	
	@Test
	public void testDeleteLibrarianValidValidID() {
		error="";
		// Create a librarian @ before each and get ID
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		try {
			service.deleteLibrarian(hlid,LIBRARIAN_VALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		// Assertequals of the id after creating
	}
	
	@Test
	public void testDeleteLibrarianValidInvalidID() {
		error="";
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		try {
			service.deleteLibrarian(hlid,LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("This librarian does not exist!",error);
	}
	
	@Test
	public void testDeleteLibrarianInvalidValidID() {
		error="";
		// Create a librarian @ before each and get ID
		int hlid=HEADLIBRARIAN_INVALIDID;
		try {
			service.deleteLibrarian(hlid,LIBRARIAN_VALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testDeleteLibrarianInvalidInvalidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		try {
			service.deleteLibrarian(hlid,LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testLibrarianIsHiredValidValidID() {
		error="";
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		// Create librarian and check if it exists
	}
	
	@Test
	public void testLibrarianIsHiredValidInvalidID() {
		error="";
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		boolean h = true;
		try {
			h=service.librarianIsHired(hlid, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(false,h);
	}
	
	@Test
	public void testLibrarianIsHiredInvalidValidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		// Create librarian and check if it exists
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testLibrarianIsHiredInvalidInvalidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		try {
			service.librarianIsHired(hlid, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	

	@Test
	public void testLibrarianIsFiredValidValidID() {
		error="";
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();
		boolean t = false;
		try {
			t=service.librarianIsFired(hlid, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(true,t);
	}
	
	@Test
	public void testLibrarianIsFiredValidInvalidID() {
		error="";
		int hlid=headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId();;
		boolean t = true;
		try {
			// Get a real valid ID
			t=service.librarianIsFired(hlid, LIBRARIAN_VALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(false,t);
	}
	
	@Test
	public void testLibrarianIsFiredInvalidValidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		try {
			service.librarianIsFired(hlid, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	@Test
	public void testLibrarianIsFiredInvalidInvalidID() {
		error="";
		int hlid=HEADLIBRARIAN_INVALIDID;
		try {
			// Get a real ID
			service.librarianIsFired(hlid, LIBRARIAN_VALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
}