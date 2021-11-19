package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Event;
import com.eventtickets.logictier.model.Payment;
import com.eventtickets.logictier.model.Ticket;
import com.eventtickets.logictier.network.EventRepository;
import com.eventtickets.logictier.network.PaymentRepository;
import com.eventtickets.logictier.network.TicketRepository;
import com.eventtickets.logictier.service.dto.FindTicketDTO;
import com.eventtickets.logictier.service.dto.MakePaymentDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService
{

  @NonNull
  private PaymentRepository paymentRepository;
  @NonNull
  private EventRepository eventRepository;
  @NonNull
  private TicketRepository ticketRepository;
  @NonNull
  private Validator validator;

  @Override

  public Payment makePayment(MakePaymentDTO makePaymentDto)
  {
    Set<ConstraintViolation<MakePaymentDTO>> violations = validator
        .validate(makePaymentDto);
    for (ConstraintViolation<MakePaymentDTO> violation : violations)
    {
      throw new IllegalArgumentException(violation.getMessage());
    }
    Event event = eventRepository.getEventById(makePaymentDto.getEvent());
    Ticket ticket = ticketRepository
        .getByEventIdAndUserId(makePaymentDto.getEvent(),
            makePaymentDto.getBuyer());

    Payment payment = new Payment(makePaymentDto.getCreditCardNumber(),
        LocalDateTime.now(), event.getPrice() * ticket.getNrOfTickets(),
        makePaymentDto.getBuyer(), makePaymentDto.getEvent());
    return paymentRepository.createPayment(payment);

  }

  @Override
  public Payment findForTicket(FindTicketDTO findTicketDto)
  {
    return paymentRepository
        .getById(findTicketDto.getBuyerId(), findTicketDto.getEventId());
  }

}
