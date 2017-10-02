package tcs.ndc.hackathon.ndccore.builder;

/**
 *
 * Interface for all builders
 */
public interface Buildable<T> {
    
    String VERSION = "IATA2016.2";
    
    T build();
}
