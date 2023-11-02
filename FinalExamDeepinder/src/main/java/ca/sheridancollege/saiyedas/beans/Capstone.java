package ca.sheridancollege.saiyedas.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Capstone {

	private Long id;
	@NonNull
	private String name;
	private int vote;
	private String information;


	public Capstone(String name, int vote, String information) {
		this.name = name;
		this.vote = vote;
		this.information = information;
	}

}
