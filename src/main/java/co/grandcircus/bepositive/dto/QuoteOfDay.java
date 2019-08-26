package co.grandcircus.bepositive.dto;

public class QuoteOfDay {

	private String quoteText;
	private String quoteAuthor;

	public String getQuoteText() {
		return quoteText;
	}

	public void setQuoteText(String quoteText) {
		this.quoteText = quoteText;
	}

	public String getQuoteAuthor() {
		return quoteAuthor;
	}

	public void setQuoteAuthor(String quoteAuthor) {
		this.quoteAuthor = quoteAuthor;
	}

	@Override
	public String toString() {
		return "QuoteOfDay [quoteText=" + quoteText + ", quoteAuthor=" + quoteAuthor + "]";
	}

}
