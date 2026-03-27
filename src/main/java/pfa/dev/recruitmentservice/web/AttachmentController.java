package pfa.dev.recruitmentservice.web;

import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.service.AttachmentService;

import java.io.IOException;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

@PostMapping("/upload")
    public ResponseEntity<AttachmentResponseDTO> uploadAttachment(
            @RequestParam("candidateId") Long candidateId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "category", defaultValue = "CV") String category
    ) throws IOException {

        AttachmentRequestDTO dto = new AttachmentRequestDTO();
        dto.setCandidateId(candidateId);
        dto.setFile(file);
        dto.setCategory(category);

        AttachmentResponseDTO response = attachmentService.createAttachment(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long id) throws IOException {
        Resource resource = attachmentService.downloadFile(id);

        // Récupérer l’attachment pour le nom du fichier
        AttachmentResponseDTO attachment = attachmentService.getAttachmentById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(resource);
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<AttachmentResponseDTO> getAttachment(@PathVariable Long id) {
        return ResponseEntity.ok(attachmentService.getAttachmentById(id));
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<Page<AttachmentResponseDTO>> getAttachmentsByCandidate(
            @PathVariable Long candidateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<AttachmentResponseDTO> attachments = attachmentService.getAllAttachmentsByCandidate(candidateId, PageRequest.of(page, size));
        return ResponseEntity.ok(attachments);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public ResponseEntity<AttachmentResponseDTO> updateAttachment(
            @PathVariable Long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "category", required = false) String category
    ) throws IOException {
        AttachmentRequestDTO dto = new AttachmentRequestDTO();
        dto.setFile(file);
        dto.setCategory(category);

        AttachmentResponseDTO updated = attachmentService.updateAttachment(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

}
