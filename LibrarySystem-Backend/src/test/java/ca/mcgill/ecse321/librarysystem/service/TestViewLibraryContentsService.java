package ca.mcgill.ecse321.librarysystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
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

	}
	
	// UNIT TESTS FOR VIEW LIBRARY CONTENTS METHODS
	
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