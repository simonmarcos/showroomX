package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Provider;
import com.SistemaGestion.ShowroomX.Repository.IProvider;
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
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
public class ProviderServiceTest extends TestCase {

    @Mock
    private IProvider providerRepository;

    @InjectMocks
    private ProviderServiceImpl providerService;

    @Test
    @Rollback
    public void testSaveWithAllFieldsOK() {
        Provider provider = this.getProviderWithAllFieldsOK();

        Mockito.when(providerRepository.save(provider)).thenReturn(provider);

        Provider providerResponse = providerService.save(provider);
        assertEquals(providerResponse, provider);
    }

    //Test without Field Name - Return = Null
    @Test
    @Rollback
    public void testSaveWithoutFieldName() {
        Provider provider = this.getProviderWithoutFieldName();

        Mockito.when(providerRepository.save(provider)).thenReturn(null);

        Provider providerResponse = providerService.save(provider);
        assertNull(providerResponse);
    }

    @Test
    @Rollback
    public void testFindAllProvider_OK() {

        Provider provider_1 = this.getProviderWithAllFieldsOK();
        Provider provider_2 = this.getProviderWithAllFieldsOK();

        Mockito.when(providerRepository.findAll()).thenReturn(Arrays.asList(provider_1, provider_2));

        List<Provider> listProvider = providerService.findAll();
        assertEquals(2, listProvider.size());

    }

    @Test
    @Rollback
    public void testFindAllProvider_NOK() {

        Provider provider_1 = this.getProviderWithAllFieldsOK();
        Provider provider_2 = this.getProviderWithAllFieldsOK();

        Mockito.when(providerRepository.findAll()).thenReturn(Arrays.asList(provider_1, provider_2));

        List<Provider> listProvider = providerService.findAll();
        assertNotSame(1, listProvider.size());

    }

    @Test
    @Rollback
    public void testFindAllPageable_OK() {

        Provider provider_1 = this.getProviderWithAllFieldsOK();
        Provider provider_2 = this.getProviderWithAllFieldsOK();

        Mockito.when(providerRepository.findAll(PageRequest.of(0, 3))).thenReturn((Page<Provider>) Arrays.asList(provider_1, provider_2, provider_1));

        List<Provider> listProvider = providerService.findAllPageable((Pageable) PageRequest.of(0, 3)).getContent();
        assertEquals(3, listProvider.size());
    }

    @Test
    @Rollback
    public void testUpdateWithAllFieldOK() {

        Provider provider = this.getProviderWithAllFieldsOK();

        Mockito.when(providerRepository.save(provider)).thenReturn(provider);

        Provider providerResponse = providerService.update(provider);
        assertEquals(providerResponse, provider);

    }

    @Test
    @Rollback
    public void testUpdateWithFieldNameNotAllow() {

        Provider provider = this.getProviderWithFieldsNameNotAllow();

        Mockito.when(providerRepository.save(provider)).thenReturn(provider);

        Provider providerResponse = providerService.update(provider);
        assertEquals(providerResponse, null);

    }

    @Test
    @Rollback
    public void testDeleteByIdProvider() {
        final Long idProvider = new Long(1);

        Mockito.when(providerRepository.deleteByIdProvider(idProvider)).thenReturn(new Long(1));

        long providerResponse = providerService.deleteByIdProvider(idProvider);
        assertEquals(1, providerResponse);
    }

    @Test
    @Rollback
    public void deleteByNameOKTest() {
        final String nameProvider = "Guerra";

        Mockito.when(providerRepository.deleteByName(nameProvider)).thenReturn(new Long(1));

        long providerResponse = providerService.deleteByName(nameProvider);
        assertEquals(1, providerResponse);
    }

    private Provider getProviderWithAllFieldsOK() {
        Provider provider = new Provider();
        provider.setIdProvider(1);
        provider.setName("Guerra");
        provider.setPhone("1160344565");
        provider.setProvince("Buenos Aires");

        return provider;
    }

    private Provider getProviderWithFieldsNameNotAllow() {
        Provider provider = new Provider();
        provider.setIdProvider(1);
        provider.setName("Guerra123%45");
        provider.setPhone("1160344565");
        provider.setProvince("Buenos Aires");

        return provider;
    }

    private Provider getProviderWithoutFieldName() {
        Provider provider = new Provider();
        provider.setIdProvider(1);
        provider.setPhone("1160344565");
        provider.setProvince("Buenos Aires");

        return provider;
    }
}