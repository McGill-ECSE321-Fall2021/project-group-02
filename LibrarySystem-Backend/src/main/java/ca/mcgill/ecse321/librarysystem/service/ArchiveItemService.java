package ca.mcgill.ecse321.librarysystem.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarysystem.dao.*;
import ca.mcgill.ecse321.librarysystem.model.*;

@Service
public class ArchiveItemService {
	
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	AlbumRepository albumRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	MovieRepository movieRepository;
	@Autowired
	JournalRepository journalRepository;
	@Autowired
	NewspaperRepository newspaperRepository;
	
	@Transactional
	public Item archiveItem(int itemID, String itemName) {
		
		if(itemRepository.existsItemById(itemID)) {
			Item itemOfInterest = itemRepository.findItemById(itemID);
			if(!(itemOfInterest.getIsArchived())) {
				itemOfInterest.setIsArchived(true);
			}
		}
		return null;
	}
	
	/*
	 * Find item by its ID
	 * @author John Park
	 * @param ID
	 * @return item
	 */
	@Transactional
	public Item getItem(int id) {
		Item item = itemRepository.findItemById(id);
		return item;
	}
	
	/*
	 * Returns a list of all archived items in the library
	 * @author John
	 * @param 
	 * @return List
	 */
	@Transactional
	public List<Item> getItemsByArchiveStatus() {
		return toList(itemRepository.findItemByIsArchived(true));
	}
	
	@Transactional
	public void updateItemStatus(Item item, int id) {
		if (item.getIsArchived() == true) {
//			throw new InvalidInputException("Item is already archived.");
		} else {
			Item itemOfInterest = itemRepository.findItemById(id);
			itemOfInterest.setIsArchived(true);
		}
	}
	
	@Transactional
	public Album createAlbum(String title, String artist) {
		Album album = new Album();
		album.setArtist(artist);
		album.setTitle(title);
		
		albumRepository.save(album);
		
		return album;
	}
	
	@Transactional
	public Book createBook(String title, String author) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		
		bookRepository.save(book);
		
		return book;
	}
	
	@Transactional
	public Movie createMovie(String title, String director) {
		Movie movie = new Movie();
		movie.setDirector(director);
		movie.setTitle(title);
		
		movieRepository.save(movie);
		
		return movie;
	}
	
	@Transactional
	public Journal createJournal(String name, Date date) {
		Journal journal = new Journal();
		journal.setDate(date);
		journal.setName(name);
		
		journalRepository.save(journal);
		
		return journal;
	}
	
	@Transactional
	public Newspaper createNewspaper(String name, Date date) {
		Newspaper newspaper = new Newspaper();
		newspaper.setDate(date);
		newspaper.setName(name);
		
		newspaperRepository.save(newspaper);
		
		return newspaper;
	}
	
	@Transactional
	public Album getAlbum(String title, String artist) {
		Album album = albumRepository.findAlbumByTitleAndArtist(title, artist);
		return album;
	}
	
	@Transactional
	public Book getBook(String title, String author) {
		Book book = bookRepository.findBookByTitleAndAuthor(title, author);
		return book;
	}
	
	@Transactional
	public Movie getMovie(String title, String director) {
		Movie movie = movieRepository.findMovieByTitleAndDirector(title, director);
		return movie;
	}
	
	@Transactional
	public Journal getJournal(String name, Date date) {
		Journal journal = journalRepository.findJournalByNameAndDate(name, date);
		return journal;
	}
	
	@Transactional
	public Newspaper getNewspaper(String name, Date date) {
		Newspaper newspaper = newspaperRepository.findNewspaperByNameAndDate(name, date);
		return newspaper;
	}
	
	@Transactional
	public List<Album> getAllArchivedAlbums() {
		return toList(albumRepository.findAlbumByIsArchived(true));
	}
	
	@Transactional
	public List<Book> getAllArchivedBooks() {
		return toList(bookRepository.findBookByIsArchived(true));
	}
	
	@Transactional
	public List<Movie> getAllArchivedMovies() {
		return toList(movieRepository.findMovieByIsArchived(true));
	}
	
	@Transactional
	public List<Journal> getAllJournals() {
		return toList(journalRepository.findAll());
	}
	
	@Transactional
	public List<Newspaper> getAllNewspaper() {
		return toList(newspaperRepository.findAll());
	}
	
	@Transactional
	public List<Album> getAlbumsByArtist(String artist) {
		List<Album> albumsByArtist = new ArrayList<>();
		for (Album a : albumRepository.findAlbumByArtist(artist)) {
			albumsByArtist.add(a);
		}
		return albumsByArtist;
	}
	
	@Transactional
	public List<Book> getBooksByAuthor(String author) {
		List<Book> booksByAuthor = new ArrayList<>();
		for (Book b : bookRepository.findBookByAuthor(author)) {
			booksByAuthor.add(b);
		}
		return booksByAuthor;
	}
	
	@Transactional
	public List<Movie> getMoviesByDirector(String director) {
		List<Movie> moviesByDirector = new ArrayList<>();
		for (Movie m : movieRepository.findMovieByDirector(director)) {
			moviesByDirector.add(m);
		}
		return moviesByDirector;
	}
	
	@Transactional
	public List<Journal> getJournalsByName(String name) {
		List<Journal> journalsByName = new ArrayList<>();
		for (Journal j : journalRepository.findJournalByName(name)) {
			journalsByName.add(j);
		}
		return journalsByName;
	}
	
	@Transactional
	public List<Newspaper> getNewspaperByName(String name) {
		List<Newspaper> newspapersByName = new ArrayList<>(); 
		for (Newspaper n : newspaperRepository.findNewspaperByName(name)) {
			newspapersByName.add(n);
		}
		return newspapersByName;
	}
	
	// --------------------- Helper method ------------------
	/* Converts iterable to list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
