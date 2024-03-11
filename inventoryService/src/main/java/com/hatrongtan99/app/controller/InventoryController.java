package com.hatrongtan99.app.controller;

import com.hatrongtan99.app.dto.*;
import com.hatrongtan99.app.dto.paginationDto.MetadataDto;
import com.hatrongtan99.app.entity.StockEntity;
import com.hatrongtan99.app.entity.StockHistoryEntity;
import com.hatrongtan99.app.services.IInventoryService;
import com.hatrongtan99.saga.dto.inventoryProduct.StockAdjustQuantityDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/bff-customer/inventory", "/bff-admin/inventory"})
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService inventoryService;

    @GetMapping("/available/{productId}")
    public ResponseEntity<Boolean> checkProductIsAvailable(
            @PathVariable("productId") Long productId
    ) {
        boolean isAvailable = this.inventoryService.productIsInStock(productId);
        return ResponseEntity.ok(isAvailable);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> adjustQuantityProduct(
            @PathVariable("productId") Long productId,
            @Valid @RequestBody StockAdjustQuantityDto adjustBody
    ) {
        this.inventoryService.adjustQuantity(productId, adjustBody);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<DetailStockResponseDto> getDetailByProductId(
            @PathVariable("productId") Long productId
    ) {
        StockEntity stockEntity = this.inventoryService.getByProductId(productId);
        return ResponseEntity.ok(DetailStockResponseDto.mapToDto(stockEntity));
    }

    @GetMapping("/history-stock/{productId}")
    public ResponseEntity<HistoryStockWithPageableResponseDto> getHistoryStock(
            @PathVariable("productId") Long productId,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "limit", required = false, defaultValue = "5") Integer pageLimit
    ) {
        Page<StockHistoryEntity> page = this.inventoryService.getHistoryByProductId(productId, pageNumber, pageLimit);
        MetadataDto metadata = MetadataDto.mapToDto(page);
        List<HistoryStockResponseDto> records = page.getContent().stream().map(i -> new HistoryStockResponseDto(i.getId(), i.getAdjustedQuantity(), i.getNote(), i.getCreateAt())).toList();
        return ResponseEntity.ok(new HistoryStockWithPageableResponseDto(records, metadata));
    }
}
