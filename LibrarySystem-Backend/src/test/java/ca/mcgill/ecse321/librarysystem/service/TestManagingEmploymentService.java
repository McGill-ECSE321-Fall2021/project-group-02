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

	
	
	private static final int HEADLIBRARIAN_INVALIDID = 10000;
	private static final String HEADLIBRARIAN_FIRSTNAME = "HLFirstName";
	private static final String HEADLIBRARIAN_LASTNAME = "HLLastName";
	private static final String HEADLIBRARIAN_ADDRESS = "HLAddress";
	private static final String HEADLIBRARIAN_CITY = "HLCity";
	private static final String HEADLIBRARIAN_EMAIL = "HLEmail";
	private static final String HEADLIBRARIAN_USERNAME = "HLUsername";
	private static final String HEADLIBRARIAN_PASSWORD = "HLPassword";

	
	
	private static final int LIBRARIAN_INVALIDID = 50000;
	private static final String LIBRARIAN_FIRSTNAME = "lFirstName";
	private static final String LIBRARIAN_LASTNAME = "lLastName";
	private static final String LIBRARIAN_ADDRESS = "lAddress";
	private static final String LIBRARIAN_CITY = "lCity";
	private static final String LIBRARIAN_EMAIL = "lEmail";
	private static final String LIBRARIAN_USERNAME = "lUsername";
	private static final String LIBRARIAN_PASSWORD = "lPassword";

	@BeforeEach // Modify this
	public void setMockOutput() {
		lenient().when(librarianDao.findLibrarianByAddress(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_ADDRESS)) {
	            Librarian l = new Librarian();
	            l.setFirstName(LIBRARIAN_FIRSTNAME);
	            l.setLastName(LIBRARIAN_LASTNAME);
	            l.setAddress(LIBRARIAN_ADDRESS);
	            l.setCity(LIBRARIAN_CITY);
	            l.setBalance(0);
	            OnlineAccount oa = new OnlineAccount();
	            oa.setUsername(LIBRARIAN_USERNAME);
	            oa.setEmail(LIBRARIAN_EMAIL);
	            oa.setPassword(LIBRARIAN_PASSWORD);
	            oa.setUser(l);
	            return l;
	        } else {
	            return null;
	        }
	    });
	    lenient().when(headLibrarianDao.findHeadLibrarianByAddress(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEADLIBRARIAN_ADDRESS)) {
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
		lenient().when(librarianDao.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(onlineAccountDao.save(any(OnlineAccount.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// ----------------------------- CreateLibrarian ---------------------------- //
	
	@Test
	public void testCreateLibrarianValidID() {
		error="";
		assertEquals(0,service.getAllLibrarians(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId()).size());
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY, LIBRARIAN_EMAIL, LIBRARIAN_USERNAME, LIBRARIAN_PASSWORD);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		checkLibrarian(l);
	}
	
	@Test
	public void testCreateLibrarianInvalidID() {
		error="";
		assertEquals(0,service.getAllLibrarians(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId()).size());
		Librarian l = null;
		try {
			l = service.createLibrarian(HEADLIBRARIAN_INVALIDID, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY, LIBRARIAN_EMAIL, LIBRARIAN_USERNAME, LIBRARIAN_PASSWORD);
		} catch (IllegalArgumentException e){
			 error= e.getMessage();
		}
		assertNotNull("Must be a head librarian to proceed.",error);	
	}
	
	@Test
	public void testCreateLibrarianOnlineAccountValidID() {
		error="";
		assertEquals(0,service.getAllLibrarians(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId()).size());
		OnlineAccount oa = new OnlineAccount();
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		Librarian l = null;
		try {
			l = service.createLibrarianOnlineAccount(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), oa,LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			 fail();
		}
		checkLibrarian(l);
	}
	
	@Test
	public void testCreateLibrarianOnlineAccountInvalidID() {
		error="";
		assertEquals(0,service.getAllLibrarians(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId()).size());
		OnlineAccount oa = new OnlineAccount();
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		Librarian l = null;
		try {
			l = service.createLibrarianOnlineAccount(HEADLIBRARIAN_INVALIDID,oa,LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			 error= e.getMessage();
		}
		assertNotNull("Must be a head librarian to proceed.",error);	
	}
	
	// ----------------------------- DeleteLibrarian ---------------------------- //
	
	@Test
	public void testDeleteLibrarianValidValidID() {
		error="";
		Librarian l = new Librarian();
		l.setFirstName(LIBRARIAN_FIRSTNAME);
		l.setLastName(LIBRARIAN_LASTNAME);
		l.setAddress(LIBRARIAN_ADDRESS);
		l.setCity(LIBRARIAN_CITY);
		l.setBalance(0);
		
		OnlineAccount oa = new OnlineAccount();
		
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		
		onlineAccountDao.save(oa);
		l.setOnlineAccount(oa);
		
		librarianDao.save(l);
		
		try {
			service.deleteLibrarian(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(),librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		// assertEquals(false,librarianDao.existsLibrarianByAddress(LIBRARIAN_ADDRESS));
	}
	
	@Test
	public void testDeleteLibrarianValidInvalidID() {
		error="";
		try {
			service.deleteLibrarian(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(),LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("This librarian does not exist!",error);
	}
	
	@Test
	public void testDeleteLibrarianInvalidValidID() {
		error="";

		try {
			service.deleteLibrarian(HEADLIBRARIAN_INVALIDID,librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testDeleteLibrarianInvalidInvalidID() {
		error="";
		try {
			service.deleteLibrarian(HEADLIBRARIAN_INVALIDID,LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	// ----------------------------- IsHired ---------------------------- //
	
	@Test
	public void testLibrarianIsHiredValidValidID() {
		error="";

		Librarian l = new Librarian();
		l.setFirstName(LIBRARIAN_FIRSTNAME);
		l.setLastName(LIBRARIAN_LASTNAME);
		l.setAddress(LIBRARIAN_ADDRESS);
		l.setCity(LIBRARIAN_CITY);
		l.setBalance(0);
		
		OnlineAccount oa = new OnlineAccount();
		
		oa.setEmail(LIBRARIAN_EMAIL);
		oa.setUsername(LIBRARIAN_USERNAME);
		oa.setPassword(LIBRARIAN_PASSWORD);
		
		onlineAccountDao.save(oa);
		l.setOnlineAccount(oa);
		
		librarianDao.save(l);
		
		try {
			service.librarianIsHired(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// assertsEquals()
	}
	
	@Test
	public void testLibrarianIsHiredValidInvalidID() {
		error="";
		boolean h = true;
		try {
			h=service.librarianIsHired(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(false,h);
	}
	
	@Test
	public void testLibrarianIsHiredInvalidValidID() {
		error="";
		
		try {
			service.librarianIsHired(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testLibrarianIsHiredInvalidInvalidID() {
		error="";
		
		try {
			service.librarianIsHired(HEADLIBRARIAN_INVALIDID, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	// ----------------------------- IsFired ---------------------------- //
	
	@Test
	public void testLibrarianIsFiredValidValidID() {
		error="";
		
		boolean t = false;
		try {
			t=service.librarianIsFired(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(true,t);
	}
	
	@Test
	public void testLibrarianIsFiredValidInvalidID() {
		error="";
		
		boolean t = true;
		try {
			// Get a real valid ID
			t=service.librarianIsFired(headLibrarianDao.findHeadLibrarianByAddress(HEADLIBRARIAN_ADDRESS).get(0).getId(), librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(false,t);
	}
	
	@Test
	public void testLibrarianIsFiredInvalidValidID() {
		error="";
		try {
			service.librarianIsFired(HEADLIBRARIAN_INVALIDID, LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	@Test
	public void testLibrarianIsFiredInvalidInvalidID() {
		error="";
		try {
			service.librarianIsFired(HEADLIBRARIAN_INVALIDID, librarianDao.findLibrarianByAddress(LIBRARIAN_ADDRESS).get(0).getId());
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	private void checkLibrarian(Librarian l) {
		assertNotNull(l);
		assertEquals(LIBRARIAN_FIRSTNAME, l.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, l.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, l.getAddress());
		assertEquals(LIBRARIAN_CITY, l.getCity());
		assertEquals(LIBRARIAN_EMAIL, l.getOnlineAccount().getEmail());
		assertEquals(LIBRARIAN_USERNAME, l.getOnlineAccount().getUsername());
		assertEquals(LIBRARIAN_PASSWORD, l.getOnlineAccount().getPassword());
	}
}