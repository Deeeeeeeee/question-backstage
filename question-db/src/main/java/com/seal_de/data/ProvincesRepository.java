package com.seal_de.data;

import com.seal_de.domain.Provinces;

import java.util.List;

public interface ProvincesRepository {
    List<Provinces> findAll();
}
