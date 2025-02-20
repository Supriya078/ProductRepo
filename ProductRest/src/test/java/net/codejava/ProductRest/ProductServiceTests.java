package net.codejava.ProductRest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import net.codejava.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	
	@InjectMocks
	ProductService p;
	
	@Mock
	ProductRepository r;
	
	@Test
	public void testGet()
	{
		
		when(r.findById(3)).thenReturn(Optional.of(new Product(3,"supriya",23.0f,"blue","patna")));
		assertEquals(p.get(3).getColour(), "blue");
	}
}
