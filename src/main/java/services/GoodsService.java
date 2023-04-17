package services;

import dao.GoodsDao;
import dto.*;
import entities.Goods;
import hibDto.ShowGoodsDtoHib;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.ShowGoodsDtoHibMapper;
import org.hibernate.Session;
import predicates.QPredicate;
import repository.BuyerRepository;
import repository.GoodsRepository;
import validators.SaveOrderDtoValidator;
import validators.DeleteGoodsValidator;
import validators.SaveGoodsValidator;
import validators.UpdateAmountGoodsValidator;

import validators.errors.ValidationException;

import java.math.BigDecimal;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoodsService extends BaseService {

    private final GoodsDao goodsDao = GoodsDao.getInstance();
    private static final GoodsService INSTANCE = new GoodsService();

    private final ShowGoodsDtoHibMapper mapper = new ShowGoodsDtoHibMapper();
    private final ImageService imageService = ImageService.getInstance();



    private final SaveGoodsValidator saveGoodsValidator = SaveGoodsValidator.getInstance();

    private final DeleteGoodsValidator deleteGoodsValidator = DeleteGoodsValidator.getInstance();

    private final UpdateAmountGoodsValidator updateAmountGoodsValidator = UpdateAmountGoodsValidator.getInstance();

    private final SaveOrderDtoValidator buyGoodsDtoValidator = SaveOrderDtoValidator.getInstance();


    @SneakyThrows
    public void convertToGoodsEntity(SaveGoodsDto goodsDto) {

        var validationResultErrors = saveGoodsValidator.isValid(goodsDto);

        if (!validationResultErrors.isEmpty()) {
            throw new ValidationException(validationResultErrors.getErrorList());
        }

        String IMAGE_FOLDER = "goods/";
        var goods = Goods.builder()
                .productName(goodsDto.getProductName())
                .description(goodsDto.getDescription())
                .price(BigDecimal.valueOf(Long.parseLong(goodsDto.getPrice())))
                .remainingQuantity(Integer.parseInt(goodsDto.getQuantity()))
                .pathToImage(IMAGE_FOLDER + goodsDto.getImageName().getSubmittedFileName())
                .build();


        imageService.upload(goods.getPathToImage(), goodsDto.getImageName().getInputStream());

        goodsDao.save(goods);

    }

    public String getDescriptionByNameTwo(String name) {
        var session = sessionFactory.getCurrentSession();
        var repository = new GoodsRepository(session);
        session.beginTransaction();
        var good = repository.getDescriptionByProdName(name);

        session.getTransaction().commit();

        return good.isPresent() ? good.get().getProductInfo().getDescription() : "there is no such good";
    }

    public String getDescriptionByName(String name) {
        return goodsDao.getDescriptionByName(name);
    }

    public Integer getAmountOfRows() {
        return goodsDao.getAmountOfRows();
    }

    public void deleteGoods(DeleteGoodsDto object) {
        var validationResultErrors = deleteGoodsValidator.isValid(object);

        if (!validationResultErrors.isEmpty()) {
            throw new ValidationException(validationResultErrors.getErrorList());
        }
        int id = goodsDao.deleteByName(object.getProductName());


    }

    public List<ShowGoodsDto> getDescription() {
        return goodsDao.findAll().stream()
                .map(goods -> ShowGoodsDto.builder()
                        .description(goods.getDescription())
                        .imagePath(goods.getPathToImage())
                        .build())
                .collect(Collectors.toList());


    }

    public List<ShowGoodsDto> getGoods() {
        return goodsDao.findAll().stream()
                .map(goods -> ShowGoodsDto.builder()
                        .name(goods.getProductName())
                        .build())
                .collect(Collectors.toList());


    }

    public List<ShowGoodsDtoHib> getGoodsWithFilter(int limit) {

        Session session = sessionFactory.getCurrentSession();

        GoodsRepository goodsRepository = new GoodsRepository(session);

        session.beginTransaction();

        var goods = goodsRepository.findWithLimitCondition(limit);
        session.getTransaction().commit();

        return goods.stream()
                .map(mapper::mapFrom)
                .toList();
    }

    public List<ShowGoodsDto> getGoods(int limit) {
        return goodsDao.findAll(new GoodsFilter(limit)).stream()
                .map(goods -> ShowGoodsDto.builder()
                        .name(goods.getProductName())
                        .allInfo("""
                                 %s;
                                 %s P;
                                 quantity: %s
                                """.formatted(goods.getProductName(),
                                String.valueOf(goods.getPrice()),
                                String.valueOf(goods.getRemainingQuantity())))
                        .imagePath(goods.getPathToImage())
                        .build())
                .collect(Collectors.toList());


    }

    //---------------------------------------------------------------------------------
//    public void buyGoods(BuyGoodsDto buyGoodsDto) {
//
//        var list = buyGoodsDtoValidator.isValid(buyGoodsDto);
//
//        if (!list.isEmpty()) {
//            throw new ValidationException(list.getErrorList());
//        }
//
//
//
//        goodsDao.decreaseAmount(Integer.parseInt(buyGoodsDto.getAmount()), buyGoodsDto.getProdName());
//
//
//    }
    //-----------------------------------------------------------------------------

    public void updateAmount(UpdateGoodsDto updateGoodsDto) {


        var list = updateAmountGoodsValidator.isValid(updateGoodsDto);

        if (!list.isEmpty()) {
            throw new ValidationException(list.getErrorList());

        }

        goodsDao.updateAmount(Integer.parseInt(updateGoodsDto.getAmount()), updateGoodsDto.getProdName());
    }


    public static GoodsService getInstance() {
        return INSTANCE;
    }
}
