package pfa.dev.recruitmentservice.web;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pfa.dev.recruitmentservice.dto.CandidateRequestDTO;
import pfa.dev.recruitmentservice.dto.CandidateResponseDTO;
import pfa.dev.recruitmentservice.service.CandidateService;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CondidateController {
    private final CandidateService candidateService;

    @PostMapping("/create")
    public ResponseEntity<CandidateResponseDTO> createCandidate( @RequestBody CandidateRequestDTO dto){
        return ResponseEntity.ok(candidateService.createCandidate(dto));
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable  Long id){
        return ResponseEntity.ok(candidateService.getCandidateById(id));

    }
    @GetMapping("/getall")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public ResponseEntity<Page<CandidateResponseDTO>> getAllCandidates(@RequestParam(defaultValue = "0")  int page , @RequestParam(defaultValue = "10") int size){
    return  ResponseEntity.ok(candidateService.getAllCandidates(PageRequest.of(page, size)));
    }
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public ResponseEntity<CandidateResponseDTO> updateCandidate(@PathVariable Long  id, @RequestBody CandidateRequestDTO dto){
       return ResponseEntity.ok(candidateService.updateCandidate(id, dto));

    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok().build();

    }

}
