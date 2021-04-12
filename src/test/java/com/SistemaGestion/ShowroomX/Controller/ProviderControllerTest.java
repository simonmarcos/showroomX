package com.SistemaGestion.ShowroomX.Controller;

import com.SistemaGestion.ShowroomX.Model.Provider;
import com.SistemaGestion.ShowroomX.Service.ProviderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = ProviderController.class)
public class ProviderControllerTest {

    @Mock
    private ProviderServiceImpl providerService;

    @InjectMocks
    private final ProviderController providerController;

    @Autowired
    private MockMvc mockMvc;

    public ProviderControllerTest() {
        this.providerController = new ProviderController(providerService);
    }

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(providerController).build();
    }

    //All fields OK
    @Test
    public void TestSaveProviderWithAllFieldOK() throws Exception {

        Provider provider = this.getProviderdWithoutNameFieldsOK();
        String providerObjectJson = new ObjectMapper().writeValueAsString(provider);

        Mockito.when(providerService.save(provider)).thenReturn(provider);

        //ResponseEntity<Provider> providerResponseEntity = providerController.save(provider);
        //Assert.assertEquals(providerResponseEntity.getStatusCode(), HttpStatus.OK);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .post("/provider/save")
                .content(providerObjectJson))
                .andReturn().getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }


    private Provider getProviderdWithoutNameFieldsOK() {
        Provider provider = new Provider();
        provider.setIdProvider(1);
        provider.setName("Jeans Guerra");
        provider.setPhone("3816760122");
        provider.setProvince("Tucumán");

        return provider;
    }
}
