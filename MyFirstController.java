package com.iktpreobuka.myFirstProject.controllers;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Pozdrav!";
	}
	
	@RequestMapping("/greetings/{name}")
	public String greetings(@PathVariable String name) {
		return "Hello "+ name;
	}
	
	@RequestMapping("/date")
	public Date date() {
		return new Date();
		
		
	}
	@RequestMapping("/family")
	public ArrayList<String> family() {
		ArrayList <String> family=new ArrayList<String>();
		family.add("Mariana");
		family.add("Sasa");
		family.add("Milan");
		family.add("Nikola");
		return family;
	}
	
	@RequestMapping("/myclass")
	public String myclass() {
		return "<table>"
				+ "<tr>"
				+ "<th> Ime </th>"
				+ "<th> Prezime </th>"
				+ "</tr>"
				+ "<tr>"
				+ "<td> Mariana </td>"
				+ "<td> Verlic </td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td> Branka </td>"
				+ "<td> Kablar </td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td> Mihal </td>"
				+ "<td> Supek </td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td> Srdjan </td>"
				+ "<td> Tomic </td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td> Vladimir </td>"
				+ "<td> Petrovic </td>"
				+ "</tr>"
				+ "</table>";
		
	}
	
	@RequestMapping("/intarray")
	public int[] arr() {
		int []arr= {5, 34, 45, 78, 33};
		return arr;
		
	}
	@RequestMapping("/sortarray")
	public int [] niz(){
		int[]niz=arr();
		for (int i=0; i<niz.length-1; i++) {
			for (int j=i+1; j<niz.length; j++) {
				if (niz[i]>niz[j]) {
					int p=niz[i];
					niz[i]=niz[j];
					niz[j]=p;
				}
					
			}
		}
		return niz;
		
		}
	@RequestMapping("/minmax")
	public String minmax() {
		int[]niz=arr();
		int i, j;
		int minimum=niz[0];
		int maximum =niz[0];
		
		for (i=0;i<niz.length; i++) {
			if (niz[i]>maximum) {
				maximum=niz[i];
			}
			else 
				if(niz[i]<minimum) {
					minimum=niz[i];
				}
		}
		
		return "minimum je " + minimum + ", maximum je" + maximum;
	}
	@RequestMapping("/suma")
	public String suma() {
		int suma=0;
		int N=20;
		for (int i=1; i<=N;i++) {
			suma=suma+i;
		}
		return "suma prvih "+ N + " brojeva je " + suma;	
	}
	//TODO GET - suma prvih n brojeva
	@RequestMapping ("/suma/{n}")
	public int suma(@PathVariable Integer n) {
		int[] niz = new int[n];
		int suma=0;
		for(int i=0;i<=niz.length;i++) {
			
			suma=suma+i;
		}
		return suma;
	}
	/*TODO GET - endpoint koji predstavlja englesko-srpski rečnik i koji za reč na
	srpskom vrati odgovarajući prevod na engleski jezik
	• putanja /recnik
	• DODATNO: ukoliko za traženu reč ne postoji prevod, tada ispisati „Rec
	tražena_rec ne postoji u recniku.“*/
	// 4.3.
	@RequestMapping(value="/recnik/{rec}", method = RequestMethod.GET)
		public String recnikSE(@PathVariable String rec) {
			HashMap<String, String> recnik = new HashMap<String, String>();
			recnik.put("leto", "summer");
			recnik.put("plivac", "swimmer");
			recnik.put("mit", "myth");
			recnik.put("delfin", "dolphin");
			if(recnik.get(rec)!=null)
				return recnik.get(rec);
			else
				return "Rec " + rec + " ne postoji u recniku!";
				
				}
			}
			
		
		
	
	
	
	

