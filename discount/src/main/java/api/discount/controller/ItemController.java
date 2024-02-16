package api.discount.controller;

import api.discount.model.ItemDto;
import api.discount.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public String save(@RequestBody ItemDto itemDto) {

        itemService.saveItem(itemDto.getName(), itemDto.getPrice(), itemDto.getStockQuantity());
        log.info("itemDto = {}", itemDto.toString());
        return "ok";
    }

}
