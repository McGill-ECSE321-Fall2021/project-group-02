package ca.mcgill.ecse321.librarysystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;



@ExtendWith(MockitoExtension.class)
public class TestBorrowItemsService {
	
	@Mock
	private PatronRepository patronDao;
	
	@Mock
	private ItemRepository itemDao;
	
	@InjectMocks
	private ItemService service;
	
	private static final int EXISTINGPERSON_ID = 0;
	private static final int NONEXISTINGPERSON_ID = 455;
	
	private static final String testString="tester";
	private static final String wrongString="wrong";
	
	private static final List<Album> album1= null;
	private static final List<Movie> movie1=null;
	private static final List<Book> book1=null;
	
	private static final OnlineAccount account1=null;
	
	private static final boolean bool= false;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(patronDao.findPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			//may have to add online account
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Patron patron= new Patron();
				patron.setAddress(testString);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(testString);
				patron.setFirstName(testString);
				patron.setLastName(testString);
				patron.setBorrowedAlbums(album1);
				patron.setBorrowedMovies(movie1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(account1);
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.findPatronByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(testString)) {
				Patron patron= new Patron();
				patron.setAddress(testString);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(testString);
				patron.setFirstName(testString);
				patron.setLastName(testString);

				patron.setBorrowedAlbums(album1);
				patron.setBorrowedMovies(movie1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(account1);
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.existsPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Patron patron= new Patron();
				patron.setAddress(testString);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(testString);
				patron.setFirstName(testString);
				patron.setLastName(testString);
				patron.setBorrowedAlbums(album1);
				patron.setBorrowedMovies(movie1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(account1);
				return patron!=null;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.existsPatronByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(testString)) {
				Patron patron= new Patron();
				patron.setAddress(testString);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(testString);
				patron.setFirstName(testString);
				patron.setLastName(testString);

				patron.setBorrowedAlbums(album1);
				patron.setBorrowedMovies(movie1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(account1);
				return patron!=null;
			}
			else {
				return null;
			}
			
		});
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        
		lenient().when(patronDao.save(any(Patron.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(itemDao.findItemById(EXISTINGPERSON_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Item item=new Book();
				item.setIsArchived(bool);
				item.setIsBorrowed(bool);
				item.setIsDamaged(bool);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsArchived(bool)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(bool)) {
				Item item=new Book();
				item.setIsArchived(bool);
				item.setIsBorrowed(bool);
				item.setIsDamaged(bool);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsBorrowed(bool)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(bool)) {
				Item item=new Book();
				item.setIsArchived(bool);
				item.setIsBorrowed(bool);
				item.setIsDamaged(bool);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsDamaged(bool)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(bool)) {
				Item item=new Book();
				item.setIsArchived(bool);
				item.setIsBorrowed(bool);
				item.setIsDamaged(bool);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.existsItemById(EXISTINGPERSON_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Item item=new Book();
				item.setIsArchived(bool);
				item.setIsBorrowed(bool);
				item.setIsDamaged(bool);
				return item!=null;
			}
			else {
				return null;
			}
			
		});
        
		lenient().when(itemDao.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
		
		
	}
	
	@Test
	public void testBorrowItemNegativeId() {
		int patronId=0;
		String itemName="The Mockingbird";
		int itemId=-2;
		Item item=null;
		
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The id of the item cannot be negative!",error);
	}
	
	
	
}




















