package com.mercado.stats.app.serviceImpl;

import com.mercado.stats.app.model.Stats;
import com.mercado.stats.app.repository.StatsRepository;
import com.mercado.stats.app.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

  @Autowired
  private StatsRepository repo;

  @Override
  public void guardarStats(final Stats stats) {
    Stats st = repo.findByPais(stats.getPais());
    if (st != null) {
      st.setInvocaciones(st.getInvocaciones() + 1);
      repo.save(st);
    } else {
      repo.save(stats);
    }
  }

  @Override
  public List<Stats> buscarMaxMin() {
    List<Stats> maxMin = new ArrayList<>();
    Stats firt = repo.findFirstByOrderByDistanciaDesc();
    Stats top = repo.findTopByOrderByDistanciaAsc();
    maxMin.add(firt);
    maxMin.add(top);
    return maxMin;
  }

}
