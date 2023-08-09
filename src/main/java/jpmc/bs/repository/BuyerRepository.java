package jpmc.bs.repository;

import jpmc.bs.entity.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, String> {
    List<BuyerEntity> findByShowNumber(int showNumber);
}
