package christmas.view;

import christmas.dto.PlanResultDto;

public class OutputView {

    private static final String APPLICATION_TITLE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_BENEFIT_TITLE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";


    public void printApplicationTitle() {
        println(APPLICATION_TITLE);
    }

    public void printPlanResult(PlanResultDto planResult) {
        printEventBenefitTitle(planResult.date());
    }

    private void printEventBenefitTitle(int date) {
        println(EVENT_BENEFIT_TITLE_FORMAT.formatted(date));
    }

    public void printExceptionMessage(IllegalArgumentException exception) {
        String errorMessage = ErrorMessageView.constructMessage(exception);
        println(errorMessage);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
