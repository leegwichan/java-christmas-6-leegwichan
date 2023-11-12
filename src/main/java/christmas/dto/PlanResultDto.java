package christmas.dto;

import christmas.domain.badge.Badge;
import christmas.domain.discount.DiscountDetails;
import christmas.domain.gift.Gift;
import christmas.domain.plan.Menu;
import christmas.domain.plan.Plan;
import java.util.Map;

public record PlanResultDto(int date, Map<Menu, Integer> order, int totalPrice,
                            Gift gift, Map<DiscountEventDto, Integer> discountDetails,
                            int totalBenefitPrice, int expectedPayment, Badge badge) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Plan plan;
        private Gift gift;
        private DiscountDetails discountDetails;
        private Badge badge;

        public Builder plan(Plan plan) {
            this.plan = plan;
            return this;
        }

        public Builder gift(Gift gift) {
            this.gift = gift;
            return this;
        }

        public Builder discountDetails(DiscountDetails discountDetails) {
            this.discountDetails = discountDetails;
            return this;
        }

        public Builder badge(Badge badge) {
            this.badge = badge;
            return this;
        }

        public PlanResultDto build() {
            int date = plan.getDate();
            Map<Menu, Integer> order = plan.getOrder();
            int totalPrice = plan.calculateTotalPrice();
            Map<DiscountEventDto, Integer> discountDetails = toDiscountDetails();
            int totalBenefitPrice = calculateTotalBenefitPrice();
            int expectedPayment = totalPrice - this.discountDetails.calculateTotalDiscountPrice();

            return new PlanResultDto(date, order, totalPrice, gift,
                    discountDetails, totalBenefitPrice, expectedPayment, badge);
        }

        private Map<DiscountEventDto, Integer> toDiscountDetails() {
            return DiscountEventDto.toDtoMap(discountDetails.getClassToDiscountPrice());
        }

        private int calculateTotalBenefitPrice() {
            return gift.calculateBenefitPrice() + discountDetails.calculateTotalDiscountPrice();
        }
    }
}
