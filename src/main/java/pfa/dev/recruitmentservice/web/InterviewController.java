package pfa.dev.recruitmentservice.web;

import jakarta.ws.rs.DELETE;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfa.dev.recruitmentservice.dto.InterviewRequestDTO;
import pfa.dev.recruitmentservice.dto.InterviewResponseDTO;
import pfa.dev.recruitmentservice.service.InterviewService;

@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor
public class InterviewController {
    private final InterviewService interviewService;


    @PostMapping("/schedule")
    public ResponseEntity<InterviewResponseDTO> scheduleInterview(InterviewRequestDTO dto){
        return ResponseEntity.ok(interviewService.scheduleInterview(dto));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<InterviewResponseDTO> updateInterview(@PathVariable  Long id, InterviewRequestDTO dto){
        return ResponseEntity.ok(interviewService.updateInterview(id, dto));
    }
    @GetMapping("/get/{id}")

    public ResponseEntity<InterviewResponseDTO> getInterviewById(@PathVariable Long id){
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }
     @GetMapping("/getall/{applicationId}")
    public ResponseEntity<Page<InterviewResponseDTO>> getInterviewsByApplication(@PathVariable Long applicationId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(interviewService.getInterviewsByApplication(applicationId, PageRequest.of(page, size))) ;   }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id){
        interviewService.deleteInterview(id);
        return ResponseEntity.ok().build();
    }
}
