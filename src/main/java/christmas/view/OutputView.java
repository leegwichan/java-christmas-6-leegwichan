package christmas.view;

import christmas.domain.gift.Gift;
import christmas.domain.plan.Menu;
import christmas.dto.DiscountEventDto;
import christmas.dto.PlanResultDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String APPLICATION_TITLE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_TITLE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_TITLE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_TITLE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_TITLE = "<증정 메뉴>";
    private static final String BENEFIT_DETAILS_TITLE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_PRICE_TITLE = "<총혜택 금액>";
    private static final String NOT_EXIST = "없음";

    private static final String MENU_FORMAT = "%s %d개";
    private static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("###,###");
    private static final String BENEFIT_DETAIL_FORMAT = "%s: %s";
    private static final String PRICE_FORMAT = "%s원";

    public void printApplicationTitle() {
        println(APPLICATION_TITLE);
    }

    public void printPlanResult(PlanResultDto planResult) {
        printEventBenefitTitle(planResult.date());
        printOrder(planResult.order());
        printTotalPrice(planResult.totalPrice());
        printGiftMenu(planResult.gift());
        printBenefitDetails(planResult.discountDetails(), planResult.gift());
        printTotalBenefitPrice(planResult.totalBenefitPrice());
    }

    private void printEventBenefitTitle(int date) {
        println(EVENT_BENEFIT_TITLE_FORMAT.formatted(date));
    }

    private void printOrder(Map<Menu, Integer> order) {
        printTitle(ORDER_TITLE);
        printMenuToCount(order);
    }

    private void printTotalPrice(int totalPrice) {
        printTitle(TOTAL_PRICE_TITLE);
        printPrice(totalPrice);
    }

    private void printGiftMenu(Gift gift) {
        printTitle(GIFT_MENU_TITLE);
        printMenuToCount(gift.getMenuToCount());
    }

    private void printBenefitDetails(Map<DiscountEventDto, Integer> discountEventDetails, Gift gift) {
        printTitle(BENEFIT_DETAILS_TITLE);

        if (discountEventDetails.isEmpty() && gift.isEmpty()) {
            println(NOT_EXIST);
            return;
        }
        discountEventDetails.forEach(this::printDiscountEventDetails);
        printGiftEventDetails(gift);
    }

    private void printDiscountEventDetails(DiscountEventDto discountEvent, int price) {
        if (price == 0) {
            return;
        }

        String benefitDetailView = BenefitDetailView.findView(discountEvent);
        printEventDetails(benefitDetailView, price);
    }

    private void printGiftEventDetails(Gift gift) {
        if (gift.isEmpty()) {
            return;
        }

        String benefitDetailView = BenefitDetailView.findGiftEventView();
        printEventDetails(benefitDetailView, gift.calculateBenefitPrice());
    }

    private void printEventDetails(String benefitDetailView, int price) {
        String benefitPriceView = toBenefitPriceView(price);
        println(BENEFIT_DETAIL_FORMAT.formatted(benefitDetailView, benefitPriceView));
    }

    private void printTotalBenefitPrice(int totalBenefitPrice) {
        printTitle(TOTAL_BENEFIT_PRICE_TITLE);
        printBenefitPrice(totalBenefitPrice);
    }

    private void printTitle(String title) {
        println(NEW_LINE.concat(title));
    }

    private void printMenuToCount(Map<Menu, Integer> menuToCount) {
        if (menuToCount.isEmpty()) {
            println(NOT_EXIST);
            return;
        }
        menuToCount.forEach(this::printMenuToCount);
    }

    private void printMenuToCount(Menu menu, int count) {
        String menuView = MenuView.findView(menu);
        println(MENU_FORMAT.formatted(menuView, count));
    }

    private void printPrice(int price) {
        String priceView = toPriceView(price);
        println(priceView);
    }

    private void printBenefitPrice(int benefitPrice) {
        String priceView = toBenefitPriceView(benefitPrice);
        println(priceView);
    }

    private String toPriceView(int price) {
        String numberView = PRICE_FORMATTER.format(price);
        return PRICE_FORMAT.formatted(numberView);
    }

    private String toBenefitPriceView(int benefitPrice) {
        int negativePrice = Math.negateExact(benefitPrice);
        return toPriceView(negativePrice);
    }

    public void printExceptionMessage(IllegalArgumentException exception) {
        String errorMessage = ErrorMessageView.constructMessage(exception);
        println(errorMessage);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
