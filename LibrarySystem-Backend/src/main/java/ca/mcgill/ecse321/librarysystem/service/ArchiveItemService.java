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
			if (itemOfInterest instanceof Album) {
				itemRepository.save(itemOfInterest);
				albumRepository.save((Album) itemOfInterest);
			} else if (itemOfInterest instanceof Book) {
				itemRepository.save(itemOfInterest);
				bookRepository.save((Book) itemOfInterest);
			} else if (itemOfInterest instanceof Movie) {
				itemRepository.save(itemOfInterest);
				movieRepository.save((Movie) itemOfInterest);
			}
		}
	}
	
	@Transactional
	public Album createAlbum(String title, String artist) {
		Album album = new Album();
		album.setArtist(artist);
		album.setTitle(title);
		
		itemRepository.save(album);
		albumRepository.save(album);
		
		return album;
	}
	
	@Transactional
	public Book createBook(String title, String author) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		
		itemRepository.save(book);
		bookRepository.save(book);
		
		return book;
	}
	
	@Transactional
	public Movie createMovie(String title, String director) {
		Movie movie = new Movie();
		movie.setDirector(director);
		movie.setTitle(title);
		
		itemRepository.save(movie);
		movieRepository.save(movie);
		
		return movie;
	}
	
	@Transactional
	public Journal createJournal(String name, Date date) {
		Journal journal = new Journal();
		journal.setDate(date);
		journal.setName(name);
		
		itemRepository.save(journal);
		journalRepository.save(journal);
		
		return journal;
	}
	
	@Transactional
	public Newspaper createNewspaper(String name, Date date) {
		Newspaper newspaper = new Newspaper();
		newspaper.setDate(date);
		newspaper.setName(name);
		
		itemRepository.save(newspaper);
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
	public List<Item> getAllArchivedItems() {
		List<Item> archivedItems = new ArrayList<>();
		for (Item i : itemRepository.findItemByIsArchived(true)) {
			if(i.getIsArchived() == true) {
				archivedItems.add(i);
			}
		}
		return archivedItems;
	}
	
	@Transactional
	public List<Album> getAllArchivedAlbums() {
		List<Album> archivedAlbums = new ArrayList<>();
		for(Album a : albumRepository.findAlbumByIsArchived(true)) {
			if(a.getIsArchived() == true) {
				archivedAlbums.add(a);
			}
		}
		return archivedAlbums;
	}
	
	@Transactional
	public List<Book> getAllArchivedBooks() {
		List<Book> archivedBooks = new ArrayList<>();
		for(Book b : bookRepository.findBookByIsArchived(true)) {
			if(b.getIsArchived() == true) {
				archivedBooks.add(b);
			}
		}
		return archivedBooks;
	}
	
	@Transactional
	public List<Movie> getAllArchivedMovies() {
		List<Movie> archivedMovies = new ArrayList<>();
		for(Movie m : movieRepository.findMovieByIsArchived(true)) {
			if(m.getIsArchived() == true) {
				archivedMovies.add(m);
			}
		}
		return archivedMovies;
	}
	
	@Transactional
	public List<Journal> getAllJournals() {
		return toList(journalRepository.findAll());       // All journals's archive status are initially set to true
	}
	
	@Transactional
	public List<Newspaper> getAllNewspaper() {            // All newspaper's archive status are initially set to true
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
