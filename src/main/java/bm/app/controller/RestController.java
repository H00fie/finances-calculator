package bm.app.controller;

import bm.app.models.FinanceProductModelDTO;
import bm.app.models.RiskLevel;
import bm.app.services.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/product/{id}")
    public FinanceProductModelDTO getARecordById(@PathVariable int id){
        return service.getARecordById(id);
    }

    @PostMapping("/product")
    public void insertARecordIntoTheDatabase(@RequestBody FinanceProductModelDTO financeProductModel){
        service.insertARecord(financeProductModel);
    }

    @PostMapping("/tenPercentRaise/{number}")
    public BigDecimal increaseInputByTenPercent(@PathVariable BigDecimal number){
        return service.increaseByTenPercent(number);
    }

    @PostMapping("/riskLevel/{months}")
    public RiskLevel checkTheRiskLevelForGivenTimePeriod(@PathVariable int months){
        return service.giveTheRiskNameBasedOnNumberOfMonths(months);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteARecordFromTheDatabase(@PathVariable int id){
        service.deleteARecord(id);
    }



}
