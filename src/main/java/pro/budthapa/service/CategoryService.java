/**
 * 
 */
package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Category;
import pro.budthapa.repo.CategoryRepository;

/**
 * @author budthapa
 * Mar 8, 2017
 * 
 */
@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepo;

	public List<Category> findAllCategories() {
		List<Category> categoryList = new ArrayList<>();
		categoryRepo.findAll().forEach(categoryList::add);
		return categoryList;
	}

	/**
	 * @param category2
	 */
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public Category findCategory(Long id){
		return categoryRepo.exists(id)?categoryRepo.findOne(id):null;
	}

	/**
	 * @param category
	 */
	public void updateCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public Category checkDuplicateCategory(String name){
		return categoryRepo.findByCategoryName(name);
	}
}
