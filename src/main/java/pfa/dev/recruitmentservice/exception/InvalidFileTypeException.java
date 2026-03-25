package pfa.dev.recruitmentservice.exception;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String detectedType) {
        super("Type de fichier non autorisé : " + detectedType);
    }
}