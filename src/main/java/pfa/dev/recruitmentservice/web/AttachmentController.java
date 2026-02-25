package pfa.dev.recruitmentservice.web;

import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.service.AttachmentService;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor

public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping("/create")
    public ResponseEntity<AttachmentResponseDTO> createAttachment(AttachmentRequestDTO dto){
        return ResponseEntity.ok(attachmentService.createAttachment(dto));

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<AttachmentResponseDTO> getAttachmentById(@PathVariable Long id){
        return ResponseEntity.ok(attachmentService.getAttachmentById(id));
    }
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id){
        attachmentService.deleteAttachment(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public  ResponseEntity<AttachmentResponseDTO> updateAttachment(@PathVariable Long id,@RequestBody  AttachmentRequestDTO dto){
        return ResponseEntity.ok(attachmentService.updateAttachment(id, dto));
    }
    @GetMapping("/getall/{candidateId}")
    public ResponseEntity<Page<AttachmentResponseDTO>> getAllAttachmentsByCandidate(@PathVariable Long candidateId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(attachmentService.getAllAttachmentsByCandidate(candidateId , PageRequest.of(page, size))) ;  }

}
