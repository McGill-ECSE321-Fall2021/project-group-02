package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibrarySystemPersistence {
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private DailyScheduleRepository dailyScheduleRepository;
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private LibrarySystemRepository librarySystemRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private NewspaperRepository newspaperRepository;
	@Autowired
	private OnlineAccountRepository onlineAccountRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WeeklyScheduleRepository weeklyScheduleRepository;
	
@AfterEach
public void clearDatabase() {
	albumRepository.deleteAll();
	bookRepository.deleteAll();
	dailyScheduleRepository.deleteAll();
	headLibrarianRepository.deleteAll();
	itemRepository.deleteAll();
	journalRepository.deleteAll();
	movieRepository.deleteAll();
	newspaperRepository.deleteAll();
	onlineAccountRepository.deleteAll();
	personRepository.deleteAll();
	userRepository.deleteAll();
	weeklyScheduleRepository.deleteAll();
}
@Test
public void testPersistAndLoadLibary() {
	Library l = new Library(null, null);
	Time startTime = java.sql.Time.valueOf(LocalTime.of(8, 00));
	Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
	l.setOpeningHour(startTime);
	l.setClosingHour(endTime);
	libraryRepository.save(l);
	Library r = libraryRepository.findLibraryByClosingHour(endTime);
	assertNotNull(r);
	assertEquals(startTime,r.getOpeningHour());
	
	Library y = libraryRepository.findLibraryByOpeningHour(startTime);
	assertNotNull(y);
	assertEquals(endTime,y.getClosingHour());
}

}
