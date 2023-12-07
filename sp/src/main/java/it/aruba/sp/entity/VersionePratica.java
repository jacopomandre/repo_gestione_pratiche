package it.aruba.sp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VersionePratica implements Cloneable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String descrizione;
	
	@ManyToOne
    @JoinColumn(name= "stato", nullable=false)
	private StatoPratica stato;
	
	@ManyToOne
    @JoinColumn(name= "risultato", nullable=true)
	private RisultatoPratica risultato;
	
	@ManyToOne
    @JoinColumn(name= "pratica", nullable=false)
	private Pratica pratica;
	
	@NonNull
	private Integer numeroVersione;
	
	private String note;
	
	@CreationTimestamp
    private LocalDateTime dataCreazione;
	
	@NonNull
	private String nome;
	
	@NonNull
	private String cognome;
	
	@NonNull
	@Size(min = 16, max = 16)
	private String codiceFiscale;
	
	@NonNull
	private LocalDate dataDiNascita;
	
	@Override
    public VersionePratica clone() throws CloneNotSupportedException {
        return (VersionePratica) super.clone();
    }

}
