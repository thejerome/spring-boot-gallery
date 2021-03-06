package com.efimchick.gallery.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
    public void dirsAreProperlyFoundById() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"pictures_HQ\",\"fullName\":\"pictures_HQ\",\"_embedded\":{\"images\":[{\"name\":\"IMAG0686.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0686.jpg\",\"size\":3175908,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ/IMAG0686.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ/IMAG0686.jpg\"}}},{\"name\":\"IMAG0911.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0911.jpg\",\"size\":2164700,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ/IMAG0911.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ/IMAG0911.jpg\"}}}],\"dirs\":[{\"name\":\"Carimate\",\"fullName\":\"pictures_HQ\\\\Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate\"}}},{\"name\":\"empty\",\"fullName\":\"pictures_HQ\\\\empty\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-empty\"}}},{\"name\":\"empty-dashed--heavily---folder\",\"fullName\":\"pictures_HQ\\\\empty-dashed--heavily---folder\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-empty--dashed----heavily------folder\"}}},{\"name\":\"light\",\"fullName\":\"pictures_HQ\\\\light\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-light\"}}},{\"name\":\"yellow\",\"fullName\":\"pictures_HQ\\\\yellow\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-yellow\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ\"}}}"))
                .andReturn();

        mockMvc.perform(get("/dirs/pictures_HQ-Carimate"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"Carimate\",\"fullName\":\"pictures_HQ\\\\Carimate\",\"_embedded\":{\"images\":[{\"name\":\"IMAG0674.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0674.jpg\",\"size\":2689467,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0674.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0674.jpg\"}}},{\"name\":\"IMAG0676.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0676.jpg\",\"size\":2626845,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0676.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0676.jpg\"}}},{\"name\":\"IMAG0678.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0678.jpg\",\"size\":2942016,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0678.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0678.jpg\"}}},{\"name\":\"IMAG0681.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0681.jpg\",\"size\":1825480,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0681.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0681.jpg\"}}},{\"name\":\"IMAG0684.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0684.jpg\",\"size\":2416295,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0684.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0684.jpg\"}}},{\"name\":\"IMAG0686.jpg\",\"fullName\":\"pictures_HQ\\\\Carimate\\\\IMAG0686.jpg\",\"size\":3175908,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ-Carimate\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate/IMAG0686.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ-Carimate/IMAG0686.jpg\"}}}]},\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-Carimate\"}}}"))
                .andReturn();
    }


    @Test
    public void dirWithNoImagesDoesNotContainEmptyCollection() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ-empty"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"empty\",\"fullName\":\"pictures_HQ\\\\empty\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ-empty\"}}}"))
                .andReturn();
    }

    @Test
    public void noDirLeadsTo404() throws Exception {
        mockMvc.perform(get("/dirs/nosuchdir"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void rootCallRedirectsToRootDir() throws Exception {

    mockMvc.perform(get("/dirs"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.header().string("location", "http://localhost/dirs/pictures_HQ"))
                .andReturn();

    }


    @Test
    public void galleryImageIdFound() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ/IMAG0686.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"IMAG0686.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0686.jpg\",\"size\":3175908,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ/IMAG0686.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ/IMAG0686.jpg\"}}}"))
                .andReturn();

        mockMvc.perform(get("/dirs/pictures_HQ/IMAG0911.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"name\":\"IMAG0911.jpg\",\"fullName\":\"pictures_HQ\\\\IMAG0911.jpg\",\"size\":2164700,\"width\":4224,\"height\":2368,\"directoryId\":\"pictures_HQ\",\"_links\":{\"self\":{\"href\":\"http://localhost/dirs/pictures_HQ/IMAG0911.jpg\"},\"data\":{\"href\":\"http://localhost/data/pictures_HQ/IMAG0911.jpg\"}}}"))
                .andReturn();
    }

    @Test
    public void noImageLeadsTo404() throws Exception {
        mockMvc.perform(get("/dirs/pictures_HQ/No_suchImage.JPG"))
                .andExpect(status().isNotFound())
                .andReturn();

    }






}