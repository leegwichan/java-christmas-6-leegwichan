package christmas.view;

import christmas.domain.plan.Menu;
import christmas.dto.PlanResultDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String APPLICATION_TITLE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_TITLE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_TITLE = NEW_LINE.concat("<주문 메뉴>");
    private static final String TOTAL_PRICE_TITLE = NEW_LINE.concat("<할인 전 총주문 금액>");

    private static final String MENU_FORMAT = "%s %d개";
    private static final DecimalFormat PRICE_FORMATTER = new DecimalFormat("###,###");
    private static final String PRICE_FORMAT = "%s원";

    public void printApplicationTitle() {
        println(APPLICATION_TITLE);
    }

    public void printPlanResult(PlanResultDto planResult) {
        printEventBenefitTitle(planResult.date());
        printOrder(planResult.order());
        printTotalPrice(planResult.totalPrice());
    }

    private void printEventBenefitTitle(int date) {
        println(EVENT_BENEFIT_TITLE_FORMAT.formatted(date));
    }

    private void printOrder(Map<Menu, Integer> order) {
        println(ORDER_TITLE);
        order.forEach(this::printOrder);
    }

    private void printOrder(Menu menu, int count) {
        String menuView = MenuView.findView(menu);
        println(MENU_FORMAT.formatted(menuView, count));
    }

    private void printTotalPrice(int totalPrice) {
        println(TOTAL_PRICE_TITLE);
        printPrice(totalPrice);
    }

    private void printPrice(int price) {
        String priceView = PRICE_FORMATTER.format(price);
        println(PRICE_FORMAT.formatted(priceView));
    }

    public void printExceptionMessage(IllegalArgumentException exception) {
        String errorMessage = ErrorMessageView.constructMessage(exception);
        println(errorMessage);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
