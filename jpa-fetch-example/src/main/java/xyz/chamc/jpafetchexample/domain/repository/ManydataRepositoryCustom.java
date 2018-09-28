package xyz.chamc.jpafetchexample.domain.repository;

import xyz.chamc.jpafetchexample.domain.model.ManydataEntity;

import java.util.List;

public interface ManydataRepositoryCustom {
    public List<ManydataEntity> findAllSimple();
    public List<ManydataEntity> findAllByFetch(Integer fetchsize);
    public List<ManydataEntity> findAllNoFetch();
}
