package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.*;
import pfa.dev.recruitmentservice.dto.AttachmentRequestDTO;
import pfa.dev.recruitmentservice.dto.AttachmentResponseDTO;
import pfa.dev.recruitmentservice.entities.Attachment;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {


    // ================= RESPONSE =================

    @Mapping(source = "candidate.id",
            target = "candidateId")
    AttachmentResponseDTO toDTO(
            Attachment attachment
    );


    // ================= CREATE =================

    @Mapping(target = "id", ignore = true)

    // candidate sera set dans Service
    @Mapping(target = "candidate", ignore = true)

    Attachment toEntity(
            AttachmentRequestDTO dto
    );


    // ================= UPDATE =================

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )

    @Mapping(target = "id", ignore = true)

    // IMPORTANT → ne change pas owner attachment
    @Mapping(target = "candidate", ignore = true)

    void updateEntity(

            AttachmentRequestDTO dto,

            @MappingTarget
            Attachment attachment
    );

}