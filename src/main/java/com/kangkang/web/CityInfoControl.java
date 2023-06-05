package com.kangkang.web;

import com.kangkang.pojo.*;
import com.kangkang.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityInfoControl {
    @Autowired
    private CityService cityService;

    @GetMapping
    public List<SuperSelect> getAllCity() {
        return cityService.selectAll();
    }

    @ManagerRequired
    @PostMapping("/manager/{currentPage}/{pageSize}")
    public PageBean<CityInfo> getAllCityInfo(@RequestBody CityInfo cityInfo, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        return cityService.selectAllInfo(cityInfo, currentPage, pageSize);
    }

    @GetMapping("/{id}")
    public CityInfo getCityById(@PathVariable String id) {
        return cityService.getCityById(id);
    }

    /**
     * 新增城市
     * @param cityInfo
     * @return
     */
    @ManagerRequired
    @PostMapping("/manager")
    public Result addNewCity(@RequestBody CityInfo cityInfo) {
        if (cityInfo.getAirportEnglishName() == null || cityInfo.getAirportEnglishName().length() == 0 || cityInfo.getAirportPyName() == null || cityInfo.getAirportPyName().length() == 0 || cityInfo.getCityEnglishName() == null || cityInfo.getCityEnglishName().length() == 0 || cityInfo.getCityPyName() == null || cityInfo.getCityPyName().length() == 0 || cityInfo.getCnName() == null || cityInfo.getCnName().length() == 0 || cityInfo.getCode() == null || cityInfo.getCode().length() == 0 || cityInfo.getIataApCode() == null || cityInfo.getIataApCode().length() == 0) {
            return Result.infoError("所填写数据不完整");
        }

        String regex = "[a-zA-z]*";
        if ((!cityInfo.getAirportEnglishName().matches(regex)) || (!cityInfo.getAirportPyName().matches(regex)) || (!cityInfo.getCityEnglishName().matches(regex)) || (!cityInfo.getCode().matches(regex)) || (!cityInfo.getIataApCode().matches(regex))) {
            return Result.infoError("机场英文名、机场中文拼音、机场代码、国际航协代码、城市英文名必须为英文字母");
        }
        cityService.add(cityInfo);
        return Result.ok();
    }

    /**
     * 修改城市
     * @param cityInfo
     * @return
     */
    @ManagerRequired
    @PutMapping("/manager")
    public Result updateCity(@RequestBody CityInfo cityInfo) {
        if (cityInfo.getAirportEnglishName() == null || cityInfo.getAirportEnglishName().length() == 0 || cityInfo.getAirportPyName() == null || cityInfo.getAirportPyName().length() == 0 || cityInfo.getCityEnglishName() == null || cityInfo.getCityEnglishName().length() == 0 || cityInfo.getCityPyName() == null || cityInfo.getCityPyName().length() == 0 || cityInfo.getCnName() == null || cityInfo.getCnName().length() == 0 || cityInfo.getCode() == null || cityInfo.getCode().length() == 0 || cityInfo.getIataApCode() == null || cityInfo.getIataApCode().length() == 0) {
            return Result.infoError("所填写数据不完整");
        }
        String regex = "[a-zA-z]*";
        if ((!cityInfo.getAirportEnglishName().matches(regex)) || (!cityInfo.getAirportPyName().matches(regex)) || (!cityInfo.getCityEnglishName().matches(regex)) || (!cityInfo.getCode().matches(regex)) || (!cityInfo.getIataApCode().matches(regex))) {
            return Result.infoError("机场英文名、机场中文拼音、机场代码、国际航协代码、城市英文名必须为英文字母");
        }
        cityService.update(cityInfo);
        return Result.ok();
    }

    /**
     * 删除城市
     * @param id
     */
    @ManagerRequired
    @DeleteMapping("/manager/{id}")
    public void deleteCity(@PathVariable String id){
        cityService.delete(id);
    }
}
