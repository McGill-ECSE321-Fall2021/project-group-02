package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Library;
import ca.mcgill.ecse321.librarysystem.model.LibrarySoftwareSystem;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.OnlineAccount;
import ca.mcgill.ecse321.librarysystem.model.Patron;
import ca.mcgill.ecse321.librarysystem.model.Person;
import ca.mcgill.ecse321.librarysystem.model.UserEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestBookPersistence {
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private PatronRepository patronRepository;
	@Autowired
	private PersonRepository personRepository;
	
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
		itemRepository.deleteAll();
		patronRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadBook() {
		
		String title = "TestTitle";
		String author = "TestAuthor";
		Book testBook = new Book();
		Patron patron = new Patron();
		Person person = new Person();
		
		
		testBook.setPatron(patron);
		testBook.setAuthor(author);
		testBook.setTitle(title);
		patron.setPerson(person);
		List<UserEntity> patrons = new ArrayList<UserEntity>();
		patrons.add(patron);
		
		personRepository.save(person);
		patronRepository.save(patron);
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
