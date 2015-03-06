package com.babyitemswap.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.babyitemswap.spring.DOA.Offer;
import com.babyitemswap.spring.DOA.OfferDoa;

@Service("offerService")
public class OfferService {
	private OfferDoa offerDoa;
	public OfferDoa getOfferDoa() {
		return offerDoa;
	}
	@Autowired
	public void setOfferDoa(OfferDoa offerDoa) {
		this.offerDoa = offerDoa;
	}
	public List<Offer> getCurrent(){
		return offerDoa.getOffers();
	}
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(Offer offer)
	{
		offerDoa.saveOrUpdate(offer);
	}
	public boolean hasOffer(String name) {
		if(name==null) return false;
		List<Offer> offers = offerDoa.getOffers(name);
		if(offers.size()==0)
		{
			return false;
		} 
		return true;
	}
	public Offer getOffer(String username) {
		if(username==null)
		{
			return null;
		}
		List<Offer> offers = offerDoa.getOffers(username);
		if(offers.size()==0)
		{
			return null;
		}
		return offers.get(0);
	}
	public void saveOrUpdate(Offer offer) {
		
			offerDoa.saveOrUpdate(offer);
		
		
	}
	public void delete(int id) {
		offerDoa.delete(id);
		
	}

}