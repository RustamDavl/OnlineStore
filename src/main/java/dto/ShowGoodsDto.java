package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShowGoodsDto {

   private String name;
   private String allInfo;
   private String description;
   private String imagePath;

}
