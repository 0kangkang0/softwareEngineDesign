package com.kangkang.web;

import com.kangkang.pojo.*;
import com.kangkang.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketControl {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Result getTicketInfo(@RequestBody QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException {
        if (queryTicketInfo.getEndCity() == null || queryTicketInfo.getStartCity() == null ||queryTicketInfo.getEndCity().length() == 0 || queryTicketInfo.getStartCity().length() == 0 || queryTicketInfo.getTime() == null) {
            return Result.infoError("输入的信息有误");
        }
        return Result.ok(ticketService.getTicketByStartArriveTime(queryTicketInfo));
    }

    @GetMapping("/{id}")
    public OneTicketInfo getTicket(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    /**
     * 删除机票
     * @param id 机票id
     * @return 更改完成的结果
     */
    @LoginRequired
    @DeleteMapping("/{id}")
    public Result cancelTicket(@PathVariable Long id) {
        ticketService.logicDeleteById(id.toString());
        return Result.ok();
    }

    /**
     * 修改机票信息
     * @param orderTicketNo
     * @return
     */
    @LoginRequired
    @PutMapping
    public Result changeTicket(@RequestBody OrderTicketNo orderTicketNo) {
        ticketService.changeTicket(orderTicketNo);
        return Result.ok();
    }

    /**
     * 查询机票信息
     * @param currentPage
     * @param pageSize
     * @param queryTicketInfo
     * @return
     */
    @ManagerRequired
    @PostMapping("/manager/{currentPage}/{pageSize}")
    public PageBean<TicketManager> getTicketManagerInfo(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody QueryTicketInfo queryTicketInfo) {
        return ticketService.getManagerTicket(queryTicketInfo, currentPage, pageSize);
    }

    /**
     * 添加机票
     * @param ticket
     * @return
     */
    @ManagerRequired
    @PostMapping("/manager")
    public Result addTicket(@RequestBody Ticket ticket) {
        if (ticket.getNum() == null || ticket.getPrice() == null || ticket.getType() == null || ticket.getType().length() == 0 || ticket.getRouteId() == null) {
            return Result.infoError("所填写信息不完整");
        }
        ticketService.addTicket(ticket);
        return Result.ok();
    }

    /**
     * 修改机票
     * @param ticket
     * @return
     */
    @ManagerRequired
    @PutMapping("/manager")
    public Result updateTicket(@RequestBody Ticket ticket) {
        if (ticket.getNum() == null || ticket.getPrice() == null || ticket.getType() == null || ticket.getType().length() == 0 || ticket.getRouteId() == null) {
            return Result.infoError("所填写信息不完整");
        }
        ticketService.updateTicket(ticket);
        return Result.ok();
    }

    /**
     * 删除机票
     * @param id
     */
    @ManagerRequired
    @DeleteMapping("/manager/{id}")
    public void DeleteTicket(@PathVariable String id){
        ticketService.deleteTicket(id);
    }
    @GetMapping("/manager/{id}")
    public Result getOneTicket(@PathVariable String id){
        TicketManager oneTicketManager = ticketService.getOneTicketManager(id);
        if(oneTicketManager==null){
            return  Result.infoError("输入的机票编号有误");
        }
        return Result.ok(oneTicketManager);
    }
}
