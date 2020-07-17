package bm.app.controller;

import bm.app.models.FinanceProductModelDTO;
import bm.app.services.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {
    private Service service;

    public RestController(Service service){
        this.service = service;
    }

    @GetMapping("/productList")
    public List<FinanceProductModelDTO> getFinancialProducts(){
        return service.selectAllRecords();
    }

    @PostMapping("/product")
    public void insertARecordIntoADatabase(@RequestBody FinanceProductModelDTO financeProductModel){
        service.insertARecord(financeProductModel);
    }



}
