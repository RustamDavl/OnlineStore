package services;

import dao.AdminDao;
import dto.ReadAdminDto;
import dto.ReadBuyerDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminService {

    private static final AdminService INSTANCE = new AdminService();

    public static AdminService getInstance() {
        return INSTANCE;
    }

    private final AdminDao adminDao = AdminDao.getInstance();


    public Optional<ReadAdminDto> login(String email, String password) {
        return adminDao.findByEmailAndPassword(email, password)
                .map(admin -> ReadAdminDto.builder()
                        .id(admin.getId())
                        .email(admin.getName())
                        .lastName(admin.getLastName())
                        .email(admin.getEmail())
                        .password(admin.getPassword())
                        .phone(admin.getPhone())
                        .address(admin.getAddress())
                        .birthday(admin.getBirthday())
                        .build());
    }


}
