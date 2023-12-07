package it.aruba.sp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aruba.sp.entity.RisultatoPratica;

@Repository
public interface RisultatoPraticaRepository extends JpaRepository<RisultatoPratica, Long> {

	public Optional <RisultatoPratica> findByDescrizione (String descrizione);

}
