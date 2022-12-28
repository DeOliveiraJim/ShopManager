package com.manager.shopmanager.validation;

import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.manager.shopmanager.entity.ProductDetail;

public class DetailListValidator implements ConstraintValidator<ValidDetails, List<ProductDetail>> {

    static final String[] languages = Locale.getISOLanguages();

    @Override
    public boolean isValid(List<ProductDetail> value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        for (int x = 0; x < value.size() - 1; x++) {
            for (int y = x + 1; y < value.size(); y++) {
                if (value.get(x).getLang().equals(value.get(y).getLang()))
                    return false;
            }
        }
        return true;
    }

}
