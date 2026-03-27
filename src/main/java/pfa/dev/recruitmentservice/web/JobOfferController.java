package pfa.dev.recruitmentservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;
import pfa.dev.recruitmentservice.service.JobOfferService;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor
public class JobOfferController {
    private final JobOfferService jobOfferService;

    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<JobOfferResponseDTO> addNewJob( @RequestBody JobOfferRequestDTO dto){
        return  ResponseEntity.ok(jobOfferService.createJobOffer(dto));
    }

    @GetMapping("/getall")
    public ResponseEntity<Page<JobOfferResponseDTO>> getAllJobOffers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(jobOfferService.getAllJobOffers(PageRequest.of(page, size)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobOfferResponseDTO> getJobOfferById(@PathVariable Long id){
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }
    @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
    @PatchMapping("/update/{id}")

     public  ResponseEntity<JobOfferResponseDTO> updateJobOffer(@PathVariable Long id, @RequestBody JobOfferRequestDTO dto){
        return ResponseEntity.ok(jobOfferService.updateJobOffer(id, dto));


     }

     @PreAuthorize("hasAnyRole('HR', 'ADMIN')")
     @DeleteMapping("/delete/{id}")


     public ResponseEntity<Void> deleteJobOffer(@PathVariable Long id){
         jobOfferService.deleteJobOffer(id);
         return ResponseEntity.ok().build();
     }


     }


