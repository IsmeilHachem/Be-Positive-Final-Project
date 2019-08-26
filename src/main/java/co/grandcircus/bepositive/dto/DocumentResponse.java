package co.grandcircus.bepositive.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentResponse {

	@JsonProperty("document_tone")
	private DocumentTone docTone;

	@JsonProperty("sentences_tone")
	private List<SentenceTone> sentenceTones;

	public DocumentTone getDocTone() {

		return docTone;
	}

	public void setDocTone(DocumentTone docTone) {

		this.docTone = docTone;
	}

	public List<SentenceTone> getSentenceTones() {

		return sentenceTones;
	}

	public void setSentenceTones(List<SentenceTone> sentenceTones) {

		this.sentenceTones = sentenceTones;
	}
}
