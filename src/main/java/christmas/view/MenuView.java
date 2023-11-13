package christmas.view;

import static java.util.Map.entry;

import christmas.domain.plan.Menu;
import christmas.exception.OrderInputException;
import java.util.Map;

public class MenuView {

    private static final Map<String, Menu> MESSAGE_TO_MENU = Map.ofEntries(
            entry("양송이수프", Menu.MUSHROOM_SOUP), entry("타파스", Menu.TAPAS), entry("시저샐러드", Menu.CAESAR_SALAD),

            entry("티본스테이크", Menu.T_BONE_STEAK), entry("바비큐립", Menu.BARBECUE_RIBS),
            entry("해산물파스타", Menu.SEAFOOD_PASTA), entry("크리스마스파스타", Menu.CHRISTMAS_PASTA),

            entry("초코케이크", Menu.CHOCOLATE_CAKE), entry("아이스크림", Menu.ICE_CREAM),

            entry("제로콜라", Menu.ZERO_COKE), entry("레드와인", Menu.RED_WINE), entry("샴페인", Menu.CHAMPAGNE)
    );

    private static final Map<Menu, String> MENU_TO_MESSAGE = Map.ofEntries(
            entry(Menu.MUSHROOM_SOUP, "양송이수프"), entry(Menu.TAPAS, "타파스"), entry(Menu.CAESAR_SALAD, "시저샐러드"),

            entry(Menu.T_BONE_STEAK, "티본스테이크"), entry(Menu.BARBECUE_RIBS, "바비큐립"),
            entry(Menu.SEAFOOD_PASTA, "해산물파스타"), entry(Menu.CHRISTMAS_PASTA, "크리스마스파스타"),

            entry(Menu.CHOCOLATE_CAKE, "초코케이크"), entry(Menu.ICE_CREAM, "아이스크림"),

            entry(Menu.ZERO_COKE, "제로콜라"), entry(Menu.RED_WINE, "레드와인"), entry(Menu.CHAMPAGNE, "샴페인")
    );

    private MenuView() {
    }

    public static Menu findMenu(String message) {
        if (!MESSAGE_TO_MENU.containsKey(message)) {
            throw new OrderInputException("menu not enrolled at view; input : " + message);
        }
        return MESSAGE_TO_MENU.get(message);
    }

    public static String findView(Menu menu) {
        if (!MENU_TO_MESSAGE.containsKey(menu)) {
            throw new OrderInputException("menu not enrolled at view; input : " + menu);
        }
        return MENU_TO_MESSAGE.get(menu);
    }
}
