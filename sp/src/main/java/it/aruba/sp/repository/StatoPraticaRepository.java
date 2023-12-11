package it.aruba.sp.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aruba.sp.entity.StatoPratica;

@Repository
public interface StatoPraticaRepository extends JpaRepository<StatoPratica, Long> {

	public StatoPratica findByDescrizione (String descrizione);
	public Optional<StatoPratica> findByStep (Integer step);

}
