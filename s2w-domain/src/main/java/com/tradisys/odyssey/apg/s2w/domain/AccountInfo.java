package com.tradisys.odyssey.apg.s2w.domain;

import java.util.Objects;

public class AccountInfo {
    protected String seed;
    protected String publicKey;
    protected String privateKey;

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountInfo that = (AccountInfo) o;
        return Objects.equals(getSeed(), that.getSeed()) &&
                Objects.equals(getPublicKey(), that.getPublicKey()) &&
                Objects.equals(getPrivateKey(), that.getPrivateKey());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSeed(), getPublicKey(), getPrivateKey());
    }
}
