package Project.repositories;


import Project.dto.PopularConversionDto;
import Project.model.Conversion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversionRepository extends CrudRepository<Conversion, Long> {

    @Query(value = "select c.id_user from conversion c where c.currency = ?1 and c.sum > ?2", nativeQuery = true)
    List<Long> findUserByConversionSum(String currency, Double sum);


    @Query(value = "select c.id_User from conversion c where c.currency = ?1 group by c.id_user having sum(c.sum) > ?2 ", nativeQuery = true)
    List<Long> findByTotalSum(String currency, Double sum);

    @Query(value = "select new Project.dto.PopularConversionDto(c.currency, c.targetCurrency)  from Conversion c group by c.currency order by count(c.id)")
    List<PopularConversionDto> findPopular();
}
