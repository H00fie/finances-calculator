package bm.app.controller;

import bm.app.models.FinanceProductModelDTO;
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
    public List<FinanceProductModelDTO> getFinancialProducts(){
        return service.selectAllRecords();
    }

    @PostMapping("/product")
    public void insertARecordIntoTheDatabase(@RequestBody FinanceProductModelDTO financeProductModel){
        service.insertARecord(financeProductModel);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteARecordFromTheDatabase(@PathVariable int id){
        service.deleteARecord(id);
    }



}
