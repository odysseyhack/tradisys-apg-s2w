package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;
import org.springframework.stereotype.Service;

@Service
public class PrincipalStoreProvideImpl implements PrincipalStoreProvider {
    private CustomerStore customerStore;
    private OrganizationStore organizationStore;

    @Override
    public BaseStore resolve(BasicPrincipal principal) {
        switch (principal.getRole()) {
            case CUSTOMER:
                return customerStore;
            case ORGANIZATION:
                return organizationStore;
            default:
                throw new IllegalStateException("Unsupported principal type");
        }
    }
}
