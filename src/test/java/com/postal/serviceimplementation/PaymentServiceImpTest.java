package com.postal.serviceimplementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.postal.hibrepoimplementation.PaymentRepoImp;
import com.postal.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceImpTest {

    @InjectMocks
    private PaymentServiceImp paymentService;

    @Mock
    private PaymentRepoImp repo;

    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment();
        // Initialize your Payment object with necessary fields
    }

    @Test
    void testAddPayment() {
        // Mock the repository method
        doNothing().when(repo).addPayment(any(Payment.class));

        // Call the method to test
        paymentService.addPayment(payment);

        // Verify the tracking ID is set (it's hard to assert exact value, just check it's not null)
        assertNotNull(payment.getTrackingid());
        assertFalse(payment.getTrackingid().isEmpty());

        // Verify that addPayment method of the repository was called once
        verify(repo, times(1)).addPayment(any(Payment.class));
    }
}
