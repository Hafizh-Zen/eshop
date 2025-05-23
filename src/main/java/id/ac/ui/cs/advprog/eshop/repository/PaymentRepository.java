package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentList = new ArrayList<>();

    public Payment save(Payment payment) {
        paymentList.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        return paymentList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentList);
    }
}
