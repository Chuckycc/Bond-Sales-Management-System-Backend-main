package com.ssm;

import com.ssm.model.SalesRecord;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class SalesRecordControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecord() {
    }

    @Test
    void getAllRecords() {
    }

    @Test
    void addRecord() {
    }

    @Test
    void deleteRecord() {
    }

//    @Test
//    void updateRecordTest() throws Exception{
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("id", "102");
//        params.add("bondName", "test");
//        params.add("salesName", "test");
//        params.add("amount", "1000");
//        params.add("createdTime", "2021-08-23");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                .put("/api/sale")
//                .params(params)).andReturn();
//        int status = result.getResponse().getStatus();
//        String content = result.getResponse().getContentAsString();
//
//        Assert.assertEquals(200, status);
//    }

    @Test
    void queryTest() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("current", "2");
        params.add("pageSize", "20");
        //params.add("id","2")
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/sale")
                .params(params)).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
    }
}