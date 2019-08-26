package co.grandcircus.bepositive;

public class ToneSummary {

	private String tone;

	private Integer count = 0;

	private Double sumOfScore = 0.0;

	public ToneSummary(String tone) {

		this.tone = tone;
	}

	public void incrementCount() {

		count++;
	}

	public void addToScore(Double score) {

		sumOfScore = sumOfScore + score;
	}

	public String getTone() {

		return tone;
	}

	public Double getAverage() {

		return sumOfScore / count;
	}

	public Integer getCount() {

		return count;
	}
}
