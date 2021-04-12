package com.SistemaGestion.ShowroomX.Service;

import com.SistemaGestion.ShowroomX.Model.Sales;
import com.SistemaGestion.ShowroomX.Repository.ISales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SalesServiceImpl {

    private final ISales dao;

    @Autowired
    public SalesServiceImpl(ISales dao) {
        this.dao = dao;
    }

    public Sales save(Sales sales) {
        //Validate that all fields are completed
        if (sales.getAmount() == 0 || sales.getPaymentTpye().isEmpty() || sales.getPrice() == 0 || sales.getClient().getIdClient() == 0) {
            return null;
        }
        return dao.save(sales);
    }

    public Sales update(Sales sale) {
        //Validate that all fields are completed
        if (sale.getAmount() == 0 || sale.getPaymentTpye().isEmpty() || sale.getPrice() == 0 || sale.getClient().getIdClient() == 0) {
            return null;
        }
        return dao.save(sale);
    }

    public List<Sales> findAll() {
        return (List<Sales>) dao.findAll();
    }

    public Page<Sales> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public List<Sales> findByDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
        try {
            Date parsed = simpleDateFormat.parse(date);
            java.sql.Date dateSales = new java.sql.Date(new Date(date).getTime());

            return dao.findByDate(dateSales);
        } catch (ParseException e) {
            e.printStackTrace();
            //In the case that the date not in the format correct
            return null;
        }
    }

    public List<Sales> findByPaymentTpye(String paymentType) {
        //Validate that String contains only Letters
        return paymentType.chars().allMatch(Character::isLetter) ? dao.findByPaymentTpye(paymentType) : null;
    }

    public List<Sales> findByClient(Long idClient) {
        List<Sales> listSales = dao.findByIdClient(idClient);

        return listSales.size() != 0 ? listSales : null;
    }

    public Long deleteByIdSales(Long idSales) {
        return dao.deleteByIdSales(idSales);
    }
}
