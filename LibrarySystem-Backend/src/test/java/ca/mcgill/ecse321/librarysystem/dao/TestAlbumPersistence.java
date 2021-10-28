package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAlbumPersistence {
@Autowired
private AlbumRepository albumRepository;
@Autowired
private LibraryRepository libraryRepository;
@Autowired
private PatronRepository patronRepository;
@Autowired
private PersonRepository personRepository;
@AfterEach
public void clearDatabase() {
	albumRepository.deleteAll();
	libraryRepository.deleteAll();
	patronRepository.deleteAll();
	personRepository.deleteAll();
}
	@Test
	public void testPersistAndLoadAlbum() {
		Album a = new Album();
		String title = "testTitle";
		String artist = "testArtist";
		a.setArtist(artist);
		a.setTitle(title);
		a.setIsArchived(false);
		a.setIsBorrowed(false);
		a.setIsDamaged(false);
		
		Person person = new Person();
		Patron patron = new Patron();
		patron.setPerson(person);
		a.setPatron(patron);
		
		personRepository.save(person);
		patronRepository.save(patron);
		albumRepository.save(a);
		int id = a.getId();
		
		a = null;
		a = albumRepository.findAlbumById(id);
		assertNotNull(a);
		assertEquals(title,a.getTitle());
		assertEquals(artist,a.getArtist());
		assertEquals(id,a.getId());
		
		a = null;
		a = albumRepository.findAlbumByTitleAndArtist(title, artist);
		assertNotNull(a);
		assertEquals(title,a.getTitle());
		assertEquals(artist,a.getArtist());
		assertEquals(id,a.getId());
	}
}
