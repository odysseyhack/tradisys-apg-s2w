package com.tradisys.odyssey.apg.s2w;

import com.tradisys.odyssey.apg.s2w.controller.CustomerController;
import com.tradisys.odyssey.apg.s2w.controller.OrganizationController;
import com.tradisys.odyssey.apg.s2w.controller.RegistrationController;
import com.tradisys.odyssey.apg.s2w.controller.TaskController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private CustomerController customerController;

    @Autowired
    private OrganizationController organizationController;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private TaskController taskController;

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

    @Test
    public void t004_findCustomerTest() {
        Assert.assertTrue(customerController.findAllCustomers().getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void t005_testOrgController() {
        Assert.assertTrue(organizationController.findAllOrganizations().getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void t006_testRegistrationController() {
        Assert.assertEquals("ONLINE", registrationController.status());
    }

    public void t007_testTaskController() {
        Assert.assertTrue(taskController.allTasks().getStatusCode() == HttpStatus.OK);
    }
}
