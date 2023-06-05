package com.kangkang.service;

import com.kangkang.pojo.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

public interface TicketService {
    List<TickInfoS>getTicketByStartArriveTime(QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException;
    OneTicketInfo getTicketById(Long id);
    void logicDeleteById(String routerId);
    void changeTicket(OrderTicketNo orderTicketNo);
    PageBean<TicketManager> getManagerTicket(QueryTicketInfo queryTicketInfo, Integer currentPage, Integer pageSize);
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
    void deleteTicket(String id);
    TicketManager getOneTicketManager(String id);
}