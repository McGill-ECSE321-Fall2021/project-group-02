package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookPersistence {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
		itemRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadBook() {
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
//		Library library = new Library(startTime, endTime);
//		LibrarySoftwareSystem ls = new LibrarySoftwareSystem(library);
		String title = "TestTitle";
		String author = "TestAuthor";
		Integer id = 1;
		Book testBook = new Book();
		
		testBook.setAuthor(author);
		testBook.setTitle(title);
		testBook.setId(id);
		bookRepository.save(testBook);
		itemRepository.save(testBook);
		
		testBook = null;
		
		testBook = bookRepository.findBookById(id);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		assertEquals(id, testBook.getId());
		
		testBook = null;
		
		testBook = bookRepository.findBookByTitleAndAuthor(title, author);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		assertEquals(id, testBook.getId());
		
		testBook = null;
		
		testBook = (Book) itemRepository.findItemById(id);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		assertEquals(id, testBook.getId());
	}

}
