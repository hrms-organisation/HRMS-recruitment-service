package pfa.dev.recruitmentservice.exception;



public class VirusDetectedException extends RuntimeException {
    public VirusDetectedException(String fileName) {
        super("Virus détecté dans le fichier : " + fileName);
    }
}