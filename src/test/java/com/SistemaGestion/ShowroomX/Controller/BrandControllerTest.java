package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Brand;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrandControllerTest extends TestCase {


    @Test
    public void saveBrandTest() throws Exception {
        Brand brand = new Brand();
        brand.setName("Jeans Guerra");
        brand.setStockXS(100);
        brand.setPurchaseAmount(280);
        brand.setUnitSaleAmount(370);
        brand.setPromotionSaleAmount(350);

    }
}