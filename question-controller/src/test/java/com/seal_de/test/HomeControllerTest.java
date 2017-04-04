package com.seal_de.test;

import com.seal_de.controller.HomeController;
import com.seal_de.data.UserRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception{
        HomeController controller = new HomeController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }

    @Test
    public void testLogin() throws Exception{
        UserRepository mockRepository = Mockito.mock(UserRepository.class);

        HomeController controller = new HomeController(mockRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "aaa")
                .param("password", "ccc"))
                .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
