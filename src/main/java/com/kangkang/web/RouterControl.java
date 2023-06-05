package com.kangkang.web;

import com.kangkang.pojo.*;
import com.kangkang.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@RestController
@RequestMapping("/router")
public class RouterControl {
    @Autowired
    RouteService routeService;

    /**
     * 用户查询航程信息
     * @param queryTicketInfo
     * @return
     * @throws ParseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping()
    public Result getInformation(@RequestBody QueryTicketInfo queryTicketInfo) throws ParseException, IOException, NoSuchAlgorithmException {
        if (queryTicketInfo.getEndCity()==null||queryTicketInfo.getStartCity()==null||queryTicketInfo.getTime()==null||queryTicketInfo.getEndCity().length()==0||queryTicketInfo.getStartCity().length()==0){
            return Result.infoError("输入的信息有误");
        }
        return Result.ok(routeService.selectInfo(queryTicketInfo));
    }

    /**
     * 管理员查询航程信息
     * @param queryTicketInfo
     * @param currentPage
     * @param pageSize
     * @return
     */
    @ManagerRequired
    @PostMapping("/manager/{currentPage}/{pageSize}")
    public PageBean<RouteInfoManager> getRouteManager(@RequestBody QueryTicketInfo queryTicketInfo, @PathVariable Integer currentPage, @PathVariable Integer pageSize){
        return routeService.getRouteInfo(queryTicketInfo, currentPage, pageSize);
    }

    /**
     * 新增路线
     * @param routeInfoManager
     * @return
     * @throws ParseException
     */
    @ManagerRequired
    @PostMapping("/manager")
    public Result addNewVoyage(@RequestBody RouteInfoManager routeInfoManager) throws ParseException {
        if(routeInfoManager.getAircraftCode()==null||routeInfoManager.getAircraftCode().length()==0||routeInfoManager.getStartTime().getScheduled()==null||routeInfoManager.getStartTime().getScheduled().length()==0||routeInfoManager.getArriveAirportCode()==null||routeInfoManager.getArriveAirportCode().length()==0||routeInfoManager.getArriveTime().getScheduled()==null||routeInfoManager.getArriveTime().getScheduled().length()==0||routeInfoManager.getStartAirportCode()==null||routeInfoManager.getStartAirportCode().length()==0){
            return Result.infoError("所填写信息不完整");
        }
        boolean b = routeService.addRouter(routeInfoManager);
        if(!b){
            return Result.error("出错了，请稍后再试");
        }
        return Result.ok();
    }
    @ManagerRequired
    @GetMapping("/manager/{id}")
    public RouteInfoManager getRouterInfo(@PathVariable String id){
        return routeService.getRouterInfoById(id);
    }

    /**
     * 修改路线信息
     * @param routeInfoManager
     * @return
     * @throws ParseException
     */
    @ManagerRequired
    @PutMapping("/manager")
    public Result updateVoyage(@RequestBody RouteInfoManager routeInfoManager) throws ParseException {
        boolean b = routeService.updateRouter(routeInfoManager);
        if(!b){
            return Result.error("出错了，请稍后再试");
        }
        return Result.ok();
    }

    /**
     * 删除路线
     * @param id
     */
    @ManagerRequired
    @DeleteMapping("/manager/{id}")
    public void deleteVoyage(@PathVariable String id){
        routeService.deleteRouterById(id);
    }
}
