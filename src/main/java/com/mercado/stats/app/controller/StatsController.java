package com.mercado.stats.app.controller;

import com.mercado.stats.app.dto.ResquestEnvioDto;
import com.mercado.stats.app.dto.StatsDto;
import com.mercado.stats.app.model.Stats;
import com.mercado.stats.app.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {

  @Autowired
  private StatsService statsService;

  @PostMapping("/envio")
  public ResponseEntity<?> getStats(@RequestBody final ResquestEnvioDto req) {
    try {
      Stats st = new Stats();
      st.setPais(req.getPais());
      st.setDistancia(req.getDistancia());
      statsService.guardarStats(st);
      return new ResponseEntity<>("Ok", HttpStatus.OK);
    } catch (Exception e) {
      e.getMessage();
      return new ResponseEntity<>("Error ", HttpStatus.BAD_GATEWAY);
    }
  }

  @GetMapping("/stats")
  public ResponseEntity<?> getStatsMax() {
    List<Stats> st = statsService.buscarMaxMin();
    Integer promedio = 0;
    Double suma = 0.0;
    for (Stats stats : st) {
      promedio = promedio + stats.getInvocaciones();
      suma = suma + stats.getDistancia() * stats.getInvocaciones();
    }
    final Double promedioFinal = suma / promedio;
    StatsDto statsDto = new StatsDto();
    statsDto.setStats(st);
    statsDto.setPromedio(promedioFinal);
    return new ResponseEntity<>(statsDto, HttpStatus.OK);
  }
}
