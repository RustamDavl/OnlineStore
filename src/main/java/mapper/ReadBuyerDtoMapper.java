package mapper;

import hibDto.ReadBuyerDtoHib;
import hibEntities.Buyer;

public class ReadBuyerDtoMapper implements Mapper<Buyer, ReadBuyerDtoHib> {
    @Override
    public ReadBuyerDtoHib mapFrom(Buyer buyer) {
        return ReadBuyerDtoHib.builder()
                .id(buyer.getId())
                .email(buyer.getEmail())
                .password(buyer.getPassword())
                .personalInfo(buyer.getPersonalInfo())
                .build();
    }
}
