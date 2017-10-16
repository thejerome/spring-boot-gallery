package com.efimchick.gallery.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {


    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void galleryImageIdFound() throws Exception {
        mockMvc.perform(get("/images/pictures_HQ%5CIMAG0686.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"IMAG0686.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0686.jpg\",\"size\":3175908,\"width\":4224,\"height\":2368,\"_links\":{\"self\":{\"href\":\"http://localhost/images/pictures_HQ%5CIMAG0686.jpg\"}}}"))
                .andReturn();

        mockMvc.perform(get("/images/pictures_HQ%5CIMAG0911.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"IMAG0911.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0911.jpg\",\"size\":2164700,\"width\":4224,\"height\":2368,\"_links\":{\"self\":{\"href\":\"http://localhost/images/pictures_HQ%5CIMAG0911.jpg\"}}}"))
                .andReturn();
    }

    @Test
    public void noImageLeadsTo404() throws Exception {
        mockMvc.perform(get("/images/No_suchImage.JPG"))
                .andExpect(status().isNotFound())
                .andReturn();

    }


}