package com.kangkang.service.impl;

import com.kangkang.mapper.CityMapper;
import com.kangkang.pojo.CityInfo;
import com.kangkang.pojo.PageBean;
import com.kangkang.pojo.SuperSelect;
import com.kangkang.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    public void add(CityInfo cityInfo){
        cityMapper.insert(cityInfo);
    }

    @Override
    public CityInfo selectByIataApCode(String idtaApCode) {
        return cityMapper.selectByIataApCode(idtaApCode);
    }

    @Override
    public List<String> selectAllIataApCode() {
        return cityMapper.selectAllIataApCode();
    }

    @Override
    public List<SuperSelect> selectAll() {
        SuperSelect[] superSelects = new SuperSelect[26];
        for (int i = 0; i < 26; i++) {
            SuperSelect superSelect = new SuperSelect();
            superSelect.setValue(((char)('A'+i))+"");
            superSelect.setLabel(((char)('A'+i))+"");
            superSelects[i]=superSelect;
        }
        List<CityInfo> cityInfos = cityMapper.selectAll();
        for (CityInfo cityInfo : cityInfos) {
            String cityPyName = cityInfo.getCityEnglishName();
            cityPyName = cityPyName.toUpperCase();
            ArrayList<SuperSelect> child = superSelects[cityPyName.charAt(0) - 'A'].getChildren();
            if(child==null)
                child=new ArrayList<>();
            SuperSelect superSelect = new SuperSelect();
            superSelect.setLabel(cityInfo.getCityPyName());
            superSelect.setValue(cityInfo.getIataApCode());
            child.add(superSelect);
            superSelects[cityPyName.charAt(0) - 'A'].setChildren(child);
        }
        List<SuperSelect> superSelects1 = new ArrayList<>(List.of(superSelects));
        superSelects1.remove('I' - 'A');
        superSelects1.remove('V' - 'A'-1);
        return superSelects1;
    }

    @Override
    public PageBean<CityInfo> selectAllInfo(CityInfo cityInfo, Integer currentPage, Integer pageSize) {
        int begin=(currentPage-1)*pageSize;
        String cnName = cityInfo.getCnName();
        String cityPyName = cityInfo.getCityPyName();
        if(cnName!=null&&cnName.length()>0){
            cityInfo.setCnName("%"+cnName+"%");
        }
        if(cityPyName!=null&&cityPyName.length()>0){
            cityInfo.setCityPyName("%"+cityPyName+"%");
        }

        List<CityInfo> cityInfos = cityMapper.selectPageByName(cityInfo, begin, pageSize);
        Integer integer = cityMapper.selectcountByName(cityInfo);
        return new PageBean<>(integer,cityInfos);
    }

    @Override
    public CityInfo getCityById(String id) {
        return cityMapper.selectById(id);
    }

    @Override
    public void update(CityInfo cityInfo) {
        cityMapper.updateById(cityInfo);
    }

    @Override
    public void delete(String id) {
        cityMapper.deleteById(id);
    }
}
