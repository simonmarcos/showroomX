package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Provider;
import com.SistemaGestion.ShowroomX.Repository.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProviderServiceImpl {

    @Autowired
    private IProvider dao;

//    public ProviderServiceImpl(IProvider dao) {
//        this.dao = dao;
//    }

    public Provider save(Provider provider) {
        if (provider.getName() == null) {
            return null;
        }
        if (!provider.getName().replace(" ", "").chars().allMatch(Character::isLetter)) {
            return null;
        }
        return dao.save(provider);
    }

    public Provider update(Provider provider) {
        if (provider.getName() == null) {
            return null;
        }
        if (!provider.getName().replace(" ", "").chars().allMatch(Character::isLetter)) {
            return null;
        }
        return dao.save(provider);
    }

    public List<Provider> findAll() {
        return (List<Provider>) dao.findAll();
    }

    public Page<Provider> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public long deleteByIdProvider(long id) {
        //Return 1 if OK
        //Return 0 if NOK
        return id != 0 ? dao.deleteByIdProvider(id) : null;
    }

    public long deleteByName(String name) {
        return !name.equals("") ? dao.deleteByName(name) : null;
    }
}
