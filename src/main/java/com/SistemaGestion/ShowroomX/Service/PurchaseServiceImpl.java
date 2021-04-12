package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Purchases;
import com.SistemaGestion.ShowroomX.Repository.IPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl {

    private final IPurchase dao;

    @Autowired
    public PurchaseServiceImpl(IPurchase dao) {
        this.dao = dao;
    }

    public Purchases save(Purchases purchases) {
        if (purchases.getAmount() == 0 || purchases.getPrice() == 0) {
            return null;
        }
        return dao.save(purchases);
    }

    public Purchases update(Purchases purchases) {
        if (purchases.getAmount() == 0 && purchases.getPrice() == 0) {
            return null;
        }
        return dao.save(purchases);
    }

    public List<Purchases> findAll() {
        return (List<Purchases>) dao.findAll();
    }

    public Page<Purchases> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Long deleteById(Long idPurchases) {
        return idPurchases != 0 ? dao.deleteByIdPurchases(idPurchases) : null;
    }
}
