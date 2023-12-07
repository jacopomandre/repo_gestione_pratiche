package it.aruba.sp.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpsertPraticaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3099402826954247538L;
	private String codicePratica;
	private String descrizioneVersione;
	private String noteVersione;

}
