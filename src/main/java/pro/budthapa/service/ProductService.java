/**
 * 
 */
package pro.budthapa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.budthapa.domain.Product;
import pro.budthapa.repo.ProductRepository;

/**
 * @author budthapa
 * Mar 21, 2017
 * 
 */
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product saveProduct(Product product){
		return productRepository.save(product);
	}
	
	public Product findProduct(String name){
		return productRepository.findByName(name);
	}

	/**
	 * @return
	 */
	public List<Product> findAllProducts() {
		List<Product> productList=new ArrayList<>();
		productRepository.findAll().forEach(productList::add);
		return productList;
	}

	/**
	 * @param id
	 */
	public Product findProduct(Long id) {
		return productRepository.findOne(id);
	}

	/**
	 * @param product
	 */
	public void updateProduct(Product product) {
		productRepository.save(product);
	}
}
