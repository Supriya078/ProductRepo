package net.codejava;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	private Logger l= LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	KafkaTemplate<String, String> kafkatemp;
	public List<Product> listAll()
	{
		return repo.findAll();
	}
	
	public void save(Product product)
	{
		repo.save(product);
	}
	public Product get(Integer id)
	{
		return repo.findById(id).get();
	}
	public void delete(Integer id)
	{
		repo.deleteById(id);
	}
	@Transactional
	public void updateColours(int id, String colour)
	{
		repo.updatecolour(id, colour);
	}
	
	@Transactional
	public void updatePlace(int id,String place )
	{
		repo.updateplace(id, place);
	}
	public void sendMsg(String msg)
	{
		kafkatemp.send("Topic",msg);
	}
	
	
}
