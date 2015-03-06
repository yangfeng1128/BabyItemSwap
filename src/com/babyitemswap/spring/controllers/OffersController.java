package com.babyitemswap.spring.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.babyitemswap.spring.DOA.FormValidationGroup;
import com.babyitemswap.spring.DOA.Offer;
import com.babyitemswap.spring.service.OfferService;

@Controller

public class OffersController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1602811671003770141L;
	/*@RequestMapping("/")
	public String showHome(HttpSession session){
		session.setAttribute("name", "Tom");
		return "home";
	}*/
	/*
	@RequestMapping("/")
	public ModelAndView showHome(){
		ModelAndView mv=new ModelAndView("home");
		Map<String,Object> attributeMap=mv.getModel();
		attributeMap.put("name", "Becky");
		
		return mv;
	}
	
	*/
	private OfferService offerService;
	
	
	public OfferService getOfferService() {
		return offerService;
	}

    @Autowired
	public void setOfferService(OfferService offerService) {
		this.offerService = offerService;
	}
    @RequestMapping("/upload")
    public String showUpload()
    {
    	return "upload";
    }


	@RequestMapping("/offers")
	public String showOffers(Model model,Principal principal){
//		offerService.throwTestException();
		List<Offer> offers=offerService.getCurrent();
		model.addAttribute("offers",offers);
		boolean hasOffer =false;
		if(principal != null)
		{
			hasOffer =offerService.hasOffer(principal.getName());
		}
		model.addAttribute("hasOffer", hasOffer);
		
		return "offers";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String showTest(Model model,@RequestParam("id") String id){
		System.out.println("ID is " +id);
		return "home";
	}
	
	@RequestMapping("/aboutus")
	public String aboutUs()
	{
		return "aboutus";
	}
	
	@RequestMapping("/contactus")
	public void contactUs(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			request.getRequestDispatcher("/message?uid='admin'").forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal){
		Offer offer = null;
		if (principal !=null)
		{
			String username=principal.getName();
			offer=offerService.getOffer(username);
		}
		if(offer == null)
		{
			offer=new Offer();
		}
		model.addAttribute("offer", offer);
		return "createoffer";
	}
	
	@RequestMapping(value="/images",method=RequestMethod.GET)
	public void showImage(Model model,@RequestParam(value="userName") String userName, @RequestParam(value="offerId") Integer offerId,
			HttpServletRequest request, HttpServletResponse response)
	{
		String filename=userName+"-"+offerId+".jpeg";
		try {
			request.getRequestDispatcher("/offerImages/"+filename).forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
//	@RequestMapping(value="/docreate",method=RequestMethod.POST)
//	public String doCreate(Model model,@Validated(value=FormValidationGroup.class) Offer offer, BindingResult result,Principal principal, @RequestParam(value="delete", required=false) String delete){
//		if(result.hasErrors())
//		{
//			return "createoffer";
//		} 
//		if (delete == null){
//			String username = principal.getName();
//			offer.getUser().setUsername(username);
//			
//			offerService.saveOrUpdate(offer);
//			return "offercreated";
//		} else {
//			offerService.delete(offer.getId());
//		}
//		
//		
//		//offerService.create(offer);
//		return "offerdeleted";
//	}
	
	@RequestMapping(value="/docreate",method=RequestMethod.POST)
	public String doCreate(Model model,@Validated(value=FormValidationGroup.class) Offer offer, 
			BindingResult result,Principal principal, @RequestParam(value="delete", required=false) String delete,
			@RequestParam(value="uploadimage", required=true) MultipartFile uploadimage)
	{
		boolean hasOffer =false;
		if(principal != null)
		{
			hasOffer =offerService.hasOffer(principal.getName());
		}
		model.addAttribute("hasOffer", hasOffer);
		
		if(result.hasErrors())
		{
			System.out.println("error do create");
			return "createoffer";
		} 
		
		if (delete == null){
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offerService.saveOrUpdate(offer);
			if (!uploadimage.isEmpty()) {
	//			System.out.println("offer");
				if (!uploadimage.getContentType().equals("image/jpeg")) {
					return "createoffer";
				}
				try {
					String imageName = username + "-" + offer.getId() + ".jpeg";
					uploadImage(imageName, uploadimage);
				} catch (RuntimeException | IOException e) {
					e.printStackTrace();
				}
			}
			
			return "offercreated";
		} else {
			offerService.delete(offer.getId());
		}
		
		
		//offerService.create(offer);
		return "offerdeleted";
	}
    
	private void uploadImage(String filename, MultipartFile image)
	throws RuntimeException, IOException
	{
		try{
			
			File file=new File("/Users/fengyang/Documents/java/myjava/upload/"+filename);
			if (file.exists())
			{
				file.delete();
			}
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(image.getBytes());
			fos.close();
		}
		catch(IOException e)
		{
			throw e;
		}
	
	}
	
}
