package com.tradisys.odyssey.apg.s2w.store;

import java.util.Objects;

public class EntityWithId<T> {
    private T value;
    private int id;

    public EntityWithId(T value, int id) {
        this.value = value;
        this.id = id;
    }

    public T getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityWithId<?> that = (EntityWithId<?>) o;
        return getId() == that.getId() &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getId());
    }
}
