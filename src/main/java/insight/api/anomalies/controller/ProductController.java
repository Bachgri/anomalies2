package insight.api.anomalies.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import insight.api.anomalies.dto.ProblemeDTO;
import insight.api.anomalies.dto.ProductDTO;
import insight.api.anomalies.dto.SolutionDTO;
import insight.api.anomalies.entity.Probleme;
import insight.api.anomalies.entity.Product;
import insight.api.anomalies.entity.Solution;
import insight.api.anomalies.service.ProductService;

@RestController
@RequestMapping("/Api/products")
public class ProductController {
	
	@Autowired
    private ProductService productService;

    // Inject ProductService through constructor

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = mapDTOToProduct(productDTO);
        Product createdProduct = productService.post(product);
        ProductDTO createdProductDTO = mapProductToDTO(createdProduct);
        return ResponseEntity.ok(createdProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    	System.err.println("editing product : " + productDTO);
        Product product = mapDTOToProduct(productDTO);
        product.setId(id);
        System.err.println("product.get() " + product);
        Product updatedProduct = productService.put(product);
        System.err.println("edited product  : " + updatedProduct );
        ProductDTO updatedProductDTO = mapProductToDTO(updatedProduct);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.get(id);
        ProductDTO productDTO = mapProductToDTO(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(this::mapProductToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    private Product mapDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());

        // Map the problem IDs (if required)
        if (productDTO.getProblemes() != null) {
            List<Probleme> problemes = new ArrayList<>();
            
            for (ProblemeDTO problemeDTO : productDTO.getProblemes()) {
                Probleme probleme = new Probleme();
                probleme.setId(problemeDTO.getId());
                probleme.setName(problemeDTO.getName());
                if (problemeDTO.getSolutions() != null) {
    	            List<Solution> solutions = new ArrayList<>();

    	            for (SolutionDTO solutionDTO : problemeDTO.getSolutions()) {
    	                Solution solution = new Solution();
    	                solution.setId(solutionDTO.getId());
    	                solution.setName(solutionDTO.getName());
    	                // Map other fields of the Solution class if needed

    	                solutions.add(solution);
    	            }

    	            probleme.setSolutions(solutions);
    	        }
                problemes.add(probleme);
            }
            
            product.setProblemes(problemes);
        }

        return product;
    }

    private Probleme mapDTOToProbleme(ProblemeDTO problemeDTO) {
        Probleme probleme = new Probleme();
        probleme.setId(problemeDTO.getId());
        probleme.setName(problemeDTO.getName());

        // Map the solutions (if required)
        if (problemeDTO.getSolutions() != null) {
            List<Solution> solutions = new ArrayList<>();

            for (SolutionDTO solutionDTO : problemeDTO.getSolutions()) {
                Solution solution = new Solution();
                solution.setId(solutionDTO.getId());
                solution.setName(solutionDTO.getName());
                // Map other fields of the Solution class if needed

                solutions.add(solution);
            }

            probleme.setSolutions(solutions);
        }

        return probleme;
    }

    private ProductDTO mapProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());

        // Map the problem IDs to their DTOs
        List<ProblemeDTO> problemeDTOs = new ArrayList();
        for (Probleme probleme : product.getProblemes()) {
            ProblemeDTO problemeDTO = new ProblemeDTO();
            problemeDTO.setId(probleme.getId());
            problemeDTO.setName(probleme.getName());
            List<SolutionDTO> solutionDTO = new ArrayList<>();
            
            for(Solution solution: probleme.getSolutions()) {
            	solutionDTO.add(mapSolutionToDTO(solution));
            }
            problemeDTO.setSolutions(solutionDTO);
            // Add other properties if needed
            problemeDTOs.add(problemeDTO);
        }
        productDTO.setProblemes(problemeDTOs);

        return productDTO;
    }

    private ProblemeDTO mapProblemeToDTO(Probleme probleme) {
        ProblemeDTO problemeDTO = new ProblemeDTO();
        System.err.println(probleme);
        problemeDTO.setId(probleme.getId());
        problemeDTO.setName(probleme.getName());

        // Fetch and map the solutions (if required)
        //if(probleme.getSolutions() != null) {
        	List<SolutionDTO> solutionDTOs = probleme.getSolutions()
        			.stream()
        			.map(this::mapSolutionToDTO)
        			.collect(Collectors.toList());
        	problemeDTO.setSolutions(solutionDTOs);
        	
        //}
        		//probleme.setSolutions(ArrayList());

        return problemeDTO;
    }
    private SolutionDTO mapSolutionToDTO(Solution solution) {
        SolutionDTO solutionDTO = new SolutionDTO();
        solutionDTO.setId(solution.getId());
        solutionDTO.setName(solution.getName());
        // Map other solution fields if necessary
        return solutionDTO;
    }


 
}
