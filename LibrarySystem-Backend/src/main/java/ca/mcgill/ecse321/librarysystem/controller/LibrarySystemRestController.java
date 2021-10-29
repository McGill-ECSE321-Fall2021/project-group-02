package ca.mcgill.ecse321.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarysystem.service.ReturnItemService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarySystemRestController {
	@Autowired
	private ReturnItemService service;
}
