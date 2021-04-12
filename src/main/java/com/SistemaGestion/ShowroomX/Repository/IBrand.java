package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Brand;
import com.SistemaGestion.ShowroomX.Model.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IBrand extends CrudRepository<Brand, Long> {

    Page<Brand> findAll(Pageable pageable);

    Brand findByName(String name);

    Long deleteByName(String name);

    Long deleteByIdBrand(Long idBrand);

    List<Brand> findByProvider(Provider provider);

    @Query("SELECT m.stockXS,m.stockS,m.stockM,m.stockL,m.stockXL FROM Brand m WHERE m.idBrand = :idBrand")
    Brand findStockById(@Param("idBrand") Integer idBrand);

}
