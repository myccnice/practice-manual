package com.myccnice.practice.manual.simulation.mybaties.dao;

import com.myccnice.practice.manual.simulation.mybaties.annotation.Select;

public interface CityDao {

    @Select("select * from table where id = 1")
    void query();
}
