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
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
		
		if(patronOfInterest.getBorrowedAlbums().size()+patronOfInterest.getBorrowedMovies().size()+patronOfInterest.getBorrowedBooks().size()>=5) {
			throw new IllegalArgumentException("Maximum number of borrowed items (5) has been reached.");
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
			
			// Remove item from patron's borrowed list by making a copy without the returned item
			if (specificItem.getClass() == Album.class) {
				List<Album> copyList = new ArrayList<Album>();
				for (Album a : specificPatron.getBorrowedAlbums()) {
					if (a.getId() != specificItem.getId()) {
						copyList.add(a);
					}
				}
				specificPatron.setBorrowedAlbums(copyList);
				patronRepository.save(specificPatron);
				
			} else if (specificItem.getClass() == Book.class) {
				List<Book> copyList = new ArrayList<Book>();
				for (Book b : specificPatron.getBorrowedBooks()) {
					if (b.getId() != specificItem.getId()) {
						copyList.add(b);
					}
				}
				specificPatron.setBorrowedBooks(copyList);
				patronRepository.save(specificPatron);
				
			} else if (specificItem.getClass() == Movie.class) {
				List<Movie> copyList = new ArrayList<Movie>();
				for (Movie m : specificPatron.getBorrowedMovies()) {
					if (m.getId() != specificItem.getId()) {
						copyList.add(m);
					}
				}
				specificPatron.setBorrowedMovies(copyList);
				patronRepository.save(specificPatron);
			}
			specificItem.setIsBorrowed(false);
			itemRepository.save(specificItem);
			// .save() for the classes too?
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
		// need to add head librarian if statement
		if(itemRepository.existsItemById(itemID)) {
			Item itemOfInterest = itemRepository.findItemById(itemID);
			if(!(itemOfInterest.getIsArchived())) {
				itemOfInterest.setIsArchived(true);
				itemRepository.save(itemOfInterest);
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
	 * Get a list of all of the archived items
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

	// get all items
	/**
	 * Find all of the items in the library system
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Item> getAllItems() {
		return toList(itemRepository.findAll());
	}
	
	/**
	 * Find item by its ID
	 * @param ID
	 * @return item
	 * 
	 * @author John
	 */
	@Transactional
	public Item getItemByID(int id) {
		Item item = itemRepository.findItemById(id);
		return item;
	}
	
	/**
	 * Get the list of items that have a specific title/name
	 * @param name
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Item> getItemByName(String name) {
		List<Item> items = new ArrayList<Item>();
		items.addAll(getBooksByTitle(name));
		items.addAll(getMoviesByTitle(name));
		items.addAll(getAlbumsByTitle(name));
		items.addAll(getNewspaperByName(name));
		items.addAll(getJournalsByName(name));
		
		return items;
	}
	
	/**
	 * Get the list of items that have a specific creator 
	 * Only books, movies, and albums have some type of creator attribute (i.e., author, director, artist)
	 * @param creator
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Item> getItemByCreator(String creator) {
		List<Item> items = new ArrayList<Item>();
		items.addAll(getBooksByAuthor(creator));
		items.addAll(getMoviesByDirector(creator));
		items.addAll(getAlbumsByArtist(creator));
		
		return items;
	}
	
	/**
	 * Get a list of all the newspapers/journals by the date they were released
	 * Only newspapers and journals have the date attribute
	 * @param date
	 * @return
	 */
	@Transactional
	public List<Item> getItemByDate(Date date) {
		List<Item> items = new ArrayList<Item>();
		items.addAll(getNewspaperByDate(date));
		items.addAll(getJournalsByDate(date));
		
		return items;
	}
	
	/**
	 * Get a list of all of the borrowed items
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Item> getAllBorrowedItems() {
		List <Item> borrowedItems = new ArrayList<Item>();
		for (Item i : itemRepository.findItemByIsBorrowed(true)) {
			if (i.getIsArchived() == true) {
				borrowedItems.add(i);
			}
		}
		
		return borrowedItems;
	}
	
	/**
	 * Get a list of all the itemss currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public List<Item> getItemsBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Item> borrowedPatronItems = new ArrayList<Item>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronItems.addAll(specificPatron.getBorrowedAlbums());
			borrowedPatronItems.addAll(specificPatron.getBorrowedBooks());
			borrowedPatronItems.addAll(specificPatron.getBorrowedMovies());
			return borrowedPatronItems = new ArrayList<Item>();
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the books currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public List<Book> getBooksBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Book> borrowedPatronBooks = new ArrayList<Book>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronBooks.addAll(specificPatron.getBorrowedBooks());
			return borrowedPatronBooks = new ArrayList<Book>();
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the albums currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public List<Album> getAlbumsBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Album> borrowedPatronAlbums = new ArrayList<Album>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronAlbums.addAll(specificPatron.getBorrowedAlbums());
			return borrowedPatronAlbums = new ArrayList<Album>();
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the movies currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Transactional
	public List<Movie> getMoviesBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Movie> borrowedPatronMovies = new ArrayList<Movie>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronMovies.addAll(specificPatron.getBorrowedMovies());
			return borrowedPatronMovies = new ArrayList<Movie>();
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	
	
	/**
	 * Get a list of all of the borrowed Movies
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Book> getAllBorrowedBooks() {
		List <Book> borrowedBooks = new ArrayList<Book>();
		for (Book b : bookRepository.findBookByIsBorrowed(true)) {
			if (b.getIsBorrowed() == true) {
				borrowedBooks.add(b);
			}
		}
		return borrowedBooks;
	}
	
	/**
	 * Get a list of all of the borrowed movies
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Movie> getAllBorrowedMovies() {
		List <Movie> borrowedMovies = new ArrayList<Movie>();
		for (Movie m : movieRepository.findMovieByIsBorrowed(true)) {
			if (m.getIsBorrowed() == true) {
				borrowedMovies.add(m);
			}
		}
		return borrowedMovies;
	}
	
	/**
	 * Get a list of all of the borrowed albums
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Album> getAllBorrowedAlbums() {
		List <Album> borrowedAlbums = new ArrayList<Album>();
		for (Album a : albumRepository.findAlbumByIsBorrowed(true)) {
			if (a.getIsBorrowed() == true) {
				borrowedAlbums.add(a);
			}
		}
		return borrowedAlbums;
	}
	
	
	/**
	 * Get a list of all of the damaged items
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Item> getAllDamagedItems() {
		List <Item> damagedItems = new ArrayList<Item>();
		for (Item i : itemRepository.findItemByIsDamaged(true)) {
			if (i.getIsDamaged() == true) {
				damagedItems.add(i);
			}
		}
		
		return damagedItems;
	}
	
	/**
	 * Get a list of all of the damaged books
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Book> getAllDamagedBooks() {
		List <Book> damagedBooks = new ArrayList<Book>();
		for (Book b : bookRepository.findBookByIsDamaged(true)) {
			if (b.getIsDamaged() == true) {
				damagedBooks.add(b);
			}
		}
		return damagedBooks;
	}
	
	/**
	 * Get a list of all of the damaged movies
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Movie> getAllDamagedMovies() {
		List <Movie> damagedMovies = new ArrayList<Movie>();
		for (Movie m : movieRepository.findMovieByIsDamaged(true)) {
			if (m.getIsDamaged() == true) {
				damagedMovies.add(m);
			}
		}
		return damagedMovies;
	}
	
	/**
	 * Get a list of all of the damaged albums
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Album> getAllDamagedAlbums() {
		List <Album> damagedAlbums = new ArrayList<Album>();
		for (Album a : albumRepository.findAlbumByIsDamaged(true)) {
			if (a.getIsDamaged() == true) {
				damagedAlbums.add(a);
			}
		}
		return damagedAlbums;
	}
	
	/**
	 * Get a list of all of the damaged newspapers
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Newspaper> getAllDamagedNewspapers() {
		List <Newspaper> damagedNewspapers = new ArrayList<Newspaper>();
		for (Newspaper n : newspaperRepository.findNewspaperByIsDamaged(true)) {
			if (n.getIsDamaged() == true) {
				damagedNewspapers.add(n);
			}
		}
		return damagedNewspapers;
	}
	
	/**
	 * Get a list of all of the damaged journals
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Journal> getAllDamagedJournals() {
		List <Journal> damagedJournals = new ArrayList<Journal>();
		for (Journal j : journalRepository.findJournalByIsDamaged(true)) {
			if (j.getIsDamaged() == true) {
				damagedJournals.add(j);
			}
		}
		return damagedJournals;
	}
	
	
	/****************************************************
           OTHER GENERAL ITEM METHODS - JULIE/JOHN
	 ****************************************************/

	/**
	 * not sure what this is - Julie
	 * @param item
	 * @param id
	 * @throws IllegalArgumentException
	 * 
	 * @author John
	 */
	@Transactional
	public void updateItemStatus(Item item, int id) throws IllegalArgumentException {
		if (item.getIsArchived() == true) {
				throw new IllegalArgumentException("Item is already archived.");
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
				itemRepository.save(specificItem);
				// bookRepository.save etcetc?
			} else {
				throw new IllegalArgumentException("Item ID does not exist.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
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
				throw new IllegalArgumentException("Item ID does not exist.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
		}
		
	}
	
	/****************************************************
          SPECIFIC ITEM TYPE METHODS - SAMI/JULIE
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
	 * @param title
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Album> getAlbumsByTitle(String title) {
		List<Album> albumsByTitle = new ArrayList<>();
		for (Album a : albumRepository.findAlbumByTitle(title)) {
			albumsByTitle.add(a);
		}
		return albumsByTitle;
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
	 * @param title
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Book> getBooksByTitle(String title) {
		List<Book> booksByTitle= new ArrayList<>();
		for (Book b : bookRepository.findBookByTitle(title)) {
			booksByTitle.add(b);
		}
		return booksByTitle;
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
	public List<Movie> getMoviesByTitle(String title) {
		List<Movie> moviesByTitle = new ArrayList<>();
		for (Movie m : movieRepository.findMovieByTitle(title)) {
			moviesByTitle.add(m);
		}
		return moviesByTitle;
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
	 * @param date
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Journal> getJournalsByDate(Date date) {
		List<Journal> journalsByDate = new ArrayList<>();
		for (Journal j : journalRepository.findJournalByDate(date)) {
			journalsByDate.add(j);
		}
		return journalsByDate;
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
	 * @param date
	 * @return
	 * 
	 * @author Julie
	 */
	@Transactional
	public List<Newspaper> getNewspaperByDate(Date date) {
		List<Newspaper> newspapersByDate = new ArrayList<>();
		for (Newspaper n : newspaperRepository.findNewspaperByDate(date)) {
			newspapersByDate.add(n);
		}
		return newspapersByDate;
			
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
