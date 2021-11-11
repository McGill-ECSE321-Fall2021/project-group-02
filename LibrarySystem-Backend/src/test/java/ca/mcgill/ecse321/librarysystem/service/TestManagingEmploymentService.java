package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	@Mock
	private WeeklyScheduleRepository weeklyScheduleDao;

	@InjectMocks
	private ManagingEmploymentService service;
	private String error;

	
	private static final int HEADLIBRARIAN_VALIDID = 0;
	private static final int HEADLIBRARIAN_INVALIDID = 1000;
	private static final String HEADLIBRARIAN_FIRSTNAME = "HLFirstName";
	private static final String HEADLIBRARIAN_LASTNAME = "HLLastName";
	private static final String HEADLIBRARIAN_ADDRESS = "HLAddress";
	private static final String HEADLIBRARIAN_CITY = "HLCity";

	

	
	private static final int LIBRARIAN_VALIDID = 2;
	private static final int LIBRARIAN_VALIDID2 = 3;
	private static final int LIBRARIAN_INVALIDID = 5000;
	private static final String LIBRARIAN_FIRSTNAME = "lFirstName";
	private static final String LIBRARIAN_LASTNAME = "lLastName";
	private static final String LIBRARIAN_ADDRESS = "lAddress";
	private static final String LIBRARIAN_CITY = "lCity";


	@BeforeEach 
	public void setMockOutput() {
		lenient().when(librarianDao.findLibrarianById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_VALIDID)) {
	        	Librarian l = new Librarian();
	            l.setFirstName("Lib");
	            l.setLastName("Rarian");
	            l.setAddress("123 McTavish Street");
	            l.setCity("Montreal");
	            l.setBalance(0);
	            l.setId(LIBRARIAN_VALIDID);
	            return l;
	        }
	        else if (invocation.getArgument(0).equals(LIBRARIAN_VALIDID2)) {
	        	Librarian l = new Librarian();
	            l.setFirstName(LIBRARIAN_FIRSTNAME);
	            l.setLastName(LIBRARIAN_LASTNAME);
	            l.setAddress(LIBRARIAN_ADDRESS);
	            l.setCity(LIBRARIAN_CITY);
	            l.setBalance(0);
	            l.setId(LIBRARIAN_VALIDID2);
	            return l;
	        }
	        else {
	            return null;
	        }
	    });
	    lenient().when(headLibrarianDao.findHeadLibrarianById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEADLIBRARIAN_VALIDID)) {
	            HeadLibrarian hl = new HeadLibrarian();
	            hl.setFirstName("Head");
	            hl.setLastName("Librarian");
	            hl.setAddress("345 McTavish Street");
	            hl.setCity(HEADLIBRARIAN_CITY);
	            hl.setBalance(0);
	            hl.setId(HEADLIBRARIAN_VALIDID);
	            WeeklySchedule ws = new WeeklySchedule();
	            hl.setWeeklySchedule(ws);
	            OnlineAccount oa = new OnlineAccount();
	            hl.setOnlineAccount(oa);
	            return hl;
	        }
	        else {
	            return null;
	        }
	    });
	    lenient().when(headLibrarianDao.existsHeadLibrarianById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEADLIBRARIAN_VALIDID)) {
	            return true;
	        } else {
	            return false;
	        }
	    });
	    lenient().when(librarianDao.existsLibrarianById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_VALIDID)) {
	            return true;
	        } else {
	            return false;
	        }
	    });
	    lenient().when(librarianDao.existsLibrarianById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_VALIDID)) {
	            return true;
	        } else {
	            return false;
	        }
	    });
	    lenient().when(librarianDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
	        List<Librarian> l = new ArrayList<Librarian>();
	        
	        Librarian l1 = new Librarian();
            l1.setFirstName("Lib");
            l1.setLastName("Rarian");
            l1.setAddress("123 McTavish Street");
            l1.setCity("Montreal");
            l1.setBalance(0);
            l1.setId(LIBRARIAN_VALIDID);
            
            Librarian l2 = new Librarian();
            l2.setFirstName(LIBRARIAN_FIRSTNAME);
            l2.setLastName(LIBRARIAN_LASTNAME);
            l2.setAddress(LIBRARIAN_ADDRESS);
            l2.setCity(LIBRARIAN_CITY);
            l2.setBalance(0);
            l2.setId(LIBRARIAN_VALIDID2);
            
            Librarian l3 = new Librarian();
            l3.setFirstName(LIBRARIAN_FIRSTNAME);
            l3.setLastName("Smith");
            l3.setAddress("123 Lib Street");
            l3.setCity(LIBRARIAN_CITY);
            l3.setBalance(0);
            l3.setId(4);
            
            Librarian l4 = new Librarian();
            l4.setFirstName("Lib");
            l4.setLastName(LIBRARIAN_LASTNAME);
            l4.setAddress("345 Lib Street");
            l4.setCity(LIBRARIAN_CITY);
            l4.setBalance(0);
            l4.setId(5);
            
            Librarian l5 = new Librarian();
            l5.setFirstName(LIBRARIAN_FIRSTNAME);
            l5.setLastName(LIBRARIAN_LASTNAME);
            l5.setAddress("789 Lib Street");
            l5.setCity(LIBRARIAN_CITY);
            l5.setBalance(0);
            l5.setId(6);
            
	        l.add(l1);
	        l.add(l2);
	        l.add(l3);
	        l.add(l4);
	        l.add(l5);
	       
	    	return l;
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
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		checkLibrarian(l);
	}
	
	@Test
	public void testCreateLibrarianInvalidID() {
		error="";
		Librarian l = null;
		try {
			l=service.createLibrarian(HEADLIBRARIAN_INVALIDID, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			 error= e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);	
	}
	
	@Test
	public void testCreateLibrarianNegativeID() {
		error="";
		Librarian l = null;
		try {
			l=service.createLibrarian(-10, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	@Test
	public void testCreateLibrarianEmptyFirstName() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "", LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianEmptyLastName() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "",LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianEmptyAddress() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,"",LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Address cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianEmptyCity() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,"");
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("City cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianLongFirstName() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj", LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianLongLastName() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj",LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianLongAddress() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,"jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj",LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Address cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateLibrarianLongCity() {
		error="";
		Librarian l = null;
		try {
			l = service.createLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,"jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj");
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("City cannot be empty or too long.",error);
	}
	
	
	// -------------------------- CreateHeadLibrarian--------------------------- //
	
	@Test
	public void testCreateHeadLibrarianValidID() {
		error="";
		System.out.println("huh");
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), HEADLIBRARIAN_FIRSTNAME, HEADLIBRARIAN_LASTNAME, HEADLIBRARIAN_ADDRESS, HEADLIBRARIAN_CITY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		checkHeadLibrarian(hl);
	}
	
	@Test
	public void testCreateHeadLibrarianInvalidID() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(HEADLIBRARIAN_INVALIDID, HEADLIBRARIAN_FIRSTNAME, HEADLIBRARIAN_LASTNAME, HEADLIBRARIAN_ADDRESS, HEADLIBRARIAN_CITY);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianNegativeID() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(-10, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianEmptyFirstName() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "", LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianEmptyLastName() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "",LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianEmptyAddress() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,"",LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Address cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianEmptyCity() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,"");
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("City cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianLongFirstName() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj", LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianLongLastName() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj",LIBRARIAN_ADDRESS,LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianLongAddress() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,"jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj",LIBRARIAN_CITY);
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("Address cannot be empty or too long.",error);
	}
	
	@Test
	public void testCreateHeadLibrarianLongCity() {
		error="";
		HeadLibrarian hl = null;
		try {
			hl = service.createHeadLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME,LIBRARIAN_ADDRESS,"jfdksljdljslkfdjklsjflksjdlkjslkfjksljfdlskfjslkfj");
		} catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertEquals("City cannot be empty or too long.",error);
	}
	
	// ----------------------------- DeleteLibrarian ---------------------------- //
	
	@Test
	public void testDeleteLibrarianValidValidID() {
		error="";
		try {
			service.deleteLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(),librarianDao.findLibrarianById(LIBRARIAN_VALIDID).getId());
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals(false,librarianDao.existsLibrarianById(LIBRARIAN_INVALIDID));
	}
	
	@Test
	public void testDeleteLibrarianValidInvalidID() {
		error="";
		try {
			service.deleteLibrarian(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(),LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertEquals("This librarian does not exist!",error);
	}
	
	@Test
	public void testDeleteLibrarianInvalidValidID() {
		error="";

		try {
			service.deleteLibrarian(HEADLIBRARIAN_INVALIDID,librarianDao.findLibrarianById(LIBRARIAN_VALIDID).getId());
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
	
	// -------------------- GetLibrarians --------------------- //
	
	@Test
	public void TestGetAllLibrariansByFirstAndLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(0).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(0).getLastName());
		assertEquals(LIBRARIAN_ADDRESS,l.get(0).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(0).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(1).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(1).getLastName());
		assertEquals("789 Lib Street",l.get(1).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(1).getCity());
	}
	
	@Test
	public void TestGetAllLibrariansByFirstAndLastNameNegativeID() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(-10, LIBRARIAN_FIRSTNAME, LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByInvalidFirstAndInvalidLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "test", "haha");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with such first and last name.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByInvalidFirstAndLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "test", LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with such first and last name.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByFirstAndInvalidLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "bzzz");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with such first and last name.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByEmptyFirstAndEmptyLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "", "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First & last name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByLongFirstAndLongLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "hjdfhskjfdslkjfkldsjkldfsjfkldsjkdjklfsdjkljflsjdklfjsljflks", "bzzzkdfjslkfsjlkdsjklfdjlksfjdkljdfskljfdklsjldfsjdfsjdfksjsdkjlkdfsjldjlkjlsk");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First & last name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByFirstAndEmptyLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME, "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First & last name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByLongFirstAndLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstAndLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "hjdfhskjfdslkjfkldsjkldfsjfkldsjkdjklfsdjkljflsjdklfjsljflks", LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First & last name cannot be empty or too long.",error);
	}
	
	
	@Test
	public void TestGetAllLibrariansByFirstName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_FIRSTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(0).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(0).getLastName());
		assertEquals(LIBRARIAN_ADDRESS,l.get(0).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(0).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(1).getFirstName());
		assertEquals("Smith",l.get(1).getLastName());
		assertEquals("123 Lib Street",l.get(1).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(1).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(2).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(2).getLastName());
		assertEquals("789 Lib Street",l.get(2).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(2).getCity());
	}
	
	@Test
	public void TestGetAllLibrariansByFirstNameNegativeID() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstName(-10, LIBRARIAN_FIRSTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByInvalidFirstName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "haha");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with such first name.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByEmptyFirstName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByLongFirstName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByFirstName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "hahjdfksfjkldjskdfsjljldflklkfsdkljslfskda");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("First name cannot be empty or too long.",error);
	}
	
	
	@Test
	public void TestGetAllLibrariansByLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(0).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(0).getLastName());
		assertEquals(LIBRARIAN_ADDRESS,l.get(0).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(0).getCity());
		
		assertEquals("Lib",l.get(1).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(1).getLastName());
		assertEquals("345 Lib Street",l.get(1).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(1).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(2).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(2).getLastName());
		assertEquals("789 Lib Street",l.get(2).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(2).getCity());
	}
	
	@Test
	public void TestGetAllLibrariansByLastNameNegativeID() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByLastName(-10, LIBRARIAN_LASTNAME);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByInvalidLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "hehe");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with such last name.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByEmptyLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetAllLibrariansByLongLastName() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrariansByLastName(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), "hekdkajslkdjskkjfdslkfdjsldkjklakldjklasjdjskaljkldshe");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Last name cannot be empty or too long.",error);
	}
	
	@Test
	public void TestGetLibrarianByIdValidValid() {
		error="";
		Librarian l = null;
		try {
		l=service.getLibrarianByID(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_VALIDID2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		checkLibrarian(l);
	}
	
	@Test
	public void TestGetLibrarianByIdValidInvalid() {
		error="";
		Librarian l = null;
		try {
		l=service.getLibrarianByID(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId(), LIBRARIAN_INVALIDID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("No librarians with ID: "+LIBRARIAN_INVALIDID,error);
	}
	
	@Test
	public void TestGetLibrarianByIdInvalidValid() {
		error="";
		Librarian l = null;
		try {
		l=service.getLibrarianByID(HEADLIBRARIAN_INVALIDID, LIBRARIAN_VALIDID2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Must be a head librarian to proceed.",error);
	}
	
	@Test
	public void TestGetAllLibrarians() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrarians(headLibrarianDao.findHeadLibrarianById(HEADLIBRARIAN_VALIDID).getId());
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		checkLibrarians(l);
	}
	
	@Test
	public void TestGetAllLibrariansNegativeID() {
		error="";
		List<Librarian> l = null;
		try {
			l = service.getAllLibrarians(-10);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("IDs have to be positive.",error);
	}
	
	// -------------------- Helper methods -------------------- //
	
	private void checkLibrarian(Librarian l) {
		assertNotNull(l);
		assertEquals(LIBRARIAN_FIRSTNAME, l.getFirstName());
		assertEquals(LIBRARIAN_LASTNAME, l.getLastName());
		assertEquals(LIBRARIAN_ADDRESS, l.getAddress());
		assertEquals(LIBRARIAN_CITY, l.getCity());
	}
	
	private void checkLibrarians(List<Librarian> l) {
		assertEquals("Lib",l.get(0).getFirstName());
		assertEquals("Rarian",l.get(0).getLastName());
		assertEquals("123 McTavish Street",l.get(0).getAddress());
		assertEquals("Montreal",l.get(0).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(1).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(1).getLastName());
		assertEquals(LIBRARIAN_ADDRESS,l.get(1).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(1).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(2).getFirstName());
		assertEquals("Smith",l.get(2).getLastName());
		assertEquals("123 Lib Street",l.get(2).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(2).getCity());
		
		assertEquals("Lib",l.get(3).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(3).getLastName());
		assertEquals("345 Lib Street",l.get(3).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(3).getCity());
		
		assertEquals(LIBRARIAN_FIRSTNAME,l.get(4).getFirstName());
		assertEquals(LIBRARIAN_LASTNAME,l.get(4).getLastName());
		assertEquals("789 Lib Street",l.get(4).getAddress());
		assertEquals(LIBRARIAN_CITY,l.get(4).getCity());
	}
	
	private void checkHeadLibrarian(HeadLibrarian hl) {
		assertNotNull(hl);
		assertEquals(HEADLIBRARIAN_FIRSTNAME, hl.getFirstName());
		assertEquals(HEADLIBRARIAN_LASTNAME, hl.getLastName());
		assertEquals(HEADLIBRARIAN_ADDRESS, hl.getAddress());
		assertEquals(HEADLIBRARIAN_CITY, hl.getCity());
	}
}