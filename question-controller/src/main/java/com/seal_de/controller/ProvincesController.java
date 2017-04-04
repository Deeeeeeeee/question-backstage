package com.seal_de.controller;

import com.seal_de.domain.Cities;
import com.seal_de.domain.Provinces;
import com.seal_de.service.CitiesService;
import com.seal_de.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ProvincesController {
    @Autowired
    private ProvincesService provincesService;

    @Autowired
    private CitiesService citiesService;

    @RequestMapping(value = "/provinces")
    public List<Provinces> provincesAll(){
        return provincesService.findAll();
    }

    @RequestMapping(value = "/citis/{id}")
    public List<Cities> citisAll(
            @PathVariable String id
    ){
        return citiesService.findByProvinceId(id);
    }

}
