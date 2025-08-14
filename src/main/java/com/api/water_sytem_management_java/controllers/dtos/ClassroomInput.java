package com.api.water_sytem_management_java.controllers.dtos;

import java.util.List;
import java.util.UUID;

public record ClassroomInput(
        String name,
        String schedule,
        UUID headTeacherId,
        List<UUID> assistantTeacherIds,
        List<UUID> studentIds
) {
}
