package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Client;
import com.SistemaGestion.ShowroomX.Model.Sales;
import com.SistemaGestion.ShowroomX.Repository.ISales;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SalesServiceTest {

    @Mock
    private ISales salesRepository;

    @InjectMocks
    private SalesServiceImpl salesService;

    @Test
    public void TestFindByIdClient_OK() {
        Sales sales = this.getSalesWithAllFieldsOK();
        Long idClient = new Long(1);

        Mockito.when(salesRepository.findByIdClient(idClient)).thenReturn(Arrays.asList(sales, sales));

        List<Sales> salesResponse = salesService.findByClient(idClient);

        Assert.assertEquals(salesResponse.size(), 2);
    }

    @Test
    public void TestFindByIdClient_NOK() {
        Sales sales = this.getSalesWithAllFieldsOK();
        Long idClient = new Long(1);

        Mockito.when(salesRepository.findByIdClient(idClient)).thenReturn(Arrays.asList(sales, sales));

        List<Sales> salesResponse = salesService.findByClient(idClient);

        Assert.assertNotSame(salesResponse.size(), 1);
    }


    //Not Found Sales with the IdClient
    @Test
    public void TestFindByIdClientNotFoundSales() {
        Sales sales = this.getSalesWithAllFieldsOK();
        Long idClient = new Long(1);

        Mockito.when(salesRepository.findByIdClient(idClient)).thenReturn(Arrays.asList());

        List<Sales> salesResponse = salesService.findByClient(idClient);
        Assert.assertNull(salesResponse);
    }


    private Sales getSalesWithAllFieldsOK() {
        Sales sales = new Sales();
        sales.setIdSales(new Long(1));
        sales.setAmount(2);
        sales.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        sales.setPaymentTpye("Efectivo");

        Client client = new Client();
        client.setIdClient(1);

        sales.setClient(client);

        return sales;
    }
}
