package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.UserEntityRepository;
import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.model.Journal;
import ca.mcgill.ecse321.librarysystem.model.Movie;
import ca.mcgill.ecse321.librarysystem.model.Newspaper;
import ca.mcgill.ecse321.librarysystem.model.Patron;
import ca.mcgill.ecse321.librarysystem.service.ItemService;

import java.sql.Date;

@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

	@Autowired
	ItemService itemService;

	@Autowired
	UserEntityRepository useRepository;

	@Autowired
	ItemRepository itemRepository;

	/************************************
	 * BORROW ITEM SERVICE - SAMI
	 ************************************/
	/**
	 * Sets an item as borrowed by a patron
	 * 
	 * @param itemName The name of the item that is being borrowed
	 * @param itemId The ID of the item that is being borrowed
	 * @param patronId The ID of the patron that is borrowing the item
	 *
	 * @return The item that is being borrowed
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/borrow/{name}", "/borrow/{name}/" })
	public ItemDto borrowItem(@PathVariable("name") String itemName, @RequestParam(name = "itemId") int itemId,
			@RequestParam(name = "patronId") int patronId) {
		Item i = itemService.borrowItem(itemId, itemName, patronId);
		return convertToDto(i);
	}

	/************************************
	 * RETURN ITEM SERVICE - JULIE
	 ************************************/
	/**
	 * Places an item back into the borrwable library contents
	 * 
	 * @param itemId The ID of the item that is being returned
	 * @param patronId The ID of the patron that is returning the item
	 * 
	 * @return The item that is being returned
	 * 
	 * @author Julie Chen
	 */
	
	@PostMapping(value = { "/return", "/return/" })
	public ItemDto returnItem(@RequestParam(name = "itemId") int itemId, @RequestParam(name = "patronId") int patronId) {
		Item i = itemService.returnItem(itemId, patronId);
		return convertToDto(i);
	}

	/************************************
	 * ARCHIVE ITEM SERVICE - JOHN
	 ************************************/
	/**
	 * Places an item into the archived library section
	 * 
	 * @param itemId The ID of the item that is being archived
	 * @param headLibrarianID The ID of the head librarian 
	 *
	 * @return The item that is being archived
	 * 
	 * @author John Park
	 */
	
	@PostMapping(value = { "/archive", "/archive/" })
	public ItemDto archiveItem(@RequestParam(name = "itemID") int itemId,
			@RequestParam(name = "headLibrarianID") int headLibrarianID) {
		Item i = itemService.archiveItem(itemId, headLibrarianID);
		return convertToDto(i);

	}

	/******************************************
	 * VIEW LIBRARY CONTENTS - JULIE/NIILO
	 ******************************************/
	/**
	 * Finds all books under a specific name
	 * 
	 * @param title The name of the book that is being searched for
	 * 
	 * @return the list of all books under the name specified
	 * 
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/books", "/items/books/" }, params = "title")
	public List<BookDto> getBooksByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getBooksByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all books under a specific author
	 * 
	 * @param title The name of the author that is being searched for
	 * 
	 * @return the list of all books under the author specified
	 * 
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/books", "/items/books/" }, params = "author")
	public List<BookDto> getBooksByAuthor(@RequestParam String author) throws IllegalArgumentException {
		return itemService.getBooksByAuthor(author).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all albums under a specific name
	 * 
	 * @param title The name of the albums that is being searched for
	 * 
	 * @return the list of all albums under the name specified
	 * 
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/albums", "/items/albums/" }, params = "title")
	public List<AlbumDto> getAlbumsByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getAlbumsByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all albums under a specific artist
	 * 
	 * @param artist The name of the artist that is being searched for
	 * 
	 * @return the list of all albums under the artist specified
	 * 
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/albums", "/items/albums/" }, params = "artist")
	public List<AlbumDto> getAlbumsByArtist(@RequestParam String artist) throws IllegalArgumentException {
		return itemService.getAlbumsByArtist(artist).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all movies under a specific name
	 * 
	 * @param title The name of the movie that is being searched for
	 * 
	 * @return the list of all movies under the name specified
	 *
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/movies", "/items/movies/" }, params = "title")
	public List<MovieDto> getMoviesByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getMoviesByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all movies under a specific director
	 * 
	 * @param title The name of the director that is being searched for
	 * 
	 * @return the list of all movies under the director specified
	 *
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/movies", "/items/movies/" }, params = "director")
	public List<MovieDto> getMoviesByDirector(@RequestParam String director) throws IllegalArgumentException {
		return itemService.getMoviesByDirector(director).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all journals under a specific name
	 * 
	 * @param title The name of the journal that is being searched for
	 * 
	 * @return the list of all journals under the name specified
	 *
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/journals", "/items/journals/" }, params = "name")
	public List<JournalDto> getJournalsByName(@RequestParam String name) throws IllegalArgumentException {
		return itemService.getJournalsByName(name).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Finds all newspapers under a specific name
	 * 
	 * @param name The name of the newspaper that is being searched for
	 * 
	 * @return the list of all newspapers under the name specified
	 *
	 * @author Niilo Vuokila
	 */
	
	@GetMapping(value = { "/items/newspapers", "/items/newspapers/" }, params = "name")
	public List<NewspaperDto> getNewspapersByName(@RequestParam String name) throws IllegalArgumentException {
		return itemService.getNewspaperByName(name).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/****************************************************
	 * OTHER GENERAL ITEM METHODS - JULIE
	 ****************************************************/
	/**
	 * Get a list of all the items in the library software system
	 * 
	 * @return the list of all items in the library
	 * 
	 * @author Julie Chen
	 */
	
	@GetMapping(value = { "/items", "/items/" })
	public List<ItemDto> getAllItems() {
		return itemService.getAllItems().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Remove an item from the library software system
	 * 
	 * @param itemId The ID of the item to be removed
	 * @param headLibrarianID The ID of the head librarian
	 * 
	 * @author Julie Chen
	 */
	
	@DeleteMapping(value = { "/items/discard", "/items/discard/" })
	public void discardItem(@RequestParam(name = "itemID") int itemId,
			@RequestParam(name = "headLibrarianID") int headLibrarianID) {
		itemService.discardItem(itemId, headLibrarianID);
	}

	/**
	 * Set an item as damaged
	 * 
	 * @param itemID The ID of the item to be set as damaged
	 * @param headLibrarianID The ID of the head librarian
	 *
	 * @author Julie Chen
	 */
	
	@PostMapping(value = { "/items/setDamaged", "/items/setDamaged/" })
	public void setDamagedItem(@RequestParam(name = "itemId") int itemID,
			@RequestParam(name = "headLibrarianID") int headLibrarianID) {
		itemService.setDamagedItem(itemID, headLibrarianID);
	}
	
	/**
	 * Set an item as available
	 * 
	 * @param itemID The ID of the item to be set as available
	 * @param headLibrarianID The ID of the head librarian
	 *
	 * @author Julie Chen
	 */

	@PostMapping(value = { "/items/available", "/items/available/" })
	public void makeItemBorrowable(@RequestParam(name = "itemID") int itemID,
			@RequestParam(name = "headLibrarianID") int headLibrarianID) {
		itemService.makeItemBorrowable(itemID, headLibrarianID);
	}

	/****************************************************
	 * SPECIFIC ITEM TYPE METHODS - SAMI
	 ****************************************************/

	/**
	 * Creates a patron
	 *
	 * @param address The address of the patron
	 * @param city The city of the patron
	 * @param balance The starting balance of the patron
	 * @param fistName The first name of the patron
	 * @param lastName The last name of the patron
	 * 
	 * @return The new patron
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/createPatron/{address}", "/createPatron/{address}/" })
	public PatronDto createPatron(@PathVariable("address") String address, @RequestParam(name = "city") String city,
			@RequestParam(name = "balance") int balance, @RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName) {
		Patron p = itemService.createPatron(address, balance, city, firstName, lastName);
		return convertToDto(p);
	}

	/**
	 * Deletes a patron
	 *
	 * @param address The address of the patron
	 *
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deletePatron/{address}", "/deletePatron/{address}/" })
	public void deletePatron(@PathVariable("address") String address) {
		itemService.deletePatron(address);
	}

	/**
	 * Adds a new book to the library software system
	 * 
	 * @param bookTitle The title of the new book
	 * @param authorName The name of the author of the new book
	 * @param isArchived The archive status of the book
	 *
	 * @return The new book that was added to the library
	 * 
	 * @author Sami Ait Ouahmane
	 */ 
	
	@PostMapping(value = { "/createBook/{title}", "/createBook/{title}/" })
	public BookDto createBook(@PathVariable("title") String bookTitle,
			@RequestParam(name = "authorName") String authorName, @RequestParam(name = "isArchived") boolean isArchived) {
		Book b = itemService.createBook(authorName, bookTitle, isArchived);
		return convertToDto(b);
	}

	/**
	 * Deletes a book
	 * 
	 * @param bookTitle The title of the book to be deleted
	 * @param authorName The name of the author of the book to be deleted
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deleteBook/{title}", "/deleteBook/{title}/" })
	public void deleteBook(@PathVariable("title") String bookTitle,
			@RequestParam(name = "authorName") String authorName) {
		itemService.deleteBook(authorName, bookTitle);
	}

	/**
	 * Adds a new album to the library software system
	 * 
	 * @param albumTitle The title of the new album
	 * @param artistName The name of the artist of the new album
	 * @param isArchived The archive status of the new album
	 *
	 * @return The new album to be added to the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/createAlbum/{title}", "/createAlbum/{title}/" })
	public AlbumDto createAlbum(@PathVariable("title") String albumTitle,
			@RequestParam(name = "artistName") String artistName, @RequestParam(name = "isArchived") boolean isArchived) {
		Album a = itemService.createAlbum(artistName, albumTitle, isArchived);
		return convertToDto(a);
	}

	/**
	 * Deletes a album
	 * 
	 * @param albumTitle The title of the album to be deleted
	 * @param artistName The name of the artist of the album to be deleted
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deleteAlbum/{title}", "/deleteAlbum/{title}/" })
	public void deleteAlbum(@PathVariable("title") String albumTitle,
			@RequestParam(name = "artistName") String artistName) {
		itemService.deleteAlbum(artistName, albumTitle);
	}

	/**
	 * Adds a new movie to the library software system
	 * 
	 * @param movieTitle The title of the new movie to be added
	 * @param directorName The name of the director of the new movie
	 * @param isArchived The archive status of the new movie
	 *
	 * @return The new movie to be added to the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/createMovie/{title}", "/createMovie/{title}/" })
	public MovieDto createMovie(@PathVariable("title") String movieTitle,
			@RequestParam(name = "directorName") String directorName, @RequestParam(name = "isArchived") boolean isArchived) {
		Movie m = itemService.createMovie(directorName, movieTitle, isArchived);
		return convertToDto(m);
	}

	/**
	 * Deletes movie
	 * 
	 * @param movieTitle The title of the movie to be deleted
	 * @param directorName The name of the director of the movie to be deleted
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deleteMovie/{title}", "/deleteMovie/{title}/" })
	public void deleteMovie(@PathVariable("title") String movieTitle,
			@RequestParam(name = "directorName") String directorName) {
		itemService.deleteMovie(directorName, movieTitle);
	}

	/**
	 * Adds a new newspaper to the library software system
	 * 
	 * @param newspaperTitle The title of the newspaper to be added
	 * @param newspaperDate The date of the newspaper to be added
	 * 
	 * @return The newspaper to be added to the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/createNewspaper/{title}", "/createNewspaper/{title}/" })
	public NewspaperDto createNewspaper(@PathVariable("title") String newspaperTitle,
			@RequestParam(name = "newspaperDate") Date newspaperDate) {
		Newspaper n = itemService.createNewspaper(newspaperTitle, newspaperDate);
		return convertToDto(n);
	}

	/**
	 * Deletes newspaper
	 * 
	 * @param newspaperTitle The title of the newspaper to be deleted
	 * @param newspaperDate The date of the newspaper to be deleted
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deleteNewspaper/{title}", "/deleteNewspaper/{title}/" })
	public void deleteNewspaper(@PathVariable("title") String newspaperTitle,
			@RequestParam(name = "newspaperDate") Date newspaperDate) {
		itemService.deleteNewspaper(newspaperTitle, newspaperDate);
	}

	/**
	 * Adds a new journal to the library software system
	 * 
	 * @param journalTitle The title of the journal to be added
	 * @param journalDate The date of the journal to be added
	 *
	 * @return The new journal to be added to the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@PostMapping(value = { "/createJournal/{title}", "/createJournal/{title}/" })
	public JournalDto createJournal(@PathVariable("title") String journalTitle,
			@RequestParam(name = "journalDate") Date journalDate) {
		Journal j = itemService.createJournal(journalTitle, journalDate);
		return convertToDto(j);
	}

	/**
	 * Deletes journal
	 * 
	 * @param journalTitle The title of the journal to be deleted
	 * @param journalDate The date of the journal to be deleted
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@DeleteMapping(value = { "/deleteJournal/{title}", "/deleteJournal/{title}/" })
	public void deleteJournal(@PathVariable("title") String journalTitle,
			@RequestParam(name = "journalDate") Date journalDate) {
		itemService.deleteNewspaper(journalTitle, journalDate);
	}

	/**
	 * Gets a list of all the books in the library software system
	 * 
	 * @return the list of all books in the library
	 * 
	 * @author Sami Ait OUahmane
	 */
	
	@GetMapping(value = { "/items/books", "/items/books/" })
	public List<BookDto> getAllBooks() {
		return itemService.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the albums in the library software system
	 * 
	 * @return the list of all albums in the library
	 * 
	 * @author Sami Ait OUahmane
	 */
	
	@GetMapping(value = { "/items/albums", "/items/albums/" })
	public List<AlbumDto> getAllAlbums() {
		return itemService.getAllAlbums().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the movies in the library software system
	 * 
	 * @return the list of all movies in the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@GetMapping(value = { "/items/movies", "/items/movies/" })
	public List<MovieDto> getAllMovies() {
		return itemService.getAllMovies().stream().map(m -> convertToDto(m)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the newspapers in the library software system
	 * 
	 * @return the list of all newspaper in the library
	 * 
	 * @author Sami Ait Ouahmane
	 */
	
	@GetMapping(value = { "/items/newspapers", "/items/newspapers/" })
	public List<NewspaperDto> getAllNewspapers() {
		return itemService.getAllNewspapers().stream().map(n -> convertToDto(n)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the journals in the library software system
	 * 
	 * @return the list of all journals in the library
	 *  
	 * @author Sami Ait Ouahmane
	 */
	
	@GetMapping(value = { "/items/journals", "/items/journals/" })
	public List<JournalDto> getAllJournals() {
		return itemService.getAllJournals().stream().map(j -> convertToDto(j)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the books borrowed by a patron
	 * 
	 * @param id The ID of the patron
	 *
	 * @return the list of all the books borrowed by a patron
	 * 
	 * @author Hyunbum Cho
	 */
	
	@GetMapping(value = { "/borrowedItems/books", "/borrowedItems/books/" })
	public List<BookDto> getAllBorrowedBooks(@RequestParam(name = "id") int id) throws IllegalArgumentException {
		return itemService.getBooksBorrowedByPatron(id).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the albums borrowed by a patron
	 *
	 * @param id The ID of the patron
	 *
	 * @return the list of all the albums borrowed by a patron
	 * 
	 * @author Hyunbum Cho
	 */
	
	@GetMapping(value = { "/borrowedItems/albums", "/borrowedItems/albums/" })
	public List<AlbumDto> getAllBorrowedAlbums(@RequestParam(name = "id") int id) throws IllegalArgumentException {
		return itemService.getAlbumsBorrowedByPatron(id).stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}

	/**
	 * Gets a list of all the movies borrowed by a patron
	 * 
	 * @param id The ID of the patron
	 *
	 * @return the list of all the movies borrowed by a patron
	 * 
	 * @author Hyunbum Cho
	 */
	
	@GetMapping(value = { "/borrowedItems/movies", "/borrowedItems/movies/" })
	public List<MovieDto> getAllBorrowedMovies(@RequestParam(name = "id") int id) throws IllegalArgumentException {
		return itemService.getMoviesBorrowedByPatron(id).stream().map(m -> convertToDto(m)).collect(Collectors.toList());
	}

	/****************************************************
	 * DTO CONVERSION - SAMI/JULIE
	 ****************************************************/
	/**
	* Converts Item object to ItemDto object
	*
	* @param i The Item object to be converted 
	*
	* @return the ItemDto object of the item
	*
	* @author Sami Ait Ouahmane
	*/
	
	private ItemDto convertToDto(Item i) {
		if (i == null) {
			throw new IllegalArgumentException("Item does not exist.");
		}
		ItemDto itemDto = new ItemDto(i.getId(), i.getIsArchived(), i.getIsBorrowed(), i.getIsDamaged());
		return itemDto;
	}
	
	/**
	* Converts Movie object to MovieDto object
	*
	* @param m The Movie object to be converted 
	*
	* @return the MovieDto object of the movie
	*
	* @author Sami Ait Ouahmane
	*/

	private MovieDto convertToDto(Movie m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Movie!");
		}
		MovieDto movieDto = new MovieDto(m.getTitle(), m.getDirector(), m.getIsBorrowed(), m.getId());
		return movieDto;
	}
	
	/**
	* Converts Newspaper object to NwespaperDto object
	*
	* @param n The Newspaper object to be converted 
	*
	* @return the NewspaperDto object of the newspaper
	*
	* @author Sami Ait Ouahmane
	*/

	private NewspaperDto convertToDto(Newspaper n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Newspaper!");
		}
		NewspaperDto newspaperDto = new NewspaperDto(n.getName(), n.getDate(), n.getId());
		return newspaperDto;
	}
	
	/**
	* Converts Journal object to JournalDto object
	*
	* @param j The Journal object to be converted 
	*
	* @return the JournalDto object of the journal
	*
	* @author Julie Chen
	*/

	private JournalDto convertToDto(Journal j) {
		if (j == null) {
			throw new IllegalArgumentException("There is no such Journal!");
		}
		JournalDto journalDto = new JournalDto(j.getName(), j.getDate(), j.getId());
		return journalDto;
	}
	
	/**
	* Converts Album object to AlbumDto object
	*
	* @param a The Album object to be converted 
	*
	* @return the AlbumDto object of the album
	*
	* @author Julie Chen
	*/

	private AlbumDto convertToDto(Album a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Album!");
		}
		AlbumDto albumDto = new AlbumDto(a.getTitle(), a.getArtist(), a.getIsBorrowed(), a.getId());
		return albumDto;
	}
	
	/**
	* Converts Book object to BookDto object
	*
	* @param b The Book object to be converted 
	*
	* @return the BookDto object of the book
	*
	* @author Julie Chen
	*/

	private BookDto convertToDto(Book b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}

		BookDto bookDto = new BookDto(b.getTitle(), b.getAuthor(), b.getIsBorrowed(), b.getId());
		return bookDto;
	}
	
	/**
	* Converts Patron object to PatronDto object
	*
	* @param p The Patron object to be converted 
	*
	* @return the PatronDto object of the patron
	*
	* @author Julie Chen
	*/

	private PatronDto convertToDto(Patron p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Patron!");
		}
		PatronDto patronDto = new PatronDto(p.getId(), p.getAddress(), p.getCity(), p.getFirstName(), p.getLastName(),
				p.getBalance());
		return patronDto;
	}
}
