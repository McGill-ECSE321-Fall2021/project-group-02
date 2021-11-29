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
	 * Allows a patron to borrow an item.
	 * @param itemId
	 * @param itemName
	 * @param patronId
	 * @return Item if input is correct and item not borrowed or in the archives
	 * @return throws IllegalArgumentException if input is incorrect or if the item is borrowed or in the archives.
	 * 
	 * @author Sami
	 */
	
	@Transactional
	public Item borrowItem(int itemId, String itemName, int patronId) {
		if(itemId<0) {
			throw new IllegalArgumentException("The id of the item cannot be negative!");
		}
		
		if(itemName==null || itemName.trim().length()==0) {
			throw new IllegalArgumentException("The name of the item cannot be empty");
		}
		
		if(patronId<0) {
			throw new IllegalArgumentException("The id of the patron cannot be negative!");
		}
		
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
					for(Album a : albumRepository.findAll()) {
						if(a.getTitle().equals(itemName)) {
							List<Album> albums=patronOfInterest.getBorrowedAlbums();
							albums.add(a);
							patronOfInterest.setBorrowedAlbums(albums);
							patronRepository.save(patronOfInterest);
							a.setIsBorrowed(true);
							itemRepository.save(a);
							albumRepository.save(a);
							return a;
						}
					}
					for (Movie m : movieRepository.findAll()) {
						if(m.getTitle().equals(itemName)) {
							List<Movie> movies=patronOfInterest.getBorrowedMovies();
							movies.add(m);
							patronOfInterest.setBorrowedMovies(movies);
							patronRepository.save(patronOfInterest);
							m.setIsBorrowed(true);
							itemRepository.save(m);
							movieRepository.save(m);
							return m;
						}
					}
					
					for (Book b : bookRepository.findAll()) {
						if(b.getTitle().equals(itemName)) {
							itemOfInterest.setIsBorrowed(true);
							b.setIsBorrowed(true);
							itemRepository.save(itemOfInterest);
							bookRepository.save(b);
							
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
	 * Places an item into the library contents as borrowable and removes from the patron's borrowed list
	 * @param itemId
	 * @param patronId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public Item returnItem(int itemId, int patronId) throws IllegalArgumentException {
		
		// Identify the specific patron returning the item
		Patron specificPatron;
		if (patronId < 0){
			throw new IllegalArgumentException("Patron ID cannot be negative.");
			
		} else if (patronRepository.existsById(patronId)) {
			specificPatron = patronRepository.findPatronById(patronId);
		}
		else {
			throw new IllegalArgumentException("Patron ID does not exist."); 
		}
		
		// Identify the specific item being returned
		if (itemId < 0) {
			throw new IllegalArgumentException("Item ID cannot be negative.");
		}
		else if(itemRepository.existsItemById(itemId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			
			// Remove item from patron's borrowed list by making a copy without the returned item
			// CRUD methods require the type (class) of the item to be identified
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
			// Make the item available to be borrowed 
			specificItem.setIsBorrowed(false);
			itemRepository.save(specificItem);
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Item ID does not exist.");
		}
	}
	/************************************
         ARCHIVE ITEM SERVICE - JOHN
	 ************************************/
	/**
	 * Sets an item as archived and cannot be borrowed
	 * @param itemID
	 * @param headLibrarianID
	 * @return archived item
	 * @throws illegalArgumentException
	 * 
	 * @author John
	 */
	@Transactional
	public Item archiveItem(int itemID, int headLibrarianID) throws IllegalArgumentException {
		// To archive an item, need approval of head librarian. If there is a head librarian ID associated with it, then it has been approved for the archives.
		HeadLibrarian specificHeadLibrarian;
		
		if(headLibrarianID < 0) {
			throw new IllegalArgumentException("Head Librarian ID cannot be negative.");
		}
		
		if(headLibrarianRepository.existsById(headLibrarianID)) {
			specificHeadLibrarian = headLibrarianRepository.findHeadLibrarianById(headLibrarianID);
		} else {
			throw new IllegalArgumentException("Head Librarian does not approve.");
		}
		
		if(itemID < 0) {
			throw new IllegalArgumentException("Item ID cannot be negative.");
		}
		
		if(itemRepository.existsItemById(itemID)) {
			Item itemOfInterest = itemRepository.findItemById(itemID);
			if(itemOfInterest.getIsBorrowed() == false) {
				if(itemOfInterest.getIsArchived() == false) {
					itemOfInterest.setIsArchived(true);
					itemRepository.save(itemOfInterest);
					return itemOfInterest;
				} else {
					throw new IllegalArgumentException("Item is already in the archives.");
				}
			} else {
				throw new IllegalArgumentException("Cannot archive a borrowed item.");
			}
		} else {
			throw new IllegalArgumentException("Item ID does not exist.");
		}
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
	 * Gets a list of all of the archived albums
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
	 * Gets a list of all of the archived books
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
	 * Gets a list of all of the archived movies
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
		Item item = null;
		if (id < 0){
			throw new IllegalArgumentException("Item ID cannot be negative.");
			
		} else if (itemRepository.existsById(id)) {
			item = itemRepository.findItemById(id);
		}
		else {
			throw new IllegalArgumentException("Item ID does not exist."); 
		}
		
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
		
		if(items.size() == 0) {
			throw new IllegalArgumentException("No items exist under that name");
		}
		
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
		
		if(items.size() == 0) {
			throw new IllegalArgumentException("No items exist under that creator");
		}
		
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
		
		if(items.size() == 0) {
			throw new IllegalArgumentException("No items exist under that date");
		}
		
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
	 * @author Sami
	 */
	@Transactional
	public List<Item> getItemsBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Item> borrowedPatronItems = new ArrayList<Item>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronItems.addAll(specificPatron.getBorrowedAlbums());
			borrowedPatronItems.addAll(specificPatron.getBorrowedBooks());
			borrowedPatronItems.addAll(specificPatron.getBorrowedMovies());
			return borrowedPatronItems;
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the books currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 * @author Sami
	 */
	@Transactional
	public List<Book> getBooksBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Book> borrowedPatronBooks = new ArrayList<Book>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronBooks.addAll(specificPatron.getBorrowedBooks());
			return borrowedPatronBooks;
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the albums currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 * @author Sami
	 */
	@Transactional
	public List<Album> getAlbumsBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Album> borrowedPatronAlbums = new ArrayList<Album>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronAlbums.addAll(specificPatron.getBorrowedAlbums());
			return borrowedPatronAlbums;
		} else {
			throw new IllegalArgumentException("Patron ID does not exist.");
		}
	}
	
	/**
	 * Get a list of all the movies currently in a specific patron's possession
	 * @param patronID
	 * @return
	 * @throws IllegalArgumentException
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getMoviesBorrowedByPatron(int patronID) throws IllegalArgumentException{
		if (patronRepository.existsById(patronID)) {
			List <Movie> borrowedPatronMovies = new ArrayList<Movie>();
			Patron specificPatron = patronRepository.findPatronById(patronID);
			borrowedPatronMovies.addAll(specificPatron.getBorrowedMovies());
			return borrowedPatronMovies;
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
           OTHER GENERAL ITEM METHODS - JULIE
	 ****************************************************/
	
	
	public Item makeItemBorrowable(int itemID, int userID) throws IllegalArgumentException {
		if (userID < 0) {
			throw new IllegalArgumentException("User ID cannot be negative.");
		} else if (headLibrarianRepository.existsById(userID)) {
			Item specificItem = itemRepository.findItemById(itemID);
			if (itemID < 0) {
				throw new IllegalArgumentException("Item ID cannot be negative.");
			} else if(itemRepository.existsItemById(itemID)) {
				specificItem.setIsBorrowed(false);
				specificItem.setIsDamaged(false);
				specificItem.setIsArchived(false);
			} else {
				throw new IllegalArgumentException("Item ID does not exist.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
		}
	}
	
	/**
	 * Sets an item as damaged and cannot be borrowed
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public Item setDamagedItem(int itemId, int userId) throws IllegalArgumentException {
		if (userId < 0) {
			throw new IllegalArgumentException("User ID cannot be negative.");
		} else if (headLibrarianRepository.existsById(userId)) {
			
			Item specificItem = itemRepository.findItemById(itemId);
			if (itemId < 0) {
				throw new IllegalArgumentException("Item ID cannot be negative.");
			} else if(itemRepository.existsItemById(itemId)) {
				specificItem.setIsBorrowed(false);
				specificItem.setIsDamaged(true);
			} else {
				throw new IllegalArgumentException("Item ID does not exist.");
			}
			
			return specificItem;
			
		} else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
		}
			
	}
	
	/**
	 * Removes an item from the library software system
	 * @param itemId
	 * @param userId
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 * @author Julie
	 */
	@Transactional
	public void discardItem(int itemId, int headLibrarianId) {
		if (headLibrarianId < 0) {
			throw new IllegalArgumentException("User ID cannot be negative.");
		} else if (headLibrarianRepository.existsById(headLibrarianId)) {
			Item specificItem = itemRepository.findItemById(itemId); 
			if (itemId < 0) {
				throw new IllegalArgumentException("Item ID cannot be negative.");
			} else if(itemRepository.existsItemById(itemId)) {
				itemRepository.delete(specificItem);
			} else {
				throw new IllegalArgumentException("Item ID does not exist.");
			}
		} else {
			throw new IllegalArgumentException("Must be a head librarian to proceed.");
		}
		
	}
	
	/****************************************************
          SPECIFIC ITEM TYPE METHODS - SAMI/JULIE
	 ****************************************************/
	
	
	/**
	 * Creates a patron
	 * @return Patron
	 * @author Sami
	 */
	@Transactional
	public Patron createPatron(String address, int balance, String city, String firstName, String lastName) {
		if(address==null || address.trim().length()==0) {
			throw new IllegalArgumentException("The address cannot be empty!");
		}
		
		if(city==null || city.trim().length()==0) {
			throw new IllegalArgumentException("The city cannot be empty!");
		}
		
		if(firstName==null || firstName.trim().length()==0) {
			throw new IllegalArgumentException("The first name cannot be empty!");
		}
		
		if(lastName==null || lastName.trim().length()==0) {
			throw new IllegalArgumentException("The last name cannot be empty!");
		}
		
		
		Patron patron=new Patron();
		patron.setAddress(address);
		patron.setBalance(balance);
		patron.setCity(city);
		patron.setFirstName(firstName);
		patron.setLastName(lastName);
		patron.setBorrowedAlbums(new ArrayList<Album>());
		patron.setBorrowedBooks(new ArrayList<Book>());
		patron.setBorrowedMovies(new ArrayList<Movie>());
		patronRepository.save(patron);
		return patron;
	}
	
	/**
	 * Deletes a patron
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deletePatron(String address) {
		if(address==null || address.trim().length()==0) {
			throw new IllegalArgumentException("The address cannot be empty!");
		}
		
		Patron patron;
		try {
			List<Patron> patronList=patronRepository.findPatronByAddress(address);
			patron=patronList.get(0);
			patronRepository.delete(patron);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The patron with this address does not exist.");
		}
	}
	
	/**
	 * Creates a book
	 * @param author
	 * @param title
	 * @param isArchived
	 * @return Book
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Book createBook(String author, String title, boolean isArchived) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the book cannot be empty!");
		}
		
		if(author==null || author.trim().length()==0) {
			throw new IllegalArgumentException("The name of the author of the book cannot be empty!");
		}
		
		Item booker = new Book();
		booker.setIsBorrowed(false);
		booker.setIsDamaged(false);
		booker.setIsArchived(isArchived);
		Book book=(Book)booker;
		book.setAuthor(author);
		book.setTitle(title);
		itemRepository.save(book);
		bookRepository.save(book);
		return book;
	}
	
	/**
	 * Deletes a book
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deleteBook(String author, String title) {
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the book cannot be empty!");
		}
		
		if(author==null || author.trim().length()==0) {
			throw new IllegalArgumentException("The name of the author of the book cannot be empty!");
		}
		
		Book book;
		try {
			book=bookRepository.findBookByTitleAndAuthor(title,author);
			bookRepository.delete(book);
			itemRepository.delete(book);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The book with this author or title does not exist.");
		}
	}
	
	/**
	 * Gets a specific book by title and author
	 * @param title
	 * @param author
	 * @return Book
	 * 
	 * @author Sami
	 */
	@Transactional
	public Book getBook(String title, String author) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the book cannot be empty!");
		}
		
		if(author==null || author.trim().length()==0) {
			throw new IllegalArgumentException("The name of the author of the book cannot be empty!");
		}
		
		
		Book book=bookRepository.findBookByTitleAndAuthor(title, author);
		return book;
	}
		
	/**
	 * Gets a list of all of the books
	 * @return List<Book>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Book> getAllBooks(){
		return toList(bookRepository.findAll());
	}
	
	/**
	 * Creates a movie
	 * @param director
	 * @param title
	 * @param isArchived
	 * @return Movie
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Movie createMovie(String director, String title, boolean isArchived) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the movie cannot be empty!");
		}
		
		if(director==null || director.trim().length()==0) {
			throw new IllegalArgumentException("The name of the director of the movie cannot be empty!");
		}
		
		
		Item mover = new Movie();
		mover.setIsBorrowed(false);
		mover.setIsDamaged(false);
		mover.setIsArchived(isArchived);
		Movie movie=(Movie)mover;
		movie.setDirector(title);
		movie.setTitle(title);
		itemRepository.save(movie);
		movieRepository.save(movie);
		return movie;
	}
	
	/**
	 * Deletes a movie
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deleteMovie(String director, String title) {
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the movie cannot be empty!");
		}
		
		if(director==null || director.trim().length()==0) {
			throw new IllegalArgumentException("The name of the director of the movie cannot be empty!");
		}
		
		Movie movie;
		try {
			movie=movieRepository.findMovieByTitleAndDirector(title,director);
			movieRepository.delete(movie);
			itemRepository.delete(movie);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The movie with this director or title does not exist.");
		}
	}
	
	/**
	 * Gets a specific movie by title and director
	 * @param director
	 * @param title
	 * @return Movie
	 * 
	 * @author Sami
	 */
	@Transactional
	public Movie getMovie(String director, String title) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the movie cannot be empty!");
		}
		
		if(director==null || director.trim().length()==0) {
			throw new IllegalArgumentException("The name of the director of the movie cannot be empty!");
		}
		
		
		Movie movie=movieRepository.findMovieByTitleAndDirector(title, director);
		return movie;
	}
		
	/**
	 * Gets a list of all of the movies
	 * @return List<Movie>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getAllMovies(){
		return toList(movieRepository.findAll());
	}
	
	/**
	 * Creates an album
	 * @param artist
	 * @param title
	 * @param isArchived
	 * @return Album
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Album createAlbum(String artist, String title, boolean isArchived) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the album cannot be empty!");
		}
		
		if(artist==null || artist.trim().length()==0) {
			throw new IllegalArgumentException("The name of the artist of the album cannot be empty!");
		}
		
		Item albumer = new Album();
		albumer.setIsBorrowed(false);
		albumer.setIsDamaged(false);
		albumer.setIsArchived(isArchived);
		Album album=(Album)albumer;
		album.setArtist(artist);
		album.setTitle(title);
		itemRepository.save(album);
		albumRepository.save(album);
		return album;
	}
	
	/**
	 * Deletes an album
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deleteAlbum(String title, String artist) {
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the album cannot be empty!");
		}
		
		if(artist==null || artist.trim().length()==0) {
			throw new IllegalArgumentException("The name of the artist of the album cannot be empty!");
		}
		Album album;
		try {
			album=albumRepository.findAlbumByTitleAndArtist(title, artist);
			albumRepository.delete(album);
			itemRepository.delete(album);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The album with this title or name of artist does not exist.");
		}
	}
	
	/**
	 * Gets a specific album by artist and title
	 * @param artist
	 * @param title
	 * @return Album
	 * 
	 * @author Sami
	 */
	public Album getAlbum(String artist, String title) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The title of the album cannot be empty!");
		}
		
		if(artist==null || artist.trim().length()==0) {
			throw new IllegalArgumentException("The name of the artist of the album cannot be empty!");
		}
		
		
		Album album=albumRepository.findAlbumByTitleAndArtist(title, artist);
		return album;
	}
	
	/**
	 * Gets a list of all of the albums
	 * @return List<Album>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Album> getAllAlbums(){
		return toList(albumRepository.findAll());
	}
	
	/**
	 * Creates a newspaper
	 * @param name
	 * @param date
	 * @return Newspaper
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Newspaper createNewspaper(String name, Date date) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the newspaper cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		
		Newspaper newspaper = new Newspaper();
		newspaper.setName(name);
		newspaper.setDate(date);
		itemRepository.save(newspaper);
		newspaperRepository.save(newspaper);
		return newspaper;
	}
	
	/**
	 * Deletes a newspaper
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deleteNewspaper(String name, Date date) {
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the newspaper cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		Newspaper newspaper;
		try {
			newspaper=newspaperRepository.findNewspaperByNameAndDate(name,date);
			newspaperRepository.delete(newspaper);
			itemRepository.delete(newspaper);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The newspaper with this name and date does not exist.");
		}
	}
	
	
	/**
	 * Gets a specific newspaper by name and date
	 * @param name
	 * @param date
	 * @return Newspaper
	 * 
	 * @author Sami
	 */
	@Transactional
	public Newspaper getNewspaper(String name, Date date) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the newspaper cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		
		
		Newspaper newspaper=newspaperRepository.findNewspaperByNameAndDate(name, date);
		return newspaper;
	}
	
	/**
	 * Gets a list of albums by artist
	 * @param artist
	 * @return List<Newspaper>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Album> getAlbumsByArtist(String artist) {
		
		if(artist==null || artist.trim().length()==0) {
			throw new IllegalArgumentException("The name of the artist of the album cannot be empty!");
		}
		
		List<Album> albumsByArtist = new ArrayList<>();
		for (Album a : albumRepository.findAlbumByArtist(artist)) {
			albumsByArtist.add(a);
		}
		return albumsByArtist;
	}
	
	/**
	 * Gets a list of albums by title
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
	 * Gets a list of books by an author
	 * @param author
	 * @return List<Book>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Book> getBooksByAuthor(String author) {
		
		if(author==null || author.trim().length()==0) {
			throw new IllegalArgumentException("The name of the author of the book cannot be empty!");
		}
		
		List<Book> booksByAuthor = new ArrayList<>();
		for (Book b : bookRepository.findBookByAuthor(author)) {
			booksByAuthor.add(b);
		}
		return booksByAuthor;
	}
	
	/**
	 * Gets a list of books by title
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
	 * Gets a list of movies by a director
	 * @param director
	 * @return List<Movie>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getMoviesByDirector(String director) {
		
		if(director==null || director.trim().length()==0) {
			throw new IllegalArgumentException("The name of the director of the movie cannot be empty!");
		}
		
		List<Movie> moviesByDirector = new ArrayList<>();
		for (Movie m : movieRepository.findMovieByDirector(director)) {
			moviesByDirector.add(m);
		}
		return moviesByDirector;
	}
	
	/**
	 * Gets a list of movies by title
	 * @param name
	 * @return List<Movie>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Movie> getMoviesByTitle(String title) {
		
		if(title==null || title.trim().length()==0) {
			throw new IllegalArgumentException("The name of the title of the movie cannot be empty!");
		}
		
		List<Movie> moviesByTitle = new ArrayList<>();
		for (Movie m : movieRepository.findMovieByTitle(title)) {
			moviesByTitle.add(m);
		}
		return moviesByTitle;
	}
	
	/**
	 * Gets a list of journals by name
	 * @param name
	 * @return List<Journal>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Journal> getJournalsByName(String name) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the journal cannot be empty!");
		}
		
		List<Journal> journalsByName = new ArrayList<>();
		for (Journal j : journalRepository.findJournalByName(name)) {
			journalsByName.add(j);
		}
		return journalsByName;
	}
	
	/**
	 * Gets a list of journals by date
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
	 * Gets a list of newspapers by name
	 * @param name
	 * @return List<Newspaper>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Newspaper> getNewspaperByName(String name) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the newspaper cannot be empty!");
		}
		
		List<Newspaper> newspapersByName = new ArrayList<>(); 
		for (Newspaper n : newspaperRepository.findNewspaperByName(name)) {
			newspapersByName.add(n);
		}
		return newspapersByName;
	}
	
	/**
	 * Gets a list of newpapers by date
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
	 * Gets a list of all of the newspapers
	 * @return List<Newsapaper>
	 * 
	 * @author Sami
	 */
	@Transactional
	public List<Newspaper> getAllNewspapers(){
		return toList(newspaperRepository.findAll());
	}
	
	/**
	 * Create a journal
	 * @param name
	 * @param date
	 * @return Journal
	 * 
	 * @author Sami
	 */
	@Transactional 
	public Journal createJournal(String name, Date date) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the journal cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		
		Journal journal=new Journal();
		journal.setName(name);
		journal.setDate(date);
		itemRepository.save(journal);
		journalRepository.save(journal);
		return journal;
	}
	
	/**
	 * Deletes a journal
	 * @return void
	 * @author Sami
	 */
	@Transactional
	public void deleteJournal(String name, Date date) {
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the journal cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		Journal journal;
		try {
			journal=journalRepository.findJournalByNameAndDate(name,date);
			journalRepository.delete(journal);
			itemRepository.delete(journal);
		}
		catch(Exception e) {
			throw new IllegalArgumentException("The newspaper with this name and date does not exist.");
		}
	}
	
	/**
	 * Gets a specific journal by name and date
	 * @param name
	 * @param date
	 * @return Journal
	 * 
	 * @author Sami
	 */
	@Transactional
	public Journal getJournal(String name, Date date) {
		
		if(name==null || name.trim().length()==0) {
			throw new IllegalArgumentException("The name of the journal cannot be empty!");
		}
		
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be null!");
		}
		
		
		Journal journal=journalRepository.findJournalByNameAndDate(name, date);
		return journal;
	}
	
	/**
	 * Gets a list of all the journals
	 * @return List<Journal>
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
