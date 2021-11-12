package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import ca.mcgill.ecse321.librarysystem.model.Journal;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspaper;

/**
 * Testing the functionality of viewing library contents
 * 
 * @author Niilo
 */

@ExtendWith(MockitoExtension.class)
public class TestViewLibraryContentsService {
	
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
	
	private static final String TEST_BOOK_TITLE = "b1";
	private static final int BOOK_ID = 1;
	private static final String TEST_ALBUM_TITLE = "a1";
	private static final int ALBUM_ID = 2;
	private static final String TEST_MOVIE_TITLE = "m1";
	private static final int MOVIE_ID = 3;
	private static final String TEST_NEWSPAPER_TITLE = "n1";
	private static final int NEWSPAPER_ID = 4;
	private static final String TEST_JOURNAL_TITLE = "j1";
	private static final int JOURNAL_ID = 5;
	
	private static final int NONEXISTING_ID1 = -1;
	private static final int NONEXISTING_ID2 = 6;
	private static final int NONEXISTING_ID3 = 1000;
	
	private static final String NONEXISTING_BOOK_TITLE = "b0";
	private static final String NONEXISTING_ALBUM_TITLE = "a0";
	private static final String NONEXISTING_MOVIE_TITLE = "m0";
	private static final String NONEXISTING_TEST_STRING = "not a tester";
	
	private static final boolean BOOL = false;
	
	private static final String TEST_STRING="tester";
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(itemDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			ArrayList<Item> tempList = new ArrayList<>();
			Album album = new Album();
			album.setArtist(TEST_STRING);
			album.setId(ALBUM_ID);
			album.setIsArchived(BOOL);
			album.setIsBorrowed(BOOL);
			album.setIsDamaged(BOOL);
			album.setTitle(TEST_ALBUM_TITLE);
			tempList.add(album);
			
			Book book = new Book();
			book.setAuthor(TEST_STRING);
			book.setId(BOOK_ID);
			book.setIsArchived(BOOL);
			book.setIsBorrowed(BOOL);
			book.setIsDamaged(BOOL);
			book.setTitle(TEST_BOOK_TITLE);
			tempList.add(book);
			
			Movie movie = new Movie();
			movie.setDirector(TEST_STRING);
			movie.setId(MOVIE_ID);
			movie.setIsArchived(BOOL);
			movie.setIsBorrowed(BOOL);
			movie.setIsDamaged(BOOL);
			movie.setTitle(TEST_MOVIE_TITLE);
			tempList.add(movie);
			
			Newspaper newspaper = new Newspaper();
			newspaper.setId(NEWSPAPER_ID);
			newspaper.setIsArchived(BOOL);
			newspaper.setIsBorrowed(BOOL);
			newspaper.setIsDamaged(BOOL);
			newspaper.setName(TEST_NEWSPAPER_TITLE);
			tempList.add(newspaper);
			
			Journal journal = new Journal();
			journal.setId(JOURNAL_ID);
			journal.setIsArchived(BOOL);
			journal.setIsBorrowed(BOOL);
			journal.setIsDamaged(BOOL);
			journal.setName(TEST_JOURNAL_TITLE);
			tempList.add(journal);
			
			return tempList;
		});
		
		lenient().when(itemDao.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(BOOK_ID)) {
				Book book = new Book();
				book.setAuthor(TEST_STRING);
				book.setId(BOOK_ID);
				book.setIsArchived(BOOL);
				book.setIsBorrowed(BOOL);
				book.setIsDamaged(BOOL);
				book.setTitle(TEST_BOOK_TITLE);
				return book;
			} else if(invocation.getArgument(0).equals(ALBUM_ID)) {
				Album album = new Album();
				album.setArtist(TEST_STRING);
				album.setId(ALBUM_ID);
				album.setIsArchived(BOOL);
				album.setIsBorrowed(BOOL);
				album.setIsDamaged(BOOL);
				album.setTitle(TEST_ALBUM_TITLE);
				return album;
			} else if(invocation.getArgument(0).equals(MOVIE_ID)) {
				Movie movie = new Movie();
				movie.setDirector(TEST_STRING);
				movie.setId(MOVIE_ID);
				movie.setIsArchived(BOOL);
				movie.setIsBorrowed(BOOL);
				movie.setIsDamaged(BOOL);
				movie.setTitle(TEST_MOVIE_TITLE);
				return movie;
			} else if(invocation.getArgument(0).equals(NEWSPAPER_ID)) {
				Newspaper newspaper = new Newspaper();
				newspaper.setId(NEWSPAPER_ID);
				newspaper.setIsArchived(BOOL);
				newspaper.setIsBorrowed(BOOL);
				newspaper.setIsDamaged(BOOL);
				newspaper.setName(TEST_NEWSPAPER_TITLE);
				return newspaper;
			} else if(invocation.getArgument(0).equals(JOURNAL_ID)) {
				Journal journal = new Journal();
				journal.setId(JOURNAL_ID);
				journal.setIsArchived(BOOL);
				journal.setIsBorrowed(BOOL);
				journal.setIsDamaged(BOOL);
				journal.setName(TEST_JOURNAL_TITLE);
				return journal;
			} else {
				return null;
			}
			
		});
		
		lenient().when(bookDao.findBookById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(BOOK_ID)) {
				Book book = new Book();
				book.setAuthor(TEST_STRING);
				book.setId(BOOK_ID);
				book.setIsArchived(BOOL);
				book.setIsBorrowed(BOOL);
				book.setIsDamaged(BOOL);
				book.setTitle(TEST_BOOK_TITLE);
				return book;
			} else {
				return null;
			}
		});
		
		lenient().when(bookDao.findBookByTitle(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_BOOK_TITLE)) {
				Book book = new Book();
				book.setAuthor(TEST_STRING);
				book.setId(BOOK_ID);
				book.setIsArchived(BOOL);
				book.setIsBorrowed(BOOL);
				book.setIsDamaged(BOOL);
				book.setTitle(TEST_BOOK_TITLE);
				ArrayList<Book> a = new ArrayList<>();
				a.add(book);
				return a;
			} else {
				return new ArrayList<Book>();
			}
		});
		
		lenient().when(bookDao.findBookByAuthor(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_STRING)) {
				Book book = new Book();
				book.setAuthor(TEST_STRING);
				book.setId(BOOK_ID);
				book.setIsArchived(BOOL);
				book.setIsBorrowed(BOOL);
				book.setIsDamaged(BOOL);
				book.setTitle(TEST_BOOK_TITLE);
				ArrayList<Book> a = new ArrayList<>();
				a.add(book);
				return a;
			} else {
				return new ArrayList<Book>();
			}
		});
		
		lenient().when(albumDao.findAlbumById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(ALBUM_ID)) {
				Album album = new Album();
				album.setArtist(TEST_STRING);
				album.setId(ALBUM_ID);
				album.setIsArchived(BOOL);
				album.setIsBorrowed(BOOL);
				album.setIsDamaged(BOOL);
				album.setTitle(TEST_ALBUM_TITLE);
				return album;
			} else {
				return null;
			}
		});
		
		lenient().when(albumDao.findAlbumByTitle(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_ALBUM_TITLE)) {
				Album album = new Album();
				album.setArtist(TEST_STRING);
				album.setId(ALBUM_ID);
				album.setIsArchived(BOOL);
				album.setIsBorrowed(BOOL);
				album.setIsDamaged(BOOL);
				album.setTitle(TEST_ALBUM_TITLE);
				ArrayList<Album> a = new ArrayList<>();
				a.add(album);
				return a;
			} else {
				return new ArrayList<Album>();
			}
		});
		
		lenient().when(albumDao.findAlbumByArtist(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_STRING)) {
				Album album = new Album();
				album.setArtist(TEST_STRING);
				album.setId(ALBUM_ID);
				album.setIsArchived(BOOL);
				album.setIsBorrowed(BOOL);
				album.setIsDamaged(BOOL);
				album.setTitle(TEST_ALBUM_TITLE);
				ArrayList<Album> a = new ArrayList<>();
				a.add(album);
				return a;
			} else {
				return new ArrayList<Album>();
			}
		});
		
		lenient().when(movieDao.findMovieById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(MOVIE_ID)) {
				Movie movie = new Movie();
				movie.setDirector(TEST_STRING);
				movie.setId(MOVIE_ID);
				movie.setIsArchived(BOOL);
				movie.setIsBorrowed(BOOL);
				movie.setIsDamaged(BOOL);
				movie.setTitle(TEST_MOVIE_TITLE);
				return movie;
			} else {
				return null;
			}
		});
		
		lenient().when(movieDao.findMovieByTitle(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_MOVIE_TITLE)) {
				Movie movie = new Movie();
				movie.setDirector(TEST_STRING);
				movie.setId(MOVIE_ID);
				movie.setIsArchived(BOOL);
				movie.setIsBorrowed(BOOL);
				movie.setIsDamaged(BOOL);
				movie.setTitle(TEST_MOVIE_TITLE);
				ArrayList<Movie> a = new ArrayList<>();
				a.add(movie);
				return a;
			} else {
				return new ArrayList<Movie>();
			}
		});
		
		lenient().when(movieDao.findMovieByDirector(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_STRING)) {
				Movie movie = new Movie();
				movie.setDirector(TEST_STRING);
				movie.setId(MOVIE_ID);
				movie.setIsArchived(BOOL);
				movie.setIsBorrowed(BOOL);
				movie.setIsDamaged(BOOL);
				movie.setTitle(TEST_MOVIE_TITLE);
				ArrayList<Movie> a = new ArrayList<>();
				a.add(movie);
				return a;
			} else {
				return new ArrayList<Movie>();
			}
		});
		
		lenient().when(newspaperDao.findNewspaperById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(NEWSPAPER_ID)) {
				Newspaper newspaper = new Newspaper();
				newspaper.setId(NEWSPAPER_ID);
				newspaper.setIsArchived(BOOL);
				newspaper.setIsBorrowed(BOOL);
				newspaper.setIsDamaged(BOOL);
				newspaper.setName(TEST_NEWSPAPER_TITLE);
				return newspaper;
			} else {
				return null;
			}
		});
		
		lenient().when(newspaperDao.findNewspaperByName(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_NEWSPAPER_TITLE)) {
				Newspaper newspaper = new Newspaper();
				newspaper.setId(NEWSPAPER_ID);
				newspaper.setIsArchived(BOOL);
				newspaper.setIsBorrowed(BOOL);
				newspaper.setIsDamaged(BOOL);
				newspaper.setName(TEST_NEWSPAPER_TITLE);
				ArrayList<Newspaper> a = new ArrayList<>();
				a.add(newspaper);
				return a;
			} else {
				return new ArrayList<Newspaper>();
			}
		});
		
		lenient().when(journalDao.findJournalById(anyInt())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(JOURNAL_ID)) {
				Journal journal = new Journal();
				journal.setId(JOURNAL_ID);
				journal.setIsArchived(BOOL);
				journal.setIsBorrowed(BOOL);
				journal.setIsDamaged(BOOL);
				journal.setName(TEST_JOURNAL_TITLE);
				return journal;
			} else {
				return null;
			}
		});
		
		lenient().when(journalDao.findJournalByName(anyString())).thenAnswer((InvocationOnMock invocation) ->{
			if(invocation.getArgument(0).equals(TEST_JOURNAL_TITLE)) {
				Journal journal = new Journal();
				journal.setId(JOURNAL_ID);
				journal.setIsArchived(BOOL);
				journal.setIsBorrowed(BOOL);
				journal.setIsDamaged(BOOL);
				journal.setName(TEST_JOURNAL_TITLE);
				ArrayList<Journal> a = new ArrayList<>();
				a.add(journal);
				return a;
			} else {
				return new ArrayList<Journal>();
			}
		});
		
	}
	
	/* UNIT TESTS FOR VIEW LIBRARY CONTENTS METHODS */
	
	// Books Repository:
	@Test
	public void testGetBookByTitleValid() {
		ArrayList<Book> b = (ArrayList<Book>) service.getBooksByTitle(TEST_BOOK_TITLE);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_BOOK_TITLE, b.get(0).getTitle());
	}
	@Test
	public void testGetBookByTitleInvalid() {
		ArrayList<Book> b = (ArrayList<Book>) service.getBooksByTitle(NONEXISTING_BOOK_TITLE);
		
		assertEquals(b.size(), 0);
	}
	@Test
	public void testGetBookByAuthorValid() {
		ArrayList<Book> b = (ArrayList<Book>) service.getBooksByAuthor(TEST_STRING);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_STRING, b.get(0).getAuthor());
	}
	@Test
	public void testGetBookByAuthorInvalid() {
		ArrayList<Book> b = (ArrayList<Book>) service.getBooksByAuthor(NONEXISTING_TEST_STRING);
		
		assertEquals(b.size(), 0);
	}
	
	// Albums Repository:
	@Test
	public void testGetAlbumByTitleValid() {
		ArrayList<Album> b = (ArrayList<Album>) service.getAlbumsByTitle(TEST_ALBUM_TITLE);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_ALBUM_TITLE, b.get(0).getTitle());
	}
	@Test
	public void testGetAlbumByTitleInvalid() {
		ArrayList<Album> b = (ArrayList<Album>) service.getAlbumsByTitle(NONEXISTING_ALBUM_TITLE);
		
		assertEquals(b.size(), 0);
	}
	@Test
	public void testGetAlbumByArtistValid() {
		ArrayList<Album> b = (ArrayList<Album>) service.getAlbumsByArtist(TEST_STRING);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_STRING, b.get(0).getArtist());
	}
	@Test
	public void testGetAlbumByArtistInvalid() {
		ArrayList<Album> b = (ArrayList<Album>) service.getAlbumsByArtist(NONEXISTING_TEST_STRING);
		
		assertEquals(b.size(), 0);
	}
	
	// Movies Repository:
	@Test
	public void testGetMovieByTitleValid() {
		ArrayList<Movie> b = (ArrayList<Movie>) service.getMoviesByTitle(TEST_MOVIE_TITLE);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_MOVIE_TITLE, b.get(0).getTitle());
	}
	@Test
	public void testGetMovieByTitleInvalid() {
		ArrayList<Movie> b = (ArrayList<Movie>) service.getMoviesByTitle(NONEXISTING_MOVIE_TITLE);
		
		assertEquals(b.size(), 0);
	}
	@Test
	public void testGetMovieByDirectorValid() {
		ArrayList<Movie> b = (ArrayList<Movie>) service.getMoviesByDirector(TEST_STRING);
		
		assertNotNull(b.get(0));
		assertEquals(TEST_STRING, b.get(0).getDirector());
	}
	@Test
	public void testGetMovieByDirectorInvalid() {
		ArrayList<Movie> b = (ArrayList<Movie>) service.getMoviesByDirector(NONEXISTING_TEST_STRING);
		
		assertEquals(b.size(), 0);
	}
	
	// Items Repository:
	@Test
	public void testGetAllItems() {
		ArrayList<Item> l = (ArrayList<Item>) service.getAllItems();
		assertNotNull(l);
	}
	@Test
	public void testGetItemByIdInvalid() {
		Item i = service.getItemByID(NONEXISTING_ID1);
		assertNull(i);
		
		i = service.getItemByID(NONEXISTING_ID2);
		assertNull(i);
		
		i = service.getItemByID(NONEXISTING_ID3);
		assertNull(i);
	}
	
	@Test
	public void testGetItemByIdValid() {
		Item i = service.getItemByID(ALBUM_ID);
		assertNotNull(i);
		assertEquals(i.getClass(), Album.class);
		
		i = service.getItemByID(BOOK_ID);
		assertNotNull(i);
		assertEquals(i.getClass(), Book.class);
		
		i = service.getItemByID(MOVIE_ID);
		assertNotNull(i);
		assertEquals(i.getClass(), Movie.class);
		
		i = service.getItemByID(NEWSPAPER_ID);
		assertNotNull(i);
		assertEquals(i.getClass(), Newspaper.class);
		
		i = service.getItemByID(JOURNAL_ID);
		assertNotNull(i);
		assertEquals(i.getClass(), Journal.class);
	}

}