package it.aruba.sp.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PraticaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779094235196084724L;
	
	private String codicePratica;
	private List<VersionePraticaDto> versioni;
    private LocalDateTime dataCreazione;
    private LocalDateTime ultimoAggiornamento;
	private String fileName;
	private String filePath;

}
