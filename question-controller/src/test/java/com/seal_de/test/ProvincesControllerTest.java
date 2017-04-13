package com.seal_de.test;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.seal_de.controller.ProvincesController;
import com.seal_de.domain.Provinces;
import com.seal_de.service.ProvincesService;
import com.seal_de.service.util.EncryptUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

public class ProvincesControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProvincesService provincesService;

    @InjectMocks
    private ProvincesController controller;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testProvincesAll() throws Exception{
        List<Provinces> provinces = createProvinces();
        when(provincesService.findAll()).thenReturn(provinces);
        mockMvc.perform(get("/provinces"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].province", is("北京市")))
                .andExpect(jsonPath("$[1].provinceId", is("440000")));
    }

    private List<Provinces> createProvinces(){
        List<Provinces> list = new ArrayList<Provinces>();
        Provinces p1 = new Provinces();
        Provinces p2 = new Provinces();
        p1.setId(1);
        p1.setProvince("北京市");
        p1.setProvinceId("110000");
        p2.setId(19);
        p2.setProvince("广东省");
        p2.setProvinceId("440000");
        list.add(p1);
        list.add(p2);
        return list;
    }

    @Test
    public void encrypt(){
        System.out.println(EncryptUtil.encypt("qq"));
    }
}
