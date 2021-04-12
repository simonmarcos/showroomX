package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Brand;
import com.SistemaGestion.ShowroomX.Repository.IBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl {

    private IBrand dao;

    @Autowired
    public BrandServiceImpl(IBrand dao) {
        this.dao = dao;
    }

    public Brand save(Brand brand) {
        if (brand.getPurchaseAmount() == 0 || brand.getUnitSaleAmount() == 0) {
            return null;
        }
        if (brand.getName() == null) {
            return null;
        }
        if (!brand.getName().replace(" ", "").chars().allMatch(Character::isLetter)) {
            return null;
        }
        return dao.save(brand);
    }

    public List<Brand> findAll() {
        return (List<Brand>) dao.findAll();
    }

    public Page<Brand> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Brand findByName(String name) {
        return dao.findByName(name);
    }

    public Long deleteById(long idBrand) {
        //Return 1 if OK
        //Return 0 if NOK
        return idBrand != 0 ? dao.deleteByIdBrand(idBrand) : null;
    }

    public long deleteByName(String name) {
        //Return 1 if OK
        //Return 0 if NOK
        return !name.equals("") ? dao.deleteByName(name) : null;
    }

    public Brand update(Brand brand) {

        if (brand.getPurchaseAmount() == 0 || brand.getUnitSaleAmount() == 0) {
            return null;
        }
        return dao.save(brand);
    }

    public Brand findStockById(Integer id) {
        return dao.findStockById(id);
    }

    public List<Brand> findByStockZero() {

        List<Brand> listBrand = new ArrayList<>();

        //Foreach list obtain and filter by sum stock equals to zero
        ((List<Brand>) dao.findAll())
                .stream()
                .forEach(b -> {
                    if ((b.getStockS() + b.getStockXS() + b.getStockM() + b.getStockL() + b.getStockXL()) == 0) {
                        listBrand.add(b);
                    }
                });

        return listBrand;
    }

}
