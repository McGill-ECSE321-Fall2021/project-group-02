package ca.mcgill.ecse321.librarysystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Album;

public interface AlbumRepository extends CrudRepository<Album, String>{
	Album findAlbumByTitle(String title);
	Album findAlbumByArtist(String artist);
}
