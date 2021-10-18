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
import ca.mcgill.ecse321.librarysystem.model.User;

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
	private AlbumRepository albumRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LibrarySystemRepository librarySystemRepository;
	
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
		itemRepository.deleteAll();
		patronRepository.deleteAll();
		albumRepository.deleteAll();
		movieRepository.deleteAll();
		onlineAccountRepository.deleteAll();
		personRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadBook() {
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
//		Library library = new Library();
		
//		LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
////		
		String title = "TestTitle";
		String author = "TestAuthor";
		Book testBook = new Book();
		boolean isArchived = false;
		boolean isDamaged = false;
		boolean isBorrowd = false;
//		
//		String albumTitle = "TestAlbumTitle";
//		String albumArtist = "TestAlbumArtist";
//		int idAlbum = 2;
//		Album album = new Album();
//		
//		String movieTitle = "TestMovieTitle";
//		String movieDirector = "TestMovieDirector";
//		int idMovie = 3;
//		Movie movie = new Movie();
//		
//		String firstName = "TestFirstName";
//		String lastName = "TestLastName";
//		String address = "TestAddress";
//		String city = "TestCity";
//		String email = "TestEmail";
//		String password = "TestPassword";
//		String username = "TestUsername";
//		
//		List<Person> personList = new ArrayList<>();
//		List<User> userList = new ArrayList<>();
//		List<Album> albumList = new ArrayList<>();
//		List<Book> bookList = new ArrayList<>();
//		List<Movie> movieList = new ArrayList<>();
//		
//		Person person = new Person();
//		person.setFirstName(firstName);
//		person.setLastName(lastName);
//		person.setId(0);
//		person.setLibrarySoftwareSystem(ls);
//		personList.add(person);
//		
//		User user = new User();
//		user.setAddress(address);
//		user.setCity(city);
//		user.setPerson(person);
//		user.setId(0);
//		user.setLibrarySoftwareSystem(ls);
//		
//		userList.add(user);
//		
//		OnlineAccount account = new OnlineAccount();
//		account.setEmail(email);
//		account.setLibrarySoftwareSystem(ls);
//		account.setPassword(password);
//		account.setUser(user);
//		account.setUsername(username);
//		
//		user.setOnlineAccount(account);
//		
//		person.setUser(userList);
//		
		testBook.setAuthor(author);
		testBook.setTitle(title);
//		testBook.setIsArchived(isArchived);
//		testBook.setIsBorrowed(isBorrowd);
//		testBook.setIsDamaged(isDamaged);
//		
//		album.setArtist(albumArtist);
//		album.setId(idAlbum);
//		album.setIsArchived(isArchived);
//		album.setIsBorrowed(isBorrowd);
//		album.setIsDamaged(isDamaged);
//		album.setLibrarySoftwareSystem(ls);
//		album.setTitle(albumTitle);
//		albumList.add(album);
//		
//		movie.setDirector(movieDirector);
//		movie.setId(idMovie);
//		movie.setIsArchived(isArchived);
//		movie.setIsBorrowed(isBorrowd);
//		movie.setIsDamaged(isDamaged);
//		movie.setLibrarySoftwareSystem(ls);
//		movie.setTitle(movieTitle);
//		movieList.add(movie);
//		
//		Patron p = new Patron();
//		p.setAddress(address);
//		p.setCity(city);
//		p.setId(0);
//		p.setLibrarySoftwareSystem(ls);
//		p.setOnlineAccount(account);
//		p.setPerson(person);
//		p.setBorrowedAlbum(albumList);
//		p.setBorrowedBook(bookList);
//		p.setBorrowedMovie(movieList);
//		
//		testBook.setPatron(p);
//		album.setPatron(p);
//		movie.setPatron(p);
		
		bookRepository.save(testBook);
//		movieRepository.save(movie);
//		albumRepository.save(album);
//		patronRepository.save(p);
//		personRepository.save(person);
//		userRepository.save(user);
//		onlineAccountRepository.save(account);
		
//		testBook = null;
		
//		testBook = bookRepository.findBookById(id);
//		assertNotNull(testBook);
//		assertEquals(title, testBook.getTitle());
//		assertEquals(author, testBook.getAuthor());
//		assertEquals(id, testBook.getId());
		
		testBook = null;
		
		testBook = bookRepository.findBookByTitleAndAuthor(title, author);
		assertNotNull(testBook);
		assertEquals(title, testBook.getTitle());
		assertEquals(author, testBook.getAuthor());
		
	}

}
