package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void shouldCreatePaymentWithAllFields() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABCD5678");

        Payment payment = new Payment(
                "1",
                "Voucher",
                "SUCCESS",
                data
        );

        assertAll(
                () -> assertEquals("1", payment.getId()),
                () -> assertEquals("Voucher", payment.getMethod()),
                () -> assertEquals("SUCCESS", payment.getStatus()),
                () -> assertEquals("ESHOP1234ABCD5678", payment.getPaymentData().get("voucherCode"))
        );
    }
}