package com.dmall.order.common;

import java.io.Serializable;

public interface DomainEntity<T> extends Serializable {
    T getId();

    boolean sameIdentityAs(T otherId);
}
