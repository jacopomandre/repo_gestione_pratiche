package it.aruba.sp.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VersionePraticaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1653009875356780656L;
	private String descrizione;
	private String stato;
	private String risultato;
	private Integer numeroVersione;
	private String note;
    private LocalDateTime dataCreazione;

}
