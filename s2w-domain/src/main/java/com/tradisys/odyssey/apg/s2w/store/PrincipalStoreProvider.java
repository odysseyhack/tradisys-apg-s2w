package com.tradisys.odyssey.apg.s2w.store;

import com.tradisys.odyssey.apg.s2w.domain.BasicPrincipal;

public interface PrincipalStoreProvider {
    BaseStore resolve(BasicPrincipal principal);
}
