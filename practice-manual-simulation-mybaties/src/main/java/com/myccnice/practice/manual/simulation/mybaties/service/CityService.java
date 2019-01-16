package com.myccnice.practice.manual.simulation.mybaties.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myccnice.practice.manual.simulation.mybaties.dao.CityDao;

@Service
public class CityService {

    @Resource(name="CityDao")
    // @Autowired
    private CityDao cityDao;

    public void query() {
        cityDao.query();
    }
}
