package com.tradisys.odyssey.apg.s2w.services.impl;

import com.tradisys.odyssey.apg.s2w.domain.AccountInfo;
import com.tradisys.odyssey.apg.s2w.domain.Customer;
import com.tradisys.odyssey.apg.s2w.domain.Role;
import com.tradisys.odyssey.apg.s2w.keychain.KeychainProvider;
import com.tradisys.odyssey.apg.s2w.services.CustomerService;
import com.tradisys.odyssey.apg.s2w.store.CustomerStore;
import com.wavesplatform.wavesj.Base58;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerStore customerStore;

    @Autowired
    KeychainProvider keychainProvider;

    @Override
    public List<Customer> findAllCustomers() {
        return customerStore.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long customerId) {
        return customerStore.findById(customerId);
    }

    @Override
    public boolean ensureCustomerExists(Long customerId) {
        return customerStore.findById(customerId).isPresent();
    }

    @Override
    public Optional<AccountInfo> findCustomerAccountInfo(Long customerId) {
        String maybeSeed = keychainProvider.getSeedById(Role.CUSTOMER.name() + "_" + customerId.toString());
        return Optional.ofNullable(maybeSeed).map(CustomerServiceImpl::mkAccountInfo);
    }

    protected static AccountInfo mkAccountInfo(String seed) {
        AccountInfo accountInfo = new AccountInfo();
        PrivateKeyAccount pka = PrivateKeyAccount.fromSeed(seed, 0, (byte) 'T');

        accountInfo.setSeed(seed);
        accountInfo.setPrivateKey(Base58.encode(pka.getPrivateKey()));
        accountInfo.setPublicKey(Base58.encode(pka.getPublicKey()));

        return accountInfo;
    }
}
