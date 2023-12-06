package it.aruba.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.aruba.sp.entity.VersionePratica;

@Repository
public interface VersionePraticaRepository extends JpaRepository<VersionePratica, Long> {


}