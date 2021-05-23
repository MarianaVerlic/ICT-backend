package com.iktpreobuka.projectNew.entities;

import java.util.Date;
public class OfferEntity {
	
	protected Integer Id;
	protected String offerName;
	protected String offerDescription;
	protected Date offerCreated;
	protected Date offerExpired;
	protected Double regularPrice;
	protected Double auctionPrice;
	protected String imagePath;
	protected Integer availableOffers;
	protected Integer boughtOffers;
	protected OfferStatus status;
	
	public OfferEntity() {}

	public OfferEntity(Integer id, String offerName, String offerDescription, Date offerCreated,
			Date offerExpired, Double regularPrice, Double auctionPrice, String imagePath, Integer availableOffers,
			Integer boughtOffers, OfferStatus status) {
		
		super();
		
		Id = id;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerCreated = offerCreated;
		this.offerExpired = offerExpired;
		this.regularPrice = regularPrice;
		this.auctionPrice = auctionPrice;
		this.imagePath = imagePath;
		this.availableOffers = availableOffers;
		this.boughtOffers = boughtOffers;
		this.status = status;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public Date getOfferCreated() {
		return offerCreated;
	}

	public void setOfferCreated(Date offerCreated) {
		this.offerCreated = offerCreated;
	}

	public Date getOfferExpired() {
		return offerExpired;
	}

	public void setOfferExpired(Date offerExpired) {
		this.offerExpired = offerExpired;
	}

	public Double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public Double getAuctionPrice() {
		return auctionPrice;
	}

	public void setAuctionPrice(Double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getAvailableOffers() {
		return availableOffers;
	}

	public void setAvailableOffers(Integer availableOffers) {
		this.availableOffers = availableOffers;
	}

	public Integer getBoughtOffers() {
		return boughtOffers;
	}

	public void setBoughtOffers(Integer boughtOffers) {
		this.boughtOffers = boughtOffers;
	}

	public OfferStatus getStatus() {
		return status;
	}

	public void setStatus(OfferStatus status) {
		this.status = status;
	}

	
	
	
	
}
