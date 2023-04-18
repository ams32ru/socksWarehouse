package com.example.sockswarehouse.repository;

import com.example.sockswarehouse.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SocksRepository extends JpaRepository<SocksEntity,Long> {

    SocksEntity findSocksByColorAndCottonPart(String color, Integer cottonPart);

    List<SocksEntity> findSocksByColorAndCottonPartEquals(String color, Integer cottonPart);

    List<SocksEntity> findSocksByColorAndCottonPartLessThan(String color, Integer cottonPart);

    List<SocksEntity> findSocksByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
}
