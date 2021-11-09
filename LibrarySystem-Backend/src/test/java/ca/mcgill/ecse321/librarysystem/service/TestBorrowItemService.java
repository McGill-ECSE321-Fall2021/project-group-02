package ca.mcgill.ecse321.librarysystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.librarysystem.dao.AlbumRepository;
import ca.mcgill.ecse321.librarysystem.dao.BookRepository;
import ca.mcgill.ecse321.librarysystem.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.JournalRepository;
import ca.mcgill.ecse321.librarysystem.dao.MovieRepository;
import ca.mcgill.ecse321.librarysystem.dao.NewspaperRepository;
import ca.mcgill.ecse321.librarysystem.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;



@ExtendWith(MockitoExtension.class)
public class TestBorrowItemService {
	
	@Mock
	ItemRepository itemDao;
	@Mock
	AlbumRepository albumDao;
	@Mock
	BookRepository bookDao;
	@Mock
	MovieRepository movieDao;
	@Mock
	JournalRepository journalDao;
	@Mock
	NewspaperRepository newspaperDao;
	@Mock
	HeadLibrarianRepository headLibrarianDao;
	@Mock
	PatronRepository patronDao;
	
	@Mock
	private OnlineAccountRepository onlineAccountDao;
	
	@InjectMocks
	private ItemService service;
	
	
	private static final int EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID = 0;
	private static final int EXISTINGBOOK_ARCHIVED_ID = 1;
	private static final int NONEXISTINGBOOK_ID = 455;
	private static final int EXISTINGBOOK_NONARCHIVED_BORROWED_ID = 2;
	private static final int NONEXISTINGBOOK_NEGATIVE_ID = -1;
	
	private static final int EXISTINGPATRON_ID=0;
	private static final int NONEXISTINGPATRON_LARGE_ID = 455;
	private static final int NONEXISTINGPATRON_NEGATIVE_ID = -1;
	
	private static final String EXISTINGBOOK_TITLE="Mockingbird";
	private static final String NONEXISTINGBOOK_TITLE="lala";
	private static final String NULLBOOK_TITLE=null;
	
	private static final String TESTSTRING="tester";
	
	private static final List<Album> ALBUM1= new ArrayList<Album>();
	private static final List<Movie> MOVIE1=new ArrayList<Movie>();
	private static final List<Book> BOOK1=new ArrayList<Book>();
	
	
	private static final boolean BOOL= false;
	
	/**
	 * Creates mock functions to prevent our tests from accessing the database.
	 * We only test books in findItemById() and existsItemById
	 * @author Sami
	 * 
	 * @return void
	 */
	@BeforeEach
	public void setMockOutput() {
		
		
		lenient().when(albumDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Album> tempList = new ArrayList<>();
			Album album = new Album();
			album.setArtist(TESTSTRING);
			album.setIsArchived(BOOL);
			album.setIsBorrowed(BOOL);
			album.setIsDamaged(BOOL);
			album.setTitle(TESTSTRING);
			tempList.add(album);
			return tempList;
			
		});
		
		lenient().when(bookDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Book> tempList = new ArrayList<>();
			Book book = new Book();
			book.setTitle(EXISTINGBOOK_TITLE);
			tempList.add(book);
			return tempList;
			
		});
		
		lenient().when(movieDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Movie> tempList = new ArrayList<>();
			Movie movie = new Movie();
			movie.setDirector(TESTSTRING);
			movie.setIsArchived(BOOL);
			movie.setIsBorrowed(BOOL);
			movie.setIsDamaged(BOOL);
			movie.setTitle(TESTSTRING);
			tempList.add(movie);
			return tempList;
			
		});
		
		lenient().when(patronDao.findPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			
			if(invocation.getArgument(0).equals(EXISTINGPATRON_ID)) {
				Patron patron= new Patron();
				patron.setId(EXISTINGPATRON_ID);
				patron.setBorrowedAlbums(ALBUM1);
				patron.setBorrowedBooks(BOOK1);
				patron.setBorrowedMovies(MOVIE1);
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		
		lenient().when(patronDao.existsPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPATRON_ID)) {
				return true;
			}
			if(invocation.getArgument(0).equals(NONEXISTINGPATRON_LARGE_ID)) {
				return false;
			}
			else {
				return null;
			}
			
		});
		
		
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        
		lenient().when(patronDao.save(any(Patron.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
		
		
		lenient().when(albumDao.save(any(Album.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(movieDao.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
		
		
		lenient().when(onlineAccountDao.save(any(OnlineAccount.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(itemDao.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID)) {
				Book book = new Book();
				book.setId(EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID);
				book.setTitle(EXISTINGBOOK_TITLE);
				return book;
			}
			if(invocation.getArgument(0).equals(EXISTINGBOOK_ARCHIVED_ID)) {
				Book book = new Book();
				book.setId(EXISTINGBOOK_ARCHIVED_ID);
				book.setTitle(EXISTINGBOOK_TITLE);
				book.setIsArchived(true);
				return book;
			}
			if(invocation.getArgument(0).equals(EXISTINGBOOK_NONARCHIVED_BORROWED_ID)) {
				Book book = new Book();
				book.setId(EXISTINGBOOK_NONARCHIVED_BORROWED_ID);
				book.setTitle(EXISTINGBOOK_TITLE);
				book.setIsBorrowed(true);
				return book;
			}
			
			else {
				return null;
			}
			
		});
		
		
		lenient().when(itemDao.existsItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID)) {
				return true;
			}
			if(invocation.getArgument(0).equals(EXISTINGBOOK_ARCHIVED_ID)) {
				return true;
			}
			if(invocation.getArgument(0).equals(EXISTINGBOOK_NONARCHIVED_BORROWED_ID)) {
				return true;
			}
			if(invocation.getArgument(0).equals(NONEXISTINGBOOK_ID)) {
				return false;
			}
			else {
				return null;
			}
			
		});
        
		lenient().when(itemDao.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
		
		
	}
	
	/**
	 * In this section we test our business method borrowItem in the ItemService class.
	 * 
	 * We first make sure that the method works with valid inputs.
	 * We then enter an invalid itemId, patronId, and bookTitle respectively in the next three test methods.
	 * In the last 2 test methods, we just make sure that the archivedItem and alreadyBorrowedItem errors are raised.
	 * @author Sami
	 */
	
	@Test
	public void testBorrowItemsValid() {
		
		int itemId=EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID;
		String itemName= EXISTINGBOOK_TITLE;
		int patronId=EXISTINGPATRON_ID;
		Item item=null;
		String error=null;
		try {
			
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNotNull(item);
		assertNull(error);
	}
	
	@Test
	public void testBorrowItemsInvalidItemId() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=NONEXISTINGBOOK_ID;
		Item item=null;
		
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The item you are looking for does not exist.",error);
	}
	
	@Test
	public void testBorrowItemsNegativeItemId() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=NONEXISTINGBOOK_NEGATIVE_ID;
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
	
	@Test
	public void testBorrowItemsInvalidPatronId() {
		int patronId=NONEXISTINGPATRON_LARGE_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("Patron ID does not exist.",error);
	}
	
	@Test
	public void testBorrowItemsNegativePatronId() {
		int patronId=NONEXISTINGPATRON_NEGATIVE_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The id of the patron cannot be negative!",error);
	}
	
	@Test
	public void testBorrowItemsInvalidBookTitle() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=NONEXISTINGBOOK_TITLE;
		int itemId=EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The item you are looking for does not exist.",error);
	}
	
	@Test
	public void testBorrowItemsNullBookTitle() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=NULLBOOK_TITLE;
		int itemId=EXISTINGBOOK_NONARCHIVED_NONBORROWED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The name of the item cannot be empty",error);
	}
	
	@Test
	public void testBorrowItemsArchived() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=EXISTINGBOOK_ARCHIVED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The item you are looking for is in the archives",error);
	}
	
	@Test
	public void testBorrowItemsAlreadyBorrowedItem() {
		int patronId=EXISTINGPATRON_ID;
		String itemName=EXISTINGBOOK_TITLE;
		int itemId=EXISTINGBOOK_NONARCHIVED_BORROWED_ID;
		Item item=null;
		String error=null;
		try {
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("The item you are looking for has already been borrowed.",error);
	}
	
}

