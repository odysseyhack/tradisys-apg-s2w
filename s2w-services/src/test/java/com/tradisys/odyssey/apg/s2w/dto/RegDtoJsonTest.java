package com.tradisys.odyssey.apg.s2w.dto;

import com.tradisys.odyssey.apg.s2w.services.config.ServicesSpringConfig;
import com.tradisys.odyssey.apg.s2w.services.dto.CustomerRegDto;
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
            "\"secondName\":\"APG\"" +
            "}";

    @Autowired
    private BiDirectionMapper mapper;

    @Test
    public void testCustomerRegDto() throws Exception {
        CustomerRegDto dto = mapper.mapAny(new ByteArrayInputStream(CUSTOMER_JSON.getBytes()), CustomerRegDto.class);
        Assert.assertNotNull(dto);
        Assert.assertEquals("Tradisys", dto.getFirstName());
        Assert.assertEquals("APG", dto.getSecondName());
    }
}
