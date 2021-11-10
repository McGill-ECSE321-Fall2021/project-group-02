package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.sql.Date;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestNewsPaperPersistence {
	@Autowired
	private NewspaperRepository newspaperRepository;

	@AfterEach
	public void clearDatabase() {
		newspaperRepository.deleteAll();
	}
	@Test
	public void testPersistAndLoadNewspaper() {

		Newspaper np = new Newspaper();
		String name = "testNewspaper";
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, 10, 17));
		np.setDate(date);
		np.setName(name);
		
		newspaperRepository.save(np);
		int id = np.getId();
		
		np = null;
		np = newspaperRepository.findNewspaperByNameAndDate(name, date);
		assertNotNull(np);
		assertEquals(date,np.getDate());
		assertEquals(name,np.getName());
		assertEquals(id, np.getId());
		
		np = newspaperRepository.findNewspaperById(id);
		assertNotNull(np);
		assertEquals(date,np.getDate());
		assertEquals(name,np.getName());
		assertEquals(id, np.getId());
	}
}
