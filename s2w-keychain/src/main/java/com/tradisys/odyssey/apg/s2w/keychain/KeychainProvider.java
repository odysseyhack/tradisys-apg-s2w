package com.tradisys.odyssey.apg.s2w.keychain;

/**
 * Common interface for keychain handling
 */
public interface KeychainProvider {

    String APG_ID = "APG_ID";

    /**
     * Perform seed generation
     *
     * @param seedName  name of a future seed to be stored in a keychain
     * @return generated seed
     */
    String generateSeed(String seedName);

    /**
     * Find and return seed by its id
     *
     * @param id  id of a seed stored in a keychain
     * @return  seed
     */
    String getSeedById(String id);
}
