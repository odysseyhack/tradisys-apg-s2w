package com.tradisys.odyssey.apg.s2w;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = S2WApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class S2WApplicationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    private String organizationRegistration = "{\"name\": \"Tradisys\",\"type\":\"ORGANIZATION\", \"role\":\"ORGANIZATION\", \"address\":\"Minsk\",\"rsin\":\"123123123\",\"status\":\"NEW\"}";
    private String customerRegistration = "{\"firstName\":\"Pavel\",\"secondName\":\"S.\",\"bsn\":\"123213\",\"address\":\"Minsk, Belarus\", \"role\":\"CUSTOMER\",\"type\":\"CUSTOMER\"}";
    private String taskCreation = "{\"name\":\"New Task\",\"description\":\"Description\",\"tokenCost\":3,\"votingPoints\":10,\"status\":\"OPEN\"}";
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void t000_testStatus()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/status")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void t001_registerOrganizationTest()
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .content(organizationRegistration)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void t002_registerCustomerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
                .content(customerRegistration)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void t003_createTaskTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/orgs/1/tasks")
                .content(taskCreation)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
