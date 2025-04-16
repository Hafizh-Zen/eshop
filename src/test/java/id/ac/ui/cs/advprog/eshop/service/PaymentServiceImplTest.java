package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl service;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    Order order;
    Map<String, String> data;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Item");
        product.setProductQuantity(1);

        order = new Order("order-1", List.of(product), 123456789L, "John Doe");
        data = new HashMap<>();
    }

    @Test
    void shouldAddPaymentWithValidVoucher() {
        data.put("voucherCode", "ESHOP1234ABCD5678");
        when(orderRepository.findById("order-1")).thenReturn(order);

        Payment result = service.addPayment(order, "Voucher", data);

        assertAll(
                () -> assertEquals("SUCCESS", result.getStatus()),
                () -> assertEquals("SUCCESS", order.getStatus())
        );
    }

    @Test
    void shouldRejectInvalidVoucherCode() {
        data.put("voucherCode", "INVALID");
        when(orderRepository.findById("order-1")).thenReturn(order);

        Payment result = service.addPayment(order, "Voucher", data);

        assertAll(
                () -> assertEquals("REJECTED", result.getStatus()),
                () -> assertEquals("FAILED", order.getStatus())
        );
    }

    @Test
    void shouldReturnPaymentByIdFromRepository() {
        Payment expected = new Payment("1", "Voucher", "SUCCESS", data);
        when(paymentRepository.findById("1")).thenReturn(expected);

        Payment result = service.getPayment("1");

        assertEquals("1", result.getId());
        assertEquals("SUCCESS", result.getStatus());
    }
}