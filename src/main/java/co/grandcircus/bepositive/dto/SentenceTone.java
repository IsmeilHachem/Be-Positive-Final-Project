package co.grandcircus.bepositive.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentenceTone {

	@JsonProperty("sentence_id")
	private Integer id;

	private String text;

	private List<Tone> tones;

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public List<Tone> getTones() {

		return tones;
	}

	public void setTones(List<Tone> tones) {

		this.tones = tones;
	}
}
