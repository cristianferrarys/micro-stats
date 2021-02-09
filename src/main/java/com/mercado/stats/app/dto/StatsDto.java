package com.mercado.stats.app.dto;

import com.mercado.stats.app.model.Stats;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StatsDto implements Serializable {
  private Double promedio;
  private List<Stats> stats;
  private static final long serialVersionUID = 988721422186783961L;

}
