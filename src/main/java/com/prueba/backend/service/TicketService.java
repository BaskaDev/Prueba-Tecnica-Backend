package com.prueba.backend.service;

import com.prueba.backend.entity.Ticket;
import com.prueba.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket findById(Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Integer id, Ticket ticketNew) {
        Ticket existingTicket = this.findById(id);
        if(existingTicket != null){
            existingTicket.setTitle_tickets(ticketNew.getTitle_tickets());
            existingTicket.setStatus_tickets(ticketNew.getStatus_tickets());
            if(ticketNew.getUserStory() != null){
                existingTicket.setUserStory(ticketNew.getUserStory());
            }
            return ticketRepository.save(existingTicket);
        }
        return  null;
    }

    public Ticket deleteTicket(Integer id) {
        Ticket ticket = findById(id);
        ticketRepository.delete(ticket);
        return ticket;
    }

    public List<Ticket> getAll(){
        return  ticketRepository.findAll();
    }

}
