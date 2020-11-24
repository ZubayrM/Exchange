package Project.service;

import Project.dto.PopularConversionDto;
import Project.dto.UserConversionDto;
import Project.repositories.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StatsService {

    @Autowired
    private ConversionRepository conversionRepository;

    public List<Long> getUserListByConversionSum(UserConversionDto dto){
        return conversionRepository.findUserByConversionSum(dto.getCurrency(), dto.getSum());
    }

    public List<Long> getUserListByTotalSumConversion(UserConversionDto dto) {
        return conversionRepository.findByTotalSum(dto.getCurrency(), dto.getSum());
    }

    public List<PopularConversionDto> getPopularConversion() {
        return conversionRepository.findPopular();
    }
}
