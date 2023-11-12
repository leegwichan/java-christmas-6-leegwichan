package christmas.view;

import christmas.dto.PlanResultDto;

public class OutputView {

    private static final String APPLICATION_TITLE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public void printApplicationTitle() {
        println(APPLICATION_TITLE);
    }

    public void printPlanResult(PlanResultDto planResult) {
        throw new UnsupportedOperationException();
    }

    public void printExceptionMessage(IllegalArgumentException exception) {
        String errorMessage = ErrorMessageView.constructMessage(exception);
        println(errorMessage);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
