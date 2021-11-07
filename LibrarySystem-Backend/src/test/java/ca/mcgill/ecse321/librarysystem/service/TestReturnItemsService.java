package ca.mcgill.ecse321.librarysystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

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

import ca.mcgill.ecse321.librarysystem.dao.BookRepository;
import ca.mcgill.ecse321.librarysystem.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.OnlineAccountRepository;
import ca.mcgill.ecse321.librarysystem.dao.PatronRepository;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.HeadLibrarian;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;

/**
 * Testing of return items processes
 * @author Julie
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestReturnItemsService {
	@Mock
	private PatronRepository patronDao;
	
	@Mock 
	private HeadLibrarianRepository headLibrarianDao;
	
	@Mock
	private ItemRepository itemDao;
	
	@Mock
	private BookRepository bookDao;
	
	@Mock
	private OnlineAccountRepository onlineAccountDao;

	@InjectMocks
	private ItemService itemService;
	
	private static final int PATRON_ID = 123456;
	private static final int NOTAPATRON_ID = 234567;
	private static final int HEADLIBRARIAN_ID = 101010;
	private static final int BOOK_ID = 101;
	private static final int NOTABOOK_ID = 102;
	
	@BeforeEach
	public void setMockOutput() { 
		// Patron
		lenient().when(patronDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(PATRON_ID)) {
				return true;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(patronDao.findPatronById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(PATRON_ID)) {
				Patron patron= new Patron();
				patron.setId(PATRON_ID);
				
				List<Book> borrowedBooks = new ArrayList<Book>();
				Book book = new Book();
				book.setId(BOOK_ID);
				borrowedBooks.add(book);
				patron.setBorrowedBooks(borrowedBooks);
				book.setIsBorrowed(true);
				
				return patron;
			}
			else {
				return null;
			}
			
		});
		
		// Head Librarian
		lenient().when(headLibrarianDao.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(HEADLIBRARIAN_ID)) {
				return true;
			}
			else {
				return null;
			}
			
		});
		
		// Item (Book)
		lenient().when(itemDao.existsItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOK_ID)) {
				return true;
			}
			else {
				return null;
			}
			
		});
		
		lenient().when(itemDao.findItemById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(BOOK_ID)) {		
				Patron patron= new Patron();
				patron.setId(PATRON_ID);
				
				List<Book> borrowedBooks = new ArrayList<Book>();
				Book book = new Book();
				book.setId(BOOK_ID);
				borrowedBooks.add(book);
				patron.setBorrowedBooks(borrowedBooks);
				book.setIsBorrowed(true);
				
				return book;
			}
			else {
				return null;
			}
			
		});
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(patronDao.save(any(Patron.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(headLibrarianDao.save(any(HeadLibrarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(itemDao.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	/**
	 * Tests that an item was properly returned into the available library items list
	 */
	public void testReturnItemValid() {
		try {
			Item returnedItem = itemService.returnItem(BOOK_ID, PATRON_ID);
			assertFalse(returnedItem.getIsBorrowed());
		} catch (Exception e){
			fail(e.getMessage());
		}
	}
	
	
	/**
	 * Tests an item with an invalid ID and valid patronID that is returned but throws an error that the item ID does not exist.
	 * The item is still set as borrowed.
	 */
	@Test
	public void testReturnItemInvalidItemID() {
		try {
			itemService.returnItem(NOTABOOK_ID, PATRON_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Item ID does not exist.");
			assertTrue(itemDao.findItemById(BOOK_ID).getIsBorrowed());
		}
	}
	
	/**
	 * Tests an item with an invalid ID and invalid patronID that is returned but throws an error that the patron ID does not exist.
	 * The item is still set as borrowed.
	 */
	@Test
	public void testReturnItemInvalidPatronID() {
		try {
			itemService.returnItem(BOOK_ID, NOTAPATRON_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Patron ID does not exist.");
			assertTrue(itemDao.findItemById(BOOK_ID).getIsBorrowed());
		}
	}
	
	/**
	 * Tests an item with an invalid ID and invalid patronID that is returned but throws an error that the patron ID does not exist.
	 * The item is still set as borrowed.
	 */
	@Test
	public void testReturnItemAllInvalid() {	
		try {
			itemService.returnItem(NOTABOOK_ID, NOTAPATRON_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Patron ID does not exist.");
			assertTrue(itemDao.findItemById(BOOK_ID).getIsBorrowed());
		}
	}
	
	/**
	 * Tests that an item was properly set as damaged.
	 */
	@Test
	public void testDamageItemValid() {
		try {
			Item damagedItem = itemService.setDamagedItem(BOOK_ID, HEADLIBRARIAN_ID);
			assertTrue(damagedItem.getIsDamaged());
		} catch (Exception e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Tests that an item attempted to be set as damaged by a patron throws an error.
	 * The item should not be set as damaged.
	 */
	@Test
	public void testDamageItemInvalidHeadLibrarianID() {
		try {
			itemService.setDamagedItem(BOOK_ID, PATRON_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Must be a head librarian to proceed.");
			assertFalse(itemDao.findItemById(BOOK_ID).getIsDamaged());
		}
	}
	
	/**
	 * Tests that an item with a non-existing itemID attempted to be set as damaged by a head librarian throws an error.
	 */
	@Test
	public void testDamageItemInvalidItemID() {
		try {
			itemService.setDamagedItem(NOTABOOK_ID, HEADLIBRARIAN_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Item ID does not exist.");
			assertFalse(itemDao.findItemById(BOOK_ID).getIsDamaged());
		}
	}
	/**
	 * Tests that an item was properly deleted from the library's item repository.
	 */
	@Test
	public void testDiscardItemValid() {
		try {
			itemService.discardItem(BOOK_ID);
			assertFalse(itemDao.existsById(BOOK_ID));
		} catch (Exception e){
			fail(e.getMessage());
		}
	}
	
	/**
	 * Tests that an item with an invalidID attempted to be deleted throws an error and is not discarded.
	 */
	@Test
	public void testDiscardItemInvalidItemID() {			
		try {
			itemService.discardItem(NOTABOOK_ID);
		} catch (Exception e){
			String error = e.getMessage();
			assertEquals(error, "Item ID does not exist.");
		}
	}
}
