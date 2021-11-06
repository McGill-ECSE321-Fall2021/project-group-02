package ca.mcgill.ecse321.librarysystem.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.beans.factory.annotation.Autowired;

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
public class TestBorrowItemsService {
	
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
	
	
	private static final int EXISTINGPERSON_ID = 0;
	private static final int NONEXISTINGPERSON_ID = 455;
	
	private static final String TESTSTRING="tester";
	private static final String WRONGSTRING="wrong";
	
	private static final List<Album> ALBUM1= new ArrayList<Album>();
	private static final List<Movie> MOVIE1=new ArrayList<Movie>();
	private static final List<Book> book1=new ArrayList<Book>();
	
	private static final OnlineAccount ACCOUNT1=new OnlineAccount();
	
	private static final boolean BOOL= false;
	
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
			book.setAuthor(TESTSTRING);
			book.setIsArchived(BOOL);
			book.setIsBorrowed(BOOL);
			book.setIsDamaged(BOOL);
			book.setTitle(TESTSTRING);
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
			//may have to add online account
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Patron patron= new Patron();
				patron.setAddress(TESTSTRING);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(TESTSTRING);
				patron.setFirstName(TESTSTRING);
				patron.setLastName(TESTSTRING);
				patron.setBorrowedAlbums(ALBUM1);
				patron.setBorrowedMovies(MOVIE1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(ACCOUNT1);
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.findPatronByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TESTSTRING)) {
				Patron patron= new Patron();
				patron.setAddress(TESTSTRING);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(TESTSTRING);
				patron.setFirstName(TESTSTRING);
				patron.setLastName(TESTSTRING);

				patron.setBorrowedAlbums(ALBUM1);
				patron.setBorrowedMovies(MOVIE1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(ACCOUNT1);
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.existsPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Patron patron= new Patron();
				patron.setAddress(TESTSTRING);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(TESTSTRING);
				patron.setFirstName(TESTSTRING);
				patron.setLastName(TESTSTRING);
				patron.setBorrowedAlbums(ALBUM1);
				patron.setBorrowedMovies(MOVIE1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(ACCOUNT1);
				return patron!=null;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.existsPatronByAddress(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(TESTSTRING)) {
				Patron patron= new Patron();
				patron.setAddress(TESTSTRING);
				patron.setBalance(EXISTINGPERSON_ID);
				patron.setCity(TESTSTRING);
				patron.setFirstName(TESTSTRING);
				patron.setLastName(TESTSTRING);

				patron.setBorrowedAlbums(ALBUM1);
				patron.setBorrowedMovies(MOVIE1);
				patron.setBorrowedBooks(book1);
				patron.setOnlineAccount(ACCOUNT1);
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
		
		lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
		
		
		lenient().when(albumDao.save(any(Album.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(movieDao.save(any(Movie.class))).thenAnswer(returnParameterAsAnswer);
		
		
		lenient().when(onlineAccountDao.save(any(OnlineAccount.class))).thenAnswer(returnParameterAsAnswer);
		
		lenient().when(itemDao.findItemById(EXISTINGPERSON_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				//set item id.
				//
				Item item=new Book();
				item.setIsArchived(BOOL);
				item.setIsBorrowed(BOOL);
				item.setIsDamaged(BOOL);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsArchived(BOOL)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOL)) {
				Item item=new Book();
				item.setIsArchived(BOOL);
				item.setIsBorrowed(BOOL);
				item.setIsDamaged(BOOL);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsBorrowed(BOOL)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOL)) {
				Item item=new Book();
				item.setIsArchived(BOOL);
				item.setIsBorrowed(BOOL);
				item.setIsDamaged(BOOL);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemByIsDamaged(BOOL)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOL)) {
				Item item=new Book();
				item.setIsArchived(BOOL);
				item.setIsBorrowed(BOOL);
				item.setIsDamaged(BOOL);
				return item;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.existsItemById(EXISTINGPERSON_ID)).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(EXISTINGPERSON_ID)) {
				Item item=new Book();
				item.setIsArchived(BOOL);
				item.setIsBorrowed(BOOL);
				item.setIsDamaged(BOOL);
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
	
	@Test
	public void testBorrowItemIdTooLarge() {
		int patronId=0;
		String itemName="The Mockingbird";
		int itemId=900;
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
	public void testBorrowpatronIdTooLarge() {
		int patronId=900;
		String itemName="The Mockingbird";
		int itemId=0;
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
	public void testBorrowItemValid() {
		
		//setting up the book
		Book book=new Book();
		book.setAuthor("Jonathan");
		book.setTitle("The Mockingbird");
		bookDao.save(book);
		itemDao.save(book);
		
		//setting up patron with online account
		OnlineAccount oa=new OnlineAccount();
		Patron pat = new Patron();
	
		oa.setEmail("pat@hotmail.com");
		oa.setUsername("patib");
		oa.setPassword("patpassword");
		
		pat.setAddress("123 Test W");
		pat.setCity("Montreal");
		
		onlineAccountDao.save(oa);
		patronDao.save(pat);
		oa.setUser(pat);
		pat.setOnlineAccount(oa);
		onlineAccountDao.save(oa);
		patronDao.save(pat);
		
		int patronId=pat.getId();
		String itemName="The Mockingbird";
		int itemId=book.getId();
		Item item=null;
		String error=null;
		try {
			//replce by immutable
			item=service.borrowItem(itemId, itemName, patronId);
		}
		catch(IllegalArgumentException e) {
			error=e.getMessage();
		}
		assertNull(item);
		assertEquals("Patron ID does not exist.",error);
	}
	
	
}




















