package it.aruba.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.aruba.sp.entity.StatoPratica;

public interface StatoPraticaRepository extends JpaRepository<StatoPratica, Long> {

	public StatoPratica findByDescrizione (String descrizione);

}
