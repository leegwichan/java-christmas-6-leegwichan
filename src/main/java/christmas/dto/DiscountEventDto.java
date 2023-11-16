package christmas.dto;

import static java.util.stream.Collectors.toMap;

import christmas.domain.discount.policy.ChristmasDDayDiscount;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscount;
import christmas.domain.discount.policy.WeekdayDiscount;
import christmas.domain.discount.policy.WeekendDiscount;
import java.util.Map;

public enum DiscountEventDto {
    CHRISTMAS_D_DAY, WEEKDAY, WEEKEND, SPECIAL;

    private static final Map<Class<? extends DiscountPolicy>, DiscountEventDto> DISCOUNT_POLICY_TO_DTO
            = Map.of(ChristmasDDayDiscount.class, CHRISTMAS_D_DAY,
            WeekdayDiscount.class, WEEKDAY,
            WeekendDiscount.class, WEEKEND,
            SpecialDiscount.class, SPECIAL);

    public static Map<DiscountEventDto, Integer> toDtoMap(Map<Class<? extends DiscountPolicy>, Integer> details) {
        return details.keySet().stream()
                .collect(toMap(DiscountEventDto::toDto, details::get));
    }

    private static DiscountEventDto toDto(Class<? extends DiscountPolicy> discountPolicy) {
        DiscountEventDto dto = DISCOUNT_POLICY_TO_DTO.get(discountPolicy);
        validate(dto);
        return dto;
    }

    private static void validate(DiscountEventDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("this DiscountPolicy can't change to dto");
        }
    }
}
