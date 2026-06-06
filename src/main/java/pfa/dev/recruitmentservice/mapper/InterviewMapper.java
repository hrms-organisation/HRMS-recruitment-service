package pfa.dev.recruitmentservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pfa.dev.recruitmentservice.dto.InterviewRequestDTO;
import pfa.dev.recruitmentservice.dto.InterviewResponseDTO;
import pfa.dev.recruitmentservice.entities.Application;
import pfa.dev.recruitmentservice.entities.Interview;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    // ================= RESPONSE =================

    @Mapping(source = "application.id",
            target = "applicationId")
    InterviewResponseDTO toDTO(
            Interview interview
    );


    // ================= CREATE =================

    @Mapping(target = "id", ignore = true)

    // MapStruct va utiliser mapApplicationId()
    @Mapping(target = "application",
            source = "applicationId")

    @Mapping(target = "status",
            constant = "PLANNED")

    @Mapping(target = "feedback",
            ignore = true)

    @Mapping(target = "rating",
            ignore = true)

    Interview toEntity(
            InterviewRequestDTO dto
    );


    // ================= UPDATE =================

    @Mapping(target = "id", ignore = true)

    // ne change pas application lors update
    @Mapping(target = "application",
            ignore = true)

    // status reste inchangé (important)
    @Mapping(target = "status",
            ignore = true)
    @Mapping( target =  "feedback" , ignore = true )

    void updateEntity(

            InterviewRequestDTO dto,

            @MappingTarget
            Interview interview
    );


    // ================= HELPER =================

    default Application mapApplicationId(
            Long applicationId) {

        if (applicationId == null)
            return null;

        Application application =
                new Application();

        application.setId(applicationId);

        return application;
    }

}