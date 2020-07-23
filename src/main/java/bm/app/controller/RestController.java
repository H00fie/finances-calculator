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

    @GetMapping("/tenPercentRaise/{number}")
    public BigDecimal increaseInputByTenPercent(@PathVariable BigDecimal number){
        return service.increaseByTenPercent(number);
    }

    @GetMapping("/priceByName/{name}")
    public BigDecimal checkThePriceByName(@PathVariable String name){
        return service.findAProductPriceByGivenName(name);
    }

    @GetMapping("/riskLevel/{months}")
    public RiskLevel checkTheRiskLevelForGivenTimePeriod(@PathVariable int months){
        return service.giveTheRiskNameBasedOnNumberOfMonths(months);
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
