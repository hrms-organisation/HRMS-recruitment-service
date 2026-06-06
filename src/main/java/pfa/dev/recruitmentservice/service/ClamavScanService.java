package pfa.dev.recruitmentservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.springframework.stereotype.Service;
import pfa.dev.recruitmentservice.exception.InvalidFileTypeException;
import pfa.dev.recruitmentservice.exception.VirusDetectedException;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClamavScanService {

    private final ClamavClient clamavClient;
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "application/pdf",
            "image/jpeg",
            "image/png",
            "image/gif",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );
    private final Tika tika = new Tika();


    public void scan(String fileName, byte[] fileBytes) {

        String detectedType = detectRealType(fileBytes);
        log.debug("Type réel détecté pour {} : {}", fileName, detectedType);

        if (!ALLOWED_TYPES.contains(detectedType)) {
            log.warn("Type non autorisé pour {} : {}", fileName, detectedType);
            throw new InvalidFileTypeException(detectedType);
        }

        log.debug("Scan ClamAV démarré pour : {}", fileName);
        ScanResult result = clamavClient.scan(new ByteArrayInputStream(fileBytes));

        if (result instanceof ScanResult.OK) {
            log.debug("Fichier propre : {}", fileName);

        } else if (result instanceof ScanResult.VirusFound virusFound) {
            log.warn("Virus détecté dans {} : {}", fileName, virusFound.getFoundViruses());
            throw new VirusDetectedException(fileName);

        } else {
            log.error("Résultat inattendu pour {} : {}", fileName, result);
            throw new RuntimeException("Échec du scan antivirus pour : " + fileName);
        }
    }

    private String detectRealType(byte[] fileBytes) {
        try {
            TikaConfig tika = TikaConfig.getDefaultConfig();
            MediaType mediaType = tika.getDetector().detect(
                    new ByteArrayInputStream(fileBytes),
                    new Metadata() // pas de nom de fichier fourni → analyse pure des bytes
            );
            return mediaType.toString();
        } catch (IOException e) {
            throw new RuntimeException("Impossible de détecter le type du fichier", e);
        }
    }
}