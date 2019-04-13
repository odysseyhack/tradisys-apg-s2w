package com.tradisys.odyssey.apg.s2w.dto;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.services.config.ServicesSpringConfig;
import com.tradisys.odyssey.apg.s2w.services.ser.BiDirectionMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServicesSpringConfig.class})
public class RegDtoJsonTest {

    private static final String CUSTOMER_JSON = "{" +
            "\"firstName\":\"Tradisys\"," +
            "\"secondName\":\"APG\"," +
            "\"type\":\"CUSTOMER\"" +
            "}";

    @Autowired
    private BiDirectionMapper mapper;

    @Test
    public void testCustomerRegDto() throws Exception {
        BasicPrincipal dto = mapper.mapAny(new ByteArrayInputStream(CUSTOMER_JSON.getBytes()), BasicPrincipal.class);
        Assert.assertNotNull(dto);
        Assert.assertEquals(Role.CUSTOMER.name(), dto.getType());
        Customer customer = (Customer) dto;
        Assert.assertEquals("Tradisys", customer.getFirstName());
        Assert.assertEquals("APG", customer.getSecondName());
    }
}
