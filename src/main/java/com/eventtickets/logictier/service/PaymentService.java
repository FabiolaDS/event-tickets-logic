package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Payment;
import com.eventtickets.logictier.service.dto.FindTicketDto;
import com.eventtickets.logictier.service.dto.MakePaymentDto;

public interface PaymentService {
    Payment makePayment (MakePaymentDto makePaymentDto);
    Payment findForTicket(FindTicketDto findTicketDto);

}
