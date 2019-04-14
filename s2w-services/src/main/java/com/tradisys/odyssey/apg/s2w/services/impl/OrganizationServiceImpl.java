package com.tradisys.odyssey.apg.s2w.services.impl;

import com.tradisys.odyssey.apg.s2w.domain.Organization;
import com.tradisys.odyssey.apg.s2w.services.OrganizationService;
import com.tradisys.odyssey.apg.s2w.store.OrganizationStore;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationStore organizationStore;

    @Override
    public boolean ensureOrganizationExists(Long organizationId) {
        return organizationStore.findById(organizationId).isPresent();
    }

    @Override
    public List<Organization> findAllOrganizations() {
        return organizationStore.findAll();
    }

    @Override
    public Optional<Organization> findOrganizationById(Long organizationId) {
        return organizationStore.findById(organizationId);
    }
}
