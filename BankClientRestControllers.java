package com.iktpreobuka.myFirstProject.controllers;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.myFirstProject.entities.BankClientBean;



@RestController
@RequestMapping("/bankclients")
public class BankClientRestControllers {

List<BankClientBean> clients = new ArrayList<BankClientBean>();

protected List<BankClientBean> getDB(){
		if(clients.size()==0) {
		clients.add(new BankClientBean (1, "Vladimir", "Dimitrieski", "vd@gmail.com", LocalDate.of(1952, 10, 23), "Kragujevac"));
		clients.add(new BankClientBean (2, "Nebojsa", "Horvat", "nh@gmail.com", LocalDate.of(1997, 4, 1), "Novi Sad"));
		}
		return clients;
	}



//TODO GET dobevi sve klijente banke -/bankclients/
@RequestMapping(value = "/", method=RequestMethod.GET)
public List<BankClientBean> getAll(){
	return getDB();
}

//TODO GET dobavi jednog klijenta banke - /bankclients/{id}
@RequestMapping(value = "/{id}", method=RequestMethod.GET)
public BankClientBean getOne(@PathVariable Integer id){
	for (BankClientBean bcb: getDB()) {
		if(bcb.getId().equals(id))
			return bcb;
	}
	return null;

}

//TODO POST napravi jednog klijenta banke - /bankclients/{id}
@RequestMapping(method=RequestMethod.POST, value="/")
public BankClientBean createClient(@RequestBody BankClientBean bcb){
	List<BankClientBean>clients=getDB();
	bcb.setId((new Random()).nextInt());
	clients.add(bcb);
	return bcb;		
	}



//TODO PUT izmeni postojeceg  klijenta banke - /bankclients/{id}
@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
public BankClientBean changeClient(@PathVariable Integer id, @RequestBody BankClientBean changedBcb) {
	List<BankClientBean>clients=getDB();
	for (BankClientBean bcb:clients) {
	if(bcb.getId().equals(id)) {
		if (changedBcb.getEmail()!=null)
		bcb.setEmail(changedBcb.getEmail());
		if (changedBcb.getName()!=null)
		bcb.setName(changedBcb.getName());
		if (changedBcb.getPrezime()!=null)
		bcb.setPrezime(changedBcb.getPrezime());
		return bcb;
	}
		
	}
	return null;
}
//TODO DELETE obrisi klijenta banke - /bankclients/{id}
/*@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")
public BankClientBean deleteClient(@PathVariable Integer Id) {
	List<BankClientBean>clients=getDB();
	for(BankClientBean bcb : getDB()) {
	if(bcb.getId().equals(Id)){
		clients.remove(bcb);
		return bcb;
	}
	}
	return null;
}*/
	@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")
	public BankClientBean deleteC(@PathVariable Integer id) {
		List<BankClientBean>clients=getDB();
		Iterator<BankClientBean> it=clients.iterator();
		while(it.hasNext()) {
			BankClientBean bcb=it.next();
			if(bcb.getId().equals(id)) {
				it.remove();
				return bcb;
			}
		}
		return null;
	}
	
//TODO GET pronaci klijente koji imaju zadato ime i prezime bankclients/findByNameandSurname?name=Vladimir&surname=Milic	
@RequestMapping(method=RequestMethod.GET, value = "/findByNameAndSurname")
public List <BankClientBean> findByNameAndSurname(@RequestParam String name, @RequestParam String prezime){
	List<BankClientBean>retClients=new ArrayList <BankClientBean>();
	List <BankClientBean>clients = getDB();
	for (BankClientBean bcb: clients) {
		if(bcb.getName().equals(name)&bcb.getPrezime().equals(prezime)) {
			retClients.add(bcb);
		}
	}
	return retClients;
}

@RequestMapping(value = "/emails", method=RequestMethod.GET)
public List<String> writeEmail(){
	List <BankClientBean>clients = getDB();
	List<String>emails=new ArrayList<String>();
	for (BankClientBean bcb:clients) {
			emails.add(bcb.getEmail());
			
		}
	return emails;
	}
	

@RequestMapping(value = "clients/findByLetter/{slovo}", method=RequestMethod.GET)
public List<String> getByFirstLetter(@RequestParam String firstLetter){
	List <BankClientBean>clients = getDB();
	List<String>names=new ArrayList<String>();
	for (BankClientBean bcb: clients) {
		if(bcb.getName().startsWith(firstLetter)) {
			names.add(bcb.getName());
		}
	}
	return names;
}

@RequestMapping(value = "/clients/findByLetters/{slovo}", method=RequestMethod.GET)
public List<String> getByFirstLetters(@RequestParam String firstName, @RequestParam String firstSurname){
	List <BankClientBean>clients = getDB();
	List <String> namesSurnames = new ArrayList<String>();
	for (BankClientBean bcb: clients) {
		if(bcb.getName().startsWith(firstName)& bcb.getPrezime().startsWith(firstSurname)) {
			String s = (bcb.getName()+" " + bcb.getPrezime());
			namesSurnames.add(s);
		}
	}
	   return namesSurnames; 

}

/*TODO GET - endpoint koji vraća listu koja sadrži imena klijenata, koja su sortirana u redosledu koji je prosleđen kao parameter
•putanja /clients/sort/{order}
*/
@RequestMapping(value = "/clients/sort/{order}", method=RequestMethod.GET)
public List<String> sortedClients(@PathVariable String order){
	//List <BankClientBean>clients = getDB();
	List <String> sortedList = new ArrayList<String>();
	for (BankClientBean bcb: getDB()) {
		sortedList.add(bcb.getName());
	}
		if (order.equalsIgnoreCase("a")) {
			Collections.sort(sortedList);
		}
		else if (order.equalsIgnoreCase("d")) {
			Collections.sort(sortedList, Collections.reverseOrder());
		}
		return sortedList; 
}

/*TODO PUT - endpoint koji u listi klijenata banke, svakom klijentu, postavlja polje boniteta‘P’ (pozitivan) ako je klijent mlađi od 65 godina ili ‘N’ negativan ako je klijent stariji od 65 godina
•putanja /clients/bonitet
•u klasu BankClientBean dodati atribute datum rođenja i bonitet */

@RequestMapping(value = "/clients/bonitet", method=RequestMethod.PUT)
public List<BankClientBean> bonitet(){
	List <BankClientBean> bonitet = new ArrayList<>();
	for (BankClientBean bcb: getDB()) {
		LocalDate now = LocalDate.now();
		Period diff = Period.between(bcb.getDatumRodjenja(), now);
		if (diff.getYears()>65) {
			bcb.setBonitet("N");
		}
		else bcb.setBonitet("P");
		bonitet.add(bcb);
	}
	return bonitet;
}
/*TODO DELETE - endpoint koji briše klijenta iz liste klijenta ukoliko klijent nema jednu od vrednosti: ime, prezime, email
•putanja /clients/delete
*/
@RequestMapping(method=RequestMethod.DELETE, value = "/clients/delete")
public List <BankClientBean> deleteNoValue() {
	for (BankClientBean bcb: getDB()) {
		if (bcb.getName().equals(null) ||bcb.getPrezime().equals(null)|| bcb.getEmail().equals(null)) {
			getDB().remove(bcb);		
		}
	}return getDB();
	
}
/*TODO GET - endpoint koji vraća ukupan broj klijenata u listi klijenata koji imaju manje od broja godina koje je prosleđeno kao parametar
•putanja /clients/countLess/{years} */

@RequestMapping(value = "/clients/countLess/{years}", method=RequestMethod.GET)
public int clientsYounger(@PathVariable Integer years){
	List <BankClientBean>clients = getDB();
	int i=0;
	for (BankClientBean bcb: clients) {
		LocalDate now = LocalDate.now();
		Period diff = Period.between(bcb.getDatumRodjenja(), now);
		if (diff.getYears()<years) {
			i++;
		}
	}
	return i;
	   
}
/*TODO GET - endpoint kojiprosečan broj godina klijenata iz liste klijenata
•putanja /clients/averageYears */
@RequestMapping(value = "/clients/averageYears", method=RequestMethod.GET)
public int averageYears(){
	int sum=0;
	for (BankClientBean bcb: getDB()) {
		LocalDate now = LocalDate.now();
		Period diff = Period.between(bcb.getDatumRodjenja(), now);
			sum+=diff.getYears();
			
		}return sum/getDB().size();

}


/*TODO PUT - endpoint koji omogućuje izmenu mesta stanovanja klijenta putanja /clients/changelocation/{clientId} u klasu BankClientBean dodati atribut grad
•novu vrednost mestastanovanjaprosleditikaoQueryParameter	*/
@RequestMapping (value= "/clients/changelocation/{clientId}", method=RequestMethod.PUT)
public BankClientBean changeTheCity(@PathVariable Integer clientId, @RequestParam String noviGrad) {
   for (BankClientBean bcb: getDB()) {
	   if (bcb.getId().equals(clientId)) {
		   bcb.setGrad(noviGrad);   
	   }return bcb;
   }
   return null;
}

/*TODO GET - endpoint koji vraća klijente banke koji žive u gradu koji je prosleđen kao parametar
•putanja /clients/from/{city} */
@RequestMapping(value = "/clients/from/{city}", method=RequestMethod.GET)
public List<BankClientBean> clientsFromCity(@PathVariable String city){
	List <BankClientBean> clientsCity = new ArrayList<BankClientBean>();
	for (BankClientBean bcb: getDB()) {
		if(bcb.getGrad().equalsIgnoreCase(city)) 
			clientsCity.add(bcb);
		}
		return clientsCity; 
	}

//TODO GET-klijenti banke koji žive u zadatom gradu i koji su mladji od zadatog
// broja god putanja /clients/findByCityAndAge 
@RequestMapping(value = "/clients/findByCityAndAge", method=RequestMethod.GET)
public List<BankClientBean> clientsFromCityAge(@RequestParam String grad, @RequestParam Integer age){
	List <BankClientBean> clientsCityAge = new ArrayList<BankClientBean>();
	for (BankClientBean bcb: getDB()) {
		LocalDate now = LocalDate.now();
		Period per = Period.between(bcb.getDatumRodjenja(), now);
		if ((bcb.getGrad().equals(grad))&&((per.getYears()<age) )) 
			clientsCityAge.add(bcb);
		}
		return clientsCityAge; 
	}








}



	
		
	




















