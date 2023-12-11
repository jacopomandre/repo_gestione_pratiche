package it.aruba.sp.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.lang.NonNull;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertVersionePraticaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3099402826954247538L;
	
	@NonNull
	private String codicePratica;
	private String descrizione;
	private String note;
	@NonNull
	private String nome;
	
	@NonNull
	private String cognome;
	
	@NonNull
	@Size(min = 16, max = 16)
	private String codiceFiscale;
	
	@NonNull
	private LocalDate dataDiNascita;
	
	private String fileName;
	
	private String filePath;

}
