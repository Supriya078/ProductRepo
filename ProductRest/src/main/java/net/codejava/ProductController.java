package net.codejava;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public List<Product> list()//flow-ProductController->ProductService->ProductRepository
	{
		return service.listAll();
	}
	@GetMapping("/products/{id}")
	public /*ResponseEntity<Product>*/Product get(@PathVariable Integer id)
	{
		try
		{
			Product product=service.get(id);
			return product;
			/*return new ResponseEntity<Product>(product,HttpStatus.OK).getBody();*/
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND).getBody();
		}
	}
	@PostMapping("/products")
	public void add(@RequestBody Product product)
	{
		/*org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		 headers.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<Product> h=new HttpEntity<>(product,headers);/
		System.out.println("hello")*/;
		service.save(product);
	}
}
