package com.iktpreobuka.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.projectNew.entities.CategoryEntity;



@RestController
@RequestMapping("projectNew/categories")
public class CategoryController {
	
	private List<CategoryEntity> getDB(){
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
		if(categories.size()==0) {
				CategoryEntity c1 = new CategoryEntity (1, " music", "description 1");
				CategoryEntity c2 = new CategoryEntity (2, " food", "description 2");
				CategoryEntity c3 = new CategoryEntity (3, " entertainment","description 3");
				categories.add(c1);
				categories.add(c2);
				categories.add(c3);
				return categories;
		}
		return categories;
		
		}

	//TODO GET dobavi sve kategorije -/categories/
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public List<CategoryEntity> getAll(){
		return getDB();
	}
	
	//TODO POST dodaj kategoriju - projectNew/categories
		@RequestMapping(method=RequestMethod.POST, value="/")
		public CategoryEntity createCategory(@RequestBody CategoryEntity c){
			List<CategoryEntity>categories=getDB();
			c.setCategoryId((new Random()).nextInt());
			categories.add(c);
			return c;		
			}
	
		//TODO PUT izmeni kategoriju - putanja /projectNew/categories/{id}
		@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
		public CategoryEntity changeCategory(@PathVariable Integer id, @RequestBody CategoryEntity c1) {
			List<CategoryEntity>categories=getDB();
			for (CategoryEntity c:categories) {
			if(c.getCategoryId().equals(id)) {
				if (c1.getCategoryName()!=null)
				c.setCategoryName(c1.getCategoryName());
				if (c1.getCategoryDescription()!=null)
				c.setCategoryDescription(c1.getCategoryDescription());
				
				return c;
			}
				
			}
			return null;
		}
		//TODO DELETE 2.6 kreirati REST endpoint koji omogućava brisanje postojeće kategorije putanja /project/categories/{id}
		@RequestMapping(method=RequestMethod.DELETE, value = "/{id}")
		public CategoryEntity deleteCategory(@PathVariable Integer id) {
			List<CategoryEntity>categories=getDB();
			Iterator<CategoryEntity> it=categories.iterator();
			while(it.hasNext()) {
				CategoryEntity c=it.next();
				if(c.getCategoryId().equals(id)) {
					it.remove();
					return c;
				}
			}
			return null;
		}
		
		//TODO GET 2.7 kreirati REST endpoint koji vraća kategoriju po vrednosti prosleđenog ID a putanja /project/categories/{id}
		@RequestMapping(value = "/{id}", method=RequestMethod.GET)
		public CategoryEntity getOne(@PathVariable Integer id){
			for (CategoryEntity c: getDB()) {
				if(c.getCategoryId().equals(id))
					return c;
			}
			return null;

		}
		
		
}
