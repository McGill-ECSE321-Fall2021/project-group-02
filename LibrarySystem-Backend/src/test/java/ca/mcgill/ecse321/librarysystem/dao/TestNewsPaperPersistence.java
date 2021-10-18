package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
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
public class TestNewsPaperPersistence {
	@Autowired
	private NewspaperRepository newspaperRepository;
	@AfterEach
	public void clearDatabase() {
		newspaperRepository.deleteAll();
	}
	@Test
	public void testPersistAndLoadNewspaper() {
		Library l = new Library();
		LibrarySoftwareSystem ls = new LibrarySoftwareSystem();
		ls.setOpeningHours(l);
		Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		l.setOpeningHour(startTime);
		l.setClosingHour(endTime);
		l.setLibrarySoftwareSystem(ls);
		Newspaper np = new Newspaper();
		String name = "testNewspaper";
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, 10, 17));
		np.setDate(date);
		np.setName(name);
		np.setId(3);
		np.setLibrarySoftwareSystem(ls);
		newspaperRepository.save(np);
		
		np = null;
		np = newspaperRepository.findNewspaperByNameAndDate(name, date);
		assertNotNull(np);
		assertEquals(date,np.getDate());
		assertEquals(name,np.getName());
		assertEquals(3,np.getId());
	}
}
