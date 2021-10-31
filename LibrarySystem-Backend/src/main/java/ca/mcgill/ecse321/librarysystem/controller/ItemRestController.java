package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import ca.mcgill.ecse321.librarysystem.service.ItemService;

import java.sql.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/returnItem")
public class ItemRestController {
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	UserEntityRepository useRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	private ItemDto convertToDto(Item i) {
		if (i == null) {
			throw new IllegalArgumentException("Item does not exist.");
			}
		ItemDto itemDto = new ItemDto(i.getId());
		return itemDto;
	}
	
	/************************************
    	  BORROW ITEM SERVICE - SAMI
	 ************************************/
	@PostMapping(value = {"/borrow/{name}", "/borrow/{name}/"} )
	public ItemDto borrowItem(@PathVariable("name") String itemName, @RequestParam(name="itemID") ItemDto itemDto, @RequestParam(name= "patronId") PatronDto patronDto) {
		Item i= itemService.borrowItem(itemDto.getID(), itemName, patronDto.getId());
		return convertToDto(i);
	}
	
	/************************************
          RETURN ITEM SERVICE - JULIE
    ************************************/
	@PostMapping(value = { "/return", "/return/"})
	public ItemDto returnItem(@RequestParam(name = "itemId") ItemDto itemDto, @RequestParam(name = "patronId") PatronDto patronDto) {
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

	// add code

	/****************************************************
           OTHER GENERAL ITEM METHODS - JULIE
	 ****************************************************/

	// add code
	
	/****************************************************
             SPECIFIC ITEM TYPE METHODS - SAMI
	 ****************************************************/

	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks() {
		return itemService.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/albums", "/albums/" })
	public List<AlbumDto> getAllAlbums() {
		return itemService.getAllAlbums().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/movies", "/movies/" })
	public List<MovieDto> getAllMovies() {
		return itemService.getAllMovies().stream().map(m -> convertToDto(m)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/newspapers", "/newspapers/" })
	public List<NewspaperDto> getAllNewspapers() {
		return itemService.getAllNewspapers().stream().map(n -> convertToDto(n)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/journals", "/journals/" })
	public List<JournalDto> getAllJournals() {
		return itemService.getAllJournals().stream().map(j -> convertToDto(j)).collect(Collectors.toList());
	}
	
	private MovieDto convertToDto(Movie m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Movie!");
		}
		MovieDto movieDto = new MovieDto(m.getTitle(),m.getDirector(),m.getPatron());
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
		AlbumDto albumDto = new AlbumDto(a.getTitle(),a.getArtist(),a.getPatron());
		return albumDto;
	}
	
	private BookDto convertToDto(Book b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		BookDto bookDto = new BookDto(b.getTitle(),b.getAuthor(),b.getPatron());
		return bookDto;
	}
}
