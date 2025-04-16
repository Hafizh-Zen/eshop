package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("dbf94e64-9b11-4376-ab8f-d0821dde9904");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("delete-test");
        product.setProductName("To Be Deleted");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete("delete-test");

        Iterator<Product> productIterator = productRepository.findAll();
        while (productIterator.hasNext()) {
            assertNotEquals("delete-test", productIterator.next().getProductId());
        }
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("update-test");
        product.setProductName("Old Name");
        product.setProductQuantity(5);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("update-test");
        updatedProduct.setProductName("New Name");
        updatedProduct.setProductQuantity(99);
        productRepository.delete("update-test");
        productRepository.create(updatedProduct);

        Iterator<Product> iterator = productRepository.findAll();
        Product result = null;
        while (iterator.hasNext()) {
            Product current = iterator.next();
            if (current.getProductId().equals("update-test")) {
                result = current;
                break;
            }
        }

        assertNotNull(result);
        assertEquals("New Name", result.getProductName());
        assertEquals(99, result.getProductQuantity());
    }

}
