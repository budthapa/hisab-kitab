/**
 * 
 */
package pro.budthapa.service;

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
}
