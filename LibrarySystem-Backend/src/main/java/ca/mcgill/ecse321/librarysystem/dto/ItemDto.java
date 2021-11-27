package ca.mcgill.ecse321.librarysystem.dto;

public class ItemDto {

	private int id;
	private boolean isArchived;
	private boolean isBorrowed;
	private boolean isDamaged;
	
	public ItemDto() {
		
	}
	
	public ItemDto(int id, boolean isArchived, boolean isBorrowed, boolean isDamaged) {
		this.id = id;
		this.isArchived = isArchived;
		this.isBorrowed = isBorrowed;
		this.isDamaged = isDamaged;
	}
	
	public Integer getID() {
		return id;
	}
	
	public boolean isArchived() {
		return isArchived;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
	
	public boolean isDamaged() {
		return isDamaged;
	}
	
}
