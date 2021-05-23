package com.iktpreobuka.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.projectNew.entities.OfferEntity;
import com.iktpreobuka.projectNew.entities.OfferStatus;


@RestController
@RequestMapping(path="/projectNew/offers")
public class OfferControler {
	
	protected List<OfferEntity> getDB(){
		List<OfferEntity> offers = new ArrayList<OfferEntity>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
			OfferEntity o1=new OfferEntity(1, "4 tickets for Killers concert", "The world unseen!", 
					new Date(), cal.getTime(), 100000.00, 8000.00, " ", 10, 0, OfferStatus.WAIT_FOR_APPROVING);
			OfferEntity o2=new OfferEntity(2, "VIVAX 456FG", "Don't miss!", 
					new Date(), cal.getTime(), 200000.00, 16000.00, " ", 5, 0, OfferStatus.WAIT_FOR_APPROVING);
			OfferEntity o3=new OfferEntity(3, "Dinner for two in Kuca Mala", "Excellent offer!", 
					new Date(), cal.getTime(), 4000.00, 1600.00, " ", 4, 0, OfferStatus.WAIT_FOR_APPROVING);
			offers.add(o1);
			offers.add(o2);
			offers.add(o3);
			return offers;
		}
	
	//TODO GET dobavi sve ponude -/offers/
		@RequestMapping(value = "/", method=RequestMethod.GET)
		public List<OfferEntity> getAll(){
			return getDB();
		}
		
	//TODO POST dodaj kategoriju - projectNew/categories
		@RequestMapping(method=RequestMethod.POST, value="/")
				public OfferEntity createOffer(@RequestBody OfferEntity o){
					List<OfferEntity>offers=getDB();
					o.setId((new Random()).nextInt());
					offers.add(o);
					return o;		
					}	
		
		//TODO PUT izmeni kategoriju - putanja /projectNew/offers/{id}
				@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
				public OfferEntity changeOffer(@PathVariable Integer id, @RequestBody OfferEntity o1) {
					List<OfferEntity>offers=getDB();
					for (OfferEntity o:offers) {
					if(o.getId().equals(id)) {
						if (o1.getOfferName()!=null)
						o.setOfferName(o1.getOfferName());
						if (o1.getOfferDescription()!=null)
						o.setOfferDescription(o1.getOfferDescription());
						if (o1.getOfferCreated()!=null)
						o.setOfferCreated(o1.getOfferCreated());
						if (o1.getOfferExpired()!=null)
						o.setOfferExpired(o1.getOfferExpired());
						if (o1.getRegularPrice()!=null)
						o.setRegularPrice(o1.getRegularPrice());
						if (o1.getAuctionPrice()!=null)
						o.setAuctionPrice(o1.getAuctionPrice());
						if (o1.getImagePath()!=null)
						o.setImagePath(o1.getImagePath());
						if (o1.getAvailableOffers()!=null)
						o.setAvailableOffers(o1.getAvailableOffers());
						if (o1.getBoughtOffers()!=null)
						o.setBoughtOffers(o1.getBoughtOffers());
						
						return o;
					}
						
					}
					return null;
				}
	
	//TODO DELETE 3.6 kreirati REST endpoint koji omogućava brisanje postojeće ponude putanja /project/offers/{id}
				@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")
				public OfferEntity deleteOffer(@PathVariable Integer id) {
					List<OfferEntity>offers=getDB();
					Iterator<OfferEntity> it=offers.iterator();
					while(it.hasNext()) {
						OfferEntity oe=it.next();
						if(oe.getId().equals(id)) {
							it.remove();
							return oe;
						}
					}
					return null;
				}
				
  //TODO GET 3.7 kreirati REST endpoint koji vraća ponudu po vrednosti prosleđenog ID a putanja /project/offers/{id}
				@RequestMapping(value = "/{id}", method=RequestMethod.GET)
				public OfferEntity getOne(@PathVariable Integer id){
					for (OfferEntity oe: getDB()) {
						if(oe.getId().equals(id))
							return oe;
					}
					return null;

				}
//TODO PUT 3.8 kreirati REST endpoint koji omogućava promenu vrednosti atributa offer status postojeće 
				//ponude putanja /project/ changeOffer /{id}/status/{status}
				@RequestMapping(value = "/change/{id}/status/{status}", method=RequestMethod.PUT)
				public OfferEntity changeOfferStatus(@PathVariable Integer id, @PathVariable OfferStatus status) {
					List<OfferEntity>offers=getDB();
					for (OfferEntity oe:offers) {
					if(oe.getId().equals(id)) {
							oe.setStatus(status);
						
						return oe;
					}
						
					}
					return null;
				}
				
				
	//TODO GET 3.9 kreirati REST endpoint koji omogućava pronalazak svih ponuda čija se akcijska cena nalazi u odgovarajućem
				//rasponu putanja /project/findByPrice/{lowerPrice}/and/{upperPrice}
				@RequestMapping(value = "/findByPrice/{lowerPrice}/and/{upperPrice}", method=RequestMethod.GET)
				public List<OfferEntity> findByPrice(@PathVariable Double lowerPrice, @PathVariable Double upperPrice){
					List <OfferEntity> offersBetween = new ArrayList<OfferEntity>();
					for (OfferEntity oe: getDB()) {
						if (oe.getAuctionPrice()> lowerPrice && oe.getAuctionPrice()<upperPrice) {
							offersBetween.add(oe);
						}
					}
						return offersBetween; 
						
					}

				
			

}
