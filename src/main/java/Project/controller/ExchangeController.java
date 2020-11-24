package Project.controller;

import Project.dto.ConversionDto;
import Project.dto.ResultConversionDto;
import Project.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    ExchangeService exchangeService;


    @GetMapping
    public ResponseEntity<ResultConversionDto> conversion(ConversionDto responseDto){
        return ResponseEntity.ok(exchangeService.conversion(responseDto));
    }

}
