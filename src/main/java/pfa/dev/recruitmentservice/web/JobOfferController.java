package pfa.dev.recruitmentservice.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfa.dev.recruitmentservice.dto.JobOfferRequestDTO;
import pfa.dev.recruitmentservice.dto.JobOfferResponseDTO;
import pfa.dev.recruitmentservice.service.JobOfferService;

@RestController
@RequestMapping("/job-offers")
@RequiredArgsConstructor

public class JobOfferController {
    private final JobOfferService jobOfferService;


    @PostMapping("/create")
    public ResponseEntity<JobOfferResponseDTO> addNewJob( @RequestBody JobOfferRequestDTO dto){
        return  ResponseEntity.ok(jobOfferService.createJobOffer(dto));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JobOfferResponseDTO> getJobOfferById(@PathVariable Long id){
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }
    @PatchMapping("/update/{id}")

     public  ResponseEntity<JobOfferResponseDTO> updateJobOffer(@PathVariable Long id, @RequestBody JobOfferRequestDTO dto){
        return ResponseEntity.ok(jobOfferService.updateJobOffer(id, dto));


     }


     @DeleteMapping("/delete/{id}")


     public ResponseEntity<Void> deleteJobOffer(@PathVariable Long id){
         jobOfferService.deleteJobOffer(id);
         return ResponseEntity.ok().build();
     }


     }


