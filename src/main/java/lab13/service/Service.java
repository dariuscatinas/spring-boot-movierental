package lab13.service;

import lab13.domain.BaseEntity;

import java.util.Set;
import java.util.function.Predicate;

public interface Service<ID,T extends BaseEntity<ID>> {

    Set<T> filterCustom(Predicate<? super T> predicate);
}
