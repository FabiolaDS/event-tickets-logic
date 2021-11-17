package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Payment;

public interface PaymentRepository {

    Payment createPayment(Payment payment);


}
