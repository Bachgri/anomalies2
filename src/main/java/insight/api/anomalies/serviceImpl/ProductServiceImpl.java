package insight.api.anomalies.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import insight.api.anomalies.entity.Product;
import insight.api.anomalies.repository.ProductRepository;
import insight.api.anomalies.service.ProductService;

 
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	@Override
	public List<Product> getAll() { 
		return productRepository.findAll();
	}

	@Override
	public Product post(Product p) { 
		return productRepository.save(p);
	}

	@Override
	public Product put(Product p) {
		return null;
	}

	@Override
	public Product get(Long id) { 
		return productRepository.findById(id).get();
	}

	@Override
	public Product delete(long p) {
		Product pp = productRepository.findById(p).get();
		productRepository.delete(pp);
		return pp;
	}

}
