package com.api.water_sytem_management_java.controllers.dtos;





public record ClientInput(
        String name,
        String email,
        String phone,
        String address
) {
    public Client toClient() {
        return new Client(name, email, phone, address);
    }
}
