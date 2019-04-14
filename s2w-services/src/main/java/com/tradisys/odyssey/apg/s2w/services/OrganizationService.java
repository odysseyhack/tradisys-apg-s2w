package com.tradisys.odyssey.apg.s2w.services;

import com.tradisys.odyssey.apg.s2w.domain.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    boolean ensureOrganizationExists(Long organizationId);
    List<Organization> findAllOrganizations();
    Optional<Organization> findOrganizationById(Long organizationId);
}
