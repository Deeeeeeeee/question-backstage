package com.seal_de.controller;

import com.seal_de.domain.Cities;
import com.seal_de.domain.Provinces;
import com.seal_de.service.CitiesService;
import com.seal_de.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProvincesController {
    private ProvincesService provincesService;
    private CitiesService citiesService;

    @Autowired
    public ProvincesController(ProvincesService provincesService, CitiesService citiesService){
        this.provincesService = provincesService;
        this.citiesService = citiesService;
    }

    @RequestMapping(value = "/provinces")
    public List<Provinces> provincesAll(){
        return provincesService.findAll();
    }

    @RequestMapping(value = "/cities/{provinceId}")
    public List<Cities> citiesAll(
            @PathVariable String provinceId
    ){
        return citiesService.findByProvinceId(provinceId);
    }

}
