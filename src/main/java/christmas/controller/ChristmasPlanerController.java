package christmas.controller;

import christmas.domain.badge.Badge;
import christmas.domain.discount.DiscountDetails;
import christmas.domain.discount.TotalDiscountEvent;
import christmas.domain.gift.Gift;
import christmas.domain.plan.EventDate;
import christmas.domain.plan.Menu;
import christmas.domain.plan.Order;
import christmas.domain.plan.Plan;
import christmas.dto.PlanResultDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.function.Supplier;

public class ChristmasPlanerController {

    private final TotalDiscountEvent discountEvent = TotalDiscountEvent.createDecemberEvent();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        startApplication();
        Plan plan = inputPlan();
        PlanResultDto planResult = applyEvents(plan);
        printEventResult(planResult);
    }

    private void startApplication() {
        outputView.printApplicationTitle();
    }

    private Plan inputPlan() {
        EventDate date = readRepeatedlyUntilNoException(this::readDate);
        Order order = readRepeatedlyUntilNoException(this::readOrder);
        return Plan.of(date, order);
    }

    private <T> T readRepeatedlyUntilNoException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception);
            return readRepeatedlyUntilNoException(supplier);
        }
    }

    private EventDate readDate() {
        int date = inputView.readDate();
        return EventDate.from(date);
    }

    private Order readOrder() {
        Map<Menu, Integer> order = inputView.readOrder();
        return Order.from(order);
    }

    private PlanResultDto applyEvents(Plan plan) {
        DiscountDetails discountDetails = discountEvent.makeDiscountDetails(plan);
        Gift gift = Gift.from(plan.calculateTotalPrice());
        int totalBenefitPrice = calculateTotalBenefitPrice(discountDetails, gift);
        Badge badge = Badge.from(totalBenefitPrice);

        return PlanResultDto.builder()
                .plan(plan).discountDetails(discountDetails)
                .gift(gift).badge(badge).build();
    }

    private int calculateTotalBenefitPrice(DiscountDetails discountDetails, Gift gift) {
        int totalDiscountPrice = discountDetails.calculateTotalDiscountPrice();
        int giftBenefitPrice = gift.calculateBenefitPrice();
        return totalDiscountPrice + giftBenefitPrice;
    }

    private void printEventResult(PlanResultDto planResult) {
        outputView.printPlanResult(planResult);
    }
}
