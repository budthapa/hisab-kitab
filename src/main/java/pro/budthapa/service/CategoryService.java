/**
 * 
 */
package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.dao.CategoryRepository;
import pro.budthapa.domain.Category;

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
}
