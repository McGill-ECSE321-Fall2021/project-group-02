package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Book;

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
		
		String title = "TestTitle";
		String author = "TestAuthor";
		Book testBook = new Book();
		
		testBook.setAuthor(author);
		testBook.setTitle(title);
		
		bookRepository.save(testBook);
		itemRepository.save(testBook);
		int id = testBook.getId();
		
		testBook = null;
		
		testBook = bookRepository.findBookByTitleAndAuthor(title, author);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		
		testBook = bookRepository.findBookById(id);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		
		testBook = (Book) itemRepository.findItemById(id);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
	}
}
