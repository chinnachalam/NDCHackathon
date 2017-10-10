package tcs.ndc.hackathon.ndcrest.model.offer.request;

public enum Cabin {
    ECONOMY("M"),
    BUSINESS("C");

    private String value;

    Cabin(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
