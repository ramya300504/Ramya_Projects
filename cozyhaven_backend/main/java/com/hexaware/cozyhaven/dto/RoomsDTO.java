package com.hexaware.cozyhaven.dto;

public class RoomsDTO {
	

	private int roomId;
    private String roomSize;
    private String bedType;  
    private double baseFare;
    private int maxPersons;
    private boolean ac;
    private boolean available;
    
    private String imageUrl;
    
    private Integer hotelId;
    
    
    
    
	public RoomsDTO() {
		super();
	}
	
	public RoomsDTO(int roomId, String roomSize, String bedType, double baseFare, int maxPersons,
            boolean ac, boolean available, String imageUrl, Integer hotelId) {
this.roomId = roomId;
this.roomSize = roomSize;
this.bedType = bedType;
this.baseFare = baseFare;
this.maxPersons = maxPersons;
this.ac = ac;
this.available = available;
this.imageUrl = imageUrl;
this.hotelId = hotelId;
}



	
	
	public RoomsDTO(int roomId, String roomSize, String bedType, double baseFare, boolean ac,
			boolean available,String imageUrl, int maxPersons, Integer hotelId) {
		super();
		this.roomId = roomId;
		this.roomSize = roomSize;
		this.bedType = bedType;
		this.baseFare = baseFare;
		this.imageUrl=imageUrl;
		this.ac = ac;
		this.available = available;
		this.maxPersons = maxPersons;
		this.hotelId = hotelId;
	}

	
	public RoomsDTO(Integer roomId, String roomSize, String bedType, double baseFare,
            boolean ac, boolean available, int maxPersons,
            Integer hotelId, String imageUrl) {
            this.roomId = roomId;
            this.roomSize = roomSize;
            this.bedType = bedType;
            this.baseFare = baseFare;
            this.ac = ac;
            this.available = available;
            this.maxPersons = maxPersons;
            this.hotelId = hotelId;
            this.imageUrl = imageUrl;
       }





	public String getImageUrl() {
		return imageUrl;
	}

    public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

    public int getRoomId() {
		return roomId;
	}

    public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

    public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public double getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}
	public int getMaxPersons() {
		return maxPersons;
	}
	public void setMaxPersons(int maxPersons) {
		this.maxPersons = maxPersons;
	}
	
	
	public boolean isAc() {
		return ac;
	}






	public void setAc(boolean ac) {
		this.ac = ac;
	}






	public boolean isAvailable() {
		return available;
	}






	public void setAvailable(boolean available) {
		this.available = available;
	}






	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	
	
    
    

}
