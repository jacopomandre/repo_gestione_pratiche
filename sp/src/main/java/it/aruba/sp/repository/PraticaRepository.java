package it.aruba.sp.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aruba.sp.entity.Pratica;

@Repository
public interface PraticaRepository extends JpaRepository<Pratica, Long> {

	public Optional <Pratica> findByCodicePratica (String codicePratica);
}
