package pfa.dev.recruitmentservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfa.dev.recruitmentservice.dto.ApplicationRequestDTO;
import pfa.dev.recruitmentservice.dto.ApplicationResponseDTO;
import pfa.dev.recruitmentservice.service.ApplicationService;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor

public class ApplicationController {

    private final ApplicationService applicationService;


    @PostMapping("/create")

    public ResponseEntity<ApplicationResponseDTO> createApplication(ApplicationRequestDTO dto){
        return ResponseEntity.ok(applicationService.createApplication(dto));

    }
     @GetMapping("/get/{id}")

    public ResponseEntity<ApplicationResponseDTO> getApplicationById( @PathVariable Long id){
        return ResponseEntity.ok(applicationService.getApplicationById(id));

    }
    @DeleteMapping("/delete/{id}")

    public ResponseEntity<Void> deleteApplication(@PathVariable Long id){
        applicationService.deleteApplication(id);
        return ResponseEntity.ok().build();

    }
    @PutMapping("/update/{id}")


     public  ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(@PathVariable Long id, @RequestBody  String status){
       return  ResponseEntity.ok(applicationService.updateApplicationStatus(id, status)) ;
     }


     @GetMapping("/getall/{candidateId}")
     public  ResponseEntity<Page<ApplicationResponseDTO>> getAllApplications( @PathVariable  Long candidateId ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(applicationService.getApplicationsByCandidate(candidateId, PageRequest.of(page, size)));
     }

}
