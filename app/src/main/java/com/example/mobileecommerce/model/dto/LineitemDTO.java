package com.example.mobileecommerce.model.dto;

import com.example.mobileecommerce.model.OptionModel;

public class LineitemDTO {
    private Integer lineItemId;
    private Integer quantity;
    private OptionModel option;

    public LineitemDTO(Integer quantity, OptionModel option) {
        this.quantity = quantity;
        this.option = option;
    }

    public LineitemDTO(Integer lineItemId, Integer quantity, OptionModel option) {
        this.lineItemId = lineItemId;
        this.quantity = quantity;
        this.option = option;
    }

    public Integer getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OptionModel getOption() {
        return option;
    }

    public void setOption(OptionModel option) {
        this.option = option;
    }
}
