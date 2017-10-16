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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirControllerTest {


    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }


    @Test
    public void galleryDirsAreUp() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ"))
                .andExpect(status().isOk());
    }

    @Test
    public void galleryDirsAreProperlyFoundById() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"pictures_HQ\",\"fullName\":\"pictures_HQ\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ\"}}}"))
                .andReturn();

        mockMvc.perform(get("/dirs/pictures_HQ%5CCarimate"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"Carimate\",\"fullName\":\"pictures_HQ\\\\Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ%5CCarimate\"}}}"))
                .andReturn();
    }

    @Test
    public void noDirLeadsTo404() throws Exception {
        mockMvc.perform(get("/dirs/nosuchdir"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

}