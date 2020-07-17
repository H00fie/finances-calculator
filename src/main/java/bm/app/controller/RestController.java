package bm.app.controller;

import bm.app.models.FinanceProductModel;
import bm.app.services.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/v1")
public class RestController {
    private Service service;

    public RestController(Service service){
        this.service = service;
    }

    @GetMapping("/productList")
    public List<FinanceProductModel> getFinancialProducts(){
        return service.selectAllRecords();
    }



}
