package net.codejava;

import java.net.http.HttpHeaders;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Product> get(@PathVariable Integer id)
	{
		try
		{
			Product product=service.get(id);
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
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
	@DeleteMapping("/products")
	public void del(@PathVariable Integer id)
	{
		service.delete(id);
	}
	@PutMapping("/update/{id}")
	public void update(@RequestBody Product product)
	{
		service.save(product);
	}
	@GetMapping("/updateall/{colour}")
	public ResponseEntity<String> updateallrecords(@PathVariable String colour)
	{
		List<Product> p=service.listAll();
		for(int i=0;i<p.size();i++)
		{
			Product l=p.get(i);
			service.updateColours(l.getId(),colour);
		}
		ResponseEntity<String> r=new ResponseEntity<>("colour is saved",HttpStatus.OK);
		return r;
		
	}
	
	@PutMapping("/updateplace/{id}/{place}")
	public ResponseEntity<String> updatePlace(@PathVariable Integer id,@PathVariable String place )
	{
		service.updatePlace(id, place);
		ResponseEntity<String> r=new ResponseEntity<>("place is saved",HttpStatus.OK);
		return r;
	}
	
	@GetMapping("/producerMsg")
	public void getRequest(@RequestParam("massage") String msg )
	{
		service.sendMsg(msg);
	}
}
