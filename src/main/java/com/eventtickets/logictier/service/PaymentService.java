package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Payment;
import com.eventtickets.logictier.service.dto.FindTicketDTO;
import com.eventtickets.logictier.service.dto.MakePaymentDTO;

public interface PaymentService {
    Payment makePayment (MakePaymentDTO makePaymentDto);
    Payment findForTicket(FindTicketDTO findTicketDto);

}
