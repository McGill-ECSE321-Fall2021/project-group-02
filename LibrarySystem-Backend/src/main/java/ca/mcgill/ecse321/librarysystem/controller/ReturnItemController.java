package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.dao.ItemRepository;
import ca.mcgill.ecse321.librarysystem.dao.UserEntityRepository;
import ca.mcgill.ecse321.librarysystem.dto.ItemDto;
import ca.mcgill.ecse321.librarysystem.dto.PatronDto;
import ca.mcgill.ecse321.librarysystem.model.Item;
import ca.mcgill.ecse321.librarysystem.service.ReturnItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/returnItem")
public class ReturnItemController {
	
	@Autowired
	ReturnItemService service;
	
	@Autowired
	UserEntityRepository useRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	// return item
	@PostMapping(value = { "/return", "/return/"})
	public ItemDto returnItems(@RequestParam(name = "itemId") ItemDto itemDto, @RequestParam(name = "patronId") PatronDto patronDto) {
		Item i = service.returnItem(itemDto.getID(), patronDto.getId());
		return convertToDto(i);
	}
	
	private ItemDto convertToDto(Item i) {
		if (i == null) {
			throw new IllegalArgumentException("Item does not exist.");
		}
		ItemDto itemDto = new ItemDto(i.getId());
		return itemDto;
	}
	
	// combine 'view library contents', 'return items', 'borrow items' into one ItemService.java?
}


