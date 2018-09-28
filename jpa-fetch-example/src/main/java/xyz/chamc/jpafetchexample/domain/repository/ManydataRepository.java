package xyz.chamc.jpafetchexample.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.chamc.jpafetchexample.domain.model.ManydataEntity;

@Repository
public interface ManydataRepository extends JpaRepository<ManydataEntity, Integer>, ManydataRepositoryCustom {
}
