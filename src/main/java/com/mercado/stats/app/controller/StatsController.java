package com.mercado.stats.app.controller;

import com.mercado.stats.app.dto.StatsDto;
import com.mercado.stats.app.model.Stats;
import com.mercado.stats.app.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {

  @Autowired
  private StatsService statsService;

  @GetMapping("/set/stats")
  public ResponseEntity<?> getStats(@RequestParam(name = "pais", defaultValue = "NA") String pais,
      @RequestParam(name = "distancia") Double distancia) {
    if (pais.equals("NA")) {
      return new ResponseEntity<>("Ingrese pais", HttpStatus.BAD_REQUEST);
    }
    Stats st = new Stats();
    st.setPais(pais);
    st.setDistancia(distancia);
    statsService.guardarStats(st);
    return new ResponseEntity<>("Ok", HttpStatus.OK);
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
    Double promedioFinal = suma / promedio;

    StatsDto statsDto = new StatsDto();
    statsDto.setStats(st);
    statsDto.setPromedio(promedioFinal);

    System.out.println("max " + st);
    return new ResponseEntity<>(statsDto, HttpStatus.OK);
  }
}
