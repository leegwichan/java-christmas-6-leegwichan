package christmas.view;

import christmas.domain.badge.Badge;
import java.util.Map;

class BadgeView {

    private static final Map<Badge, String> BADGE_TO_MESSAGE = Map.of(
            Badge.SANTA, "산타", Badge.TREE, "트리",
            Badge.STAR, "별", Badge.NOTHING, "없음");

    private BadgeView() {
    }

    public static String findView(Badge badge) {
        if (!BADGE_TO_MESSAGE.containsKey(badge)) {
            throw new IllegalArgumentException("Badge not enrolled at view; input : " + badge);
        }
        return BADGE_TO_MESSAGE.get(badge);
    }
}
