package co.grandcircus.bepositive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tone {

	private Double score;

	@JsonProperty("tone_id")
	private String toneId;

	@JsonProperty("tone_name")
	private String toneName;

	public double getScore() {

		return score;
	}

	public void setScore(Double score) {

		this.score = score;
	}

	public String getToneId() {

		return toneId;
	}

	public void setToneId(String toneId) {

		this.toneId = toneId;
	}

	public String getToneName() {

		return toneName;
	}

	public void setToneName(String toneName) {

		this.toneName = toneName;
	}

	@Override
	public String toString() {

		return "{Tone Id: " + getToneId() + ", Name: " + getToneName() + ", Score:" + getScore() + "}";
	}
}
