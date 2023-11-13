package christmas.view;

import christmas.dto.DiscountEventDto;
import java.util.Map;

class BenefitDetailView {

    private static final Map<DiscountEventDto, String> DISCOUNT_EVENT_TO_MESSAGE = Map.of(
            DiscountEventDto.CHRISTMAS_D_DAY, "크리스마스 디데이 할인", DiscountEventDto.WEEKDAY, "평일 할인",
            DiscountEventDto.WEEKEND, "주말 할인", DiscountEventDto.SPECIAL, "특별 할인"
    );
    private static final String GIFT_EVENT_MESSAGE = "증정 이벤트";

    private BenefitDetailView() {
    }

    public static String findView(DiscountEventDto discountEvent) {
        if (!DISCOUNT_EVENT_TO_MESSAGE.containsKey(discountEvent)) {
            throw new IllegalArgumentException("DiscountEventDto not enrolled at view; input : " + discountEvent);
        }
        return DISCOUNT_EVENT_TO_MESSAGE.get(discountEvent);
    }

    public static String findGiftEventView() {
        return GIFT_EVENT_MESSAGE;
    }
}
