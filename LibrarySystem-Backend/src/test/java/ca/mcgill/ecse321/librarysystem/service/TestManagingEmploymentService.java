package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

	@InjectMocks
	private ManagingEmploymentService service;

	
	private static final int HEADLIBRARIAN_ID1 = 1;
	private static final int HEADLIBRARIAN_ID2 = 10000;
	private static final int LIBRARIAN_ID1 = 3;
	private static final int LIBRARIAN_ID2 = 50000;
	
	private static final String LIBRARIAN_FIRSTNAME = "testFirstName";
	private static final String LIBRARIAN_LASTNAME = "testLastName";
	private static final String LIBRARIAN_ADDRESS = "testAddress";
	private static final String LIBRARIAN_CITY = "testCity";
	private static final String LIBRARIAN_EMAIL = "testEmail";
	private static final String LIBRARIAN_USERNAME = "testUsername";
	private static final String LIBRARIAN_PASSWORD = "testPassword";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(librarianDao.findLibrarianByAddress(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(HEADLIBRARIAN_ID1)) {
	            Librarian l = new Librarian();
	            l.setAddress(LIBRARIAN_ADDRESS);
	            return l;
	        } else {
	            return null;
	        }
	    });
	}
}