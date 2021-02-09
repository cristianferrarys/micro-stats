package com.mercado.stats.app.service;

import com.mercado.stats.app.model.Stats;

import java.util.List;

public interface StatsService {

  void guardarStats(Stats stats);
  List<Stats> buscarMaxMin();
  
}
