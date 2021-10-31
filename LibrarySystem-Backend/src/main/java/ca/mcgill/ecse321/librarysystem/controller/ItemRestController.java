package ca.mcgill.ecse321.librarysystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dto.*;
import ca.mcgill.ecse321.librarysystem.model.Album;
import ca.mcgill.ecse321.librarysystem.model.Book;
import ca.mcgill.ecse321.librarysystem.service.ItemsService;

@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {
	@Autowired
	private ItemsService borrowItemsService;
	
	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks() {
		return borrowItemsService.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/albums", "/albums/" })
	public List<AlbumDto> getAllAlbums() {
		return borrowItemsService.getAllAlbums().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
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
