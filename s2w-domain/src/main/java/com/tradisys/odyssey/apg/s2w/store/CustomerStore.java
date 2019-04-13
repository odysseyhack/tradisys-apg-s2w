package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerStore extends BaseStore<Customer> {
    Optional<Long> customerIdByBSN(String bsn);
}
