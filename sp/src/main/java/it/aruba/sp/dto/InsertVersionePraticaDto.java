package it.aruba.sp.dto;

import java.io.Serializable;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertVersionePraticaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4497521379164528398L;

	private String descrizione;
	@Nonnull
	private Long idRisultato;
	@Nonnull
	private Long idStato;
	@Nonnull
	private Long idPratica;
	private String note;
}
