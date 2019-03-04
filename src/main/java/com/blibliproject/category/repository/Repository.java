package com.blibliproject.category.repository;

import java.util.List;

public interface Repository<T,K> {
    List<T> readAll();

    T readById(K id);

    T create(T data);
}
