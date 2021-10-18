/*package ca.mcgill.ecse321.librarysystem.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.librarysystem.model.*;
import ca.mcgill.ecse321.librarysystem.model.DailySchedule.WeekDay;


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
	@Autowired
	private LibrarianRepository librarianRepository;
	@Autowired
	private PatronRepository patronRepository;
	
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
	
	l = null;
	l = libraryRepository.findLibraryByClosingHour(endTime);
	assertNotNull(l);
	assertEquals(startTime,l.getOpeningHour());
	assertEquals(endTime,l.getClosingHour());
	
	l = null;
	l = libraryRepository.findLibraryByOpeningHour(endTime);
	assertEquals(endTime,l.getClosingHour());
	assertEquals(startTime,l.getOpeningHour());
}
@Test
public void testPersistAndLoadAlbum() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Album a = new Album(false, false, false, 0, ls, "", "", null);
	String title = "testTitle";
	String artist = "testArtist";
	a.setArtist(artist);
	a.setTitle(title);
	a.setId(1);
	albumRepository.save(a);
	
	a = null;
			
	a = albumRepository.findAlbumById(1);
	assertNotNull(a);
	assertEquals(title,a.getTitle());
	assertEquals(artist,a.getArtist());
	assertEquals(1,a.getId());
	
	a = null;
	a = albumRepository.findAlbumByTitleAndArtist(title, artist);
	assertNotNull(a);
	assertEquals(title,a.getTitle());
	assertEquals(artist,a.getArtist());
	assertEquals(1,a.getId());
}
@Test
public void testPersistAndLoadJournal() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Journal j = new Journal(false, false, false, 0, ls, null, null);
	String name = "testJournal";
	Date date = java.sql.Date.valueOf(LocalDate.of(2021, 10, 16));
	j.setDate(date);
	j.setName(name);
	j.setId(2);
	journalRepository.save(j);
	
	j = null;
	j = journalRepository.findJournalById(2);
	assertNotNull(j);
	assertEquals(name,j.getName());
	assertEquals(date,j.getDate());
	
	j = null;
	j = journalRepository.findJournalByNameAndDate(name, date);
	assertNotNull(j);
	assertEquals(name,j.getName());
	assertEquals(date,j.getDate());
}
@Test
public void testPersistAndLoadNewspaper() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Newspaper np = new Newspaper(false, false, false, 0, ls, null, null);
	String name = "testNewspaper";
	Date date = java.sql.Date.valueOf(LocalDate.of(2021, 10, 17));
	np.setDate(date);
	np.setName(name);
	np.setId(3);
	newspaperRepository.save(np);
	
	np = null;
	np = newspaperRepository.findNewspaperByNameAndDate(name, date);
	assertNotNull(np);
	assertEquals(date,np.getDate());
	assertEquals(name,np.getName());
	assertEquals(3,np.getId());
}
@Test
public void testPersistAndLoadItem() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Item i = new Item(false, false, false, 0, ls);
	i.setIsBorrowed(true);
	i.setIsDamaged(true);
	i.setId(20);
	itemRepository.save(i);
	
	i = null;
	i = itemRepository.findItemById(20);
	assertNotNull(i);
	assertEquals(20,i.getId());
	assertEquals(false,i.getIsArchived());
	assertEquals(true,i.getIsBorrowed());
	assertEquals(true,i.getIsDamaged());
}
@Test
public void testPersistAndLoadPerson() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Person p = new Person(null, null, ls);
	String firstName = "testFirstName";
	String lastName = "testLastName";
	p.setFirstName(firstName);
	p.setLastName(lastName);
	personRepository.save(p);
	
	Person testP = (personRepository.findPersonByFirstName(firstName)).get(0);
	assertNotNull(testP);
	assertEquals("testFirstName",testP.getFirstName());
	assertEquals("testLastName",testP.getLastName());
}
@Test
public void testPersistenceAndLoadUser() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	User u = new User(0, null, null, ls, null);
	Person p = new Person("ufn","uln",ls);
	OnlineAccount oa=new OnlineAccount("u@hotmail.com", "u", "upassword", u, ls);
	onlineAccountRepository.save(oa);
	personRepository.save(p);
	u.setAddress("123 Test Blvd");
	u.setCity("Montreal");
	u.setId(10);
	u.setOnlineAccount(oa);
	u.setPerson(p);
	userRepository.save(u);
	
	u = null;
	u = userRepository.findUserById(10);
	assertNotNull(u);
	assertEquals(10, u.getId());
	assertEquals("ufn",u.getPerson().getFirstName());
	assertEquals("123 Test Blvd", u.getAddress());
	assertEquals("Montreal", u.getCity());
	assertEquals("u@hotmail.com",u.getOnlineAccount().getEmail());
}
@Test
public void testPersistAndLoadLibrarian() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Person p = new Person("libfn", "libln", ls);
	Librarian lib = new Librarian(0, null, null, ls, p, null);
	OnlineAccount oa=new OnlineAccount("lib@hotmail.com", "lib", "lpassword", lib, ls);
	WeeklySchedule ws=new WeeklySchedule(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)), java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)), ls);
	weeklyScheduleRepository.save(ws);
	onlineAccountRepository.save(oa);
	lib.setOnlineAccount(oa);
	lib.setId(1);
	lib.setAddress("123 Test St");
	lib.setCity("Montreal");
	lib.setPerson(p);
	lib.setWeeklySchedule(ws);
	librarianRepository.save(lib);
	
	lib = null;
	lib = librarianRepository.findLibrarianById(1);
	assertNotNull(lib);
	assertEquals(1, lib.getId());
	assertEquals("libfn",lib.getPerson().getFirstName());
	assertEquals("123 Test St", lib.getAddress());
	assertEquals("Montreal", lib.getCity());
	assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
	
	lib = null;
	lib = librarianRepository.findLibrarianByPerson(p);
	assertNotNull(lib);
	assertEquals(1, lib.getId());
	assertEquals("libfn",lib.getPerson().getFirstName());
	assertEquals("123 Test St", lib.getAddress());
	assertEquals("Montreal", lib.getCity());
	assertEquals("lib@hotmail.com",lib.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),lib.getWeeklySchedule().getStartDate());
}
@Test
public void testPersistAndLoadHeadLibrarian() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Person p = new Person("hlfn", "hlln", ls);
	HeadLibrarian hl=new HeadLibrarian(0, null, null, ls, p, null);
	OnlineAccount oa=new OnlineAccount("hlib@hotmail.com", "Headlib", "hlpassword", hl, ls);
	WeeklySchedule ws=new WeeklySchedule(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)), java.sql.Date.valueOf(LocalDate.of(2021, 10, 22)), ls);
	weeklyScheduleRepository.save(ws);
	onlineAccountRepository.save(oa);
	personRepository.save(p);
	hl.setOnlineAccount(oa);
	hl.setId(2);
	hl.setAddress("123 Test Blvd");
	hl.setCity("Montreal");
	hl.setPerson(p);
	hl.setWeeklySchedule(ws);
	headLibrarianRepository.save(hl);
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianById(2);
	assertNotNull(hl);
	assertEquals(2, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
	
	hl = null;
	hl = headLibrarianRepository.findHeadLibrarianByPerson(p);
	assertNotNull(hl);
	assertEquals(2, hl.getId());
	assertEquals("hlfn",hl.getPerson().getFirstName());
	assertEquals("123 Test Blvd", hl.getAddress());
	assertEquals("Montreal", hl.getCity());
	assertEquals("hlib@hotmail.com",hl.getOnlineAccount().getEmail());
	assertEquals(java.sql.Date.valueOf(LocalDate.of(2021, 10, 18)),hl.getWeeklySchedule().getStartDate());
}
@Test
public void testPersistAndLoadPatron() {
	Library l = new Library (java.sql.Time.valueOf(LocalTime.of(8, 00)),java.sql.Time.valueOf(LocalTime.of(17, 00)));
	LibrarySoftwareSystem ls = new LibrarySoftwareSystem(l);
	Person p = new Person("patfn", "patln", ls);
	Patron pat = new Patron(0, null, null, ls, p);
	OnlineAccount oa = new OnlineAccount("pat@hotmail.com", "pat", "patpassword", pat, ls);
	onlineAccountRepository.save(oa);
	personRepository.save(p);
	pat.setAddress("123 Test W");
	pat.setCity("Montreal");
	pat.setId(13);
	pat.setOnlineAccount(oa);
	pat.setPerson(p);
	patronRepository.save(pat);
	
	pat = patronRepository.findPatronById(13);
	assertNotNull(pat);
	assertEquals(13, pat.getId());
	assertEquals("patfn",pat.getPerson().getFirstName());
	assertEquals("123 Test W", pat.getAddress());
	assertEquals("Montreal", pat.getCity());
	assertEquals("pat@hotmail.com",pat.getOnlineAccount().getEmail());
	
	pat = patronRepository.findPatronByPerson(p);
	assertNotNull(pat);
	assertEquals(13, pat.getId());
	assertEquals("patfn",pat.getPerson().getFirstName());
	assertEquals("123 Test W", pat.getAddress());
	assertEquals("Montreal", pat.getCity());
	assertEquals("pat@hotmail.com",pat.getOnlineAccount().getEmail());
}
}
*/