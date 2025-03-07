package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    private PaymentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new PaymentRepository();
    }

    @Test
    void testSaveAndFindById() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABCD5678");
        Payment payment = new Payment("1", "Voucher", "SUCCESS", data);
        repository.save(payment);

        Payment result = repository.findById("1");
        assertNotNull(result);
        assertEquals("SUCCESS", result.getStatus());
    }

    @Test
    void testFindAll() {
        assertEquals(0, repository.findAll().size());
    }
}