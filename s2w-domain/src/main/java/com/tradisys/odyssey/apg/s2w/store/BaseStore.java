package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.BasicIdentity;

import java.util.List;
import java.util.Optional;

public interface BaseStore<T extends BasicIdentity> {
    long insert(T t);
    void update(T t);
    void deleteById(long id);
    Optional<T> findById(long id);
    List<T> findAll();
}
