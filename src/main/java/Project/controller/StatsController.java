package Project.controller;


import Project.dto.UserConversionDto;
import Project.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;


    @GetMapping("/conversion")
    public ResponseEntity<List<Long>> getUserToConversionSum(UserConversionDto dto){
        return ResponseEntity.ok(statsService.getUserListByConversionSum(dto));
    }

    @GetMapping("/total")
    public ResponseEntity<List<Long>> getUserListToTotalSum(UserConversionDto dto){
        return ResponseEntity.ok(statsService.getUserListByTotalSumConversion(dto));
    }

    @GetMapping("/popular")
    public ResponseEntity getPopularConversion(){
        return ResponseEntity.ok(statsService.getPopularConversion());
    }


}
