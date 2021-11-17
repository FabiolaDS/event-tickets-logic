package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.Payment;
import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.network.EventRepository;
import com.eventtickets.logictier.network.PaymentRepository;
import com.eventtickets.logictier.network.TicketRepository;
import com.eventtickets.logictier.service.dto.MakePaymentDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @NonNull
    private PaymentRepository paymentRepository;
    @NonNull
    private EventRepository eventRepository;
    @NonNull
    private TicketRepository ticketRepository;


    @Override

    public Payment makePayment(MakePaymentDto makePaymentDto) {
        Event event = eventRepository.getEventById(makePaymentDto.getEvent());
        Ticket ticket = ticketRepository.getByEventIdAndUserId(makePaymentDto.getEvent(), makePaymentDto.getBuyer());

        Payment payment = new Payment(makePaymentDto.getCreditCardNumber(),
                LocalDateTime.now(),
                event.getPrice() * ticket.getNrOfTickets(),
                makePaymentDto.getBuyer(),
                makePaymentDto.getEvent()
        );
        return paymentRepository.createPayment(payment);

    }

}
