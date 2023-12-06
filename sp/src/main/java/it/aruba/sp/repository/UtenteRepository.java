package it.aruba.sp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aruba.sp.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	
	 public Optional<Utente> findByUsername (String username);
}
