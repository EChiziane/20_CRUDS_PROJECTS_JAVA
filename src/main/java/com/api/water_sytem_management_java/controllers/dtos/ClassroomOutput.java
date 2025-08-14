package com.api.water_sytem_management_java.controllers.dtos;

import java.util.List;
import java.util.UUID;

public record ClassroomOutput(
        UUID id,
        String name,
        String schedule,
        String headTeacherName,
        UUID headTeacherId,
        List<String> assistantTeacherNames,
        List<UUID> assistantTeacherIds,
        List<String> studentNames,
        List<UUID> studentIds
) {
}
