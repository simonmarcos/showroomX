package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.Brand;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BrandRepositoryTest extends TestCase {

    @Autowired
    private IBrand iBrandRepository;

    @Test
    public void saveBrandWithValuesOKTest() {
        Brand brand = new Brand();
        brand.setName("Fernet Branca");
        brand.setStockXS(100);
        brand.setPurchaseAmount(280);
        brand.setUnitSaleAmount(370);
        brand.setPromotionSaleAmount(350);

        Brand brandResponse = iBrandRepository.save(brand);
        assertEquals(brand, brandResponse);
    }

    //Field Name exceeds allowed limit
    @Test(expected = Exception.class)
    public void saveBrandWithLengthNameLongExcepcionTest() {
        Brand brand = new Brand();
        brand.setName("Fernet Brancaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        brand.setStockXS(100);
        brand.setPurchaseAmount(280);
        brand.setUnitSaleAmount(370);
        brand.setPromotionSaleAmount(350);

        iBrandRepository.save(brand);
    }

    //Sabe Brand Without Name
    @Test(expected = Exception.class)
    @Rollback
    public void saveBrandWithoutNameExceptionTest() {
        Brand brand = new Brand();
        brand.setStockXS(100);
        brand.setPurchaseAmount(280);
        brand.setUnitSaleAmount(370);
        brand.setPromotionSaleAmount(350);

        iBrandRepository.save(brand);
    }

    //Find Size Iterable
    @Test
    public void findAllBrandBySizeTest() {
        final long sizeListBrandStatic = 3;
        long sizeListBrandDB = iBrandRepository.findAll().spliterator().getExactSizeIfKnown();
        assertEquals(sizeListBrandStatic, sizeListBrandDB);
    }

    @Test
    @Rollback
    public void findAllPageableTest() {
        Page<Brand> listBrand = iBrandRepository.findAll(PageRequest.of(0, 2, Sort.by("name").ascending()));
        assertEquals(2, listBrand.getTotalElements());
    }

    //Delete By ID Message OK
    @Test
    @Rollback
    public void deleteBrandByIdSuccessTest() {
        final long idBrand = 5;
        long brandResponse = iBrandRepository.deleteByIdBrand(idBrand);
        assertEquals(1, brandResponse);
    }

    //Delete By Name Message OK
    @Test
    @Rollback
    public void deleteBrandByNameSuccessTest() {
        final String nameBrand = "Fernet Branca";
        long brandResponse = iBrandRepository.deleteByName(nameBrand);
        assertEquals(1, brandResponse);
    }

    //Delete By Name Message NOK
    @Test
    @Rollback
    public void deleteBrandByNameExceptionTest() {
        final String nameBrand = "Fernet Brancaa";
        long brandResponse = iBrandRepository.deleteByName(nameBrand);
        assertEquals(0, brandResponse);
    }
}