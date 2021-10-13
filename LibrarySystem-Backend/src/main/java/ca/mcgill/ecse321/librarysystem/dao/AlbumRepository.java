package ca.mcgill.ecse321.librarysystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarysystem.model.Album;

public interface AlbumRepository extends CrudRepository<Album, String>{
	Album findAlbumByTitle(String title);
	List<Album> findAlbumByArtist(String artist);
}
