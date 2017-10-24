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

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Evgenii_Efimchik on 11-Oct-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }


    @Test
    public void dataControllerMayFindExistingImage() throws Exception {
        mockMvc.perform(get("/data/pictures_HQ/IMAG0686.jpg"))
                .andExpect(status().isOk());
    }

    @Test
    public void noSuchFilrLeadsTo404() throws Exception {
        mockMvc.perform(get("/data/no_such_path"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void dataControllerMayFindExistingImageIgnoringCase() throws Exception {
        mockMvc.perform(get("/data/pictures_HQ/IMAG0686.JPG"))
                .andExpect(status().isOk());
    }

    @Test
    public void dataControllerFindsProperImage() throws Exception {
        mockMvc.perform(get("/data/pictures_HQ/IMAG0686.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().bytes(Files.readAllBytes(Paths.get("pictures_HQ", "IMAG0686.jpg"))));

        mockMvc.perform(get("/data/pictures_HQ/IMAG0911.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().bytes(Files.readAllBytes(Paths.get("pictures_HQ", "IMAG0911.jpg"))));
    }


}