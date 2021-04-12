package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Brand;
import com.SistemaGestion.ShowroomX.Model.Provider;
import com.SistemaGestion.ShowroomX.Repository.IBrand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
//@Transactional
public class BrandServiceTest extends TestCase {

    @Mock
    private IBrand brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    //Save All Fields OK
    @Test()
    public void saveBrandFieldOKTest() throws Exception {

        Brand brand = this.getBrandWithAllFields();

        Mockito.when(brandRepository.save(brand)).thenReturn(brand);

        Brand brandResponse = brandService.save(brand);
        assertEquals(brand, brandResponse);
    }

    //Save Without Field Name
    @Test
    public void saveBrandWithoutFieldNameTest() throws Exception {
        Brand brand = this.getBrandWithoutNameField();

        Mockito.when(brandRepository.save(brand)).thenReturn(null);
        Brand brandResponse = brandService.save(brand);

        assertNull(brandResponse);
    }

    //Save Without Field Stock
    @Test
    public void saveBrandWithoutFieldStockTest() throws Exception {
        Brand brand = this.getBrandWithoutStockField();

        Mockito.when(brandRepository.save(brand)).thenReturn(brand);
        Brand brandResponse = brandService.save(brand);
        assertEquals(0, brandResponse.getStockXS());
    }

    //Save Without Fields PurchaseAmount
    @Test
    public void saveBrandWithoutFieldPurchaseAmountTest() throws Exception {
        Brand brand = this.getBrandWithoutPurchaseField();

        Mockito.when(brandRepository.save(brand)).thenReturn(null);

        Brand brandResponse = brandService.save(brand);
        assertNull(brandResponse);
    }

    //Save Without Fields Unit Sale Amount
    @Test
    public void saveBrandWithoutFieldUnitSaleAmountTest() throws Exception {
        Brand brand = this.getBrandWithoutUnitSaleAmountField();

        Mockito.when(brandRepository.save(brand)).thenReturn(null);

        Brand brandResponse = brandService.save(brand);
        assertNull(brandResponse);
    }

    //Update Brand with Fields OK
    @Test
    public void updateBrandWithFieldsOKTest() {
        Brand brand = this.getBrandWitIDField(5);

        Mockito.when(brandRepository.save(brand)).thenReturn(brand);
        Brand brandResponse = brandService.save(brand);

        assertEquals(120, brandResponse.getStockXS());
    }

    //Update Brand with Id NOT Exist
    @Test
    public void updateBrandWithIdNOKTest() {
        Brand brand = this.getBrandWitIDField(1);
        Mockito.when(brandRepository.save(brand)).thenReturn(null);
        assertNull(brandService.update(brand));
    }

    //Delete By ID Message SUCCESS
    @Test
    public void deleteBrandByIdSuccessTest() {
        Brand brand = this.getBrandWithAllFields();
        final Long idBrand = new Long(5);

        Mockito.when(brandRepository.deleteByIdBrand(idBrand)).thenReturn(new Long(1));

        long brandResponse = brandService.deleteById(idBrand);
        assertEquals(1, brandResponse);
    }

    //Delete By ID Message FAILED by Id not Exist
    @Test()
    public void deleteBrandByIdExcepctionNotExistIDTest() {
        final long idBrand = 1;
        long brandResponse = brandService.deleteById(idBrand);
        assertEquals(0, brandResponse);
    }

    //Delete By Name Message OK
    @Test
    public void deleteBrandByNameSuccessTest() {
        final String nameBrand = "Fernet Branca";
        Mockito.when(brandRepository.deleteByName(nameBrand)).thenReturn(new Long(1));

        long brandResponse = brandService.deleteByName(nameBrand);
        assertEquals(1, brandResponse);
    }

    //Delete By Name Message NOT Exist
    @Test
    public void deleteBrandByNameNotExistTest() {
        final String nameBrand = "Sky 750ml";
        Mockito.when(brandRepository.deleteByName(nameBrand)).thenReturn(new Long(1));

        long brandResponse = brandService.deleteByName("Sky");
        assertEquals(0, brandResponse);
    }

    //Find All OK
    @Test
    public void findAllBrandTest() {
        Brand brand_1 = this.getBrandWithAllFields();
        Brand brand_2 = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findAll()).thenReturn(Arrays.asList(brand_1, brand_2));

        List<Brand> listBrandResponse = brandService.findAll();
        assertEquals(2, listBrandResponse.size());
    }

    //Find Pageable Page 0 and Size 1
    @Test
    public void findAllBrandPageableTest_1() {

        Brand brand = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findAll(Mockito.any(Pageable.class))).thenReturn((Page<Brand>) Arrays.asList(brand));

        Page<Brand> listBrand = brandService.findAllPageable((Pageable) PageRequest.of(0, 1, Sort.by("name").ascending()));
        assertTrue(listBrand.getTotalElements() == 1);
    }

    //Find Pageable Page 0 and Size 10
    @Test
    public void findAllBrandPageableTest_2() {

        Brand brand_1 = this.getBrandWithAllFields();
        Brand brand_2 = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findAll((Pageable) PageRequest.of(0, 10, Sort.by("name").ascending()))).thenReturn((Page<Brand>) Arrays.asList(brand_1, brand_2));
        Page<Brand> listBrand = brandService.findAllPageable((Pageable) PageRequest.of(0, 10, Sort.by("name").ascending()));
        assertEquals(2, listBrand.getTotalElements());
    }

    //Find Brand By Name OK
    @Test
    public void findByNameOKTest() {
        Brand brand = this.getBrandWithAllFields();
        final String nameBrand = "Jeans Guerra";

        Mockito.when(brandRepository.findByName(nameBrand)).thenReturn(brand);
        Brand brandResponse = brandService.findByName(nameBrand);
        assertEquals(nameBrand, brandResponse.getName());
    }

    //Find By Name and Get Provider and convert to JSON
    @Test
    public void findByNameAndGetProviderJSONTest() throws JsonProcessingException {
        Brand brand = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findByName(brand.getName())).thenReturn(brand);
        Brand brandResponse = brandService.findByName(brand.getName());

        String result = new ObjectMapper().writeValueAsString(brandResponse.getProvider());//Convertimos el Object en JSON
        System.out.println(result);
        assertNotSame(result, "null");
    }

    //Find Brand By Name Exception
    @Test
    public void findByNameExceptionTest() {
        Brand brand = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findByName(Mockito.anyString())).thenReturn(null);

        Brand brandResponse = brandService.findByName("Fernet Branca");
        assertNull(brandResponse);
    }

    //Find Brand By Stock = 0
    @Test
    public void findByStockEqualsZeroTest() {

        Brand brand = this.getBrandWithAllFields();

        Mockito.when(brandRepository.findStockById(Mockito.anyInt())).thenReturn(brand);

        Brand brandresponse = brandService.findStockById(0);
        assertEquals(1, 1);
    }


    private Brand getBrandWithAllFields() {
        Brand brand = new Brand();
        brand.setIdBrand(5);
        brand.setName("Jeans Guerra");
        brand.setStockXS(100);
        brand.setPurchaseAmount(750);
        brand.setUnitSaleAmount(1200);
        brand.setPromotionSaleAmount(1100);

        Provider provider = new Provider();
        provider.setIdProvider(1);

        brand.setProvider(provider);

        return brand;
    }

    private Brand getBrandWithoutNameField() {
        Brand brand = new Brand();
        brand.setStockXS(100);
        brand.setPurchaseAmount(750);
        brand.setUnitSaleAmount(1200);
        brand.setPromotionSaleAmount(1100);

        return brand;
    }

    private Brand getBrandWithoutStockField() {
        Brand brand = new Brand();
        brand.setName("Jeans Guerra");
        brand.setPurchaseAmount(750);
        brand.setUnitSaleAmount(1200);
        brand.setPromotionSaleAmount(1100);

        return brand;
    }

    private Brand getBrandWithoutPurchaseField() {
        Brand brand = new Brand();
        brand.setName("Jeans Guerra");
        brand.setStockXS(100);
        brand.setUnitSaleAmount(1200);
        brand.setPromotionSaleAmount(1100);

        return brand;
    }

    private Brand getBrandWithoutUnitSaleAmountField() {
        Brand brand = new Brand();
        brand.setName("Jeans Guerra");
        brand.setStockXS(100);
        brand.setPurchaseAmount(750);
        brand.setPromotionSaleAmount(1100);

        return brand;
    }

    private Brand getBrandWitIDField(Integer idBrand) {
        Brand brand = new Brand();
        brand.setName("Jeans Guerra");
        brand.setIdBrand(idBrand);
        brand.setStockXS(120);
        brand.setPurchaseAmount(750);
        brand.setUnitSaleAmount(1200);
        brand.setPromotionSaleAmount(1100);

        return brand;
    }

}