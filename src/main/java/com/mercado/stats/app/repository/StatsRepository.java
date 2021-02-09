package com.mercado.stats.app.repository;

import com.mercado.stats.app.model.Stats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {
  @Query("SELECT s FROM Stats s WHERE s.invocaciones = (SELECT MAX(s.invocaciones) FROM s) or s.invocaciones = (SELECT MIN(s.invocaciones) FROM s)")
  List<Stats> findMaxMin();
  
  Stats findFirstByOrderByDistanciaDesc();
  Stats findTopByOrderByDistanciaAsc();
  
  Stats findByPais(String pais);
  
  
  
}
