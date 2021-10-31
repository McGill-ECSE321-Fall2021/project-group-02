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
public class ItemService {
	
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
	@Autowired 
	HeadLibrarianRepository headLibrarianRepository;
	@Autowired 
	PatronRepository patronRepository;
	
	/************************************
	      BORROW ITEM SERVICE - SAMI
	 ************************************/
	
	/** 
	 * DESCRIPTION HERE
	 * @param itemId
	 * @param itemName
	 * @param patronId
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Item borrowItem(int itemId, String itemName, int patronId) {
		Patron patronOfInterest;
		if (patronRepository.existsPatronById(patronId)) {
			patronOfInterest= patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron has invalid ID");
		}
		
		if(patronOfInterest.getBorrowedAlbums().size()+patronOfInterest.getBorrowedMovies().size()+patronOfInterest.getBorrowedBooks().size()>=5) {
			throw new IllegalArgumentException("Patron can't borrow because he has already borrowed 5 books");
		}
		
		if(itemRepository.existsItemById(itemId)) {
			Item itemOfInterest=itemRepository.findItemById(itemId); 
			if (!itemOfInterest.getIsArchived()) {
				if(!itemOfInterest.getIsBorrowed()) {
					for(Album a : patronOfInterest.getBorrowedAlbums()) {
						if(a.getTitle().equals(itemName)) {
							List<Album> albums=patronOfInterest.getBorrowedAlbums();
							albums.add(a);
							patronOfInterest.setBorrowedAlbums(albums); 
							patronRepository.save(patronOfInterest);
							return a;
						}
					}
					
					for (Movie m : patronOfInterest.getBorrowedMovies()) {
						if(m.getTitle().equals(itemName)) {
							List<Movie> movies=patronOfInterest.getBorrowedMovies();
							movies.add(m);
							patronOfInterest.setBorrowedMovies(movies);
							patronRepository.save(patronOfInterest);
							return m;
						}
					}
					
					for (Book b : patronOfInterest.getBorrowedBooks()) {
						if(b.getTitle().equals(itemName)) {
							List<Book> books=patronOfInterest.getBorrowedBooks();
							books.add(b);
							patronOfInterest.setBorrowedBooks(books);
							patronRepository.save(patronOfInterest);
							return b;
						}
					}
					
				}
				else {
					throw new IllegalArgumentException("The item you are looking for has already been borrowed.");
				}
			}
			else {
				throw new IllegalArgumentException("The item you are looking for is in the archives");
			}
		}
		throw new IllegalArgumentException("The item you are looking for does not exist.");
	}
	
	
	/************************************
	      RETURN ITEM SERVICE - JULIE
	 ************************************/
	
	/**
	 * DESCRIPTION HERE
	 * @param itemId
	 * @param patronId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public Item returnItem(int itemId, int patronId) throws IllegalArgumentException {
		
		// Identify the specific patron
		Patron specificPatron;
		if (patronRepository.existsById(patronId)) {
			specificPatron = patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron ID does not exist."); 
		}

		if(itemRepository.existsItemById(itemId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
			// Remove item from patron's borrowed list by making a copy without the specificItem
			if (specificItem.getClass() == Album.class) {
				List<Album> copyList = new ArrayList<Album>();
				for (Album a : specificPatron.getBorrowedAlbums()) {
					if (a.getId() != specificItem.getId()) {
						copyList.add(a);
					}
				}
				specificPatron.setBorrowedAlbums(copyList);
				
			} else if (specificItem.getClass() == Book.class) {
				List<Book> copyList = new ArrayList<Book>();
				for (Book b : specificPatron.getBorrowedBooks()) {
					if (b.getId() != specificItem.getId()) {
						copyList.add(b);
					}
				}
				specificPatron.setBorrowedBooks(copyList);
				
			} else if (specificItem.getClass() == Movie.class) {
				List<Movie> copyList = new ArrayList<Movie>();
				for (Movie m : specificPatron.getBorrowedMovies()) {
					if (m.getId() != specificItem.getId()) {
						copyList.add(m);
					}
				}
				specificPatron.setBorrowedMovies(copyList);
			}
			specificItem.setIsBorrowed(false);
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Item ID does not exist.");
		}
	}
	/************************************
         ARCHIVE ITEM SERVICE - JOHN
	 ************************************/
	/**
	 * DESCRIPTION HERE
	 * @param itemID
	 * @param itemName
	 * @return
	 * 
	 * @author John
	 */
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
	
	/**
	 * Returns a list of all archived items in the library
	 * @param 
	 * @return List
	 * 
	 * @author John
	 */
	@Transactional
	public List<Item> getItemsByArchiveStatus() {
		return toList(itemRepository.findItemByIsArchived(true));
	}
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author John
	 */
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
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author John
	 */
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
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author John
	 */
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
	
	/** 
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author John
	 */
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
	
	
	/******************************************
    	VIEW LIBRARY CONTENTS - JULIE/NIILO
	 ******************************************/

	// add code
	
	/****************************************************
           OTHER GENERAL ITEM METHODS - JULIE/JOHN
	 ****************************************************/

	/**
	 * Find item by its ID
	 * @param ID
	 * @return item
	 * 
	 * @author John Park
	 */
	@Transactional
	public Item getItem(int id) {
		Item item = itemRepository.findItemById(id);
		return item;
	}
	
	// not sure what this is - Julie
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
	
	/**
	 * DESCRIPTION HERE
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public Item setDamagedItem(int itemId, int userId) throws IllegalArgumentException {
		
		if (headLibrarianRepository.existsById(userId)) {
			
			Item specificItem = itemRepository.findItemById(itemId); 
			if(itemRepository.existsItemById(itemId)) {
				specificItem.setIsBorrowed(false);
				specificItem.setIsDamaged(true);
			} else {
				throw new IllegalArgumentException("Item ID does not exists.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Head librarian ID does not exist.");
		}
			
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public Item discardItem(int itemId, int userId) throws IllegalArgumentException {
		
	if (headLibrarianRepository.existsById(userId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
			if(itemRepository.existsItemById(itemId)) {
				itemRepository.delete(specificItem);
			} else {
				throw new IllegalArgumentException("Item ID does not exists.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Head librarian ID does not exist.");
		}
		
	}
	
	/****************************************************
           SPECIFIC ITEM TYPE METHODS - SAMI
	 ****************************************************/
	/**
	 * DESCRIPTION HERE
	 * @param title
	 * @param artist
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Album createAlbum(String title, String artist) {
		Album album = new Album();
		album.setArtist(artist);
		album.setTitle(title);
		
		itemRepository.save(album);
		albumRepository.save(album);
		return album;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param author
	 * @param title
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Book createBook(String author, String title, Patron patron, boolean isArchived) {
		Item booker = new Book();
		booker.setIsBorrowed(false);
		booker.setIsDamaged(false);
		booker.setIsArchived(isArchived);
		Book book=(Book)booker;
		book.setAuthor(author);
		book.setTitle(title);
		book.setPatron(patron);
		itemRepository.save(book);
		bookRepository.save(book);
		return book;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param title
	 * @param author
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Book getBook(String title, String author) {
		Book book=bookRepository.findBookByTitleAndAuthor(title, author);
		return book;
	}
		
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Book> getAllBooks(){
		return toList(bookRepository.findAll());
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param director
	 * @param title
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Movie createMovie(String director, String title, Patron patron, boolean isArchived) {
		Item mover = new Movie();
		mover.setIsBorrowed(false);
		mover.setIsDamaged(false);
		mover.setIsArchived(isArchived);
		Movie movie=(Movie)mover;
		movie.setDirector(title);
		movie.setTitle(title);
		movie.setPatron(patron);
		itemRepository.save(movie);
		movieRepository.save(movie);
		return movie;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param director
	 * @param title
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Movie getMovie(String director, String title) {
		Movie movie=movieRepository.findMovieByTitleAndDirector(title, director);
		return movie;
	}
		
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getAllMovies(){
		return toList(movieRepository.findAll());
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param artist
	 * @param title
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Album createAlbum(String artist, String title, Patron patron, boolean isArchived) {
		Item albumer = new Album();
		albumer.setIsBorrowed(false);
		albumer.setIsDamaged(false);
		albumer.setIsArchived(isArchived);
		Album album=(Album)albumer;
		album.setArtist(artist);
		album.setTitle(title);
		album.setPatron(patron);
		return album;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param artist
	 * @param title
	 * @return
	 * 
	 * @author Sami
	 */
	public Album getAlbum(String artist, String title) {
		
		Album album=albumRepository.findAlbumByTitleAndArtist(title, artist);
		return album;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Album> getAllAlbums(){
		return toList(albumRepository.findAll());
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @param date
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Newspaper createNewspaper(String name, Date date) {
		Newspaper newspaper = new Newspaper();
		newspaper.setName(name);
		newspaper.setDate(date);
		itemRepository.save(newspaper);
		newspaperRepository.save(newspaper);
		return newspaper;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @param date
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Newspaper getNewspaper(String name, Date date) {
		
		Newspaper newspaper=newspaperRepository.findNewspaperByNameAndDate(name, date);
		return newspaper;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param artist
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Album> getAlbumsByArtist(String artist) {
		List<Album> albumsByArtist = new ArrayList<>();
		for (Album a : albumRepository.findAlbumByArtist(artist)) {
			albumsByArtist.add(a);
		}
		return albumsByArtist;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param author
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Book> getBooksByAuthor(String author) {
		List<Book> booksByAuthor = new ArrayList<>();
		for (Book b : bookRepository.findBookByAuthor(author)) {
			booksByAuthor.add(b);
		}
		return booksByAuthor;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param director
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getMoviesByDirector(String director) {
		List<Movie> moviesByDirector = new ArrayList<>();
		for (Movie m : movieRepository.findMovieByDirector(director)) {
			moviesByDirector.add(m);
		}
		return moviesByDirector;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Journal> getJournalsByName(String name) {
		List<Journal> journalsByName = new ArrayList<>();
		for (Journal j : journalRepository.findJournalByName(name)) {
			journalsByName.add(j);
		}
		return journalsByName;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Newspaper> getNewspaperByName(String name) {
		List<Newspaper> newspapersByName = new ArrayList<>(); 
		for (Newspaper n : newspaperRepository.findNewspaperByName(name)) {
			newspapersByName.add(n);
		}
		return newspapersByName;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Newspaper> getAllNewspapers(){
		return toList(newspaperRepository.findAll());
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @param date
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Journal createJournal(String name, Date date) {
		Journal journal=new Journal();
		journal.setName(name);
		journal.setDate(date);
		itemRepository.save(journal);
		journalRepository.save(journal);
		return journal;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @param name
	 * @param date
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public Journal getJournal(String name, Date date) {
		
		Journal journal=journalRepository.findJournalByNameAndDate(name, date);
		return journal;
	}
	
	/**
	 * DESCRIPTION HERE
	 * @return
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Journal> getAllJournals(){
		return toList(journalRepository.findAll());
	}
	
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	

}
