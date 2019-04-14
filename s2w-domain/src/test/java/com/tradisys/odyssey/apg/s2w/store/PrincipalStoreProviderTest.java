package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.config.DomainSpringConfig;
import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DomainSpringConfig.class)
public class PrincipalStoreProviderTest {

    @Autowired
    PrincipalStoreProvider principalStoreProvider;

    @Test
    public void testCustomerStoreProvider() {
        Customer customer = new Customer();
        customer.setFirstName("AAA");
        customer.setSecondName("BBB");
        customer.setRole(Role.CUSTOMER);
        BaseStore customerStore = principalStoreProvider.resolve(customer);
        Assert.assertTrue("customerStore is valid", customerStore instanceof CustomerStore);
    }

    @Test
    public void testOrganizationStoreProvider() {
        Organization organization = new Organization();
        organization.setName("AAA");
        organization.setRole(Role.ORGANIZATION);
        BaseStore customerStore = principalStoreProvider.resolve(organization);
        Assert.assertTrue("customerStore is valid", customerStore instanceof OrganizationStore);
    }

    @Test
    public void testCorrectTypeInstantiation() {
        Assert.assertTrue("Correct type is used", principalStoreProvider instanceof PrincipalStoreProvideImpl);
    }
}
