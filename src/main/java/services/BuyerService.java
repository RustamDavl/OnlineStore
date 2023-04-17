package services;

import dao.AdminDao;
import dao.BuyerDao;
import dao.GoodsDao;
import dto.CreateBuyerDto;
import dto.ReadBuyerDto;
import dto.ShowBuyerDto;
import entities.Admin;
import entities.Buyer;
import hibDto.ReadBuyerDtoHib;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import mapper.ReadBuyerDtoMapper;
import org.hibernate.Session;
import repository.BuyerRepository;
import roles.Role;
import validators.additonal.ValidBirthday;
import validators.BuyerDtoValidator;

import validators.errors.ValidationException;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerService extends BaseService {


    private static final BuyerService INSTANCE = new BuyerService();

    private final ReadBuyerDtoMapper mapper = new ReadBuyerDtoMapper();

    private final BuyerDao buyerDao = BuyerDao.getInstance();

    private final BuyerDtoValidator buyerDtoValidator = BuyerDtoValidator.getInstance();

    private final AdminDao adminDao = AdminDao.getInstance();


    public Optional<ReadBuyerDtoHib> login2(String email, String password) {
        Session session = sessionFactory.getCurrentSession();

        BuyerRepository buyerRepository = new BuyerRepository(session);
        session.beginTransaction();
        var buyer = buyerRepository.findByEmailAndPassword(email, password);

        session.getTransaction().commit();
        return buyer.stream()
                .map(mapper::mapFrom)
                .findFirst();
    }

    public Optional<ReadBuyerDto> login(String email, String password) {

        return buyerDao.getByEmailAndPassword(email, password)
                .map(buyer -> ReadBuyerDto.builder().
                        id(buyer.getId())
                        .name(buyer.getName())
                        .lastName(buyer.getLastName())
                        .email(buyer.getEmail())
                        .password(buyer.getPassword())
                        .phone(buyer.getPhone())
                        .address(buyer.getAddress())
                        .birthday(buyer.getBirthday())
                        .build());
    }

    public List<ShowBuyerDto> showBuyerDtoList() {
        return buyerDao.findAll()
                .stream()
                .map(buyer -> ShowBuyerDto.builder()
                        .id(buyer.getId())
                        .name(buyer.getName())
                        .lastName(buyer.getLastName())
                        .phone(buyer.getPhone())
                        .email(buyer.getEmail())
                        .build())
                .toList();
    }


    public void convertToBuyer(CreateBuyerDto object) {


        var list = buyerDtoValidator.isValid(object);

        if (!list.isEmpty()) {

            throw new ValidationException(list.getErrorList());

        }
        if (object.getRole().equals(Role.BUYER.name())) {
            Buyer buyer = Buyer.builder()
                    .name(object.getName())
                    .lastName(object.getLastName())
                    .email(object.getEmail())
                    .password(object.getPassword())
                    .phone(object.getPhone())
                    .address(object.getAddress())
                    .birthday(ValidBirthday.format(object.getBirthday()))
                    .build();
            buyerDao.save(buyer);
        } else if (object.getRole().equals(Role.ADMIN.name())) {

            Admin admin = Admin.builder()
                    .name(object.getName())
                    .lastName(object.getLastName())
                    .email(object.getEmail())
                    .password(object.getPassword())
                    .phone(object.getPhone())
                    .address(object.getAddress())
                    .birthday(ValidBirthday.format(object.getBirthday()))
                    .build();

            adminDao.save(admin);


        }


    }


    public static BuyerService getInstance() {
        return INSTANCE;
    }
}
