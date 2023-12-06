package it.aruba.sp.entity;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pratica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name= "allegato", nullable=true)
	private Allegato allegato;
	
	@NonNull
	@Column(unique = true)
	private String codicePratica;
	
	@OneToMany(mappedBy="pratica")
	private List<VersionePratica> versioni;
	
	@CreationTimestamp
    private Instant dataCreazione;
	
	@UpdateTimestamp
    private Instant ultimoAggiornamento;
	
}
