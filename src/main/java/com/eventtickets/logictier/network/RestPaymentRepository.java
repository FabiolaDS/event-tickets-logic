package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Payment;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RestPaymentRepository extends RestRepository implements PaymentRepository
{


    public RestPaymentRepository(@NonNull RestTemplate restTemplate, @Value("${eventTicket.data.url}") String dataUrl) {
        super(restTemplate, dataUrl, "payments");
    }

    @Override
    public Payment createPayment(Payment payment) {
        return rest().postForObject(url(),payment,Payment.class);


    }
}
