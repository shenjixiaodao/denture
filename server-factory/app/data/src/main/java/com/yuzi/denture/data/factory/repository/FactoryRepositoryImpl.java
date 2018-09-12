package com.yuzi.denture.data.factory.repository;



import com.yuzi.denture.data.factory.mapper.DentureMapper;
import com.yuzi.denture.domain.factory.Denture;
import com.yuzi.denture.domain.factory.repository.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FactoryRepositoryImpl implements FactoryRepository {

    @Autowired
    private DentureMapper dentureMapper;

    @Override
    public void add(Denture denture) {
        dentureMapper.save(denture);
    }
}
