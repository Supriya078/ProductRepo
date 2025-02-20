package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	@Modifying
	@Query("Update Product set colour=:colour where id=:id")
	void updatecolour(int id, String colour);
	
	@Modifying
	@Query("Update Product set place=:place where id=:id")
	void updateplace(int id,String place);
}