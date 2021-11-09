package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    	  BORROW ITEM SERVICE - SAMI
	 ************************************/
	/**
	 * Sets an item as borrowed by a patron
	 * @param itemName
	 * @param itemDto
	 * @param patronDto
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/borrow/{name}", "/borrow/{name}/"} )
	public ItemDto borrowItem(@PathVariable("name") String itemName, @RequestParam(name="itemId") int itemId, @RequestParam(name= "patronId") int patronId) {
		Item i= itemService.borrowItem(itemId, itemName, patronId);
		return convertToDto(i);
	}
	
	
	
	/************************************
          RETURN ITEM SERVICE - JULIE
    ************************************/
	/**
	 * Places an item back into the borrwable library contents
	 * @param itemName
	 * @param itemDto
	 * @param patronDto
	 * @return
	 * 
	 * @author Julie
	 */
	@PostMapping(value = { "/return/{itemId}", "/return/{itemId}/"})
	public ItemDto returnItem(@PathVariable("itemId") int itemId,  @RequestParam(name = "itemID") ItemDto itemDto, @RequestParam(name = "patronID") PatronDto patronDto) {
		Item i = itemService.returnItem(itemDto.getID(), patronDto.getId());
		return convertToDto(i);
	}
	
	/************************************
          ARCHIVE ITEM SERVICE - JOHN
	 ************************************/
	
	// add code
	
	/******************************************
	    VIEW LIBRARY CONTENTS - JULIE/NIILO
	 ******************************************/
	/**
	 * Finds all books under a specific name
	 * @param title The name of the book that is being searched for
	 * 
	 * @return the list of all books under the name specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/books", "/items/books/"}, params = "title")
	public List<BookDto> getBooksByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getBooksByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all books under a specific author
	 * @param title The name of the author that is being searched for
	 * 
	 * @return the list of all books under the author specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/books", "/items/books/"}, params = "author")
	public List<BookDto> getBooksByAuthor(@RequestParam String author) throws IllegalArgumentException {
		return itemService.getBooksByAuthor(author).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all albums under a specific name
	 * @param title The name of the albums that is being searched for
	 * 
	 * @return the list of all albums under the name specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/albums", "/items/albums/"}, params = "title")
	public List<AlbumDto> getAlbumsByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getAlbumsByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all albums under a specific artist
	 * @param artist The name of the artist that is being searched for
	 * 
	 * @return the list of all albums under the artist specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/albums", "/items/albums/"}, params = "artist")
	public List<AlbumDto> getAlbumsByArtist(@RequestParam String artist) throws IllegalArgumentException {
		return itemService.getAlbumsByArtist(artist).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all movies under a specific name
	 * @param title The name of the movie that is being searched for
	 * 
	 * @return the list of all movies under the name specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/movies", "/items/movies/"}, params = "title")
	public List<MovieDto> getMoviesByTitle(@RequestParam String title) throws IllegalArgumentException {
		return itemService.getMoviesByTitle(title).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all movies under a specific director
	 * @param title The name of the director that is being searched for
	 * 
	 * @return the list of all movies under the director specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/movies", "/items/movies/"}, params = "director")
	public List<MovieDto> getMoviesByDirector(@RequestParam String director) throws IllegalArgumentException {
		return itemService.getMoviesByDirector(director).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all journals under a specific name
	 * @param title The name of the journal that is being searched for
	 * 
	 * @return the list of all journals under the name specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/journals", "/items/journals/"}, params = "name")
	public List<JournalDto> getJournalsByName(@RequestParam String name) throws IllegalArgumentException {
		return itemService.getJournalsByName(name).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Finds all newspapers under a specific name
	 * @param name The name of the newspaper that is being searched for
	 * 
	 * @return the list of all newspapers under the name specified
	 * @author Niilo
	 */
	@GetMapping(value = {"/items/newspapers", "/items/newspapers/"}, params = "name")
	public List<NewspaperDto> getNewspapersByName(@RequestParam String name) throws IllegalArgumentException {
		return itemService.getNewspaperByName(name).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/****************************************************
           OTHER GENERAL ITEM METHODS - JULIE
	 ****************************************************/
	/**
	 * Get a list of all the items in the library software system
	 * @return
	 * 
	 * @author Julie
	 */
	@GetMapping(value = { "/items", "/items/" })
	public List<ItemDto> getAllItems() {
		return itemService.getAllItems().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Remove an item from the library software system
	 * @param itemId
	 * @param userID
	 * 
	 * @author Julie
	 */
	@DeleteMapping(value = {"/items/{name}", "/items/{name}"})
	public void discardItem(@PathVariable("name") String itemName,  @RequestParam(name = "itemID") ItemDto itemDto) {
		itemService.discardItem(itemDto.getID());
	}
	
	
	/****************************************************
             SPECIFIC ITEM TYPE METHODS - SAMI
	 ****************************************************/
	
	/**
	 * Creates a patron
	 * @author Sami
	 * @return Patron
	 */
	@PostMapping(value= {"/createPatron/{address}","/createPatron/{address}/"})
	public PatronDto createPatron(@PathVariable("address") String address, @RequestParam(name="city") String city, @RequestParam(name="balance") int balance, @RequestParam(name="firstName") String firstName, @RequestParam(name="lastName") String lastName) {
		Patron p=itemService.createPatron(address, balance, city, firstName, lastName);
		return convertToDto(p);
	}
	
	
	/**
	 * Adds a new book to the library software system
	 * @param bookTitle
	 * @param authorName
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/createBook/{title}", "/createBook/{title}/"} )
	public BookDto createBook(@PathVariable("title") String bookTitle, @RequestParam(name="authorName") String authorName, @RequestParam(name= "isArchived") boolean isArchived) {
		Book b= itemService.createBook(authorName, bookTitle, isArchived);
		return convertToDto(b);
	}
	
	/**
	 * Adds a new album to the library software system
	 * @param albumTitle
	 * @param artistName
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/createAlbum/{title}", "/createAlbum/{title}/"} )
	public AlbumDto createAlbum(@PathVariable("title") String albumTitle, @RequestParam(name="artistName") String artistName, @RequestParam(name= "patron") Patron patron, @RequestParam(name= "isArchived") boolean isArchived) {
		Album a= itemService.createAlbum(artistName,albumTitle, isArchived);
		return convertToDto(a);
	}
	
	/**
	 * Adds a new movie to the library software system
	 * @param movieTitle
	 * @param directorName
	 * @param patron
	 * @param isArchived
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/createMovie/{title}", "/createMovie/{title}/"} )
	public MovieDto createMovie(@PathVariable("title") String movieTitle, @RequestParam(name="directorName") String directorName, @RequestParam(name= "patron") Patron patron, @RequestParam(name= "isArchived") boolean isArchived) {
		Movie m= itemService.createMovie(directorName, movieTitle, isArchived);
		return convertToDto(m);
	}
	
	/**
	 * Adds a new newspaper to the library software system
	 * @param newspaperTitle
	 * @param newspaperDate
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/createNewspaper/{title}", "/createNewspaper/{title}/"} )
	public NewspaperDto createNewspaper(@PathVariable("title") String newspaperTitle, @RequestParam(name="newspaperDate") Date newspaperDate) {
		System.out.println("Entered the api method");
		Newspaper n= itemService.createNewspaper(newspaperTitle, newspaperDate);
		return convertToDto(n);
	}
	
	/**
	 * Adds a new journal to the library software system
	 * @param journalTitle
	 * @param journalDate
	 * @return
	 * 
	 * @author Sami
	 */
	@PostMapping(value = {"/createJournal/{title}", "/createJournal/{title}/"} )
	public JournalDto createJournal(@PathVariable("title") String journalTitle, @RequestParam(name="journalDate") Date journalDate) {
		Journal j= itemService.createJournal(journalTitle, journalDate);
		return convertToDto(j);
	}
	
	/**
	 * Gets a list of all the books in the library software system
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/items/books", "/items/books/" })
	public List<BookDto> getAllBooks() {
		return itemService.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	/**
	 * Gets a list of all the albums in the library software system
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/items/albums", "/items/albums/" })
	public List<AlbumDto> getAllAlbums() {
		return itemService.getAllAlbums().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}
	
	/**
	 * Gets a list of all the movies in the library software system
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/items/movies", "/items/movies/" })
	public List<MovieDto> getAllMovies() {
		return itemService.getAllMovies().stream().map(m -> convertToDto(m)).collect(Collectors.toList());
	}
	
	/**
	 * Gets a list of all the newspapers in the library software system
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/items/newspapers", "/items/newspapers/" })
	public List<NewspaperDto> getAllNewspapers() {
		return itemService.getAllNewspapers().stream().map(n -> convertToDto(n)).collect(Collectors.toList());
	}
	
	/**
	 * Gets a list of all the journals in the library software system
	 * @return
	 * 
	 * @author Sami
	 */
	@GetMapping(value = { "/items/journals", "/items/journals/" })
	public List<JournalDto> getAllJournals() {
		return itemService.getAllJournals().stream().map(j -> convertToDto(j)).collect(Collectors.toList());
	}
	
	
	
	/****************************************************
                   DTO CONVERSION - SAMI/JULIE
	 ****************************************************/
	private ItemDto convertToDto(Item i) {
		if (i == null) {
			throw new IllegalArgumentException("Item does not exist.");
			}
		ItemDto itemDto = new ItemDto(i.getId());
		return itemDto;
	}
	
	private MovieDto convertToDto(Movie m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Movie!");
		}
		MovieDto movieDto = new MovieDto(m.getTitle(),m.getDirector());
		return movieDto;
	}
	
	private NewspaperDto convertToDto(Newspaper n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Newspaper!");
		}
		NewspaperDto newspaperDto = new NewspaperDto(n.getName(),n.getDate());
		return newspaperDto;
	}
	

	private JournalDto convertToDto(Journal j) {
		if (j == null) {
			throw new IllegalArgumentException("There is no such Journal!");
		}
		JournalDto journalDto = new JournalDto(j.getName(),j.getDate());
		return journalDto;
	}
	
	private AlbumDto convertToDto(Album a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Album!");
		}
		AlbumDto albumDto = new AlbumDto(a.getTitle(),a.getArtist());
		return albumDto;
	}
	
	private BookDto convertToDto(Book b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		BookDto bookDto = new BookDto(b.getTitle(),b.getAuthor(), b.getId());
		return bookDto;
	}
	
	private PatronDto convertToDto(Patron p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Patron!");
		}
		PatronDto patronDto = new PatronDto(p.getId(),p.getAddress(),p.getCity(),p.getFirstName(),p.getLastName(),p.getBalance());
		return patronDto;
	}
}
