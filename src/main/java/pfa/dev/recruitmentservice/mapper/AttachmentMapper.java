package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.*;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.entities.Attachment;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachmentMapper {

    // ========= ENTITY -> RESPONSE =========
    @Mapping(source = "candidate.id", target = "candidateId")
    @Mapping(source = "fileName", target = "fileName")
    @Mapping(source = "fileType", target = "fileType")
    @Mapping(source = "category", target = "category")
    AttachmentResponseDTO toDTO(Attachment attachment);

    // ========= REQUEST -> ENTITY =========
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidate", ignore = true)
    @Mapping(target = "fileData", ignore = true)  // sera rempli dans le service depuis MultipartFile
    Attachment toEntity(AttachmentRequestDTO dto);

    // ========= UPDATE =========
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AttachmentRequestDTO dto, @MappingTarget Attachment attachment);
}