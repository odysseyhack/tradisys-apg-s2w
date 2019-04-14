package com.tradisys.odyssey.apg.s2w.waves;

import com.wavesplatform.wavesj.Account;
import com.wavesplatform.wavesj.Asset;

public class ConfigProperties {
    public static final byte CHAIN = Account.TESTNET;
    public static final String S2WToken = "CvtAXaRj8YV5AhCN5SnAogdnZjwqrTWqBbnuQE2behbE";
    public static final String S2WVotingToken = "4tEutR41HbddH3sXpVWHwF3KP1FFPgbrLmmz66rUMpvs";
    public static final String URL = "https://testnode4.wavesnodes.com";
    public static final long ORG_INIT_BALANCE = 1000;
    public static final long TRANSFER_FEE = Asset.TOKEN / 1000;
}
