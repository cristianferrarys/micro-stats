package com.mercado.stats.app.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "stats")
public class Stats implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String pais;
  private Double distancia;
  private Integer invocaciones = 1;
  private static final long serialVersionUID = 3713996449078510641L;

}
