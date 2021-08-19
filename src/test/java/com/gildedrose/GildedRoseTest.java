package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class GildedRoseTest {

    @Test
    void normalItemsWithPositiveSellInDecreaseQualityByOne() {
        Item item = new Item("any normal item", 1, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(99, subject.items[0].quality);
    }

    @Test
    void normalItemsWithZeroSellInDecreaseQualityByTwo() {
        Item item = new Item("any normal item", 0, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(98, subject.items[0].quality);
    }

    @Test
    void normalItemsWithNegativeSellInDecreaseQualityByTwo() {
        Item item = new Item("any normal item", 0, 100);
        GildedRose subject = new GildedRose(new Item[]{item});
        subject.updateQuality();
        assertEquals(98, subject.items[0].quality);
    }

}
