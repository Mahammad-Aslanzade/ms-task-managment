package com.example.msjira.controller;

import com.example.msjira.model.teleSales.TeleSaleDto;
import com.example.msjira.model.teleSales.TeleSaleReqDto;
import com.example.msjira.service.TeleSalesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telesale")
@RequiredArgsConstructor
public class TeleSalesController {

    private final TeleSalesService teleSalesService;

    @GetMapping
    public List<TeleSaleDto> getAllTeleSales(){
        return teleSalesService.getAllTeleSales();
    }

    @GetMapping("/{teleSaleId}")
    public TeleSaleDto getTeleSaleById(@PathVariable String teleSaleId){
        return teleSalesService.getTeleSaleById(teleSaleId);
    }

    @PostMapping
    public void createTeleSale(@Valid @RequestBody TeleSaleReqDto teleSaleReqDto){
        teleSalesService.createTeleSale(teleSaleReqDto);
    }

    @PutMapping("/{teleSaleId}")
    public void updateTeleSale(@PathVariable String teleSaleId ,@Valid @RequestBody TeleSaleReqDto teleSaleReqDto){
        teleSalesService.updateTeleSale(teleSaleId , teleSaleReqDto);
    }

    @DeleteMapping("/{teleSaleId}")
    public void deleteTeleSale(@PathVariable String teleSaleId){
        teleSalesService.deleteTeleSale(teleSaleId);
    }

}
