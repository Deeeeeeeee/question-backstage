package com.seal_de.data;

import com.seal_de.domain.Cities;

import java.util.List;

public interface CitiesRepository {
    List<Cities> findByProvinceId(String provinceId);
}
