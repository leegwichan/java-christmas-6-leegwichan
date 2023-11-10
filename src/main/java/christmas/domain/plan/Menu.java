package christmas.domain.plan;

public enum Menu {

    MUSHROOM_SOUP(6_000, Category.APPETIZER),
    TAPAS(5_500, Category.APPETIZER),
    CAESAR_SALAD(8_000, Category.APPETIZER),

    T_BONE_STEAK(55_000, Category.MAIN),
    BARBECUE_RIBS(54_000, Category.MAIN),
    SEAFOOD_PASTA(35_000, Category.MAIN),
    CHRISTMAS_PASTA(25_000, Category.MAIN),

    CHOCOLATE_CAKE(15_000, Category.DESSERT),
    ICE_CREAM(5_000, Category.DESSERT),

    ZERO_COKE(3_000, Category.DRINK),
    RED_WINE(60_000, Category.DRINK),
    CHAMPAGNE(25_000, Category.DRINK);

    private final int price;
    private final Category category;

    Menu(int price, Category category) {
        this.price = price;
        this.category = category;
    }

    public boolean isMain() {
        return this.category == Category.MAIN;
    }

    public boolean isDessert() {
        return this.category == Category.DESSERT;
    }

    public int getPrice() {
        return price;
    }

    private enum Category {
        APPETIZER, MAIN, DESSERT, DRINK
    }
}
