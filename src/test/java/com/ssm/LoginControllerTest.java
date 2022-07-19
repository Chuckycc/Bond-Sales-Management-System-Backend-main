package com.ssm;


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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;


/**
 * @Author: Suo
 * @Date: Aug 23, 2021
 * @function: Test InsertRecordController class */
@SpringBootTest
@RunWith(SpringRunner.class)
class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    //@MockBean
    //private SaleMapper saleMapper;

    @BeforeEach
    private void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    private void tearDown() {
    }

    //   @Test
//    void testLogin() throws Exception {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("username", "admin");
//        params.add("password", "admin");
//
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                .post("/api/login/account")
//                .params(params)).andReturn();
//
//        int status = result.getResponse().getStatus();
//        String content = result.getResponse().getContentAsString();
//
//        Assert.assertEquals(200, status);
//    }
    @Test
    void testCurrentUser() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/currentUser")
                .params(params)).andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
    }

    @Test
    void testLogout() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "admin");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/currentUser")
                .params(params)).andReturn();

        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();

        Assert.assertEquals(200, status);
    }

}


